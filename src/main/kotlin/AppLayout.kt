import csstype.Bottom
import csstype.Position
import csstype.px
import emotion.react.css
import mui.icons.material.Add
import mui.icons.material.Menu
import mui.material.Fab
import mui.system.SxProps
import mui.system.Theme
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.main
import react.router.Outlet
import react.useState

val AppLayout = FC<Props> {
    var isDrawerOpen by useState(false)
    div {

        Fab {
            sx {
                position = Position.absolute
                top = 16.px
                left = 16.px
            }
            onClick = {
                isDrawerOpen = true
            }
            Menu()
        }
        main {
            //ページが挿入される
            Outlet()
        }
        footer {

        }
    }
}