package htlgkr.pos.huerecords;

import java.time.LocalDate;
import java.time.LocalTime;

public record Person(
        int id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String gender,
        double salary,
        Address address,
        long phoneNumber,
        String eMailAddress,
        String jobTitle,
        String department,
        LocalTime workStartTime,
        LocalTime wordEndTime,
        String notes
) {
    private static int nextId = 0;

    public Person(String firstName, String lastName, LocalDate birthDate, String gender, double salary, Address address, long phoneNumber, String EMailAddress, String jobTitle, String department, LocalTime workStartTime, LocalTime wordEndTime, String notes) {
        this(nextId, firstName, lastName, birthDate, gender, salary, address, phoneNumber, EMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
        nextId++;
    }
}