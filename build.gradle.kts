plugins {
	kotlin("jvm") version "2.0.10"
	id("com.gradleup.shadow") version "8.3.0"
}

group = "com.thenolle.kotlin"
version = "1.0"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
	implementation("net.minestom:minestom-snapshots:11ed85a921")
	implementation("org.slf4j:slf4j-simple:2.0.16")
}

tasks {
	jar {
		manifest {
			attributes["Main-Class"] = "com.thenolle.kotlin.MainKt"
		}
	}

	build {
		dependsOn(shadowJar)
	}
	shadowJar {
		mergeServiceFiles()
		archiveClassifier.set("") // Prevent the -all suffix on the shadowjar file.
	}

	test {
		useJUnitPlatform()
	}
}

kotlin {
	jvmToolchain(21)
}