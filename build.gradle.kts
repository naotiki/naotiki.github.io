plugins {
    kotlin("js") version "1.7.20"
}

group = "me.unity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val kotlinWrappersVersion = "1.0.0-pre.361"
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
    testImplementation(kotlin("test-js"))
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
               cssSupport{
                   enabled=true
               }

            }
            dceTask {
                dceOptions.devMode = true
            }
            testTask {
                useMocha()
            }
        }
    }
}
