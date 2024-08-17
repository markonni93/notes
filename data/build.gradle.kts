plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin)
  alias(libs.plugins.ksp)
  alias(libs.plugins.hilt.plugin)
}

android {
  namespace = "com.thequicknotes.data"
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

  ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.generateKotlin", "true")
  }
}

dependencies {

  implementation(libs.android.lifecycle.compose)

  implementation(libs.room.runtime)
  implementation(libs.room.paging)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)

  implementation(libs.hilt)
  ksp(libs.hilt.compiler)

  implementation(libs.android.paging)
}