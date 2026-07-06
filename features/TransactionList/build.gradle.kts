plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.built.in1.kotlin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.ritesrport.transactionlist"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.foundation)
    implementation(libs.material)
    implementation(libs.ui.tooling.preview)
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    implementation(project(":core:DataBase"))
    implementation(project(":items:TransactionCard"))
}