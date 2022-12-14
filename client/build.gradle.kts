plugins {
    kotlin("js") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "ru.altmanea.edu-server-22"
version = "0.1"

repositories {
    mavenCentral()
    flatDir {
        dirs("$projectDir/../model/build/libs")
    }
}


val kotlinWrappersVersion = "0.0.1-pre.296-kotlin-1.6.10"
fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation("ru.altmanea.edu-ktor:model-js-0.1")
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:${kotlinWrappersVersion}"))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-router-dom"))
    implementation(kotlinw("redux"))
    implementation(kotlinw("react-redux"))
    implementation(kotlinw("react-query"))
    implementation(kotlinw("styled-next"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")
    implementation(npm("axios", "0.24.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.3")

}
kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

tasks.register<Copy>("copyBuild") {
    from("/build/distributions/client.js", "/build/distributions/client.js.map")
    into("../src/main/resources/")
}
tasks.register<Copy>("copyBuildToBuild") {
    from("/build/distributions/client.js", "/build/distributions/client.js.map")
    into("../build/resources/main/")
}
tasks.named("build") { finalizedBy("copyBuild") }
tasks.named("build") { finalizedBy("copyBuildToBuild") }