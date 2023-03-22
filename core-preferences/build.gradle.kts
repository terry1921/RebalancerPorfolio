import dev.rockstar.portfolio.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.datastore)

    // di
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}