import mui.material.ButtonProps
import mui.material.TableCellProps
import mui.types.PropsWithComponent
import react.ElementType
import react.Props



inline fun <P:Props> Props.unsafeProps(props:P.()->Unit){
    unsafeCast<P>().props()
}
fun <P : Props> PropsWithComponent.component(element: ElementType<P>?, props:P.()->Unit){
    component=element
    unsafeProps(props)
}
fun <P : Props> ButtonProps.component(element: ElementType<P>?, props:P.()->Unit){
    component=element
    unsafeProps(props)
}
var ButtonProps.component :  ElementType<*>?
    get() =  unsafeCast<PropsWithComponent>().component
    set(value) { unsafeCast<PropsWithComponent>().component=value}