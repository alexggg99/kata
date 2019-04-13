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
        assertEquals(0, stringCalculator.add("") );
    }

    @Test
    public void simplePlus() {
        assertEquals(7, stringCalculator.add("4+4"));
    }

    @Test
    public void complexPlus() {
        assertEquals(7, stringCalculator.add("-4+4"));
    }

    @Test
    public void simpleMinus() {
        assertEquals(1, stringCalculator.add("4-3"));
    }

    @Test
    public void testNegativeResult() {
        assertEquals(1, stringCalculator.add("4-3"));
    }

    @Test
    public void complexFormula() {
        assertEquals(-8, stringCalculator.add("4-3+1-10"));
    }

    @Test
    public void lettreStringAndNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("f,7");
        });
    }

}
