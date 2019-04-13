import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class StringCalculatorKotlinTest {

    private val calculator = StringCalculator()

    @Test
    fun `An empty string returns zero`() {
        assertEquals(0, calculator.add(""))
    }

    @Test
    fun `A single number returns the value`() {
        assertEquals(1, calculator.add("1"))
    }

    @Test
    fun `Two numbers, comma delimited, returns the sum`() {
        assertEquals(3, calculator.add("1,2"))
    }

    @Test
    fun `Two numbers, newline delimited, returns the sum`() {
        assertEquals(3, calculator.add("1\n2"))
    }

    @Test
    fun `Three numbers, delimited either way, returns the sum`() {
        assertEquals(10, calculator.add("1\n2,3\n4"))
    }

    @Test
    fun `Negative numbers throw an exception with the message`() {
        assertFailsWith<IllegalArgumentException> { assertEquals(3, calculator.add("1\n-2,-3\n4")) }
    }
}