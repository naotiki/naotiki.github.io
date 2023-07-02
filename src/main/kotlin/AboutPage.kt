import components.AppLink
import emotion.react.css
import mui.icons.material.ArrowForward
import mui.icons.material.GitHub
import mui.icons.material.Twitter
import mui.material.*
import mui.material.Size
import mui.material.styles.TypographyVariant
import mui.system.Breakpoint
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.iframe
import web.cssom.*
import kotlin.js.Date
import kotlin.math.round


val AboutPage = FC<Props> {
    Container {
        Stack {
            sx {
                alignItems = AlignItems.center
            }
            Avatar {
                src = "naotiki_icon.webp"
                sx {
                    width = 256.px
                    height = 256.px
                }
            }

            Typography {
                variant = TypographyVariant.h3
                sx { textAlign = TextAlign.center }
                +"なおちき / Naotiki"
            }
            Stack {
                direction = responsive(StackDirection.row)
                spacing = responsive(2)
                Fab {
                    href = "https://twitter.com/naotikiKt"
                    size = Size.small
                    color = FabColor.primary
                    Twitter()
                }
                Fab {
                    href = "https://github.com/naotiki"
                    size = Size.small
                    color = FabColor.primary
                    GitHub()
                }
            }
        }
        div {
            css {
                width = 100.px
                height = 30.px
            }
        }
        Typography {
            variant = TypographyVariant.h4
            +"README"
        }
        Typography {
            variant = TypographyVariant.body1
            sx {
                whiteSpace = WhiteSpace.preWrap
            }
            +"""
            なおちき(Naotiki)です。なおてぃき ではないです。ですが Naochiki ではありません。
            Kotlinが大好きな高専生です。
            Androidアプリ作ったりKotlin/JSやKotlin/Wasmなどで遊んでいます。
            Compose (Multiplatform)が大好き。
            """.trimIndent()
        }
        AppLink {
            to=PageRoutes.Works.path
            variant = TypographyVariant.h4
            +"Works"
            ArrowForward{
                sx{
                    marginLeft=10.px
                }
            }
        }
        Container{
            maxWidth=Breakpoint.sm
            Carousel {
                sx{
                    alignItems= AlignItems.stretch
                }
                works.forEach {
                    CarouselItem {
                        sx{
                            height=Auto.auto
                        }
                        WorkCard {
                            workItem = it
                        }
                    }
                }
            }
        }

    }
}

fun calcBirthday(today: Double = Date.now()): Double {
    val now = Date(today)
    return round(
        (((now.getFullYear() - 2006.0) + ((now.getMonth() - 8.0) / 12.0) + ((now.getDate() - 30.0) / 30.0 /
                12.0))) * 10000.0
    ) / 10000.0
}