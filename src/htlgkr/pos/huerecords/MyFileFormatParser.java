package htlgkr.pos.huerecords;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyFileFormatParser {
    private List<Person> personList;
    private FileInputStream fis;

    public MyFileFormatParser(FileInputStream fis) {
        personList = new ArrayList<>();
        this.fis = fis;
    }

    /**
     * Not scuffed at all
     */
    public void load() {
        try (BufferedReader writer = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = writer.readLine()) != null) {
                if (!line.isEmpty()) {
                    int id = Integer.valueOf(line.split("=")[1]);
                    line = writer.readLine();
                    String firstname = line.split("=")[1];
                    line = writer.readLine();
                    String lastname = line.split("=")[1];
                    line = writer.readLine();
                    LocalDate birthdate = LocalDate.parse(line.split("=")[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    line = writer.readLine();
                    String gender = line.split("=")[1];
                    line = writer.readLine();
                    double salary = Double.valueOf(line.split("=")[1]);
                    line = writer.readLine();
                    Address address = getAddressFromLine(line);
                    line = writer.readLine();
                    long phoneNumber = Long.parseLong(line.split("=")[1]);
                    line = writer.readLine();
                    String eMailAddress = line.split("=")[1];
                    line = writer.readLine();
                    String jobTitle = line.split("=")[1];
                    line = writer.readLine();
                    String department = line.split("=")[1];
                    line = writer.readLine();
                    LocalTime workStartTime = LocalTime.parse(line.split("=")[1], DateTimeFormatter.ofPattern("HH:mm"));
                    line = writer.readLine();
                    LocalTime wordEndTime = LocalTime.parse(line.split("=")[1], DateTimeFormatter.ofPattern("HH:mm"));
                    line = writer.readLine();
                    String notes = line.split("=")[1];
                    personList.add(
                            new Person(
                                    id,
                                    firstname,
                                    lastname,
                                    birthdate,
                                    gender,
                                    salary,
                                    address,
                                    phoneNumber,
                                    eMailAddress,
                                    jobTitle,
                                    department,
                                    workStartTime,
                                    wordEndTime,
                                    notes
                            )
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Address getAddressFromLine(String line) {
        String valuesAsString = line.substring(line.indexOf("["), line.indexOf("]"));
        String[] valuesInArray = valuesAsString.split(",");

        String streetAndHouseNumber = valuesInArray[0].trim().split("=")[1];
        String zipCodeAndCity = valuesInArray[1].trim().split("=")[1];
        String country = valuesInArray[2].trim().split("=")[1];
        return new Address(
                streetAndHouseNumber,
                zipCodeAndCity,
                country
        );
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
