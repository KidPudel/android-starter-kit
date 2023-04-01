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
