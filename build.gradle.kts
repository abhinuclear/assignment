// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.5.4")
    val kotlin_version by extra("1.9.0")
    val room_version by extra("2.6.1")
    val hilt_version by extra("2.48")
    val retrofit_version by extra("2.9.0")
    val paging_version by extra("3.2.1")



    repositories {
        google()
        mavenCentral()
    }



    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}


