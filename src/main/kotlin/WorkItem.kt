import kotlinx.datetime.LocalDate
import mui.icons.material.Computer
import mui.material.*
import react.*


enum class Platforms(val icon:ComponentType<SvgIconProps>) {
    Web(mui.icons.material.Web),
    Android(mui.icons.material.Android),
    Desktop(Computer)
}

external interface WorkItemProps : Props {
    var workItem: WorkItem
}
sealed class Attribute(val order:Int){
    //EmojiEvents
    data class Award(val name:String):Attribute(0)
    object JointDevelopment:Attribute(1)
    data class Platform(val platform: Platforms):Attribute(2)
    data class Stack(val name: String):Attribute(3)
}
data class WorkItem(
    val name: String,
    val description: String,
    val update: LocalDate,
    private val attributes:Set<Attribute> = emptySet(),
    val artifactUrl: String?=null,
    val repoUrl: String? = null,
    val thumbnailUrl:String?=null,
    private val detailPage: ComponentType<WorkItemProps>? = null
) {
    val sortedAttributes=attributes.sortedBy { it.order }.toSet()
    val hasDetailPage get() = detailPage!=null
    val itemUrlComponent= name.lowercase().replace(" ","_")
    constructor(
        name: String,
        description: String,
        update: LocalDate,
        attributes:Set<Attribute> = emptySet(),
        artifactUrl: String?=null,
        repoUrl: String? = null,
        thumbnailUrl:String?=null,
        detailPage: (ChildrenBuilder.(WorkItemProps) -> Unit)?
    ) : this(
        name,
        description,
        update,
        attributes,
        artifactUrl,
        repoUrl,
        thumbnailUrl,
        detailPage?.let { FC(block = it) })

    fun createDetail() = detailPage?.create {
        workItem = this@WorkItem
    }
}

val WorkDetailWrapper = FC<PropsWithChildren> {
    Container {
        fixed=true
        +it.children
    }
}