import components.AppLink
import components.NewTabAnchor
import csstype.*
import emotion.react.css
import emotion.styled.styled
import mui.material.*
import mui.material.List
import mui.material.styles.TypographyVariant
import mui.system.Breakpoint
import mui.system.Theme
import mui.system.responsive
import mui.system.sx
import mui.types.PropsWithComponent
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.footer
import react.dom.html.ReactHTML.main
import react.router.Outlet
import react.router.dom.Link
import web.cssom.*
import mui.icons.material.*
import mui.icons.material.Menu

val Offset = div.styled {
    +it.asDynamic().theme.unsafeCast<mui.material.styles.Theme>().mixins.toolbar.unsafeCast<Properties>()
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
                direction = responsive(StackDirection.column)
                sx {
                    width = 250.px
                    height = 100.pct
                }
                List {
                    ListItem {
                        AppLink {
                            to="/"
                            variant = mui.material.styles.TypographyVariant.h4
                            +"naotiki.me"
                        }

                    }
                    PageRoutes.values().forEach {
                        ListItem {
                            ListItemButton {
                                unsafeProps<PropsWithComponent> {
                                    component(Link) {
                                        to = it.path
                                    }
                                }
                                ListItemText {
                                    +it.getName()

                                }
                                ListItemIcon{
                                    ArrowForward()
                                }
                            }
                        }
                    }
                }
                div {
                    css {
                        marginTop = Auto.auto
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
                    component(NewTabAnchor) {
                        href = "https://github.com/naotiki/naotiki.github.io"
                    }
                    GitHub()
                    +"naotiki/naotiki.github.io"
                }
            }
        }
        Stack {
            direction = responsive(StackDirection.row)
            sx {
                alignItems = AlignItems.center
                margin = Margin(10.px, Auto.auto)
            }
            div {
                css {
                    width = 0.px
                }
                Fab {
                    sx {
                        position = Position.relative
                        left = 16.px
                        /*top = 16.px
                        left = 16.px*/

                    }
                    onClick = {
                        isDrawerOpen = true
                    }
                    Menu()
                }
            }

            AppLink {
                variant = TypographyVariant.h4
                sx {
                    margin=Auto.auto
                }
                +"naotiki.me"
            }
        }
    } else {
        AppBar {
            //position = AppBarPosition.fixed
            Toolbar {
                // disableGutters=true
                AppLink {
                    variant = TypographyVariant.h5
                    +"naotiki.me"
                }
                flexSpacer()
                Box {
                    PageRoutes.values().forEach {
                        Button {
                            component(Link) {
                                to = it.path
                            }
                            color = ButtonColor.inherit
                            +it.getName()
                        }
                    }

                    Tooltip {
                        title = ReactNode("naotiki/naotiki.github.io")
                        IconButton {
                            unsafeProps<PropsWithComponent> {
                                component(NewTabAnchor) {
                                    href = "https://github.com/naotiki/naotiki.github.io"
                                }
                            }
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
        Offset()
    }
    main {
        //ページが挿入される
        Outlet()
    }
    footer {

    }

}