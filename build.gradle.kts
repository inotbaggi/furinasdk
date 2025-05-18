plugins {
    kotlin("jvm") version "2.1.20"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
    id("com.gradleup.shadow") version "9.0.0-beta8"
}

group = "me.baggi"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://jitpack.io")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.fancyplugins.de/releases")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20")

    // Minecraft libraries
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("fr.mrmicky:fastboard:2.1.3")
    implementation("fr.skytasul:glowingentities:1.4.3")
    implementation("dev.triumphteam:triumph-gui:3.1.11")

    // Other
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("org.java-websocket:Java-WebSocket:1.6.0")
    implementation("com.google.guava:guava:33.4.8-jre")
    implementation("com.google.code.gson:gson:2.13.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.shadowJar {
    archiveFileName = "${project.name}-$version.jar"
}

bukkit {
    main = "me.baggi.furinasdk.Plugin"
    version = project.version.toString()
    name = "FurinaSDK"
    description = "Library for Baggi plugins"
    author = "iNotBaggi"
    website = "https://inotbaggi.t.me"
    apiVersion = "1.21"
}