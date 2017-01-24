package co.orre.goddesscraft

object Util {
    fun isParsableToInt(i: String): Boolean {
        try {
            i.toInt()
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }

    }

    fun isParsableToBoolean(i: String): Boolean {
        try {
            i.toBoolean()
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }

    }
}
