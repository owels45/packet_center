import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ApplicationTest {
    private Application app = new Application();
    private List<Record> records;

    @Before
    public void init(){
        app.getRecords();
        app.execute();
    }

    @Test
    public void executeSQL01() throws Exception {
        assertEquals(1_000_000, app.executeSQL01());
    }

    @Test
    public void executeSQL02() throws Exception {
        assertEquals(11_115, app.executeSQL02());
    }

    @Test
    public void executeSQL03() throws Exception {
        assertEquals(2_686, app.executeSQL03());
    }

    @Test
    public void executeSQL04() throws Exception {
        assertEquals(1_343, app.executeSQL04());
    }

    @Test
    public void executeSQL05() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(127_555);
        list.add(309_059);
        list.add(263_230);
        assertEquals(list, app.executeSQL05());
    }

    @Test
    public void executeSQL06() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(468_257);
        list.add(154_270);
        assertEquals(list, app.executeSQL06());
    }

    @Test
    public void executeSQL07() throws Exception {
        Map<Boolean, Long> map = new HashMap<>();
        map.put(Boolean.FALSE, 919_981L);
        map.put(Boolean.TRUE, 80_019L);
        assertEquals(map, app.executeSQL07());
    }

    @Test
    public void executeSQL08() throws Exception {
        Map<String, Long> map = new HashMap<>();
        map.put("S", 26_551L);
        map.put("M", 26_337L);
        map.put("L", 26_309L);
        assertEquals(map, app.executeSQL08());
    }

    @Test
    public void executeSQL09() throws Exception {
        Map<String, Long> map = new HashMap<>();
        map.put("C", 98_830L);
        map.put("A", 99_281L);
        assertEquals(map, app.executeSQL09());
    }

    @Test
    public void executeSQL10() throws Exception {
        Map<String, Long> map = new HashMap<>();
        map.put("I", 3L);
        map.put("J", 3L);
        map.put("G", 3L);
        map.put("H", 1L);
        map.put("D", 3L);
        map.put("B", 1L);
        map.put("C", 2L);
        map.put("F", 2L);
        map.put("A", 1L);
        assertEquals(map, app.executeSQL10());
    }

    @Test
    public void executeSQL11() throws Exception {
        Map<Boolean, Integer> map = new HashMap<>();
        map.put(Boolean.FALSE, 56_055_016);
        map.put(Boolean.TRUE, 558_159);
        assertEquals(map, app.executeSQL11());
    }

    @Test
    public void executeSQL12() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("D", 3_095);
        map.put("C", 3_185);
        assertEquals(map, app.executeSQL12());
    }

}