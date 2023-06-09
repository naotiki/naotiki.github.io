import csstype.*
import emotion.react.css
import emotion.styled.styled
import mui.icons.material.DarkModeOutlined
import mui.icons.material.GitHub
import mui.icons.material.LightModeOutlined
import mui.icons.material.Menu
import mui.material.*
import mui.material.styles.TypographyVariant
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
    var isDrawerOpen by useState(false)
    val isDownSm = useMediaQuery<Theme>({
        it.breakpoints.down(Breakpoint.sm)
    })
    val colorMode = useContext(DarkModeContext)
    if (isDownSm) {
        Drawer {
            anchor = DrawerAnchor.left
            open = isDrawerOpen
            onClose = { _, _ ->
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
                    Pages.values().forEach {
                        ListItem {
                            Link {
                                href = it.path
                                ListItemButton {
                                    ListItemText {
                                        +it.getName()
                                    }
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
                    onClick = {
                        colorMode?.toggle?.invoke()
                    }
                    color = ButtonColor.inherit
                    startIcon = if (colorMode?.isDarkMode == true)
                        DarkModeOutlined.create()
                    else LightModeOutlined.create()
                    +if (colorMode?.isDarkMode == true) "Cool" else "目がぁぁぁぁ"
                }
                Button {
                    href = "https://github.com/naotiki/naotiki.github.io"
                    GitHub()
                    +"naotiki/naotiki.github.io"
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
                    Pages.values().forEach {
                        Button {
                            color = ButtonColor.inherit
                            href = it.path
                            +it.getName()
                        }
                    }

                    Tooltip {
                        title = ReactNode("naotiki/naotiki.github.io")
                        IconButton {
                            asDynamic().href = "https://github.com/naotiki/naotiki.github.io"
                            GitHub {
                                sx { verticalAlign = VerticalAlign.middle }
                            }
                        }
                    }
                    Tooltip {
                        title = ReactNode(if (colorMode?.isDarkMode == true) "Cool" else "目がぁぁぁぁ")
                        IconButton {
                            onClick = { colorMode?.toggle?.invoke() }
                            color = IconButtonColor.inherit
                            if (colorMode?.isDarkMode == true)
                                DarkModeOutlined()
                            else LightModeOutlined()

                        }
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