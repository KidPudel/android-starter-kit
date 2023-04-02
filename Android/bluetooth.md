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
   _(if getAdapter() == null, does not supports Bluetooth)_

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

We will have a list of scanned devices and paired devices

How to get bluetooth related **_functionality_**??

```kotlin
private val BluetoothManager by lazy {
    application.getSystemService(BluetoothManager::class.java)
}
```

And now we can get a `BluetoothAdapter`

```kotlin
private val bluetoothAdapter by lazy {
    bluetoothManager?.adapter
}
```

# Callback with device information
To get that we need to use BroadcastReceiver android component


# Defenitions
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
