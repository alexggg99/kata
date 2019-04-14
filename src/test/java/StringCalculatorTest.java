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
        assertEquals(0, stringCalculator.calculate("") );
    }

    @Test
    public void simplePlus() {
        assertEquals(8, stringCalculator.calculate("4+4"));
    }

    @Test
    public void simplePlus2() {
        assertEquals(8, stringCalculator.calculate(" 4+4"));
    }

    @Test
    public void simplePlus3() {
        assertEquals(8, stringCalculator.calculate("4+4 "));
    }

    @Test
    public void simplePlus4() {
        assertEquals(8, stringCalculator.calculate(" 4+4 "));
    }

    @Test
    public void complexPlus() {
        assertEquals(0, stringCalculator.calculate("-4+4"));
    }

    @Test
    public void simpleMinus() {
        assertEquals(1, stringCalculator.calculate("4-3"));
    }

    @Test
    public void testNegativeResult() {
        assertEquals(1, stringCalculator.calculate("4-3"));
    }

    @Test
    public void complexFormula() {
        assertEquals(-8, stringCalculator.calculate("4-3+1-10"));
    }

    @Test
    public void complexFormula3() {
        assertEquals(77, stringCalculator.calculate("4-30+1+102"));
    }

    @Test
    public void complexFormula4() {
        assertEquals(100059, stringCalculator.calculate("-4+60+1+100002"));
    }


    @Test
    public void complexFormula5() {
        assertEquals(34, stringCalculator.calculate("-(34-2)+2"));
    }


    @Test
    public void complexFormula6() {
        assertEquals(-77, stringCalculator.calculate("(4+2)+(11-2)"));
    }

    @Test
    public void complexFormula7() {
        assertEquals(15, stringCalculator.calculate("(12+1)+(11-0)-11+32-(121-1)+0"));
    }

    @Test
    public void formulaNewLine() {
        assertEquals(57, stringCalculator.calculate("-4\n+60+1"));
    }

    @Test
    public void illegalSymbols1() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.calculate("4-M-1");

        });
    }

    @Test
    public void illegalSymbols2() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.calculate("6+ 6+");
        });
    }

    @Test
    public void illegalSymbols3() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.calculate("3=7");
        });
    }

    @Test
    public void illegalSymbols4() {
        assertEquals(10, stringCalculator.calculate("3+ 7"));
    }

    @Test
    public void illegalSymbols5() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.calculate("3++7");
        });
    }

    @Test
    public void lettreStringAndNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.calculate("f,7");
        });
    }

}
