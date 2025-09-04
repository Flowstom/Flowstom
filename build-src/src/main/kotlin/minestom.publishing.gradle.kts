plugins {
    `maven-publish`
    signing
    alias(libs.plugins.nmcp)
}

publishing.publications.create<MavenPublication>("maven") {
    groupId = project.group.toString()
    artifactId = project.name // eg "flowstom" or "testing"
    version = project.version.toString()

    from(project.components["java"])

    pom {
        name.set(this@create.artifactId)
        description.set("A fork of Minestom aimed at simplifying the process of adding custom server-side content.")
        url.set("https://github.com/flowstom/flowstom")

        licenses {
            license {
                name.set("Apache 2.0")
                url.set("https://github.com/flowstom/flowstom/blob/main/LICENSE")
            }
        }

        developers {
            developer {
                id.set("McMelonTV")
            }
        }

        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/flowstom/flowstom/issues")
        }

        scm {
            connection.set("scm:git:git://github.com/flowstom/flowstom.git")
            developerConnection.set("scm:git:git@github.com:flowstom/flowstom.git")
            url.set("https://github.com/flowstom/flowstom")
            tag.set("HEAD")
        }

        ciManagement {
            system.set("Github Actions")
            url.set("https://github.com/flowstom/flowstom/actions")
        }
    }
}

signing {
    isRequired = System.getenv("CI") != null

    val privateKey = System.getenv("GPG_PRIVATE_KEY")
    val keyPassphrase = System.getenv()["GPG_PASSPHRASE"]
    useInMemoryPgpKeys(privateKey, keyPassphrase)

    sign(publishing.publications)
}
