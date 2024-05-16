package dramaplays;

import static org.junit.jupiter.api.Assertions.*;

import dramaplays.model.Invoice;
import dramaplays.model.Performance;
import dramaplays.model.Play;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class FactorPrinterTest {
    private Map<String, Play> plays;
    private FactorPrinter factorPrinter;

    @BeforeEach
    void setUp() {
        Play play1 = new Play("Rostam & Sohrab", "tragedy");
        Play play2 = new Play("Venetian Merchant", "comedy");
        Play play3 = new Play("Fake", "fakeType");
        plays = Map.of(
                "rostamAndSohrab", play1,
                "venetianMerchant", play2,
                "fake", play3
        );
        factorPrinter = new FactorPrinter();
    }

    @Test
    public void TestPrintWithoutInvoice() {
        Invoice invoice = new Invoice("Ali", List.of());

        assertEquals(
                """
                Factor for Ali
                Amount owed is $0.00
                You earned 0 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithOnePerformance() {
        Performance performance1 = new Performance("rostamAndSohrab", 100);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        assertEquals(
                """
                Factor for Ali
                  Rostam & Sohrab: $1,100.00 (100 seats)
                Amount owed is $1,100.00
                You earned 70 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithMoreThatOnePerformances() {
        Performance performance1 = new Performance("rostamAndSohrab", 100);
        Performance performance2 = new Performance("venetianMerchant", 50);
        Invoice invoice = new Invoice("Ali", List.of(performance1, performance2));

        assertEquals(
                """
                Factor for Ali
                  Rostam & Sohrab: $1,100.00 (100 seats)
                  Venetian Merchant: $700.00 (50 seats)
                Amount owed is $1,800.00
                You earned 100 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithComedyPlayWithManyAudience() {
        Performance performance1 = new Performance("rostamAndSohrab", 1000);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        assertEquals("""
                Factor for Ali
                  Rostam & Sohrab: $10,100.00 (1000 seats)
                Amount owed is $10,100.00
                You earned 970 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithTragedyPlayWithManyAudience() {
        Performance performance1 = new Performance("venetianMerchant", 1000);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        assertEquals("""
                Factor for Ali
                  Venetian Merchant: $8,300.00 (1000 seats)
                Amount owed is $8,300.00
                You earned 1170 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithComedyPlayWithFewAudience() {
        Performance performance1 = new Performance("rostamAndSohrab", 1);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        assertEquals("""
                Factor for Ali
                  Rostam & Sohrab: $400.00 (1 seats)
                Amount owed is $400.00
                You earned 0 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithTragedyPlayWithFewAudience() {
        Performance performance1 = new Performance("venetianMerchant", 1);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        assertEquals("""
                Factor for Ali
                  Venetian Merchant: $303.00 (1 seats)
                Amount owed is $303.00
                You earned 0 credits
                """, factorPrinter.print(invoice, plays));
    }

    @Test
    public void TestPrintWithInvalidPlayType() {
        Performance performance1 = new Performance("fake", 1);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        Error error = assertThrows(Error.class, () -> factorPrinter.print(invoice, plays));
        assertEquals("unknown type: fakeType", error.getMessage());
    }

    @Test
    public void TestPrintWithNullPlays() {
        Performance performance1 = new Performance("rostamAndSohrab", 100);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        Error error = assertThrows(Error.class, () -> factorPrinter.print(invoice, null));
        assertEquals("null plays is not allowed", error.getMessage());
    }

    @Test
    public void TestPrintWithInvalidPlayId() {
        Performance performance1 = new Performance("invalidPlayId", 100);
        Invoice invoice = new Invoice("Ali", List.of(performance1));

        Error error = assertThrows(Error.class, () -> factorPrinter.print(invoice, plays));
        assertEquals("unknown play: invalidPlayId", error.getMessage());
    }
}
