import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Application {
    private ArrayList<String> packetSizes;
    private ArrayList<String> sourceList;
    private ArrayList<String> destinationList;
    private ArrayList<Record> records;

    public Application() {
        packetSizes = new ArrayList<>();
        sourceList = new ArrayList<>();
        destinationList = new ArrayList<>();
        records = new ArrayList<>();
    }

    public void initPacketSizes() {
        packetSizes.add("S");
        packetSizes.add("M");
        packetSizes.add("L");
    }

    public void initSourceList() {
        sourceList.add("A");
        sourceList.add("B");
        sourceList.add("C");
        sourceList.add("D");
        sourceList.add("E");
        sourceList.add("F");
        sourceList.add("G");
        sourceList.add("H");
        sourceList.add("I");
        sourceList.add("J");
    }

    public void initDestinationList() {
        destinationList.add("A");
        destinationList.add("B");
        destinationList.add("C");
        destinationList.add("D");
        destinationList.add("E");
        destinationList.add("F");
        destinationList.add("G");
        destinationList.add("H");
        destinationList.add("I");
        destinationList.add("J");
    }

    public void generateRecords() {
        for (int i = 0;i < Configuration.instance.maximumNumberOfRecords;i++) {
            String randomPacketSize = packetSizes.get(Configuration.instance.randomNumberGenerator.nextInt(0,packetSizes.size()-1));
            int randomWeight = Configuration.instance.randomNumberGenerator.nextInt(500,5000);

            int randomIndexSource = Configuration.instance.randomNumberGenerator.nextInt(0,sourceList.size()-1);
            String randomSource = sourceList.get(randomIndexSource);

            int randomIndexDestination;

            do {
                randomIndexDestination = Configuration.instance.randomNumberGenerator.nextInt(0,destinationList.size()-1);
            } while (randomIndexDestination == randomIndexSource);

            String randomDestination = destinationList.get(randomIndexDestination);

            boolean isExpress = false;
            if (Configuration.instance.randomNumberGenerator.nextDouble() <= Configuration.instance.ratioExpress)
                isExpress = true;

            boolean isValue = false;
            if (Configuration.instance.randomNumberGenerator.nextDouble() <= Configuration.instance.ratioValue)
                isValue = true;

            boolean isFragile = false;
            if (Configuration.instance.randomNumberGenerator.nextDouble() <= Configuration.instance.ratioFragile)
                isFragile = true;

            Record record = new Record(i+1,randomPacketSize,randomWeight,randomSource,randomDestination,isExpress,isValue,isFragile);
            records.add(record);
        }
    }

    public void generateToCSVFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Configuration.instance.dataPath + "records.csv")));

            for (int i = 0;i < Configuration.instance.maximumNumberOfRecords;i++)
                bufferedWriter.write(records.get(i).toString() + Configuration.instance.lineSeparator);

            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void main(String... args) {
        // Application application = new Application();
        // application.initPacketSizes();
        // application.initSourceList();
        // application.initDestinationList();
        // application.generateRecords();
        // application.generateToCSVFile();
    }
}