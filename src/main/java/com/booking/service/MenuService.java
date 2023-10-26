package com.booking.service;


import java.util.List;
import java.util.Scanner;


import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ReservationRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = ReservationRepository.getAllReservations();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Tampilkan History Reservation + Total Keuntungan", "Back to main menu"};
        String[] subMenuArrV2 = {"Show Reservation", "Search Reservation", "Edit Reservation","Back to main menu"};
    
        String resvId = "";
        String ganti = "";
        String workstage = "";
                        
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        boolean isGanti = false;
        
        do {
            PrintService.printMenu("Aplikasi Booking Salon", mainMenuArr);
            optionMainMenu = Integer.valueOf(ValidationService.validateInput(input));
            clearScreen();
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(ValidationService.validateInput(input));
                        clearScreen();
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan recent reservation
                                System.out.println("Show Recent Reservation\n");
                                PrintService.showRecentReservation(reservationList);
                                backToSubMenu = false;
                                break;
                            case 2:
                                // panggil fitur tampilkan semua customer
                                System.out.println("Show All Customer\n");
                                PrintService.showAllCustomer(personList);
                                break;
                            case 3:
                                // panggil fitur tampilkan semua employee
                                System.out.println("Show All Employee\n");
                                PrintService.showAllEmployee(personList);
                                break;
                            case 4:
                                // panggil fitur tampilkan history reservation + total keuntungan
                                System.out.println("Show History Reservation\n");
                                PrintService.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                    // panggil fitur menambahkan reservation
                    ReservationService.createReservation(personList, serviceList, reservationList);
                    break;
                case 3:
                    // panggil fitur mengubah workstage menjadi finish/cancel
                    do {
                        
                        PrintService.printMenu("Complete/cancel reservation", subMenuArrV2);
                        optionSubMenu = Integer.valueOf(ValidationService.validateInput(input));
                        clearScreen();
                        // Sub menu - menu 2
                        switch (optionSubMenu) {
                            case 1:
                                // panggil fitur tampilkan in process reservation
                                System.out.println("Show In Process Reservation\n");
                                PrintService.showInProcessReservation(reservationList);
                                backToSubMenu = false;
                                break;
                            case 2:
                                // panggil fitur pencarian in process reservation 
                                System.out.println("Search In Process Reservation\n");
                                PrintService.showInProcessReservation(reservationList);
                                System.out.println("Silahkan Masukan Reservation Id (0 untuk kembali) : ");
                                resvId = ValidationService.validateReservationByReservationId(reservationList, input);
                                if (resvId.equalsIgnoreCase("0")) {
                                    backToSubMenu = true;
                                    break;
                                }
                                PrintService.showInProcessReservation(ReservationService.getReservationByReservationId(reservationList, resvId));
                                break;
                            case 3:
                                // panggil fitur edit in process reservation
                                do {
                                    PrintService.showInProcessReservation(reservationList);
                                    System.out.println("Ingin Mengganti Workstage (Y/T)?");
                                    ganti = input.nextLine();
                                    if (ganti.equalsIgnoreCase("Y")) {
                                        isGanti = false;
                                        System.out.println("Silahkan Masukan Reservation Id : ");
                                        resvId = ValidationService.validateReservationByReservationId(reservationList, input);
                                        System.out.println("Reservation Id yang ingin anda ganti adalah " + resvId);
                                        System.out.println("Silahkan Masukan Workstage : ");
                                        workstage = input.nextLine();
                                        ReservationService.editReservationWorkstage(reservationList ,ReservationService.getReservationByReservationId(reservationList, resvId), workstage);
                                    } else if (ganti.equalsIgnoreCase("T")){
                                        System.out.println("Edit Workstage Selesai!");
                                        isGanti = true;
                                        backToSubMenu = false;
                                    } else {
                                        System.out.println("Inputan tidak sesuai, silahkan ulangi!!!");
                                        isGanti = false;
                                    }
                                } while (!isGanti);
                                break;
                            case 0:
                                backToSubMenu = true;
                        }
                    } while (!backToSubMenu);
                    break;
                case 0:
                    backToMainMenu = true;
                    break;
            }
        } while (!backToMainMenu);
	}

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
