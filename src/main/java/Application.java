import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    private List<Record> records = new ArrayList<>();

    //this variable should be never touched and always remains the same after initialization (method importCSVFile(String fileName))
    private List<Record> recordsOriginal;


    //Strings
    private String strSource;
    private Predicate<Record> recordWhereSourceEqualsStrSource = r -> r.getSource().equals(strSource);

    private String strNotSource;
    private Predicate<Record> recordWhereSourceEqualsStrNotSource = r -> !r.getSource().equals(strNotSource);

    private ArrayList<String> sourceList = new ArrayList<String>();
    private ArrayList<String> destinationList = new ArrayList<String>();
    private Predicate<Record> recordWhereSourceContainsSourceList = r -> sourceList.contains(r.getSource());
    private Predicate<Record> recordWhereDestinationContainsDestinationList = r -> destinationList.contains(r.getDestination());

    private ArrayList<String> negativeSourceList = new ArrayList<String>();
    private ArrayList<String> negativeDestinationList = new ArrayList<String>();
    private Predicate<Record> recordWhereSourceNotContainsNegativeSourceList = r -> !negativeSourceList.contains(r.getSource());
    private Predicate<Record> recordWhereDestinationNotContainsNegativeDestinationList = r -> !negativeDestinationList.contains(r.getDestination());

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
    private boolean boolIsExpress;
    private Predicate<Record> recordWhereIsExpressEqualsBoolIsExpress = r -> r.isExpress() == boolIsExpress;

    private boolean boolIsValue;
    private Predicate<Record> recordWhereIsValueEqualsBoolIsValue = r -> r.isValue() == boolIsValue;

    private boolean boolIsFragile;
    private Predicate<Record> recordWhereIsFragileEqualsBoolIsFragile = r -> r.isFragile() == boolIsFragile;

    //Comparators
    private Comparator<Record> orderByWeightDesc = (first, second) -> (int) (second.getWeight() - (first.getWeight()));


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
        copyRecordsToRecordsOriginal();
    }


    private void copyRecordsToRecordsOriginal() {
        recordsOriginal = new ArrayList<>(records.size());
        Collections.copy(records, recordsOriginal);
    }

    public List<Record> getRecords() {
        return records;
    }

    // count
    public long executeSQL01() {
        //return records.stream().count();
        //entspricht der oberen Zeile, ist aber schneller
        return (long) records.size();
    }

    // count, where
    public long executeSQL02() {
        copyRecordsToRecordsOriginal();

        strSource = "A";
        strDestination = "D";
        Stream<Record> stream = records.stream().filter(recordWhereSourceEqualsStrSource);
        return stream.filter(recordWhereDestinationEqualsStrDestination).count();
    }

    // count, where, in
    public long executeSQL03() {
        strSize = "S";
        Stream<Record> stream = records.stream().filter(recordWhereSizeEqualsStrSize);

        sourceList.clear();
        sourceList.add("A");
        sourceList.add("B");
        sourceList.add("C");
        stream = stream.filter(recordWhereSourceContainsSourceList);

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

        negativeSourceList.clear();
        negativeSourceList.add("A");
        negativeSourceList.add("B");
        stream = stream.filter(recordWhereSourceNotContainsNegativeSourceList);

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
    public ArrayList<Integer> executeSQL05() {
        strSize = "S";
        Stream<Record> stream = records.stream().filter(recordWhereSizeEqualsStrSize);

        destinationList.clear();
        destinationList.add("D");
        destinationList.add("E");
        destinationList.add("F");
        stream = stream.filter(recordWhereDestinationContainsDestinationList);

        strSource = "B";
        stream = stream.filter(recordWhereSourceEqualsStrSource);

        intWeightGET = 3500;
        stream = stream.filter(recordWhereWeightIsGreaterEqualThanIntWeightGET);

        boolIsExpress = true;
        stream = stream.filter(recordWhereIsExpressEqualsBoolIsExpress);

        boolIsValue = true;
        stream = stream.filter(recordWhereIsValueEqualsBoolIsValue);

        stream = stream.sorted(orderByWeightDesc).limit(3);

        return stream.map(Record::getId).collect(Collectors.toCollection(ArrayList<Integer>::new));
    }

    // id, where, in, order by desc, order by asc
    public ArrayList<Integer> executeSQL06() {
        strSize = "M";
        Stream<Record> stream = records.stream().filter(recordWhereSizeEqualsStrSize);

        strDestination = "C";
        stream = stream.filter(recordWhereDestinationEqualsStrDestination);

        sourceList.clear();
        sourceList.add("G");
        sourceList.add("H");
        sourceList.add("I");
        sourceList.add("J");
        stream = stream.filter(recordWhereSourceContainsSourceList);

        intWeightLET = 1500;
        stream = stream.filter(recordWhereWeightIsLesserEqualThanIntWeightLET);

        boolIsExpress = true;
        stream = stream.filter(recordWhereIsExpressEqualsBoolIsExpress);

        boolIsValue = true;
        stream = stream.filter(recordWhereIsValueEqualsBoolIsValue);

        Comparator<Record> comparator = orderByWeightDesc.thenComparing(Comparator.comparing(Record::getDestination));

        return stream.sorted(comparator).map(Record::getId).collect(Collectors.toCollection(ArrayList<Integer>::new));
    }

    // count, group by
    public Map<Boolean, Long> executeSQL07() {
        return records.stream().collect(Collectors.groupingBy(Record::isExpress, Collectors.counting()));
    }

    // count, where, group by
    public Map<String, Long> executeSQL08() {
        boolIsExpress = true;
        boolIsValue = false;
        return records.stream().filter(recordWhereIsExpressEqualsBoolIsExpress)
                .filter(recordWhereIsValueEqualsBoolIsValue)
                .collect(Collectors.groupingBy(Record::getSize, Collectors.counting()));
    }

    // count, where, in, group by
    public Map<String, Long> executeSQL09() {
        destinationList.clear();
        destinationList.add("A");
        destinationList.add("C");
        boolIsValue = false;
        return records.stream().filter(recordWhereDestinationContainsDestinationList)
                .filter(recordWhereIsValueEqualsBoolIsValue)
                .collect(Collectors.groupingBy(Record::getDestination, Collectors.counting()));
    }

    // count, where, not in, group by
    public Map<String, Long> executeSQL10() {
        negativeSourceList.clear();
        negativeSourceList.add("B");
        negativeSourceList.add("C");
        boolIsExpress = true;
        boolIsValue = true;
        boolIsFragile = true;

        return records.stream().filter(recordWhereSourceNotContainsNegativeSourceList)
                .filter(recordWhereIsExpressEqualsBoolIsExpress)
                .filter(recordWhereIsValueEqualsBoolIsValue)
                .filter(recordWhereIsFragileEqualsBoolIsFragile)
                .collect(Collectors.groupingBy(Record::getDestination, Collectors.counting()));
    }

    // sum, where, not in, in, group by
    public Map<Boolean, Integer> executeSQL11() {
        strSource = "B";
        negativeDestinationList.clear();
        negativeDestinationList.add("D");
        negativeDestinationList.add("E");
        intWeightGET = 1000;
        intWeightLET = 2750;
        return records.stream().filter(recordWhereSourceEqualsStrSource)
                .filter(recordWhereDestinationNotContainsNegativeDestinationList)
                .filter(recordWhereWeightIsGreaterEqualThanIntWeightGET)
                .filter(recordWhereWeightIsLesserEqualThanIntWeightLET)
                .collect(Collectors.groupingBy(Record::isValue, Collectors.summingInt(Record::getWeight)));
    }

    // avg, where, in, in, group by
    public Map<String, Integer> executeSQL12() {
        sourceList.clear();
        sourceList.add("A");
        sourceList.add("B");

        destinationList.clear();
        destinationList.add("C");
        destinationList.add("D");
        intWeightGET = 1250;
        boolIsFragile = true;
        Stream<Record> stream = records.stream().filter(recordWhereSourceContainsSourceList)
                .filter(recordWhereDestinationContainsDestinationList)
                .filter(recordWhereWeightIsGreaterEqualThanIntWeightGET)
                .filter(recordWhereIsFragileEqualsBoolIsFragile);

        Map<String, Double> map = stream.collect(Collectors.groupingBy(Record::getDestination, Collectors.averagingInt(Record::getWeight)));
        Map<String, Integer> mapInt = new HashMap<>();
        for (String key : map.keySet()) mapInt.put(key, map.get(key).intValue());
        return mapInt;
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
        Application app = new Application();
        app.execute();
    }
}