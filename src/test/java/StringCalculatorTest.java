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
    public void singleNumer() {
        assertEquals(1, stringCalculator.add("1") );
        assertEquals(2, stringCalculator.add("2") );
    }

    @Test
    public void twoNumbers_comma() {
        assertEquals(3, stringCalculator.add("1,2") );
        assertEquals(7, stringCalculator.add("3,4") );
    }

    @Test
    public void twoNumbers_newString() {
        assertEquals(3, stringCalculator.add("1\n2") );
        assertEquals(7, stringCalculator.add("3\n4") );
    }

}
