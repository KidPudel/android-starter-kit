by setting a member with `internal` modifier it will be visible only in this module.  
Module - a group of files that are compiled together, like shared module, app module, ios module.
Module have it's own gradle dependencies, etc.


common:
```kotlin
expect data class BluetoothDevice(
  val id: String
  val name: String
)
```
android:
```kotlin
actual data class BluetoothDevice(
  val id: String
  val name: String
  internal val androidDevice: BluetoothDevice
)
```

ios:
```kotlin
actual data class BluetoothDevice(
  val id: String
  val name: String
  internal val peripheral: CBPeripheral
)
```
