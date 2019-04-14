class StringCalculator {
    fun calculate(rawInput: String): Int {
        if (rawInput.isEmpty())
            return 0

        var signedNumber = ""
        var sum = 0
        var expression = rawInput

        if (rawInput[0].isDigit())
            expression = rawInput.reversed().plus('+').reversed()

        for (character in expression.reversed()) {

            if (character == '+' || character == '-') {
                signedNumber = signedNumber.plus(character).reversed()
                sum += signedNumber.toInt()
                signedNumber = ""
            } else if (character.isDigit()) {
                signedNumber = signedNumber.plus(character)
            } else throw IllegalArgumentException()

        }
        return sum
    }

}