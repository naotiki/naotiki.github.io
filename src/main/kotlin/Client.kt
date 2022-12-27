import browser.document
import hooks.UseDarkModeOutput
import mui.material.styles.createTheme
import react.create
import react.createContext
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div").also {
        document.body.appendChild(it)
    }

    createRoot(container).render(
        App.create()
    )
}