package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice(services, customer);
        this.workstage = workstage;
    };

    //Diskon dihitung per harga service
    // public static double calculateReservationPrice(List<Service> services, Customer customer){
    //     String[] member = {"Silver", "Gold"};
    //     double[] diskon = {0.05, 0.1};

    //     double getDiskon = 0;
    //     double diskonService = 0;
    //     double price = 0;
    //     for (int i = 0; i < member.length; i++) {
    //         price = 0;
    //         if (customer.getMember().getMembershipName().equalsIgnoreCase(member[i])) {
    //             getDiskon = diskon[i];
    //             for (Service service : services) {
    //                 diskonService = service.getPrice() * getDiskon;
    //                 price += service.getPrice() - diskonService;     
    //                 
    //             }   
    //         } else if (customer.getMember().getMembershipName().equalsIgnoreCase("none")) {
    //             for (Service service : services) {
    //                 price += service.getPrice();
    //             }
    //         }
    //     }
    //     return price;
    // }
    
    //Diskon untuk seluruh harga service
    public static double calculateReservationPrice(List<Service> services, Customer customer){
        String[] member = {"Silver", "Gold"};
        double[] diskon = {0.05, 0.1};

        double getDiskon = 0;
        double diskonService = 0;
        double price = 0;

        for (Service service : services) {
            if (customer.getMember().getMembershipName().equalsIgnoreCase("none")) {
                price = price + service.getPrice();
            } else {
                for (int i = 0; i < diskon.length; i++) {
                    if (customer.getMember().getMembershipName().equalsIgnoreCase(member[i])) {
                        getDiskon = diskon[i];
                        price += service.getPrice();
                        diskonService = price * getDiskon;
                        price = price - diskonService;
                    }
                }
            }
            
        }
        return price;
    }
}
