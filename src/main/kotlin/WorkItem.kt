import kotlinx.datetime.LocalDate
import mui.icons.material.Computer
import mui.material.*
import mui.system.sx
import react.*
import web.cssom.px

private val defaultDetailPage = FC<WorkItemProps> {
    DetailHeader {
        +it
    }
}

enum class Platforms(val icon: ComponentType<SvgIconProps>) {
    Web(mui.icons.material.Web),
    Android(mui.icons.material.Android),
    Desktop(Computer)
}
enum class Stacks(val url:String){
    Kotlin("https://cdn.svgporn.com/logos/kotlin-icon.svg"),
    Compose("https://cdn.svgporn.com/logos/compose-multiplatform.svg"),
    React("https://cdn.svgporn.com/logos/react.svg"),
    Next("https://cdn.svgporn.com/logos/nextjs-icon.svg"),
    Vue("https://cdn.svgporn.com/logos/vue.svg"),
    Nuxt("https://cdn.svgporn.com/logos/nuxt-icon.svg"),
    Firebase("https://cdn.svgporn.com/logos/firebase.svg")
}
external interface WorkItemProps : Props {
    var workItem: WorkItem
}

sealed class Attribute(val order: Int) {
    //EmojiEvents
    data class Award(val name: String) : Attribute(0)
    object JointDevelopment : Attribute(1)
    data class Platform(val platform: Platforms) : Attribute(2)
    data class Stack(val stack: Stacks) : Attribute(3)
}
data class WorkItem(
    val name: String,
    val description: String,
    val update: LocalDate,
    private val attributes: Set<Attribute> = emptySet(),
    val artifactUrl: String? = null,
    val repoUrl: String? = null,
    val thumbnailUrl: String? = null,
    private val detailPage: ComponentType<WorkItemProps> = defaultDetailPage
) {
    val sortedAttributes = attributes.sortedBy { it.order }.toSet()
    val itemUrlComponent = name.lowercase().replace(" ", "_")
    constructor(
        name: String,
        description: String,
        update: LocalDate,
        attributes: Set<Attribute> = emptySet(),
        artifactUrl: String? = null,
        repoUrl: String? = null,
        thumbnailUrl: String? = null,
        detailPage: (ChildrenBuilder.(WorkItemProps) -> Unit)
    ) : this(
        name,
        description,
        update,
        attributes,
        artifactUrl,
        repoUrl,
        thumbnailUrl,
        FC(block = detailPage)
    )

    fun createDetail() = detailPage.create {
        workItem = this@WorkItem
    }
}

val WorkDetailWrapper = FC<PropsWithChildren> {
    Container {
        sx{
            marginBottom=20.px
        }
        fixed = true
        +it.children
    }
}