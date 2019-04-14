class StringCalculator {
    fun calculate(str: String): Double {
        var expr = parse(str)
        return eval(expr)
    }

    fun parse(str: String): Expression {
        var expr = str.trim()
        throwIfIllegalArgs(expr)
        var arr = expr.split("+", "-")
        when {
            expr.contains("+") -> return Plus(Number(arr.get(0).toDouble()), Number(arr.get(1).toDouble()))
            expr.contains("-") -> return Minus(Number(arr.get(0).toDouble()), Number(arr.get(1).toDouble()))
        }
        return Number(0.0)
    }

    private fun throwIfIllegalArgs(expr: String) {
        var fobiddenSimbols = expr.filter { !it.isDigit() && !it.equals('+') && !it.equals('-') }
        if (fobiddenSimbols.length > 0) throw IllegalArgumentException()
    }

    fun eval(expr: Expression): Double = when(expr) {
        is Number -> expr.value
        is Plus -> eval(expr.expr1) + eval(expr.expr2)
        is Minus -> eval(expr.expr1) - eval(expr.expr2)
    }

}

sealed class Expression
data class Number(val value: Double): Expression()
data class Plus(val expr1: Expression, val expr2: Expression): Expression()
class Minus(val expr1: Expression, val expr2: Expression): Expression()