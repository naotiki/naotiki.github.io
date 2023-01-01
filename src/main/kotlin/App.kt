import js.core.jso
import mui.material.*
import mui.material.styles.ThemeProvider
import mui.material.styles.TypographyVariant
import mui.material.styles.createTheme
import react.*
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter
import usehooks.UseDarkModeOutput
import usehooks.useDarkMode

val DarkModeContext = createContext<UseDarkModeOutput>()


enum class Pages(
    val page: ElementType<Props>, val path: String = "/", private val routeName: String? = null
) {
    About(AboutPage, "/"),
    Works(WorksPage, "/works"),
    Assets(AssetsPage, "/assets");

    fun getName() = routeName ?: name
}

val App = FC<Props> {
    DarkModeContext.Provider {
        val darkModeOutput = useDarkMode(null)
        val theme = useMemo(darkModeOutput.isDarkMode) {
            createTheme(jso {
                palette = jso {
                    mode = if (darkModeOutput.isDarkMode) PaletteMode.dark
                    else PaletteMode.light
                }
            })
        }
        value = darkModeOutput
        ThemeProvider {
            this.theme = theme
            CssBaseline()
            BrowserRouter {
                Routes {
                    Route {
                        element = AppLayout.create()
                        Pages.values().forEach {
                            Route {
                                if (it.path == "/") index = true
                                else path = it.path
                                element = it.page.create()
                            }
                        }
                        Route {
                            path = "*"
                            element =
                                Container.create {
                                    Typography {
                                        variant = TypographyVariant.h1
                                        align = TypographyAlign.center
                                        +"404 Not Found"
                                    }
                                    Typography {
                                        variant = TypographyVariant.h6
                                        align = TypographyAlign.center
                                        +"ページが見つかりませんでした"
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}