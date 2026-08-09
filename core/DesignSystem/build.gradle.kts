plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.built.in1.kotlin)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ritesrport.core.DesignSystem"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.androidx.compose.material3)

    implementation(project(":core:Model"))
    debugImplementation(libs.androidx.compose.ui.tooling)
}