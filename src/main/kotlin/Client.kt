import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {

    val container = document.createElement("div").also {
        document.body.appendChild(it)
    }

    createRoot(container).render(
        App.create()
    )
}