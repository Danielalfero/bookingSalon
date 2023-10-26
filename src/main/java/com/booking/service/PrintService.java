package com.booking.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println();
        System.out.println(title);
        System.out.println();
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        // for (Service service : serviceList) {
        //     result += service.getServiceName() + ", ";
        // }
        for (int i = 0; i < serviceList.size(); i++) {
            if (i == serviceList.size()-1) {
                result += serviceList.get(i).getServiceName() + "";
            } else {
                result += serviceList.get(i).getServiceName() + ", ";
            }
        }
        return result;
    }

    public static void showServices(List<Service> serviceList){
        int num = 1;
        System.out.println("==========================================================");
        System.out.printf("| %-4s | %-8s | %-18s | %-15s |\n", "No", "ID", "Nama", "Harga");
        System.out.println("==========================================================");
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-8s | %-18s | %-15s |\n", num, service.getServiceId(), service.getServiceName(), formatCurency(service.getPrice()));
            num++;
        }
        System.out.println("==========================================================");
    }
    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
     
        System.out.println("===========================================================================================================================================");
        System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n","No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("===========================================================================================================================================");
        for (Reservation reservation : reservationList) {

            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n", num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
        System.out.println("===========================================================================================================================================");
            
    }

    public static void showInProcessReservation(List<Reservation> reservationList){
        int num = 1;
   
        
        System.out.println("===========================================================================================================================================");
        System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n","No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("===========================================================================================================================================");
        for (Reservation reservation : reservationList) {
            
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n", num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
        System.out.println("===========================================================================================================================================");    
        }
    

    public static void showHistoryReservation(List<Reservation> reservationList){
        int num = 1;
        double totalKeuntungan = 0;
        //String resvID = "Rsv-00";
        
        System.out.println("===========================================================================================================================================");
        System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n","No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("===========================================================================================================================================");
        for (Reservation reservation : reservationList) {
            // if(num < 10){
            //     resvID = "Rsv-0" + num;
            // }else if(num >= 10 && num < 100){
            //     resvID = "Rsv-" + num;
            // }
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
                totalKeuntungan += reservation.getReservationPrice();
            }
            //reservation.setReservationId(resvID);
            System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n", num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;

        }
        System.out.println("===========================================================================================================================================");   
        System.out.printf("| %-86s | %-46s |\n", "Total Keuntungan", formatCurency(totalKeuntungan));
        System.out.println("===========================================================================================================================================");   
    }
    

    public static void showInProcessReservation(Reservation reservation){
        int num = 1;
        System.out.println("===========================================================================================================================================");
        System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n","No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("===========================================================================================================================================");
        
        if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
            System.out.printf("| %-4s | %-8s | %-15s | %-50s | %-15s | %-15s | %-10s |\n", num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), formatCurency(reservation.getReservationPrice()), reservation.getEmployee().getName(), reservation.getWorkstage());
            num++;
        }
        
        System.out.println("===========================================================================================================================================");
    }

    public static void showAllCustomer(List<Person> customerList){
        int num = 1;
        Customer cust;
        System.out.println("=======================================================================================");
        System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s |\n", "No", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("=======================================================================================");
        for (Person person : customerList) {
            if (person instanceof Customer) {
                cust = (Customer)person;
                System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s | %-15s |\n", num, cust.getId(), cust.getName(), cust.getAddress(), cust.getMember().getMembershipName(), formatCurency(cust.getWallet()));
                num++;
            }
        }
        System.out.println("=======================================================================================");

    }

    public static void showAllEmployee(List<Person> employeeList){
        int num = 1;
        Employee emp;
        System.out.println("=====================================================================");
        System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s |\n", "No", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("=====================================================================");
        for (Person person : employeeList) {
            
            if (person instanceof Employee) {
                emp = (Employee)person;
                System.out.printf("| %-4s | %-8s | %-11s | %-15s | %-15s |\n", num, emp.getId(), emp.getName(), emp.getAddress(), emp.getExperience());
                num++;
            }
        }
        System.out.println("=====================================================================");

    }

    public static String formatCurency(double uang){        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("Rp#,##0", symbols); 

        return df.format(uang);
    }
}
