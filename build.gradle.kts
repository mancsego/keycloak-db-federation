plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "de.dgpar.api"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")

	// Keycloak SPI
	implementation("org.keycloak:keycloak-core:26.2.0")
	implementation("org.keycloak:keycloak-server-spi:26.2.0")
	implementation("org.keycloak:keycloak-services:26.2.0")

	// JPA & Hibernate
	implementation("org.keycloak:keycloak-model-jpa:26.2.0")

	// Lombok (optional)
	compileOnly("org.projectlombok:lombok:1.18.38")
	annotationProcessor("org.projectlombok:lombok:1.18.38")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
