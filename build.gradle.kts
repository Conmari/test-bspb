plugins {
    id("java-library")
    id("maven-publish")
    id("io.qameta.allure") version "2.12.0"
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
val lang3Version = "3.18.0"
val assertjVersion = "3.27.3"
val allureJunit5Version = "2.28.1"
val allureCucumber7jvmVersion = "2.23.0"
val restAssuredVersion = "5.5.5"
val hamcrestVersion = "2.0.0.0"
val jacksonVersion = "2.19.2"

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    implementation("io.github.bonigarcia:webdrivermanager:$webdriverManagerVersion")
    implementation("org.apache.commons:commons-lang3:$lang3Version")  /* webdriverManagerVersion подтягивал версию commons-lang3:3.17.0
                                                                            внутри какая то уязвимость которая может превести к StackOverflowError */
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:$junitPlatformVersion")
    testImplementation("org.junit.platform:junit-platform-suite-api:$junitPlatformVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")

    testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")

    testImplementation("io.qameta.allure:allure-junit5:$allureJunit5Version")
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:$allureCucumber7jvmVersion")

    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("org.hamcrest:java-hamcrest:$hamcrestVersion")

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
    systemProperty("cucumber.plugin", "pretty,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
    systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results").get().asFile.absolutePath)
}

