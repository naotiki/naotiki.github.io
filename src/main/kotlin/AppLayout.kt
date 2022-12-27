import csstype.*
import emotion.react.css
import emotion.styled.styled
import mui.icons.material.*
import mui.icons.material.Menu
import mui.material.*
import mui.material.Link
import mui.material.List
import mui.material.styles.TypographyVariant
import mui.material.styles.useTheme
import mui.system.Breakpoint
import mui.system.Theme
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.main
import react.router.Outlet

val Offset = div.styled { _, theme ->
    +theme.unsafeCast<mui.material.styles.Theme>().mixins.toolbar.unsafeCast<Properties>()
}
val AppLayout = FC<Props> {
    val theme = useTheme<mui.material.styles.Theme>()
    var isDrawerOpen by useState(false)
    val isDownSm = useMediaQuery<Theme>({
        it.breakpoints.down(Breakpoint.sm)
    })
    val colorMode = useContext(DarkModeContext)
    if (isDownSm) {
        Drawer {
            anchor = DrawerAnchor.left
            open = isDrawerOpen
            onClose = { b: dynamic, s: String ->
                isDrawerOpen = !isDrawerOpen
            }
            Stack {
                direction= responsive(StackDirection.column)
                sx {
                    width = 250.px
                    height=100.pct
                }
                List {
                    ListItem {
                        +"Naotiki/"
                    }
                    ListItem {
                        Link {
                            href = "/"
                            ListItemButton {
                                ListItemText {
                                    +"About"
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
                div{
                    css {
                        marginTop=Auto.auto
                    }
                }
                Button {
                    onClick = { colorMode.toggle() }
                    color = ButtonColor.inherit
                    startIcon=if (colorMode.isDarkMode)
                        DarkModeOutlined.create()
                    else LightModeOutlined.create()
                    +if (colorMode.isDarkMode) "Cool" else "目がぁぁぁぁ"
                }
                Link {
                    href = "https://github.com/naotiki/naotiki.github.io"

                    Typography {
                        GitHub{
                            sx { verticalAlign= VerticalAlign.middle }
                        }
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
                    component = div
                    sx {
                        flexGrow = 1.asDynamic()
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
                        asDynamic().href= "https://github.com/naotiki/naotiki.github.io"
                        GitHub{
                            sx { verticalAlign= VerticalAlign.middle }
                        }
                    }
                    IconButton {
                        onClick = { colorMode.toggle() }
                        color = IconButtonColor.inherit
                        if (colorMode.isDarkMode)
                            DarkModeOutlined()
                        else LightModeOutlined()
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