class StringCalculator {
    fun calculate(rawInput: String): Int {

        var signedNumber = ""
        var sum = 0

        var expression = rawInput.replace("\n", "").replace(" ", "")

        if (expression.isEmpty())
            return 0

        if (!rawInput[rawInput.length - 1].isDigit()) {
            rawInput.dropLast(1)
            throw IllegalArgumentException("Sign %s in the end of expression is not allowed".format(rawInput.reversed()[0]))
        }

        if (rawInput[0].isDigit())
            expression = expression.reversed().plus('+').reversed()

        for (character in expression.reversed()) {

            if (character == '+' || character == '-') {
                if (signedNumber == "")
                    continue
                signedNumber = signedNumber.plus(character).reversed()
                sum += signedNumber.toInt()
                signedNumber = ""
            } else if (character.isDigit()) {
                signedNumber = signedNumber.plus(character)
            } else throw IllegalArgumentException("Sign %s is not supported".format(character))

        }
        return sum
    }

}