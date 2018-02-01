import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Application {
    private List<Record> records = new ArrayList<>();

    //Strings
    private String strSource;
    private Predicate<Record> recordWhereSourceEqualsStrSource = r -> r.getSource().equals(strSource);

    private String strNotSource;
    private Predicate<Record> recordWhereSourceEqualsStrNotSource = r -> !r.getSource().equals(strNotSource);

    private ArrayList<String> listStr = new ArrayList<String>();
    private Predicate<Record> recordWhereSourceContainsListStr = r -> listStr.contains(r.getSource());

    private ArrayList<String> negativeListStr = new ArrayList<String>();
    private Predicate<Record> recordWhereSourceNotContainsNegativeListStr = r -> !negativeListStr.contains(r.getSource());

    private String strDestination;
    private Predicate<Record> recordWhereDestinationEqualsStrDestination = r -> r.getDestination().equals(strDestination);

    private String strSize;
    private Predicate<Record> recordWhereSizeEqualsStrSize = r -> r.getSize().equals(strSize);

    //ints
    private int intWeightGET;
    private Predicate<Record> recordWhereWeightIsGreaterEqualThanIntWeightGET = r -> r.getWeight() >= intWeightGET;

    private int intWeightLET;
    private Predicate<Record> recordWhereWeightIsLesserEqualThanIntWeightLET = r -> r.getWeight() <= intWeightLET;

    //booleans
    boolean boolIsExpress;
    private Predicate<Record> recordWhereIsExpressEqualsBoolIsExpress = r -> r.isExpress() == boolIsExpress;

    boolean boolIsValue;
    private Predicate<Record> recordWhereIsValueEqualsBoolIsValue = r -> r.isValue() == boolIsValue;



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
        return records.stream().count();
    }

    // count, where
    public long executeSQL02() {
        strSource = "A";
        strDestination = "D";
        Stream<Record> stream = records.stream().filter(recordWhereSourceEqualsStrSource);
        return stream.filter(recordWhereDestinationEqualsStrDestination).count();
    }

    // count, where, in
    public long executeSQL03() {
        strSize = "S";
        Stream<Record> stream = records.stream().filter(recordWhereSizeEqualsStrSize);

        listStr.clear();
        listStr.add("A");
        listStr.add("B");
        listStr.add("C");
        stream = stream.filter(recordWhereSourceContainsListStr);

        intWeightGET = 1000;
        stream = stream.filter(recordWhereWeightIsGreaterEqualThanIntWeightGET);

        intWeightLET = 2500;
        stream = stream.filter(recordWhereWeightIsLesserEqualThanIntWeightLET);

        boolIsExpress = true;
        stream = stream.filter(recordWhereIsExpressEqualsBoolIsExpress);

        return stream.count();
    }

    // count, where, not in
    public long executeSQL04() {
        strSize = "L";
        Stream<Record> stream = records.stream().filter(recordWhereSizeEqualsStrSize);

        negativeListStr.clear();
        negativeListStr.add("A");
        negativeListStr.add("B");
        stream = stream.filter(recordWhereSourceNotContainsNegativeListStr);

        intWeightGET = 1250;
        stream = stream.filter(recordWhereWeightIsGreaterEqualThanIntWeightGET);

        intWeightLET = 3750;
        stream = stream.filter(recordWhereWeightIsLesserEqualThanIntWeightLET);

        boolIsExpress = false;
        stream = stream.filter(recordWhereIsExpressEqualsBoolIsExpress);

        boolIsValue = true;
        stream = stream.filter(recordWhereIsValueEqualsBoolIsValue);

        return stream.count();
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