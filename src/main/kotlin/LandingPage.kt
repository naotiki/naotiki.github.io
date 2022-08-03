import csstype.*
import react.FC
import react.Props
import emotion.react.css
import mui.material.Avatar
import mui.system.sx
import org.w3c.dom.Image
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.useState

external interface WelcomeProps : Props {
    var name: String
}

val LandingPage = FC<WelcomeProps> { props ->
    var name by useState(props.name)
    div{
        css{
            justifyContent= JustifyContent.center
        }
        Avatar {
            src = "naotiki_icon.webp"
            sx {

                width = 256.px
                height = 256.px
            }

        }
    }

}