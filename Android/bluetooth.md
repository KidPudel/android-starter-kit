# The basic flow of transmitting data between each other
1. Discoverable device makes itself enabled for incomming connect requests.  
2. Another devices finds discoverable device through a service discovery process.
3. Discovarable accepts the pairing request of hunting device.
4. Two devices complete bounding process, in which they exchange security keys.
5. Both devices cache these keys for later use.
6. After pairing and bounding, they exchange information.
7. After session is complete device that initiated the pairing request releases channel that linked to the discoverable device.
   - Two divices remaining bounded, so that they can automatically reconnect in the future, if they are in range and neighter of devices removed bounding

# To use Bluetooth APIs..
1. Add permissions in manifest
   - `BLUETOOTH`
   - `Scan` (flag: `never for location` thus location permission is not required)
   - `Connect`
2. Access `BluetoothAdapter`
3. Determine if bluetooth is available, and if so:
   - Find nearby Bluetooth devices (paired and new)
   - Connect to a device
   - Tranfer data with a connected device

# Set up Bluetooth
Once all permissions is set bluetooth setup is accomplished in two steps using `BluetoothAdapter`:
1. Get the `BluetoothAdapter` (it is required in all Bluetooth activity)  
   You can get a `BluetoothAdapter` via context's `BluetoothManager` system service.  
   _(`if getAdapter() == null`, does not supports Bluetooth)_

   ```kotlin
   val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
   val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
   if (bluetoothAdapter == null) {
      // Device doesn't support Bluetooth
   }
   ```
2. Enable Bluetooth
   Check if bluetooth is enabled by `isEnabled()`, if it's false, then request to enable it with startActivityForResult
   ```kotlin
   if (bluetoothAdapter?.isEnabled == false) {
      val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
      startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
      // REQUEST_ENABLE_BT locally-defined integer that must be greater than or equal to 0.
      // The system passes this constant back to you in your onActivityResult() implementation as the requestCode parameter.
   }
   ```
   <img width=540px alt="socket is id address + port" src="https://developer.android.com/static/images/guide/topics/connectivity/bluetooth/enable-bluetooth.png"/>


# Find Bluetooth devices
Using the BluetoothAdapter, you can find remote Bluetooth devices either through device discovery or by querying the list of paired devices.
> Device discovery is a scanning procedure that searches the local area for Bluetooth-enabled devices and requests some information about each one.  
A nearby Bluetooth device responds to a discovery request only if it is currently accepting information requests by being discoverable.  
If a device is discoverable, it responds to the discovery request by sharing some information, such as the device's name, its class, and its unique MAC address.  
Using this information, the device that is performing the discovery process can then choose to initiate a connection to the discovered device.

Because discoverable devices might reveal information about the user's location, the device discovery process requires location access. (flag: `never for location` thus location permission is not required)  

#### After connection
> Once a connection is made with a remote device for the first time, a pairing request is automatically presented to the user. When a device is paired, the basic information about that device—such as the device's name, class, and MAC address—is saved and can be read using the Bluetooth APIs. Using the known MAC address for a remote device, a connection can be initiated with it at any time without performing discovery, assuming the device is still within range.

## Difference between being paired and being connected:

- To be _**paired**_ means that two devices are aware of each other's existence, have a shared link-key that can be used for authentication, and are capable of establishing an encrypted connection with each other.
- To be _**connected**_ means that the devices currently share an RFCOMM channel and are able to transmit data with each other. The current Bluetooth APIs require devices to be paired before an RFCOMM connection can be established. Pairing is automatically performed when you initiate an encrypted connection with the Bluetooth APIs.


# Query paired devices
Before performing device discovery it worth to search for already paired devices, to see if desired device if already paired.  
You can do that by get bonded (paired) devices `bondedDevices`
```kotlin
val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
pairedDevices?.forEach { device ->
   val deviceName = device.name
   val deviceHardwareAddress = device.address // MAC address
}
```
_**To initiate a connection with a Bluetooth device, all that's needed from the associated BluetoothDevice object is the MAC address**_

---

# Discover devices
To start discovering devices, call `startDiscovery()`. _The process is asynchronous and returns a boolean value indicating whether discovery has successfully started_. The discovery process usually involves an inquiry scan of about 12 seconds, followed by a page scan of each device found to retrieve its Bluetooth name.  

**_To receive information about each device discovered_, your app must register a `BroadcastReceiver` for the `ACTION_FOUND` intent. _The system broadcasts this intent for each device_. The intent contains the extra fields `EXTRA_DEVICE` and `EXTRA_CLASS`, which in turn contain a `BluetoothDevice` and a `BluetoothClass`, respectively. The following code snippet shows how you can register to handle the broadcast when devices are discovered:**

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
   ...

   // Register for broadcasts when a device is discovered.
   val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
   registerReceiver(receiver, filter)
}

// Create a BroadcastReceiver for ACTION_FOUND.
private val receiver = object : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {
       val action: String = intent.action
       when(action) {
           BluetoothDevice.ACTION_FOUND -> {
               // Discovery has found a device. Get the BluetoothDevice
               // object and its info from the Intent.
               val device: BluetoothDevice =
                       intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
               val deviceName = device.name
               val deviceHardwareAddress = device.address // MAC address
           }
       }
   }
}

override fun onDestroy() {
   super.onDestroy()
   ...

   // Don't forget to unregister the ACTION_FOUND receiver.
   unregisterReceiver(receiver)
}
```
_To initiate a connection with a Bluetooth device, you call `getAddress()` on the `BluetoothDevice` to retrieve the associated MAC address._

---

# Enable discoverability
To make the local device discoverable to other devices, call `startActivityForResult(Intent, int)` with the `ACTION_REQUEST_DISCOVERABLE` intent. (This issues a request to enable the system's discoverable mode without having to navigate to the Settings app) _By default, the device becomes discoverable for two minutes. You can define a different duration, up to one hour, by adding the `EXTRA_DISCOVERABLE_DURATION` extra._
```kotlin
val requestCode = 1;
val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
   putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
}
startActivityForResult(discoverableIntent, requestCode)
```
![enable-discoverability](https://user-images.githubusercontent.com/63263301/229359686-df932dc2-0ad3-4d65-9ebe-7be82d6e1094.png)

If the user responds "Allow," then the device becomes _discoverable for the specified amount of time_. Then activity receives a call to the `onActivityResult()` callback, with the result code equal to the duration that the device is discoverable. If the user responded "Deny", or if an error occurred, the result code is `RESULT_CANCELED`.

# Connect Bluetooth devices
To create a connection between two devices, you must implement both the server-side and client-side mechanisms because:
- one device must open a server socket (to listen)
- and the other one must initiate the connection using the server device's MAC address.  

> _**Hunting device is client and listening device is a server**_

The server device and the client device each obtain the required `BluetoothSocket` in different ways.
- The server receives socket information when an _**incoming connection is accepted**_. 
- The client provides socket information _**when it opens an RFCOMM channel to the server**_.

> _**The server and client are considered connected to each other when they each have a connected BluetoothSocket on the same RFCOMM channel.**_

At this point, each device can obtain input and output streams, and data transfer can begin

## Connection techniques
One implementation technique is to automatically prepare each device as a server so that each device has a server socket open and listening for connections. In this case, either device can initiate a connection with the other and become the client. Alternatively, one device can explicitly host the connection and open a server socket on demand, and the other device initiates the connection.  
![bluetooth-pairing](https://user-images.githubusercontent.com/63263301/229361763-2aded5a4-0f1c-47ef-a54b-1bbefc280b3b.png)

> **Note:** _If the two devices have not been previously paired, then the Android framework automatically shows a pairing request notification or dialog to the user during the connection procedure, as shown. Therefore, when your app attempts to connect devices, it doesn't need to be concerned about whether or not the devices are paired_. Your RFCOMM connection attempt gets blocked until the user has successfully paired the two devices, and the attempt fails if the user rejects pairing, or if the pairing process fails or times out.



# Connect as a server
**_When you want to connect two devices, one must act as a server by holding an open `BluetoothServerSocket`. The purpose of the server socket is to listen for incoming connection requests and provide a connected BluetoothSocket after a request is accepted._**  
_When the `BluetoothSocket` is acquired from the `BluetoothServerSocket`, the `BluetoothServerSocket` **can—and should—be discarded**, unless you want the device to accept more connections._



To set up a server socket and accept a connection, complete the following sequence of steps:

1. Get a `BluetoothServerSocket` by calling `listenUsingRfcommWithServiceRecord(String, UUID)`.
   The **`string` is an identifiable name of your service**, which the system automatically writes to a new Service Discovery Protocol (SDP) database entry on the device.  
   The name is arbitrary and can simply be your app name. The `Universally Unique Identifier (UUID)` is also included in the SDP entry and forms the basis for the connection agreement with the client device.  
   _**When the client attempts to connect with this device, it carries a UUID that uniquely identifies the service with which it wants to connect** (These UUIDs must match in order for the connection to be accepted.)_  
   
   ### Definition of UUID
   > In this case, UUID used to uniquely identify your app's Bluetooth service.  
   A UUID is a standardized 128-bit format for a string ID used to uniquely identify information.  
   UUID is used to identify information that needs to be unique within a system or a network because the probability of a UUID being repeated is effectively zero.  
   
   ### Generation of UUID
   It is generated independently, without the use of a centralized authority. To get a UUID to use with your app, you can use one of the many random UUID generators on the web, then initialize a UUID with fromString(String).

2. Start listening for connection requests by calling `accept()`.
   > This is a blocking call. It returns when either a connection has been accepted or an exception has occurred.  
   A connection is _**accepted only when a remote device has sent a connection request containing a `UUID` that matches the one registered with this listening server socket**_.  
   _When successful, `accept()` returns a connected `BluetoothSocket`_.

3. Unless you want to accept additional connections, call `close()`.
   > This method call releases the server socket and all its resources, but doesn't close the connected BluetoothSocket that's been returned by `accept()`. 
   Unlike TCP/IP, RFCOMM allows only one connected client per channel at a time, so in most cases it makes sense to call close() on the BluetoothServerSocket immediately after accepting a connected socket.

Because the `accept()` call is a _**blocking call, do not execute it in the main activity UI thread**_.  
Executing it in another thread ensures that your app can still respond to other user interactions.  

**It usually makes sense to do all work that involves a `BluetoothServerSocket` or `BluetoothSocket` in a new thread managed by your app. To abort a blocked call such as `accept()`, call `close()` on the `BluetoothServerSocket` or `BluetoothSocket` !!_**from another thread**_!!. Note that all methods on a `BluetoothServerSocket` or BluetoothSocket are thread-safe.**

```kotlin
private inner class AcceptThread : Thread() {

   private val mmServerSocket: BluetoothServerSocket? by lazy(LazyThreadSafetyMode.NONE) {
       bluetoothAdapter?.listenUsingInsecureRfcommWithServiceRecord(NAME, MY_UUID)
   }

   override fun run() {
       // Keep listening until exception occurs or a socket is returned.
       var shouldLoop = true
       while (shouldLoop) {
           val socket: BluetoothSocket? = try {
               mmServerSocket?.accept()
           } catch (e: IOException) {
               Log.e(TAG, "Socket's accept() method failed", e)
               shouldLoop = false
               null
           }
           socket?.also {
               manageMyConnectedSocket(it)
               mmServerSocket?.close()
               shouldLoop = false
           }
       }
   }

   // Closes the connect socket and causes the thread to finish.
   fun cancel() {
       try {
           mmServerSocket?.close()
       } catch (e: IOException) {
           Log.e(TAG, "Could not close the connect socket", e)
       }
   }
}
```

In this example, only one incoming connection is desired, so as soon as a connection is accepted and the `BluetoothSocket` is acquired, _**the app passes the acquired `BluetoothSocket` to a separate thread**_, closes the BluetoothServerSocket, and breaks out of the loop.

> Note that when `accept()` returns the `BluetoothSocket`, the socket is already connected. Therefore, _**you shouldn't call `connect()`, as you do from the client side**_.

The app-specific `manageMyConnectedSocket()` method is _**designed to initiate the thread for transferring data**_, which is discussed in the topic about [transferring Bluetooth data]().

Usually, you should close your `BluetoothServerSocket` as soon as you are done listening for incoming connections. In this example, `close()` is called as soon as the `BluetoothSocket` is acquired. You may also want to provide a public method in your thread that can close the private `BluetoothSocket` in the event that you need to stop listening on that server socket.

# Connect as a client
In order to initiate a connection with a remote device that is accepting connections on an open server socket, you must first obtain a `BluetoothDevice` object that represents the remote device.  
_**You must then use the `BluetoothDevice` to acquire a `BluetoothSocket` and initiate the connection.**_


The basic procedure is as follows:
1. Using the `BluetoothDevice`, get a `BluetoothSocket` by calling `createRfcommSocketToServiceRecord(UUID)`.
   This method initializes a `BluetoothSocket` object that allows the client to connect to a `BluetoothDevice`.  
   The UUID passed here must match the UUID used by the server device when it called `listenUsingRfcommWithServiceRecord(String, UUID)` to open its `BluetoothServerSocket`.  
   To use a matching UUID, hard-code the UUID string into your app, and then reference it from both the server and client code.
2. Initiate the connection by calling `connect()`. Note that this method is a blocking call.
   After a client calls this method, the system performs an SDP lookup to find the remote device with the matching UUID.  
   If the lookup is successful and the remote device accepts the connection, it shares the RFCOMM channel to use during the connection, and the `connect()` method returns.  
   If the connection fails, or if the `connect()` method times out (after about 12 seconds), then the method throws an IOException.

Because `connect()` is a blocking call, you should use thread that is separate from the main activity (UI) thread.

```kotlin
private inner class ConnectThread(device: BluetoothDevice) : Thread() {

   private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
       device.createRfcommSocketToServiceRecord(MY_UUID)
   }

   public override fun run() {
       // Cancel discovery because it otherwise slows down the connection.
       bluetoothAdapter?.cancelDiscovery()

       mmSocket?.let { socket ->
           // Connect to the remote device through the socket. This call blocks
           // until it succeeds or throws an exception.
           socket.connect()

           // The connection attempt succeeded. Perform work associated with
           // the connection in a separate thread.
           manageMyConnectedSocket(socket)
       }
   }

   // Closes the client socket and causes the thread to finish.
   fun cancel() {
       try {
           mmSocket?.close()
       } catch (e: IOException) {
           Log.e(TAG, "Could not close the client socket", e)
       }
   }
}
```

otice that in this snippet, `cancelDiscovery()` is called before the connection attempt occurs.  
_**You should always call `cancelDiscovery()` before `connect()`, especially because `cancelDiscovery()` succeeds regardless of whether device discovery is currently in progress**_.  
If your app needs to determine whether device discovery is in progress, you can check using `isDiscovering()`.

# Hints


We have `BluetoothDeviceDomain` that represents remote device, it has:
- `name`: the name which will appear in the list
- unique MAC `address`

`BluetoothContoller` - heart of bluetooth logic:
- scan for device
- connect devices
- start/stop discovery scan
- release to free up a memory
- check that we have a permision `context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED`
- updatePairedDevice


## Feature
- `hardwear.bluetooth`

# Callback with device information
To get that we need to use BroadcastReceiver android component


# Defenitions
## RFCOMM
android introduced single bluetooth profile RFCOMM (wireless serial port profile)
Creates Low level socket connection between two devices

## `BluetoothAdapter`
> (represents device's own bluetooth adapter (the Bluetooth radio)) is hardware module that **_contains all functionality_**, this is an **_entry-point for all bluetooth interaction_**.  

#### Usage
1. Using this you can discover other bluetooth devices  
2. Query a list of bounded (paired) devices  
3. Instantiate a `BluetoothDevice` using known MAC address  
4. Create a `BluetoothServerSocket` to listen for communication of other devices  

## `BluetoothManager`
> High-level manager to obtain `BluetoothAdapter` and conduct overall Bluetooth management
To get `BluetoothManager`:
```kotlin
context.getSystemService(BluetoothManager::class.java)
```

## `BluetoothDevice`
> Representation of remote Bluetooth device

#### Usage
1. Use this to request a connection with a remote device through a `BluetoothSocket`.  
2. Query information about the device, like a name, address, class and bounding state.  

## Socket
> Endpoint for communication between two machines.

<img width=540px alt="socket is id address + port" src="https://user-images.githubusercontent.com/63263301/229348248-9e9cc63b-7dd5-4523-85ae-9c3aaf7a5594.png"/>

## Server socket
> Special type of socket that listens for a request come in over a network, it then performs some information based on the request, and then possibly returns a result to a requester

## `BluetoothSocket`
>  Represents an interface of a Bluetooth socket (similar to TCP socket) this is a connection point on an app  
an interface for wireless chat between two devices, streaming data back and forth 

#### Usage
To exchange data with another device with `InputStream` and `OutputStream`

## `BluetoothServerSocket`
> Represents an open server socket that listens for incoming requests (similar to TCP server socket).

#### Usage
In order to connect to devices, one must open server socket with this class. When a remote Bluetooth device makes a connection request, another device will listen to that request accept it and respond with `BluetoothSocket`

## `BluetoothClass`
> Describes the general characteristics and capabilities of a Bluetooth device.

## `BluetoothProfile`
> An interface that represents a Bluetooth profile. A Bluetooth profile is a wireless interface specification for Bluetooth-based communication between devices.
