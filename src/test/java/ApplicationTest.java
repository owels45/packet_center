import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ApplicationTest {
    private Application app = new Application();
    private List<Record> records;

    @Before
    public void init(){
        app.getRecords();
    }

    @Test
    public void executeSQL01() throws Exception {
        assertEquals(1_000_000, app.executeSQL01());
    }

    @Test
    public void executeSQL02() throws Exception {
    }

    @Test
    public void executeSQL03() throws Exception {
    }

    @Test
    public void executeSQL04() throws Exception {
    }

    @Test
    public void executeSQL05() throws Exception {
    }

    @Test
    public void executeSQL06() throws Exception {
    }

    @Test
    public void executeSQL07() throws Exception {
    }

    @Test
    public void executeSQL08() throws Exception {
    }

    @Test
    public void executeSQL09() throws Exception {
    }

    @Test
    public void executeSQL10() throws Exception {
    }

    @Test
    public void executeSQL11() throws Exception {
    }

    @Test
    public void executeSQL12() throws Exception {
    }

}