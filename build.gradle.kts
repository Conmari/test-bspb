plugins {
    id("java-library")
    id("maven-publish")
}

repositories {
    mavenCentral()
    // mavenLocal()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.34.0")
    implementation("io.github.bonigarcia:webdrivermanager:6.1.0")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("org.slf4j:slf4j-simple:2.0.17")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.12.2")
}

group = "scari.corp"
version = "1.0-SNAPSHOT"
description = "test-bspb"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
