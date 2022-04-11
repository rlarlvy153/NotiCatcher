buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.1.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
