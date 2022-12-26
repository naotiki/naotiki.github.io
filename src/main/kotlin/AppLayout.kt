import csstype.*
import emotion.styled.styled
import mui.icons.material.Brightness4
import mui.icons.material.Brightness7
import mui.icons.material.GitHub
import mui.icons.material.Menu
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.material.styles.useTheme
import mui.system.Breakpoint
import mui.system.Theme
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.main
import react.router.Outlet
import react.useContext
import react.useState

val Offset = div.styled { _, theme ->
    +theme.unsafeCast<mui.material.styles.Theme>().mixins.toolbar.unsafeCast<Properties>()
}
val AppLayout = FC<Props> {
    val theme= useTheme<mui.material.styles.Theme>()
    var isDrawerOpen by useState(false)
    val isDownSm = useMediaQuery<Theme>({
        it.breakpoints.down(Breakpoint.sm)
    })
    val colorMode = useContext(ColorModeContext)



    if (isDownSm) {
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
                    sx {

                    }
                    ListItem {
                        +"Naotiki/"
                    }
                    ListItem {

                        Link {
                            href = "/"
                            ListItemButton {

                                ListItemText {
                                    +"About Naotiki"
                                }
                            }
                        }
                    }
                    ListItem {
                        Link {
                            href = "/works"
                            ListItemButton {
                                ListItemText {
                                    +"Works"
                                }
                            }
                        }
                    }
                }

                Link {
                    sx {
                        position = Position.absolute
                        bottom = 5.px
                    }
                    href = "https://github.com/naotiki/naotiki.github.io"

                    Typography {
                        GitHub()
                        +"naotiki/naotiki.github.io"
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
    } else {
        AppBar {
            //position = AppBarPosition.fixed
            Toolbar {
                // disableGutters=true
                Typography {
                    variant = TypographyVariant.h5
                    component= div
                    sx {
                        flexGrow=1.asDynamic()
                    }
                    +"Naotiki"
                }
                Box {
                    Button {
                        color = ButtonColor.inherit
                        href = "/"
                        +"About"
                    }
                    Button {
                        href = "/works"
                        color = ButtonColor.inherit
                        +"Works"
                    }
                    IconButton{
                        onClick= { colorMode.toggleColorMode() }
                        color=IconButtonColor.inherit
                        if (theme.palette.mode==PaletteMode.light)
                            Brightness4()
                        else Brightness7()
                    }
                }
            }

        }

    }
    Offset()
    main {
        //ページが挿入される
        Outlet()
    }
    footer {

    }

}