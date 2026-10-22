package task11

sealed interface SmartDevice {
    val id: String
    fun report(): String
}

data class Light(override val id: String, val brightness: Int) : SmartDevice {
    override fun report() = "Light $id: brightness $brightness%"
}

data class Thermostat(override val id: String, val temperature: Double) : SmartDevice {
    override fun report() = "Thermostat $id: temperature ${temperature}C"
}

data class Camera(override val id: String, val recording: Boolean) : SmartDevice {
    override fun report() = "Camera $id: recording=$recording"
}

fun diagnose(device: SmartDevice): String = when (device) {
    is Light -> if (device.brightness == 0) "Light is off" else "Light is active"
    is Thermostat -> if (device.temperature > 30.0) "Overheating!" else "Temperature is normal"
    is Camera -> if (device.recording) "Recording in progress" else "Standby"
}

fun main() {
    val devices = listOf(
        Light("L-1", 80),
        Thermostat("T-1", 32.5),
        Camera("C-1", true),
        Light("L-2", 0)
    )
    for (device in devices) {
        println("${device.report()} -> ${diagnose(device)}")
    }
}