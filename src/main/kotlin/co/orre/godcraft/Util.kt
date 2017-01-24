package co.orre.godcraft

object Util {
    fun isParsableToInt(i: String): Boolean {
        try {
            i.toInt()
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }

    }
}
