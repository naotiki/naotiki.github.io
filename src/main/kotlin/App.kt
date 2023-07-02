import js.core.jso
import mui.material.*
import mui.material.styles.ThemeProvider
import mui.material.styles.createTheme
import react.*
import react.router.*
import usehooks.UseDarkModeOutput
import usehooks.useDarkMode

val DarkModeContext = createContext<UseDarkModeOutput>()

enum class PageRoutes(
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
        val appTheme = useMemo(darkModeOutput.isDarkMode) {
            createTheme(jso {
                palette = jso {
                    mode = if (darkModeOutput.isDarkMode) PaletteMode.dark
                    else PaletteMode.light

                }
            })
        }
        value = darkModeOutput
        ThemeProvider {
            theme = appTheme
            CssBaseline()
            RouterProvider{
                router=appRouter
            }
        }
    }
}