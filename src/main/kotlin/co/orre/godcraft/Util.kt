package co.orre.godcraft

object Util {
    fun isParsableToInt(i: String): Boolean {
        try {
            Integer.parseInt(i)
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }

    }

    fun isParsableToBoolean(i: String): Boolean {
        try {
            java.lang.Boolean.parseBoolean(i)
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }

    }
}
