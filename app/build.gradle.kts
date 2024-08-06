
plugins {
  alias(libs.plugins.application)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.ksp)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.hilt.plugin)
  alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
  namespace = "com.example.notes"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.notes"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  hilt {
    enableAggregatingTask = true
  }
}

dependencies {

  implementation(project(":app:data"))
  implementation(project(":app:components"))
  implementation(project(":create"))
  implementation(libs.android.ktx)
  implementation(libs.android.ktx.lifecycle)
  implementation(libs.android.lifecycle.compose)
  implementation(libs.android.activity)

  implementation(libs.compose.ui)
  implementation(libs.compose.ui.tooling)
  implementation(libs.compose.ui.graphics)
  implementation(libs.compose.material3)
  implementation(libs.compose.fonts)
  implementation(platform(libs.compose.bom))

  implementation(libs.compose.navigation)

  implementation(libs.hilt)
  ksp(libs.hilt.compiler)

  testImplementation(libs.test.junit)
  androidTestImplementation(libs.test.junit.ext)
  androidTestImplementation(libs.espresso.core)
  androidTestImplementation(platform(libs.compose.bom))
  androidTestImplementation(libs.compose.ui.test)
  debugImplementation(libs.compose.ui.tooling)
  debugImplementation(libs.compose.ui.manifest.test)
}