// Top-level build file
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Use 4.4.1 as it is currently the most stable version
    id("com.google.gms.google-services") version "4.4.1" apply false
}
