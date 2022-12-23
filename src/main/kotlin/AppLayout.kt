import csstype.Position
import csstype.px
import mui.icons.material.Menu
import mui.material.*
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
        Drawer {
            anchor = DrawerAnchor.left
            open = isDrawerOpen
            onClose = { b: dynamic, s: String ->
                isDrawerOpen = !isDrawerOpen
            }
            Box {
                sx {
                    width = 250.px
                }
                List {
                    ListItem{
                        +"Naotiki/"
                    }
                    ListItem{
                        ListItemButton{
                            ListItemText{
                                +"About"
                            }
                        }
                    }
                    ListItem{
                        ListItemButton{
                            ListItemText{
                                +"Artifact"
                            }
                        }
                    }
                }
            }
        }
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