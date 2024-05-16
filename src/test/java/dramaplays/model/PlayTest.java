package dramaplays.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayTest {

    @Test
    public void testPlayConstructor() {
        Play play = new Play("Rostam & Sohrab", "tragedy");

        assertEquals("Rostam & Sohrab", play.name);
        assertEquals("tragedy", play.type);
    }
}
