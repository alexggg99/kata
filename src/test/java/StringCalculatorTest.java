import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void befor() {
        this.stringCalculator = new StringCalculator();
    }

    @Test
    public void emptyString_returnZero() {
        assertEquals(0, stringCalculator.calculate("") );
    }

    @Test()
    public void fobiddenSymbols() {
        assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("?1="));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("11+p"));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("2."));
        assertThrows(IllegalArgumentException.class, () ->stringCalculator.calculate("$78"));
    }

    @Test
    public void singlePlus() {
        assertEquals(3, stringCalculator.calculate(" 1+2") );
        assertEquals(2, stringCalculator.calculate("2+0") );
        assertEquals(340, stringCalculator.calculate(" 8+332 ") );
    }

    @Test
    public void singleMinus() {
        assertEquals(-1, stringCalculator.calculate("1-2") );
        assertEquals(9, stringCalculator.calculate("13-4") );
        assertEquals(-6, stringCalculator.calculate("8-14 ") );
    }

}
