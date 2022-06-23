import java.io.File
import java.io.FileInputStream
import java.util.*

object Versions {

    fun getProperty(file: File, key: String): String = Properties().apply { load(FileInputStream(file)) }.getProperty(key)

    const val COMPILE_SDK = 32
    const val TARGET_SDK = 32
    const val MIN_SDK = 21

    const val KOTLIN = "1.6.21"

    const val ACTIVITY_COMPOSE = "1.4.0"
    const val COIL = "2.1.0"
    const val COMPOSE = "1.2.0-rc02"
    const val CONSTRAINT_LAYOUT_COMPOSE = "1.0.1"
    const val CORE_KTX = "1.7.0"
    const val HILT = "2.42"
    const val LIFECYCLE = "2.4.1"
    const val OKHTTP = "4.9.3"
    const val RETROFIT = "2.9.0"
    const val TIMBER = "5.0.1"
}