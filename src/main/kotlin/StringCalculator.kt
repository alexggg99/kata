import java.util.*

class StringCalculator {
    fun calculate(rawInput: String): Int {

        val expressionsStack = Stack<String>()
        expressionsStack.push("")

        if (rawInput.isEmpty())return 0
        val expression = rawInput.replace("\n", "").replace(" ", "")
        if (rawInput[rawInput.length - 1] == '+' || rawInput[rawInput.length - 1] == '-')
            throw IllegalArgumentException("Sign %s in the end of expression is not allowed".format(rawInput.reversed()[0]))
        val completedByBrackets = "(".plus(expression).plus(")")


        for (letter in completedByBrackets.reversed()) {

            if (letter != '(' && letter != ')') {
                var exp = expressionsStack.pop()
                exp = exp.plus(letter)
                expressionsStack.push(exp)
            }
            if (letter == ')') {
                expressionsStack.push("")
            }

            if (letter == '(') {
                val exp = expressionsStack.pop()
                val res = calculateUnit(exp.reversed())
                var exp2 = expressionsStack.pop()
                exp2 = exp2.plus(res.toString().reversed())
                expressionsStack.push(exp2)

            }
        }
        return expressionsStack.pop().reversed().toInt()

    }

    fun calculateUnit(expressionUnit: String): Int {

        var signedNumber = ""
        var sum = 0
        var expression = expressionUnit

        if (expressionUnit[0].isDigit())
            expression = expressionUnit.reversed().plus('+').reversed()

        for (character in expression.reversed()) {

            if (character == '+' || character == '-') {
                if (signedNumber == "")
                    throw IllegalArgumentException("Unexpected operators duplication for %s".format(character))
                signedNumber = signedNumber.plus(character).reversed()
                sum += signedNumber.toInt()
                signedNumber = ""
            } else if (character.isDigit()) {
                signedNumber = signedNumber.plus(character)
            } else throw IllegalArgumentException("Sign %s is not supported".format(character))

        }
        return sum;
    }

}