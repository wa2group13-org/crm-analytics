import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import java.util.*

plugins {
	id("idea")
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("kapt") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	kotlin("plugin.jpa") version "1.9.23"
	kotlin("plugin.serialization") version "1.9.23"
	id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
	id("org.openapi.generator") version "7.5.0"
	id("org.asciidoctor.jvm.convert") version "4.0.2"
}
val springCloudVersion by extra("2023.0.2")

group = "it.polito.wa2.g13"
version = "1.1.2"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

idea {
	module {
		isDownloadJavadoc = true
		isDownloadSources = true
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.kafka:spring-kafka")

	implementation("org.springframework.boot:spring-boot-starter-aop")

	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	// Needed by springdoc-openapi
	implementation("org.springdoc:springdoc-openapi-starter-webflux-api:2.6.0")
	implementation("com.github.therapi:therapi-runtime-javadoc:0.15.0")
	kapt("com.github.therapi:therapi-runtime-javadoc-scribe:0.15.0")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:mongodb")
	testImplementation("org.testcontainers:kafka")

	runtimeOnly("org.springframework.boot:spring-boot-docker-compose")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<BootBuildImage>("bootBuildImage") {
	val tagName = { tag: String -> "${System.getenv("DOCKER_USERNAME")}/${project.name}:${tag}" }

	imageName = tagName("${project.version}")
	tags = listOf(tagName("${project.version}"), tagName("latest"))

	docker {
		publishRegistry {
			username = System.getenv("DOCKER_USERNAME")
			password = System.getenv("DOCKER_PASSWORD")
		}
	}
}
dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
	}
}

abstract class ProjectVersion : DefaultTask() {
	@TaskAction
	fun action() {
		print(project.version)
	}
}

tasks.register<ProjectVersion>("projectVersion")

// Store the version of the current project in the project.properties file
// in the build directory
tasks.withType<ProcessResources> {
	doLast {
		val propertiesFile = file("${layout.buildDirectory.get()}/resources/main/project.properties")
		propertiesFile.parentFile.mkdirs()
		val properties = Properties().apply {
			this.setProperty("project.version", project.version.toString())
		}

		propertiesFile.writer().use { properties.store(it, null) }
	}
}

tasks.generateOpenApiDocs {
	waitTimeInSeconds = 60 * 3
}

// Make the output generation much easier by using an ENV for specifying the
// generatorName
tasks.openApiGenerate {
	dependsOn(tasks.generateOpenApiDocs)
	generatorName = System.getenv("GENERATOR_NAME")
	inputSpec = "${layout.buildDirectory.get()}/openapi.json"
	outputDir = "${layout.buildDirectory.get()}/openapi-gen/${System.getenv("GENERATOR_NAME")}"
}

tasks.asciidoctor {
	setSourceDir(file("${layout.buildDirectory.get()}/openapi-gen/asciidoc"))
	setOutputDir(file("${layout.buildDirectory.get()}/docs"))
}