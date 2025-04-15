package devandroid.micaela.moldylemons;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameworkTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    @DisplayName("Testa a adição de dois números")
    public void second_addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testTimeConsumingOperation() {}

}