plugins {
    id("org.jetbrains.kotlin.android")
    id("com.android.application")
    kotlin("kapt")
}

android {
    compileSdk = Versions.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.kgp.noticatcher"

        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION

        versionCode = Versions.APP_VERSION_CODE
        versionName = Versions.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.MATERIAL)

    //livedata 사용
    implementation(Dependencies.AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_KTX)

    //test 관리필요하면 buildSrc로
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.TEST_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO_CORE)

    //room
    implementation(Dependencies.Room.ROOM_RUNTIME)
    implementation(Dependencies.Room.ROOM_KTX)
    kapt(Dependencies.Room.ROOM_COMPILER)

    //gson
    implementation(Dependencies.GSON)

    //koin
    implementation(Dependencies.Koin.KOIN_CORE)
    implementation(Dependencies.Koin.KOIN_ANDROID)

    //timber
    implementation(Dependencies.TIMBER)

}