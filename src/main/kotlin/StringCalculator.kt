import java.util.*

class StringCalculator {
    fun calculate(str: String): Double {
        var expr = parse(str)
        return eval(expr)
    }

    fun parse(str: String): Expression {
        var expr = str.trim()
        throwIfIllegalArgs(expr)

        var stack: Stack<Expression> = Stack();
        for (element in expr.split(" ")) {
            when {
                element.isNum() -> {
                    if(stack.isEmpty())
                        stack.push(Number(element.toDouble()))
                    else {
                        var expression = stack.pop()
                        if(expression !is Operator) throw java.lang.IllegalArgumentException()
                        expression.setExpres2(Number(element.toDouble()))
                        stack.push(expression)
                    }

                }
                element.isExpression() -> {
                    if(stack.isEmpty()) throw java.lang.IllegalArgumentException()
                    var expression = element.toExpression(stack.pop(), null)
                    stack.push(expression)
                }
                else -> throw java.lang.IllegalArgumentException()
            }
        }
        return stack.pop()
    }

    private fun throwIfIllegalArgs(expr: String) {
        var fobiddenSimbols = expr.filter { !it.isDigit() && !it.equals('+') && !it.equals('-') && !it.isWhitespace()}
        if (fobiddenSimbols.length > 0) throw IllegalArgumentException()
    }

    fun eval(expr: Expression): Double = when(expr) {
        is Number -> expr.value
        is Plus -> eval(expr.expr1!!) + eval(expr.expr2!!)
        is Minus -> eval(expr.expr1!!) - eval(expr.expr2!!)
    }

}

interface Operator {
    var expr1: Expression?
    var expr2: Expression?
    fun setExpres1(expres1: Expression) {
        this.expr1 = expres1
    }

    fun setExpres2(expres2: Expression) {
        this.expr2 = expres2
    }
}
sealed class Expression
data class Number(var value: Double): Expression()
data class Plus(override var expr1: Expression?, override var expr2: Expression?): Expression(), Operator
data class Minus(override var expr1: Expression?, override var expr2: Expression?): Expression(), Operator

fun String.isNum(): Boolean {
    return this.toIntOrNull() != null
}


fun String.isExpression(): Boolean {
    return this.equals("+") || this.equals("-")
}

fun String.toExpression(expt1: Expression?, expr2: Expression?): Expression {
    when {
        this.contains("+") -> return Plus(expt1, expr2)
        this.contains("-") -> return Minus(expt1, expr2)
    }
    return Number(1.0)
}