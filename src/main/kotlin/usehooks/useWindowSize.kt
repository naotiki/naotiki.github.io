@file:JsModule("usehooks-ts")
@file:JsNonModule

package usehooks

@JsName("useElementSize")
external fun useWindowSize(): WindowSize
external interface WindowSize{
    val width:Number
    val height:Number
}
