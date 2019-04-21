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
                        if (expression !is Operator) throw java.lang.IllegalArgumentException()
                        if (expression.isDivideByZero(element.toDouble())) throw java.lang.IllegalArgumentException()
                        if (expression.expr2 == null) {
                            expression.setExpres2(Number(element.toDouble()))
                        } else {
                            var emp = expression.expr2
                            while ((emp as Operator).expr2 != null) {
                                emp = (emp as Operator).expr2
                            }
                            (emp as Operator).setExpres2(Number(element.toDouble()))
                        }
                        stack.push(expression)
                    }

                }
                element.isExpression() -> {
                    if(stack.isEmpty()) throw java.lang.IllegalArgumentException()
                    var expression = element.toExpression(null, null)
                    var previousExpr = stack.peek()
                    if (previousExpr is Number) {
                        (expression as Operator).setExpres1(previousExpr)
                    } else {
                        if((previousExpr is Plus || previousExpr is Minus) && (expression is Multiply || expression is Divide)) {
                            var prev: Operator = previousExpr as Operator
                            (expression as Operator).setExpres1(prev.expr2 as Expression)
                            prev.setExpres2(expression)
                            expression = prev as Expression
                        } else {
                            (expression as Operator).setExpres1(previousExpr)
                        }
                    }
                    stack.push(expression)
                }
                else -> throw java.lang.IllegalArgumentException()
            }
        }
        return stack.pop()
    }

    private fun throwIfIllegalArgs(expr: String) {
        var fobiddenSimbols = expr.filter { !it.isDigit() && !it.equals('+') && !it.equals('-') && !it.isWhitespace() && !it.equals('*') && !it.equals('/')}
        if (fobiddenSimbols.length > 0) throw IllegalArgumentException()
    }

    fun eval(expr: Expression): Double = when(expr) {
        is Number -> expr.value
        is Plus -> eval(expr.expr1!!) + eval(expr.expr2!!)
        is Minus -> eval(expr.expr1!!) - eval(expr.expr2!!)
        is Multiply -> eval(expr.expr1!!) * eval(expr.expr2!!)
        is Divide -> eval(expr.expr1!!) / eval(expr.expr2!!)
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
data class Multiply(override var expr1: Expression?, override var expr2: Expression?): Expression(), Operator
data class Divide(override var expr1: Expression?, override var expr2: Expression?): Expression(), Operator

fun Expression.isDivideByZero(value: Double): Boolean {
    return this is Divide && value == 0.0
}

fun String.isNum(): Boolean {
    return this.toIntOrNull() != null
}


fun String.isExpression(): Boolean {
    return this.equals("+") || this.equals("-")|| this.equals("/") || this.equals("*")
}

fun String.toExpression(expt1: Expression?, expr2: Expression?): Expression {
    when {
        this.contains("+") -> return Plus(expt1, expr2)
        this.contains("-") -> return Minus(expt1, expr2)
        this.contains("*") -> return Multiply(expt1, expr2)
        this.contains("/") -> return Divide(expt1, expr2)
    }
    return Number(1.0)
}