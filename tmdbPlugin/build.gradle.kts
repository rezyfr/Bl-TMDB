plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly(gradleApi())


}

tasks {
    compileKotlin { kotlinOptions.jvmTarget = "1.8" }
}

gradlePlugin {
    (plugins) {
        register("tmdbPlugin") {

            id = "io.rezyfr.tmdb.gradle.tmdb-plugin"

            implementationClass = "io.rezyfr.tmdb.plugin.TmdbPlugin"
        }

    }
}

