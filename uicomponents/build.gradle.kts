plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.thequicknotes.uicomponents"
  compileSdk = 35

  defaultConfig {
    minSdk = 26

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
  implementation(libs.android.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  implementation(libs.compose.ui)
  implementation(libs.compose.ui.tooling)
  implementation(libs.compose.ui.graphics)
  implementation(libs.compose.material3)
  implementation(libs.compose.fonts)
  implementation(platform(libs.compose.bom))
  testImplementation(libs.test.junit)
  androidTestImplementation(libs.test.junit.ext)
  androidTestImplementation(libs.espresso.core)
}