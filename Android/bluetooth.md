# The basic flow of transmitting data between each other
1. Discoverable device makes itself enabled for incomming connect requests.  
2. Another devices finds discoverable device through a service discovery process.
3. Discovarable accepts the pairing request of hunting device.
4. Two devices complete bounding process, in which they exchange security keys.
5. Both devices cache these keys for later use.
6. After pairing and bounding, they exchange information.
7. After session is complete device that initiated the pairing request releases channel that linked to the discoverable device.
   - Two divices remaining bounded, so that they can automatically reconnect in the future, if they are in range and neighter of devices removed bounding


android introduced single bluetooth profile RFCOMM (wireless serial port profile)
Creates Low level socket connection between two devices

Profile in bluetooth is a contract - definition of set of services that a device has to expose

Required step to connecting is pairing, it’s a security step (encryption)

The biggest difference between Bluetooth classic and BLE

Bluetoorh LE uses primary profile, called generic attribute profile (gap) - which is organization of data in key- value store

Bluetooth uses sockets - an interface for wireless chat between two devices, streaming data back and forth 

To create a connection between two devices, you must implement both the server-side and client-side mechanisms because one device must open a server socket, and the other one must initiate the connection using the server device's MAC address.  

We have `BluetoothDevice` that represents remote device, it has:
- `name`: the name which will appear in the list
- unique MAC `address`

`BluetoothContoller` - heart of bluetooth logic:
- scan for device
- connect devices
- start/stop discovery scan
- release to free up a memory
- check that we have a permision `context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED`
- updatePairedDevice

## Persmissions
- Bluetooth
- Scan (flag: never for location so you don't need a location permission as by default)
- Connect

## Feature
- `hardwear.bluetooth`

We will have a list of scanned devices and paired devices

How to get bluetooth related **_functionality_**??

> now we need a `BluetoothAdapter` which is hardware module that **_contains all functionality_** (represents device's own bluetooth adapter (the Bluetooth radio))
> `BluetoothAdapter` is a basic class that provides basic Bluetooth operations on a device.

for that we need `SystemService` (system-level service by class) that is provided from an OS (android `BluetoothManager`)
> `BluetoothManager` **_provides access to the Bluetooth functionality on a device_**. It allows you to discover nearby Bluetooth devices, establish connections with them, and manage the data transfer between them.

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
