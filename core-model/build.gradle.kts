import com.mx.rockstar.portfolio.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    compileSdk = com.mx.rockstar.portfolio.Configuration.compileSdk

    defaultConfig {
        minSdk = com.mx.rockstar.portfolio.Configuration.minSdk
        targetSdk = com.mx.rockstar.portfolio.Configuration.targetSdk
    }
}

dependencies {
    // json parsing
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // logger
    api(libs.timber)
}