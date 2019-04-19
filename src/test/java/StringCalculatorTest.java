import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void befor() {
        this.stringCalculator = new StringCalculator();
    }

    @Test
    public void emptyString_returnZero() {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("") );
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("?1="));
    }

    @Test()
    public void fobiddenSymbols() {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("?1="));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("11+p"));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("2."));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("$78"));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("8 + 332-"));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("8 +- 332"));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("8 + --332"));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("8 +332"));
    }

    @Test
    public void singlePlus() {
        assertEquals(3, stringCalculator.calculate(" 1 + 2") );
        assertEquals(2, stringCalculator.calculate("2 + 0") );
        assertEquals(340, stringCalculator.calculate(" 8 + 332 ") );
        assertEquals(10, stringCalculator.calculate(" +8 + 2 ") );
        assertEquals(6, stringCalculator.calculate(" +8 + -2 ") );
    }

    @Test
    public void singleMinus() {
        assertEquals(-1, stringCalculator.calculate(" 1 - 2") );
        assertEquals(9, stringCalculator.calculate("13 - 4") );
        assertEquals(-6, stringCalculator.calculate("8 - 14 ") );
        assertEquals(22, stringCalculator.calculate("8 + +14 ") );
        assertEquals(22, stringCalculator.calculate("+8 - -14 ") );
    }

    @Test
    public void complexExpression() {
        assertEquals(406, stringCalculator.calculate(" 1 - 2 + 4 - 7 - 77 - -10 + 22 - 0 + 455") );
        assertEquals(61, stringCalculator.calculate("100 - 2 - 48 + 22 - 11") );
    }

}
