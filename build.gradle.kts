import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinJsCompilation
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinJsCompilationFactory
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackOutput
import org.jetbrains.kotlin.gradle.targets.js.webpack.WebpackDevtool
import org.jetbrains.kotlin.ir.util.kotlinPackageFqn
import java.nio.file.Files
import java.nio.file.StandardCopyOption

plugins {
    kotlin("js") version "1.8.21"
}

group = "me.naotiki"
version = "1.0-SNAPSHOT"
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.1")
    }
}
repositories {
    mavenCentral()
}
val kotlinWrappersVersion = "1.0.0-pre.564"
fun kotlinw(target: String) = "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    //wrappers
    implementation(enforcedPlatform(kotlinw("wrappers-bom:$kotlinWrappersVersion")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("emotion"))
    implementation(kotlinw("react-router-dom"))
    implementation(kotlinw("redux"))
    implementation(kotlinw("react-redux"))
    implementation(kotlinw("mui"))
    implementation(kotlinw("mui-icons"))
    //npm
    implementation(npm("usehooks-ts", "2.8.0"))
    //others...
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")
    testImplementation(kotlin("test-js"))
}
kotlin {

    js(IR) {
        binaries.executable()
        browser {
            webpackTask {
                println(mode)
                if(mode==KotlinWebpackConfig.Mode.PRODUCTION){
                    sourceMaps=false
                }
            }
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }


            /*dceTask {
                dceOptions.devMode = true
            }*/
            nodejs {
                testTask {
                    useMocha()
                }
            }
        }
    }
    rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
        rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().run {
            download = true // default
            nodeVersion = "16.15.0" // usehooks-ts require nodeVersion >=16.15.0
        }
    }


// これはJavaScriptではなくただの文字列をファイルに書き込んでいるだけです。なのでKotlin100%
    tasks.named("kotlinNodeJsSetup") {
        doFirst {
            val dir = rootDir.resolve("webpack.config.d")
            dir.mkdir()
            val writer = dir.resolve("webpack.config.js").writer()
            writer.appendLine("if (config.devServer){config.devServer=Object.assign(config.devServer,{historyApiFallback:true});}")
            writer.appendLine("config.output.publicPath=\"/\"")
            writer.flush()
        }
        dependsOn("generateHtml")
    }
// GitHub Pages 用に404をコピー
    /*tasks.build{
        doLast {
            val dist = buildDir.resolve("distributions").toPath()
            Files.copy(dist.resolve("index.html"), dist.resolve("404.html"),StandardCopyOption.REPLACE_EXISTING)
        }
    }*/

    task("generateHtml") {
        fun FlowOrPhrasingOrMetaDataContent.metaProp(prop: String, content: String) = meta {
            attributes["property"] = prop
            this.content = content
        }
        File("src/main/resources/index.html").bufferedWriter().run {
            appendLine("<!DOCTYPE html>")
            appendHTML().html {
                lang = "ja"
                head {
                    attributes["prefix"] = "og: https://ogp.me/ns#"
                    meta(charset = "UTF-8")
                    meta(
                        "viewport",
                        content = "initial-scale=1, width=device-width"
                    )
                    link("https://naotiki.me", "canonical")
                    link("/favicon.ico", "icon")
                    link("/naotiki.svg", "icon", "image/svg+xml")
                    metaProp("og:site_name", "なおちきのポートフォリオ")
                    title("なおちきです")
                    metaProp("og:title", "なおちきです")
                    meta("author", "なおちき")
                    meta("description", "Kotlin/JSで書かれたなおちきのポートフォリオです。")
                    metaProp("og:description", "Kotlin/JSで書かれたなおちきのポートフォリオです。")
                    metaProp("og:image", "https://r2.naotiki.me/NAOTIKI.png")
                    metaProp("og:url", "https://naotiki.me")
                    metaProp("og:type", "website")
                    metaProp("twitter:card", "summary_large_image")
                    metaProp("twitter:site", "@Naotiki13")
                }
                body {
                    script(src = "/naotiki-website.js") {}
                }
            }
            appendLine(
                """
<!--
Kotlinで書き切るぜ！！！
            \/
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM90C<<<<+11<<<<<<<<<?1TMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM90I!`                         `?XHMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM9VC<```                                 _?4MMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMM80C<!~```                                        `~?WMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMN6<~`                                                  _?UMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMN> `                                                     _?WMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMM9C!                                                         ?VHMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMH>`                                                           _ZMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMM9I_                                                             (ZHMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMM#>_                                                               (dMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMS!                                                                 dMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMM9C_                                          ` ` `                  (WMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMH>_               `                        `  ....... `              _?XMMMMMMMMMMMMMMM
MMMMMMMMMMMMMR!               ..(++--.                    .jXMMNHA+..`            ` zMMMMMMMMMMMMMMM
MMMMMMMMMMMMHC_              .dHMMMMNk+.               ` (dWMMMMMMMHo.              (ZHMMMMMMMMMMMMM
MMMMMMMMMMMNI<              (dHMMMMMMMR<`                JMMMMMMMMMMHk_              (dMMMMMMMMMMMMM
MMMMMMMMMMMHI<            `.dMMMMMMMMMH>``               zMMMMMMMMMMM#>               zMMMMMMMMMMMMM
MMMMMMMMMMMHI~            ` jHMMMMMMMMK:`                _?WMMMMMMMMMK> `             jMMMMMMMMMMMMM
MMMMMMMMMMMHI~               ~?OUHHH9Y>`                   ~?4HMMMH9C!`               jMNMMMMMMMMMMM
MMMMMMMMMMMH>_                  _!!!~`                       _???<!~``               `(XMMMMMMMMMMMM
MMMMMMMMMMMK:                                                                         (wWMMMMMMMMMMM
MMMMMMMMMMMR~                                                                         -?XMMMMMMMMMMM
MMMMMMMMMMMR~                                                                          _zMMMMMMMMMMM
MMMMMMMMMMMR~                                                                         ``jMMMMMMMMMMM
MMMMMMMMMMMR~                                                                           (MMMMMMMMMMM
MMMMMMMMMMMR~                      ` ......................                             (WMMMMMMMMMM
MMMMMMMMMMMR~                     ` (dXWMMMMMMMMMMMMMMMMNky<                            (OWMMMMMMMMM
MMMMMMMMMMMR~                     ` jMMMMMMMMMMMMMMMMMMMMMM3`                           -1XMMMMMMMMM
MMMMMMMMMMMR~                        `~?<<<<<<<<<<<<<<<<<~`                              _zMMMMMMMMM
MMMMMMMMMMM0~                                                                            -jMMMMMMMMM
MMMMMMMMMHC!                                                                            `.jMMMMMMMMM
MMMMMMMMMR~                                                                              .jMMMMMMMMM
MMMMMMMMMR_                                                                              -jMMMMMMMMM
MMMMMMMMMR_                                                                              (dMMMMMMMMM
MMMMMMMMMC`                                                                             (dWMMMMMMMMM
MMMMMMMHC<   `  .- `                                                                 `  (WMMMMMMMMMM
MMMMMMMR<_   .J&QA&-.                                                                 ` jMMMMMMMMMMM
MMMMMMMR<`   jMMMMNR<                    ` ..(-.`                                      _dMMMMMMMMMMM
MMMMMMMR<``.(dMMMMM#>`                `  .(dWMNo.                  `   ...  `      ` `(uXMMMMMMMMMMM
MMMMMMMR<``(dWMMMMMNl``            `   .(dHMMMNk<_                   .jOso-           jMMMMMMMMMMMMM
MMMMMMMR<``(XMMMMMMNI_`        `    ..(dWMMMMMNRz_               ` .(dWMMNk+-  `   `.(dMMMMMMMMMMMMM
MMMMMMMR<-.jMMMMMMMNI_     ` `   ..(dWNMMMMMMMMHZ<             `  .(dMMMMMMNI.      (dMMMMMMMMMMMMMM
MMMMMMMR<jdWMMMMMMMNI_  `    .(+dQNNMMMMMMMMMMMMH>          `    (dWMMMMMMMNHs-   ` jMMMMMMMMMMMMMMM
MMMMMMMNHNMMMMMMMMMMNmx--(+QQNNNMMMMMMMMMMMMMMMMNO- ` `` ``    (dqMMMMMMMMMMMNI ` -jXMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMNNNNNNMMMMMMMMMMMMMMMMMMMMMMNms+(((((+uuQQNMMMMMMMMMMMMMNR<_ jWMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNHQQQQHNNMMMMMMMMMMMMMMMMMMNmy+dMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNQMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM
-->
"""
            )
            flush()
            close()
        }
    }

}