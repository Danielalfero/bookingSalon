package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
    public static String validateInput(Scanner input){
        String regex = "[0-9]+";
        String userInput = "";
        do {
            userInput = input.nextLine();
            if(!userInput.matches(regex)){
                System.out.println("Inputan tidak sesuai, silahkan ulangi!!!");
            }
        } while (!userInput.matches(regex));
        return userInput;
    }

    public static String validateCustomerByCustomerId(List<Person> allPerson, Scanner input){
        String userInput = "";
        boolean corect = false;
        do {
            userInput = input.nextLine();
            for (Person person : allPerson) {
                if (person instanceof Customer) {
                    if (userInput.equals(person.getId())) {
                        corect = true;
                    }
                }
            }
            if(!corect){
                System.out.println("Customer yang dicari tidak tersedia");
            }
        } while (!corect);
        return userInput;
    }

    public static String validateEmployeeByEmployeeId(List<Person> allPerson, Scanner input){
        String userInput = "";
        boolean corect = false;
        do {
            userInput = input.nextLine();
            for (Person person : allPerson) {
                if (person instanceof Employee) {
                    if (userInput.equals(person.getId())) {
                        corect = true;
                    }
                }
            }
            if(!corect){
                System.out.println("Employee yang dicari tidak tersedia");
            }
        } while (!corect);
        return userInput;
    }

    public static String validateReservationByReservationId(List<Reservation> allReservations, Scanner input){
        String userInput = "";
        boolean corect = false;
        do {
            userInput = input.nextLine();
            for (Reservation reservation : allReservations) {
                if (userInput.equals(reservation.getReservationId()) && reservation.getWorkstage().equalsIgnoreCase("In process")) {
                    corect = true;
                }
            }
            if (userInput.equalsIgnoreCase("0")) {
                corect = true;
            }
            if(!corect){
                System.out.println("Reservation yang dicari tidak tersedia");
            }
        } while (!corect);
        return userInput;
    }

    public static String validateServiceByServiceId(List<Service> allServices, Scanner input){
        String userInput = "";
        boolean corect = false;
        do {
            userInput = input.nextLine();
            for (Service service : allServices) {
                if (userInput.equals(service.getServiceId())) {
                    corect = true;
                }
            }
            if(!corect){
                System.out.println("Service yang dicari tidak tersedia");
            }
        } while (!corect);
        return userInput;
    }

    //Validasi apakah service sudah dipilih atau belum
    public static String validateService(List<Service> allServices, List<Service> servicesList, Scanner input){
        String userInput = "";

        boolean isFound = false;
        boolean isEmpty = false;

        do {
            userInput = validateServiceByServiceId(allServices, input);    
            if (servicesList.isEmpty()) {
                isEmpty = true;
            } else if (servicesList.size() == allServices.size()) {
                isEmpty = true;
            } else {
                for (Service service : servicesList) {
                    if (service.getServiceId().equals(userInput)) {
                        isFound = true;
                        System.out.println("Service sudah dipilih");
                        break;
                    } else {
                        isFound = false;
                        isEmpty = true;
                    }
                }
                
            }
        } while (!isEmpty || isFound);

        return userInput;
    }
}
