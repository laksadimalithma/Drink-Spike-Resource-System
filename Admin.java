//SID:2521157

package com.spikeaware;

import java.io.FileWriter;
import java.io.PrintWriter;
/*
 * ADMIN CLASS
 * ---------------------------------------------------------------------------
 * This class handles system authentication for the administrator.
 *
 * It provides a basic security mechanism using:
 * - Username and password verification
 * - Limited login attempts to prevent brute-force access
 *
 * This increases system security and controls access to sensitive operations
 * such as adding, approving, and deleting resources.
 * ----------------------------------------------------------------------------
 */

public class Admin {

    // Predefined admin credentials
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345@0";

    // Tracks number of failed login attempts
    private int attempts = 0;

    /*
     * LOGIN METHOD
     * ------------------------------------------------
     * Validates user input against stored credentials.
     *
     * Returns:
     * - true  → successful login to the system
     * - false → failed login to the system
     * -------------------------------------------------
     */
    public boolean login(String u, String p) {

        // Safety check (prevents null input errors)
        if (u == null || p == null) {
            System.out.println("Invalid input.Try again.");
            return false;
        }

        // Lock account after 5 failed attempts
        if (attempts >= 5) {
            System.out.println("Account locked due to too many failed attempts.");
            return false;
        }

        // Check correct credentials
        if (u.equals(USERNAME) && p.equals(PASSWORD)) {

            System.out.println("Login successful. Welcome as a Admin.");
            attempts = 0; // reset attempts after success

            // LOG LOGIN
            try (FileWriter fw = new FileWriter("adminlog.txt", true);
                 PrintWriter writer = new PrintWriter(fw)) {

                writer.println("Admin login success");

            } catch (Exception e) {
                System.out.println("Error writing login and activity log.");
            }
            return true;

        } else {

            attempts++;
            System.out.println("Invalid login attempt: " + attempts + "/5");
            return false;
        }
    }
}