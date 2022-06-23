buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
    id("com.github.ben-manes.versions") version "0.42.0" apply true
    id("org.jetbrains.kotlin.jvm") version Versions.KOTLIN apply false
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}