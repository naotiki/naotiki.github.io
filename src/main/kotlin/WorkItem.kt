import js.uri.encodeURIComponent
import kotlinx.datetime.LocalDate
import mui.icons.material.Android
import mui.icons.material.Computer
import mui.icons.material.Web
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
    class Awarded(val name:String):Attribute(0)
    object JointDevelopment:Attribute(1)
    class Platform(val platform: Platforms):Attribute(2)
    class Technology(val name:String):Attribute(3)

}
data class WorkItem(
    val name: String,
    val description: String,
    val update: LocalDate,
    private val attributes:List<Attribute> = emptyList(),
    val artifactUrl: String?=null,
    val repoUrl: String? = null,
    val thumbnailUrl:String?=null,
    private val detailPage: ComponentType<WorkItemProps>? = null
) {
    val sortedAttributes=attributes.sortedBy { it.order }
    val hasDetailPage get() = detailPage!=null
    val itemUrlComponent= name.lowercase()
    constructor(
        name: String,
        description: String,
        update: LocalDate,
        attributes:List<Attribute> = emptyList(),
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