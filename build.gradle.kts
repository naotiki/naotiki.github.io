plugins {
    kotlin("js") version "1.7.21"
}

group = "me.unity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val kotlinWrappersVersion = "1.0.0-pre.462"
dependencies {
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-icons")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    testImplementation(kotlin("test-js"))
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
            }
            /*webpackTask {
                args.add("--history-api-fallback")
            }*/
            /*dceTask {
                dceOptions.devMode = true
            }*/
            testTask {
                useMocha()
            }
        }
    }
}
