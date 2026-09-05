plugins {
    `java-library`
    alias(libs.plugins.lombok)
}

group = "net.flowstom"
version = "dev"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.minestom)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform)
}

testing {
    suites {
        named<JvmTestSuite>("test") {
            useJUnitJupiter()
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}
