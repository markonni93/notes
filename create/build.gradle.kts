plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt.plugin)
}

android {
  namespace = "com.example.create"
  compileSdk = 34

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

  hilt {
    enableAggregatingTask = true
  }
}

dependencies {
  implementation(project(":data"))
  implementation(project(":uicomponents"))
  implementation(libs.hilt)
  ksp(libs.hilt.compiler)
  
  implementation(libs.android.lifecycle.compose)

  implementation(libs.compose.ui)
  implementation(libs.compose.ui.tooling)
  implementation(libs.compose.ui.graphics)
  implementation(libs.compose.material3)
  implementation(libs.compose.fonts)
  implementation(platform(libs.compose.bom))

  implementation(libs.android.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
  testImplementation(libs.test.junit)
  androidTestImplementation(libs.test.junit.ext)
  androidTestImplementation(libs.espresso.core)

}