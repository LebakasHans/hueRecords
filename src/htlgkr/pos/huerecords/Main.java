package htlgkr.pos.huerecords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    private static List<Person> persons = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        boolean needToSave = false;
        int choice;
        do{
            choice = showMenu();
            switch (choice){
                case 1:
                    addNewPerson();
                    needToSave = true;
                    break;
                case 2:
                    editPerson();
                    needToSave = true;
                    break;
                case 3:
                    deletePerson();
                    needToSave = true;
                    break;
                case 4:
                    List<Person> matches = searchPerson();
                    for (Person p : matches) {
                        System.out.println(p.toString());
                    }
                    break;
                case 5:
                    Person foundPerson = getPersonWithId();
                    if (foundPerson != null){
                        System.out.println(foundPerson);
                    }
                    break;
                case 6:
                    analytics();
                    break;
            }
            if (needToSave){
                try {
                    MyFileFormatWriter myFileFormatWriter = new MyFileFormatWriter(persons, new FileOutputStream("savedPersons.txt"));
                    myFileFormatWriter.save();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }while (choice != 7);
    }

    public static int showMenu(){
        System.out.println("What would you like to do");
        System.out.println("1. Add new person");
        System.out.println("2. Edit person");
        System.out.println("3. Delete person");
        System.out.println("4. Search for persons");
        System.out.println("5. Get information of a person");
        System.out.println("6. Go to Analytics");
        System.out.println("7. End");
        return input.nextInt();
    }

    public static void addNewPerson(){
        System.out.println("Enter firstname:");
        String firstname = input.next();

        System.out.println("Enter lastname:");
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

    public static void editPerson(){
        System.out.println("Enter the id of the person(negative number to cancel):");
        int id = input.nextInt();
        if(id <= 0 ){
            Person originalPerson;
            int indexOfPersonToEdit;
            List<Person> personToEditInList = persons.stream()
                    .filter(person -> person.id() == id)
                    .collect(Collectors.toList());
            if (personToEditInList.size() > 0){
                originalPerson = personToEditInList.get(0);
                indexOfPersonToEdit = persons.indexOf(originalPerson);
            }else {
                System.err.println("There is no Person with this Id");
                return;
            }

            showEditMenu();
            int choice = input.nextInt();
            Person editedPerson;
            switch(choice){
                case 1:
                    System.out.println("Enter new Firstname:");
                    String newFirstname = input.next();
                    editedPerson = originalPerson.getPersonWithSetFirstname(newFirstname);
                    break;
                case 2:
                    System.out.println("Enter new Lastname:");
                    String newLastname = input.next();
                    editedPerson = originalPerson.getPersonWithSetLastname(newLastname);
                    break;
                case 3:
                    System.out.println("Enter new Birthdate(dd.MM.yyyy):");
                    LocalDate newBirthDate = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                    editedPerson = originalPerson.getPersonWithSetBirthdate(newBirthDate);
                    break;
                case 4:
                    System.out.println("Enter new Gender:");
                    String newGender = input.next();
                    editedPerson = originalPerson.getPersonWithSetGender(newGender);
                    break;
                case 5:
                    System.out.println("Enter new Salary:");
                    double newSalary = input.nextDouble();
                    editedPerson = originalPerson.getPersonWithSetSalary(newSalary);
                    break;
                case 6:
                    System.out.println("Enter new Street and House number:");
                    String newStreetAndHouseNumber = input.next();

                    System.out.println("Enter new Zip Code and City:");
                    String newZipCodeAndCity = input.next();

                    System.out.println("Enter new Country:");
                    String newCountry = input.next();
                    Address newAddress = new Address(newStreetAndHouseNumber, newZipCodeAndCity, newCountry);
                    editedPerson = originalPerson.getPersonWithSetAddress(newAddress);
                    break;
                case 7:
                    System.out.println("Enter new Phone Number:");
                    long newPhoneNumber = input.nextLong();
                    editedPerson = originalPerson.getPersonWithSetPhoneNumber(newPhoneNumber);
                    break;
                case 8:
                    System.out.println("Enter new E-Mail Address:");
                    String newEMailAddress = input.next();
                    editedPerson = originalPerson.getPersonWithSetEMailAddress(newEMailAddress);
                    break;
                case 9:
                    System.out.println("Enter new Job Title:");
                    String newJobTitle = input.next();
                    editedPerson = originalPerson.getPersonWithSetJobTitle(newJobTitle);
                    break;
                case 10:
                    System.out.println("Enter new Department:");
                    String newDepartment = input.next();
                    editedPerson = originalPerson.getPersonWithSetDepartment(newDepartment);
                    break;
                case 11:
                    System.out.println("Enter new Working start time(HH:mm):");
                    LocalTime newWorkStartTime = LocalTime.parse(input.next(), DateTimeFormatter.ofPattern("HH:mm"));
                    editedPerson = originalPerson.getPersonWithSetWorkStartTime(newWorkStartTime);
                    break;
                case 12:
                    System.out.println("Enter new Working end time(HH:mm):");
                    LocalTime newWorkEndTime = LocalTime.parse(input.next(), DateTimeFormatter.ofPattern("HH:mm"));
                    editedPerson = originalPerson.getPersonWithSetWorkEndTime(newWorkEndTime);
                    break;
                case 13:
                    System.out.println("Enter new Notes:");
                    String newNotes = input.next();
                    editedPerson = originalPerson.getPersonWithSetNotes(newNotes);
                    break;
                default:
                    System.err.println("Invalid choice");
                    return;
            }
            persons.set(indexOfPersonToEdit, editedPerson);
        }
    }

    private static void showEditMenu() {
        System.out.println("What would you like to change?");
        System.out.println("1. Firstname");
        System.out.println("2. Lastname");
        System.out.println("3. Birthdate");
        System.out.println("4. Gender");
        System.out.println("5. Salary");
        System.out.println("6. Address");
        System.out.println("7. Phone number");
        System.out.println("8. E-Mail address");
        System.out.println("9. Job title");
        System.out.println("10. Department");
        System.out.println("11. Working start time");
        System.out.println("12. Working end time");
        System.out.println("13. Notes");
    }

    public static void deletePerson(){
        System.out.println("Enter the id of the person(negative number to cancel):");
        int id = input.nextInt();
        if(id <= 0 ){
            List<Person> personToDelete = persons.stream()
                    .filter(person -> person.id() == id)
                    .collect(Collectors.toList());
            if (personToDelete.size() > 0){
                persons.remove(personToDelete.get(0));
            }else {
                System.err.println("There is no Person with this Id");
            }
        }
    }

    public static List<Person> searchPerson(){
        System.out.println("Enter the search term:");
        String searchTerm = input.next();
        List<Person> foundPersons = new ArrayList<>();
        try {
        for (Person person : persons) {
            for (Field field : person.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(person);
                if (value.toString().toUpperCase().contains(searchTerm.toUpperCase()) && !foundPersons.contains(person)) {
                    foundPersons.add(person);
                }
            }
        }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (foundPersons.isEmpty()){
            System.err.println("There is no one who matches the search term");
        }

        return foundPersons;
    }

    public static Person getPersonWithId(){
        System.out.println("Enter the id of the person:");
        int id = input.nextInt();
        List<Person> foundPerson = persons.stream()
                .filter(person -> person.id() == id)
                .collect(Collectors.toList());
        if (foundPerson.size() > 0){
            return foundPerson.get(0);
        }else {
            System.err.println("There is no Person with this Id");
        }
        return null;
    }

    public static void analytics(){
        System.out.println("Which analytic would you like to know:");
        System.out.println("1. How many persons have a specific gender");
        System.out.println("2. What is the average age");
        System.out.println("3. What is the highest/lowest/average salary");
        int whichAnalytic = input.nextInt();
        switch (whichAnalytic){
            case 1:
                printGenderCount();
                break;
            case 2:
                printAverageAge();
                break;
            case 3:
                printSalaries();
                break;
            default:
                System.err.println("Invalid choice");
                break;
        }
    }

    private static void printGenderCount() {
        System.out.println("Which gender would you like to know");
        String gender = input.next();
        long count = persons.stream()
                .filter(person -> person.gender().equals(gender))
                .count();
        System.out.println("There are " + count + " people with this gender in the system");
    }

    private static void printAverageAge() {
        double averageAge = persons.stream()
                .mapToInt(person -> Period.between(LocalDate.now(), person.birthDate()).getYears())
                .average()
                .getAsDouble();
        System.out.println("The average age of the people in the system is: " + averageAge);
    }

    private static void printSalaries() {
        if (!persons.isEmpty()) {
            OptionalDouble highestSalary = persons.stream()
                    .mapToDouble(person -> person.salary())
                    .max();
            OptionalDouble lowestSalary = persons.stream()
                    .mapToDouble(person -> person.salary())
                    .min();
            OptionalDouble averageSalary = persons.stream()
                    .mapToDouble(person -> person.salary())
                    .average();
            System.out.println("The highest Salary is: " + highestSalary.getAsDouble());
            System.out.println("The lowes Salary is: " + lowestSalary.getAsDouble());
            System.out.println("The average Salary is: " + averageSalary.getAsDouble());
        }else {
            System.err.println("There is no person in the system");
        }
    }
}