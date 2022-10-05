import kotlinx.js.BigInt
import kotlin.js.Date
import kotlin.test.Test
import kotlin.test.assertEquals
class UnitTest {
    @Test
    fun Superrrrrrr_Strict_Naotiki_yo(){
        val now=Date(Date.UTC(2022,8,30))
        println("${now.getFullYear()}/${now.getMonth()}/${now.getDate()}")

        assertEquals(16.0,calcBirthday(Date.UTC(2022,8,30)))

    }
}