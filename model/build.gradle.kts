plugins {
    kotlin("multiplatform") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

val kotlinVersion = "1.6.10"
val serializationVersion = "1.3.2"

group = "ru.altmanea.edu-server-22"
version = "0.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm {}
    js(LEGACY) {
        browser {}
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.1")
                implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.1")
            }
        }
    }
}