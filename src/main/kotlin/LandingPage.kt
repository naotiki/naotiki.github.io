import csstype.AlignItems
import csstype.TextAlign
import csstype.WhiteSpace
import csstype.px
import emotion.react.css
import mui.icons.material.GitHub
import mui.icons.material.Twitter
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useState
import kotlin.js.Date
import kotlin.math.round

external interface WelcomeProps : Props {
    var name: String
}


val LandingPage = FC<WelcomeProps> { props ->
    var name by useState(props.name)

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
                +"なおちき/Naotiki"
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
            +"Kotlinが大好きな高専生です。\n"
            +"約${calcBirthday()}歳です。"
        }

    }
}

fun calcBirthday(today: Double = Date.now()): Double {
    val now = Date(today)
    return round((((now.getFullYear() - 2006.0) + ((now.getMonth() - 8.0) / 12.0) + ((now.getDate() - 30.0) / 30.0 /
            12.0)))*10000.0)/10000.0
}