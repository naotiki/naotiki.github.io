package utils

fun Collection<*>.nextIndexWithLoop(index: Int):Int{
    return (index+1).takeIf{it in indices }?:0
}
fun Collection<*>.previousIndexWithLoop(index: Int):Int{
    return (index - 1).takeIf { it in indices } ?: (size - 1)
}