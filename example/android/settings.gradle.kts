pluginManagement {
    val flutterSdkPath = run {
        val properties = java.util.Properties()
        file("local.properties").inputStream().use { properties.load(it) }
        val flutterSdkPath = properties.getProperty("flutter.sdk")
        require(flutterSdkPath != null) { "flutter.sdk not set in local.properties" }
        flutterSdkPath
    }

    includeBuild("$flutterSdkPath/packages/flutter_tools/gradle")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("dev.flutter.flutter-plugin-loader") version "1.0.0"
    id("com.android.application") version "9.2.1" apply false

    // AGP 9.2.1 bundles Kotlin 2.2.10, but Flutter's DependencyVersionChecker warns
    // below KGP 2.2.20. Pin it explicitly here to silence that warning until an AGP
    // whose bundled Kotlin is >= 2.2.20 becomes the stable baseline.
    id("org.jetbrains.kotlin.android") version "2.2.20" apply false
}

include(":app")
