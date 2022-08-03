import csstype.AlignItems
import csstype.TextAlign
import csstype.px
import mui.icons.material.GitHub
import mui.icons.material.Twitter
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.useState

external interface WelcomeProps : Props {
    var name: String
}

val LandingPage = FC<WelcomeProps> { props ->
    var name by useState(props.name)

    Box {

        Stack {
            sx {
                // display= Display.flex
                //  justifyContent = JustifyContent.center
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
                    size = Size.small
                    color = FabColor.primary
                    Twitter()
                }
                Fab {
                    size = Size.small
                    color = FabColor.primary
                    GitHub()
                }

            }

        }
    }

}