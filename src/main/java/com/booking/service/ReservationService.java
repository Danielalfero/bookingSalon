package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {

    private static Scanner input = new Scanner(System.in);
    public static void createReservation(List<Person> allPerson, List<Service> serviceList, List<Reservation> reservationsList){
        List<Service> serviceCustomerList = new ArrayList<>();

        Reservation resv = null;
        Customer cust = null;
        Employee emp = null;
        Service serv = null;

        String inputCustID = "";
        String inputEmpID = "";
        String inputServID = "";
        String pilihLagi = "";

        boolean isPilihLagi = false;
        boolean corect = false;

        double totalPrice = 0;

        System.out.println();
        System.out.println("Create Reservation");
        System.out.println();

        //Menampilkan list customer
        PrintService.showAllCustomer(allPerson);
        System.out.println("Silahkan Masukan Customer Id : ");
        inputCustID = ValidationService.validateCustomerByCustomerId(allPerson, input);
        cust = (Customer) getCustomerByCustomerId(allPerson, inputCustID);

        
        //Menampilkan list employee
        System.out.println();
        PrintService.showAllEmployee(allPerson);
        System.out.println("Silahkan Masukan Employee Id : ");
        inputEmpID = ValidationService.validateEmployeeByEmployeeId(allPerson, input);
        emp = (Employee) getEmployeeByEmployeeId(allPerson, inputEmpID);

        
        //Menampilkan list service
        System.out.println();
        PrintService.showServices(serviceList);
        do {
            System.out.println("Silahkan Masukan Service Id : ");
            inputServID = ValidationService.validateService(serviceList,serviceCustomerList , input);
            
            if (!(serviceCustomerList.size() == serviceList.size())) {
                do {
                    System.out.println("Ingin pilih service yang lain (Y/T)?");
                    pilihLagi = input.nextLine();
                    if (pilihLagi.equalsIgnoreCase("Y")) {
                        corect = true;
                        isPilihLagi = true;                    
                    } else if (pilihLagi.equalsIgnoreCase("T")) {
                        corect = true;
                        isPilihLagi = false;
                    } else {
                        System.out.println("Inputan tidak sesuai, silahkan ulangi!!!");
                        corect = false;
                    }
                } while (!corect);
            } else {
                isPilihLagi = false;
            }
            serv = getServiceByServiceId(serviceList, inputServID);
            serviceCustomerList.add(serv);
            totalPrice = Reservation.calculateReservationPrice(serviceCustomerList, cust);
            cust.setWallet(updateWallet(cust.getWallet(), serv.getPrice()));
        } while (isPilihLagi);
        System.out.println("Booking Berhasil!");
        System.out.println("Total Biaya Booking : " + PrintService.formatCurency(totalPrice));

        //Membuat reservation
        resv = new Reservation("Rsv-00", cust, emp, serviceCustomerList, 0, "In Process");
        resv.setReservationPrice(Reservation.calculateReservationPrice(serviceCustomerList, cust));
        reservationsList.add(resv);
        int num = 0;
        String resvID = "";
        for (Reservation reservation : reservationsList) {
            num++;
            if(num < 10){
                resvID = "Rsv-0" + num;
            }else if(num >= 10 && num < 100){
                resvID = "Rsv-" + num;
            }
            reservation.setReservationId(resvID);
        }
    }

    public static Person getCustomerByCustomerId(List<Person> customerList, String inputCustID){
        for (Person person : customerList) {
            if (person instanceof Customer && person.getId().equals(inputCustID)) {
                return person;
            }
        }
        return null;
    }

    public static Person getEmployeeByEmployeeId(List<Person> employeeList, String inputEmpID){
        for (Person person : employeeList) {
            if (person instanceof Employee && person.getId().equals(inputEmpID)) {
                return person;
            }
        }
        return null;
    }

    public static Service getServiceByServiceId(List<Service> servicesList, String inputServID){
        for (Service service : servicesList) {
            if (service.getServiceId().equals(inputServID)) {
                return service;
            }
        }
        return null;
    }

    public static void editReservationWorkstage(List<Reservation> allReservation, Reservation reservation, String workstage){
        for (Reservation reserv : allReservation) {
            if (reserv.getReservationId().equalsIgnoreCase(reservation.getReservationId())) {
                reserv.setWorkstage(workstage);
            }
        }
    }

    public static List<Reservation> searchInProccessReservation(List<Reservation> listReservations, String resvID){
        return listReservations.stream()
            .filter(reservation -> reservation.getReservationId().equals(resvID))
            .collect(Collectors.toList());
    }

    public static Reservation getReservationByReservationId(List<Reservation> listReservations, String resvID){
        for (Reservation reservation : listReservations) {
            if(reservation.getReservationId().equals(resvID)){
                return reservation;
            }
        } 
        return null;
    }

    public static double updateWallet(double wallet, double price){
        double newWallet = wallet - price;
        return newWallet;
    }
    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
