import browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div").also {
        document.body.appendChild(it)
    }

    createRoot(container).render(
        App.create()
    )
}