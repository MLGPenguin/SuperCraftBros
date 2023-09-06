plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.9.0"
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.20")
}

group = "me.superpenguin"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

publishing.publications.create<MavenPublication>("maven") {
    from(components["java"])
}

tasks.withType<JavaCompile>() { options.encoding = "UTF-8" }
tasks.withType<Javadoc>() { options.encoding = "UTF-8" }
