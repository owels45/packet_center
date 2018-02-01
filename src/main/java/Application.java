import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Application {
    private List<Record> records = new ArrayList<>();

    public void importCSVFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                Record r = new Record(
                        Integer.parseInt(strings[0]),
                        strings[1],
                        Integer.parseInt(strings[2]),
                        strings[3],
                        strings[4],
                        Boolean.parseBoolean(strings[5]),
                        Boolean.parseBoolean(strings[6]),
                        Boolean.parseBoolean(strings[7]));
                records.add(r);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public List<Record> getRecords() {
        return records;
    }

    // count
    public long executeSQL01() {
        long count = records.stream().count();
        return count;
    }

    // count, where
    public void executeSQL02() {
    }

    // count, where, in
    public void executeSQL03() {
    }

    // count, where, not in
    public void executeSQL04() {
    }

    // id, where, in, order by desc limit
    public void executeSQL05() {
    }

    // id, where, in, order by desc, order by asc
    public void executeSQL06() {
    }

    // count, group by
    public void executeSQL07() {
    }

    // count, where, group by
    public void executeSQL08() {
    }

    // count, where, in, group by
    public void executeSQL09() {
    }

    // count, where, not in, group by
    public void executeSQL10() {
    }

    // sum, where, not in, in, group by
    public void executeSQL11() {
    }

    // avg, where, in, in, group by
    public void executeSQL12() {
    }

    public void execute() {
        importCSVFile("data/records.csv");
        executeSQL01();
        executeSQL02();
        executeSQL03();
        executeSQL04();
        executeSQL05();
        executeSQL06();
        executeSQL07();
        executeSQL08();
        executeSQL09();
        executeSQL10();
        executeSQL11();
        executeSQL12();
    }

    public static void main(String... args) {
//        Application app = new Application();
//        app.execute();
    }
}