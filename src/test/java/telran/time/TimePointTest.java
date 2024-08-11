package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimePointTest {
    @Test
    void compareToTest() {
        TimePoint tp1 = new TimePoint(1, TimeUnit.HOUR);
        TimePoint tp2 = new TimePoint(60, TimeUnit.MINUTE);
        assertEquals(0, tp1.compareTo(tp2));
    }

    @Test
    void equalsTest() {
        TimePoint tp1 = new TimePoint(1, TimeUnit.HOUR);
        TimePoint tp2 = new TimePoint(60, TimeUnit.MINUTE);
        assertTrue(tp1.equals(tp2));
    }

    @Test
    void convertTest() {
        TimePoint tp = new TimePoint(1, TimeUnit.HOUR);
        TimePoint converted = tp.convert(TimeUnit.MINUTE);
        assertEquals(60, converted.getAmount());
        assertEquals(TimeUnit.MINUTE, converted.getTimeUnit());
    }

    @Test
    void withAdjusterTest() {
        TimePoint tp = new TimePoint(1, TimeUnit.HOUR);
        TimePointAdjuster adjuster = new PlusTimePointAdjuster(30, TimeUnit.MINUTE);
        TimePoint adjusted = tp.with(adjuster);
        assertEquals(90, adjusted.convert(TimeUnit.MINUTE).getAmount());
    }
    @Test
    void betweenTest() {
        TimePoint tp1 = new TimePoint(1, TimeUnit.HOUR);
        TimePoint tp2 = new TimePoint(30, TimeUnit.MINUTE);
        assertEquals(-30, TimeUnit.MINUTE.between(tp1, tp2));
    }
    @Test
    void adjustTest() {
        TimePoint tp = new TimePoint(1, TimeUnit.HOUR);
        TimePointAdjuster adjuster = new PlusTimePointAdjuster(30, TimeUnit.MINUTE);
        TimePoint adjusted = adjuster.adjust(tp);
        assertEquals(90, adjusted.convert(TimeUnit.MINUTE).getAmount());
    }
    @Test
    void proximityAdjusterTest() {
        TimePoint[] timePoints = {
            new TimePoint(1, TimeUnit.HOUR),
            new TimePoint(2, TimeUnit.HOUR),
            new TimePoint(3, TimeUnit.HOUR)
        };
        TimePointAdjuster adjuster = new FutureProximityAdjuster(timePoints);
        TimePoint tp = new TimePoint(1.5f, TimeUnit.HOUR);
        TimePoint adjusted = adjuster.adjust(tp);
        assertEquals(2, adjusted.getAmount());
        assertEquals(TimeUnit.HOUR, adjusted.getTimeUnit());
    }

}
