//SID:2521157

package com.spikeaware;

import java.util.Scanner;

/*
 * MAIN APPLICATION  CLASS
 * -----------------------------------------------------
 * Handles all user interactions and system navigations.
 * Acts as the UI layer between user and system logic.
 * -----------------------------------------------------
 */

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Database db = new Database();
        Admin admin = new Admin();

        // Load saved data when start the program
        db.loadFromFile();
        db.loadAnalytics();

        int choice = 0;

        /*
         * MAIN MENU LOOP
         * Runs continuously until user exits system
         */
        do {

            System.out.println("\n====================================");
            System.out.println("|              UK                  |");
            System.out.println("|DRINK SPIKING RESOURCE HUB SYSTEM |");
            System.out.println("|           MAIN MENU              |");
            System.out.println("====================================");
            System.out.println("1. View Resources");
            System.out.println("2. Search Resources");
            System.out.println("3. Filter Resources");
            System.out.println("4. Admin Login");
            System.out.println("0. Exit the system");

            try {
                System.out.print("\nEnter your option: ");
                choice = sc.nextInt();
                sc.nextLine(); // clear buffer
            } catch (Exception e) {
                System.out.println("Invalid input. Enter numbers only.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    // Show published resources only
                    db.viewResources();
                    break;

                case 2:
                    // Search system resources by keyword
                    System.out.print("Enter keyword: ");
                    db.search(sc.nextLine());
                    break;

                case 3:
                    // Filter by year to search resources
                    System.out.print("Enter year (0 for all): ");
                    db.filter(sc.nextInt());
                    sc.nextLine();
                    break;

                case 4:
                    // Admin authentication
                    System.out.print("Username: ");
                    String u = sc.nextLine();

                    System.out.print("Password: ");
                    String p = sc.nextLine();

                    if (admin.login(u, p)) {
                        adminMenu(sc, db);
                    } else {
                        System.out.println("Login failed.Try again.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

        System.out.println("\nThank you for using the system.");
    }

    /*
     * ADMIN MAIN MENU
     * Only accessible after successful login by enter correct username and password
     */
    public static void adminMenu(Scanner sc, Database db) {

        int choice = 0;

        do {
            System.out.println("\n-----------------------------");
            System.out.println("----- SYSTEM ADMIN MENU -----");
            System.out.println("-----------------------------");
            System.out.println("1. Add Research Resource");
            System.out.println("2. Add Awareness Resource");
            System.out.println("3. Approve Resource");
            System.out.println("4. Delete Resource");
            System.out.println("5. Analytics");
            System.out.println("0. Logout");

            try {
                System.out.print("\nEnter your option: ");
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    // Research Resource (R1, R2...)
                    String rId = db.generateResearchId();

                    System.out.println("Generated ID: " + rId);

                    System.out.print("Title: ");
                    String t = sc.nextLine();

                    System.out.print("Description: ");
                    String d = sc.nextLine();

                    System.out.print("Author: ");
                    String a = sc.nextLine();

                    System.out.print("Year: ");
                    int y = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Journal: ");
                    String j = sc.nextLine();

                    db.addResource(new ResearchResource(rId, t, d, "Pending", a, y, j));
                    break;

                case 2:
                    // Awareness Resource (A1, A2...)
                    String aId = db.generateAwarenessId();

                    System.out.println("Generated ID: " + aId);

                    System.out.print("Title: ");
                    String t2 = sc.nextLine();

                    System.out.print("Description: ");
                    String d2 = sc.nextLine();

                    System.out.print("Organization: ");
                    String org = sc.nextLine();

                    System.out.print("Category: ");
                    String cat = sc.nextLine();

                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();

                    db.addResource(new AwarenessResource(aId, t2, d2, "Pending", org, cat, year));
                    break;

                case 3:
                    System.out.print("Enter Resource ID: ");
                    db.approve(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter Resource ID: ");
                    db.delete(sc.nextLine());
                    break;

                case 5:
                    db.analytics();
                    break;

                case 0:
                    System.out.println("Logging out the system...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }
}