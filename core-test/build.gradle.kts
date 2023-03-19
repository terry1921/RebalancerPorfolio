import com.mx.rockstar.portfolio.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
}

android {
    compileSdk = com.mx.rockstar.portfolio.Configuration.compileSdk

    defaultConfig {
        minSdk = com.mx.rockstar.portfolio.Configuration.minSdk
        targetSdk = com.mx.rockstar.portfolio.Configuration.targetSdk
    }
}

dependencies {
    // modules

    // unit test
    implementation(libs.coroutines)
    implementation(libs.coroutines.test)
    implementation(libs.junit)
}