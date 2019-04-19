import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

  private StringCalculator stringCalculator;

  @BeforeEach
  void befor() {
    this.stringCalculator = new StringCalculator();
  }

  @Test
  void emptyString_returnZero() {
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate(""));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("?1="));
  }

  @Test()
  void fobiddenSymbols() {
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("?1="));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("11+p"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("2."));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("$78"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 + 332-"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 +- 332"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 + --332"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 +332"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 / 0"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 : 0"));
    assertThrows(IllegalArgumentException.class, () -> stringCalculator.calculate("8 ** 0"));
  }

  @Test
  void singlePlus() {
    assertEquals(3, stringCalculator.calculate(" 1 + 2"));
    assertEquals(2, stringCalculator.calculate("2 + 0"));
    assertEquals(340, stringCalculator.calculate(" 8 + 332 "));
    assertEquals(10, stringCalculator.calculate(" +8 + 2 "));
    assertEquals(6, stringCalculator.calculate(" +8 + -2 "));
  }

  @Test
  void singleMinus() {
    assertEquals(-1, stringCalculator.calculate(" 1 - 2"));
    assertEquals(9, stringCalculator.calculate("13 - 4"));
    assertEquals(-6, stringCalculator.calculate("8 - 14 "));
    assertEquals(22, stringCalculator.calculate("8 + +14 "));
    assertEquals(22, stringCalculator.calculate("+8 - -14 "));
    assertEquals(6, stringCalculator.calculate("-8 - -14 "));
  }

  @Test
  void complexExpression() {
    assertEquals(406, stringCalculator.calculate(" 1 - 2 + 4 - 7 - 77 - -10 + 22 - 0 + 455"));
    assertEquals(61, stringCalculator.calculate("100 - 2 - 48 + 22 - 11"));
  }

  @Test
  void hugeNumbers() {
    assertEquals(26000000, stringCalculator.calculate(" 2000000 + 8000000 + 16000000"));
    assertEquals(-26000000, stringCalculator.calculate(" -2000000 + -8000000 + -16000000"));
    assertEquals(400000000, stringCalculator.calculate(" -20000 * -20000 "));
    assertEquals(1, stringCalculator.calculate(" -20000 / -200001 "));
  }

  @Test
  void singleMultiplication() {
    assertEquals(20, stringCalculator.calculate(" 1 * 20"));
    assertEquals(40, stringCalculator.calculate("-2 * -20"));
    assertEquals(-40, stringCalculator.calculate("2 * -20"));
    assertEquals(-2, stringCalculator.calculate(" -1 * 2"));
  }

  @Test
  void singleDivision() {
    assertEquals(1, stringCalculator.calculate(" 40 / 50"));
    assertEquals(-2, stringCalculator.calculate("80 / -40"));
    assertEquals(-8, stringCalculator.calculate("-80 / 10"));
    assertEquals(1, stringCalculator.calculate(" -2 / -2"));
  }

  @Test
  void commutationProperty() {
    assertEquals(stringCalculator.calculate(" 20 * 6"), stringCalculator.calculate(" 6 * 20"));
    assertEquals(stringCalculator.calculate(" 28 + 12"), stringCalculator.calculate(" 12 + 28"));
    assertEquals(stringCalculator.calculate(" -28 + -12"), stringCalculator.calculate(" -12 - 28"));
    assertNotEquals(stringCalculator.calculate(" 5 / 4"), stringCalculator.calculate(" 4 / 5"));
  }

  @Test
  void associativeProperty() {
    assertEquals(
        stringCalculator.calculate(" 20 * 6 * 8"), stringCalculator.calculate("8 * 6 * 20"));
    assertNotEquals(
        stringCalculator.calculate(" 20 / 6 / 8"), stringCalculator.calculate("8 / 6 / 20"));
  }

  @Test
  void priorityProperty() {
    assertEquals(128, stringCalculator.calculate("8 + 6 * 20"));
    assertEquals(11, stringCalculator.calculate("8 + 60 / 20"));
  }

  @Test
  void identityElement() {
    assertEquals(8, stringCalculator.calculate("8 * 1"));
    assertEquals(8, stringCalculator.calculate("8 / 1"));
  }

  @Test
  void _0Property() {
    assertEquals(0, stringCalculator.calculate("8 * 1 * 4 * 9 * 6 * 0 * 1"));
    assertEquals(0, stringCalculator.calculate("0 / 1"));
  }

  @Test
  void negationProperty() {
    assertEquals(1, stringCalculator.calculate("-1 * -1"));
    assertEquals(-1, stringCalculator.calculate("100 / -100"));
  }

  @Test
  void operatorsCombination() {
    assertEquals(110, stringCalculator.calculate("110 * 8 / 9"));
  }
}
