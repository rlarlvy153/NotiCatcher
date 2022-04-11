object Versions {
    const val COMPILE_SDK_VERSION = 31
    const val MIN_SDK_VERSION = 24
    const val TARGET_SDK_VERSION = 31
    const val APP_VERSION_CODE = 1
    const val APP_VERSION_NAME = "1.0"

    const val ANDROID_GRADLE_PLUGIN = "7.0.4"
    const val KOTLIN_VERSION = "1.6.10"
    const val ROOM_VERSION = "2.4.2"
    const val KOIN_VERSION = "3.1.5"
}

object Dependencies {

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:1.7.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.4.1"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:2.4.1"
        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    }

    object Room {
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM_VERSION}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM_VERSION}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM_VERSION}"
    }

    object Koin {
        const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN_VERSION}"
        const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN_VERSION}"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val TEST_EXT = "androidx.test.ext:junit:1.1.3"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }

    const val TIMBER = "com.jakewharton.timber:timber:5.0.1"

    const val GSON = "com.google.code.gson:gson:2.8.8"

    const val MATERIAL = "com.google.android.material:material:1.5.0"
}
