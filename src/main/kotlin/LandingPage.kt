import csstype.*
import emotion.react.css
import mui.icons.material.GitHub
import mui.icons.material.Twitter
import mui.material.*
import mui.material.Size
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import muix.pickers.DateTimePickerProps
import org.w3c.dom.HTML.div
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useState
import kotlin.js.Date

external interface WelcomeProps : Props {
    var name: String
}

val LandingPage = FC<WelcomeProps> { props ->
    var name by useState(props.name)
    Box {
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
                +"Naotiki"
            }
            Stack {
                direction = responsive(StackDirection.row)
                spacing = responsive(2)
                Fab {
                    href = "https://twitter.com/NaotikiKt"
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
        div{
           css {
               width=100.px
               height=30.px

           }
        }
        Typography {
            variant = TypographyVariant.h4

            +"README"
        }
        Typography {
            +"Kotlinが大好きな高専生です"
            +Date(Date.now()).toDateString()
        }

    }

}