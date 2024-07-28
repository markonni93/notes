// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.application) apply false
  alias(libs.plugins.kotlin) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.compose.compiler) apply false
  alias(libs.plugins.hilt.plugin) apply false
  alias(libs.plugins.navigation.safeargs.kotlin) apply false
  alias(libs.plugins.android.library) apply false
}