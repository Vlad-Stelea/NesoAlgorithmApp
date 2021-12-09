package entityTest;

import entities.PrettyDate;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PrettyDateTest {

    @Test
    public void testGets() {
        PrettyDate prettyDate = new PrettyDate(1, 2, 21, 3,2,0);
        assertEquals(21, prettyDate.getYear());
        assertEquals(1, prettyDate.getMonth());
        assertEquals(2, prettyDate.getDay());
        assertEquals(3, prettyDate.getHours());
        assertEquals(2, prettyDate.getMinutes());
        assertEquals(0, prettyDate.getSeconds());
    }
}
