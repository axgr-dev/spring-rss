import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.2.0-M3"
  id("io.spring.dependency-management") version "1.1.3"
  kotlin("jvm") version "1.9.10"
  kotlin("plugin.spring") version "1.9.10"
}

group = "dev.axgr"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
  maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("com.rometools:rome:2.1.0")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}