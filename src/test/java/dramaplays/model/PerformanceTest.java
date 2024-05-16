package dramaplays.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PerformanceTest {

    @Test
    public void testPerformanceConstructor() {
        Performance performance = new Performance("Rostam & Sohrab", 100);

        assertEquals("Rostam & Sohrab", performance.playID);
        assertEquals(100, performance.audience);
    }
}
