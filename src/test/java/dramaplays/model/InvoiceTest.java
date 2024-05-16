package dramaplays.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

public class InvoiceTest {

    @Test
    public void testInvoiceConstructorWithOnePerformance() {
        Performance perf = new Performance("Rostam & Sohrab", 100);
        Invoice invoice = new Invoice("Gholi", List.of(perf));

        assertEquals("Gholi", invoice.customer);
        assertEquals(1, invoice.performances.size());
        assertEquals(perf, invoice.performances.get(0));
    }

    @Test
    public void testInvoiceConstructorWithoutAnyPerformance() {
        Invoice invoice = new Invoice("Gholi", List.of());

        assertEquals("Gholi", invoice.customer);
        assertEquals(0, invoice.performances.size());
    }

    @Test
    public void testInvoiceConstructorWithNullPerformance() {
        Invoice invoice = new Invoice("Gholi", null);

        assertEquals("Gholi", invoice.customer);
        assertNull(invoice.performances);
    }

    @Test
    public void testInvoiceConstructorWithMoreThanOnePerformances() {
        Performance perf1 = new Performance("Rostam & Sohrab", 100);
        Performance perf2 = new Performance("Leili & Majnoon", 50);
        Invoice invoice = new Invoice("Gholi", List.of(perf1, perf2));

        assertEquals("Gholi", invoice.customer);
        assertEquals(2, invoice.performances.size());
        assertEquals(perf1, invoice.performances.get(0));
        assertEquals(perf2, invoice.performances.get(1));
    }
}
