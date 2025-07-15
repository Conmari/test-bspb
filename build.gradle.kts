plugins {
    id("java-library")
    id("maven-publish")
}

repositories {
    mavenCentral()
    // mavenLocal()
}


val seleniumVersion = "4.34.0"
val webdriverManagerVersion = "6.1.0"
val slf4jVersion = "2.0.17"
val junitJupiterVersion = "5.12.2"
val junitPlatformVersion = "1.12.2"
val cucumberVersion = "7.24.0"

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    implementation("io.github.bonigarcia:webdrivermanager:$webdriverManagerVersion")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")    
    testImplementation("org.junit.platform:junit-platform-suite-api:$junitPlatformVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")

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
    systemProperty("cucumber.filter.tags", System.getProperty("cucumber.filter.tags"))

}

