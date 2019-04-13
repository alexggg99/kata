class StringCalculator {
    fun add(str: String): Int {
        if(str.isNullOrEmpty()) return 0
        if(isDelimetedStr(str)) {
            var result = 0
            for (item in str.split(",", "\n"))
                result += item.toInt()
            return result
        }
        return str.toInt()
    }

    fun isDelimetedStr(str: String): Boolean {
        return str.contains(",") || str.contains("\n")
    }

}