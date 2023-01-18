package htlgkr.pos.huerecords;

import java.time.LocalDate;
import java.time.LocalTime;

public record Person(
        int id,
        String firstname,
        String lastname,
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

    public Person(String firstName, String lastName, LocalDate birthDate, String gender, double salary, Address address, long phoneNumber, String eMailAddress, String jobTitle, String department, LocalTime workStartTime, LocalTime wordEndTime, String notes) {
        this(nextId, firstName, lastName, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
        nextId++;
    }

    public Person getPersonWithSetFirstname(String newFirstName){
        return new Person(id, newFirstName, lastname, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetLastname(String newLastName){
        return new Person(id, firstname, newLastName, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetBirthdate(LocalDate newBirthDate){
        return new Person(id, firstname, lastname, newBirthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetGender(String newGender){
        return new Person(id, firstname, lastname, birthDate, newGender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetSalary(double newSalary){
        return new Person(id, firstname, lastname, birthDate, gender, newSalary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetAddress(Address newAddress){
        return new Person(id, firstname, lastname, birthDate, gender, salary, newAddress, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetPhoneNumber(long newPhoneNumber){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, newPhoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetEMailAddress(String newEMailAddress){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, phoneNumber, newEMailAddress, jobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetJobTitle(String newJobTitle){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, phoneNumber, eMailAddress, newJobTitle, department, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetDepartment(String newDepartment){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, newDepartment, workStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetWorkStartTime(LocalTime newWorkStartTime){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, newWorkStartTime, wordEndTime, notes);
    }

    public Person getPersonWithSetWorkEndTime(LocalTime newWorkEndTime){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, newWorkEndTime, notes);
    }

    public Person getPersonWithSetNotes(String newNotes){
        return new Person(id, firstname, lastname, birthDate, gender, salary, address, phoneNumber, eMailAddress, jobTitle, department, workStartTime, wordEndTime, newNotes);
    }
}