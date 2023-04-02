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

# Create a connection between two devices
To create a connection between two devices, you must implement both the server-side and client-side mechanisms because:
- one device must open a server socket (to listen)
- and the other one must initiate the connection using the server device's MAC address.  

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
