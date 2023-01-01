import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

enum class State {
    Developing,
    Complete
}

data class WorkItem(
    val name: String, val description: String, val update: LocalDate,
    val artifactUrl: String?, val jointDevelopment: Boolean, val githubUrl: String? = null,val award:String?=null
)

val works = listOf(
    WorkItem(
        "Wikiしりとり",
        """しりとりAIもどき
            |AIに例外を吐かせたらあなたの勝ち""".trimMargin(), LocalDate(2022, Month.MAY, 15),
        "https://naotiki.github.io/wiki-shiritori-nuxt", false, "https://github.com/naotiki/wiki-shiritori-nuxt"
    ),WorkItem(
        "Draw R4sh",
        """一瞬でお絵かきバトル！！！
            |あなたの絵心を見せつけよう！！！""".trimMargin(), LocalDate(2022, Month.MAY, 5),
        "https://draw-rush.web.app", true, "https://github.com/Yumemi-Hackathon22-D/DRAW-RUSH"
    ),WorkItem(
        "Jiyukenkyu2021",
        """パッ○マン風ゲームを機械学習したAIにプレイさせる""".trimMargin(), LocalDate(2021, Month.AUGUST, 29),
        "https://github.com/naotiki/Jiyukenkyu2021/blob/master/README.md", false, "https://github.com/naotiki/Jiyukenkyu2021"
    ),
)