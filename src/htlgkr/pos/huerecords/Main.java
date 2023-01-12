package htlgkr.pos.huerecords;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<Person> persons = new ArrayList<>();

    public static void main(String[] args){

    }

    public static void addNewPerson(Scanner input){
        System.out.println("Enter firstname:");
        String firstname = input.next();

        System.out.println("Enter lastName:");
        String lastName = input.next();

        System.out.println("Enter birthdate (dd.MM.yyyy):");
        LocalDate birthDate = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Enter gender:");
        String gender = input.next();

        System.out.println("Enter salary:");
        double salary = input.nextDouble();

        System.out.println("Enter Street and House number:");
        String streetAndHouseNumber = input.next();

        System.out.println("Enter Zip Code and City:");
        String zipCodeAndCity = input.next();

        System.out.println("Enter Country:");
        String country = input.next();

        System.out.println("Enter phoneNumber:");
        long phoneNumber = input.nextLong();

        System.out.println("Enter E-MailAddress:");
        String eMailAddress = input.next();

        System.out.println("Enter Job title:");
        String jobTitle = input.next();

        System.out.println("Enter department:");
        String department = input.next();

        System.out.println("Enter work start time(HH:mm):");
        LocalTime workStartTime = LocalTime.parse(input.next(), DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println("Enter work end time(HH:mm):");
        LocalTime wordEndTime = LocalTime.parse(input.next(), DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println("Enter notes:");
        String notes = input.next();

        persons.add(new Person(
                firstname,
                lastName,
                birthDate,
                gender,
                salary,
                new Address(streetAndHouseNumber,
                            zipCodeAndCity,
                            country),
                phoneNumber,
                eMailAddress,
                jobTitle,
                department,
                workStartTime,
                wordEndTime,
                notes));
    }

    public static void deletePerson(Scanner input){
        System.out.println("Enter id of person(negative number to cancel):");
        int id = input.nextInt();
        if(id < 0)return;


    }
}
