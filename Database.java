//SID:2521157

package com.spikeaware;

import java.io.*;
import java.util.*;

/*
 * DATABASE CLASS (FINAL DISTINCTION VERSION)
 * --------------------------------------------------
 * This class handles all data operations:
 * - Resource storage (ArrayList)
 * - ID generation (R1 / A1 format)
 * - File persistence (save & load)
 * - Search, filter, approve, delete
 * - Analytics tracking
 *
 * NOTE:
 * System starts clean (no unnecessary error messages)
 * ---------------------------------------------------
 */


public class Database {

    private final ArrayList<Resource> resources = new ArrayList<>();

    private int views = 0;
    private int searches = 0;

    private int researchCounter = 1;
    private int awarenessCounter = 1;

    // ================================
    // ID GENERATION
    // ================================

    public String generateResearchId() {
        return "R" + researchCounter++;
    }

    public String generateAwarenessId() {
        return "A" + awarenessCounter++;
    }

    // ================================
    // ADD RESOURCES
    // ================================

    public void addResource(Resource r) {
        resources.add(r);
        System.out.println("✔ Resource added (Pending Approval)");
        saveToFile();
    }

    // ================================
    // VIEW RESOURCES
    // ================================

    public void viewResources() {

        boolean found = false;

        for (Resource r : resources) {
            if (r.getStatus().equals("Published")) {
                r.display();
                found = true;
            }
        }
            if (found) {
                views++;
                saveAnalytics();

            }

        if (!found) {
            System.out.println("No published resources available.");
        }
    }

    // ================================
    // SEARCH RESOURCES BY KEYWORD
    // ================================

    public void search(String keyword) {

        boolean found = false;
        searches++;
        saveAnalytics();

        System.out.println("\nSearching for: " + keyword);

        for (Resource r : resources) {
            String text = r.getTitle() + " " + r.getDescription();

            if (r.getStatus().equals("Published") &&
                    text.toLowerCase().contains(keyword.toLowerCase())) {
                r.display();
                found = true;
            }
        }
        saveAnalytics();

        if (!found) {
            System.out.println("No results found.");
        }
    }

    // ================================
    // FILTER RESOURCES BY YEAR
    // ================================

    public void filter(int year) {

        boolean found = false;

        System.out.println("\nResources for year: " + year);

        for (Resource r : resources) {

            if (r.getStatus().equals("Published")) {

                if (r instanceof ResearchResource) {
                    ResearchResource rr = (ResearchResource) r;

                    if (rr.getYear() == year) {
                        rr.display();
                        found = true;
                    }
                }

                else if (r instanceof AwarenessResource) {
                    AwarenessResource ar = (AwarenessResource) r;

                    if (ar.getYear() == year) {
                        ar.display();
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No matching results.");
        }
    }

    // ================================
    // APPROVE RESOURCE
    // ================================

    public void approve(String id) {

        for (Resource r : resources) {
            if (r.getId().equalsIgnoreCase(id)) {
                r.setStatus("Published");
                System.out.println("Resource approved");
                logAction("Resource Approved: " +id);
                saveToFile();
                return;
            }
        }

        System.out.println("ID not found.");
    }

    // ================================
    // DELETE RESOURCE
    // ================================

    public void delete(String id) {

        boolean removed = resources.removeIf(r -> r.getId().equalsIgnoreCase(id));

        if (removed) {
            System.out.println("Resource deleted");
            logAction("Resource Deleted: " +id);
            saveToFile();
        } else {
            System.out.println("ID not found.Try again.");
        }
    }

    // ================================
    // ANALYTICS
    // ================================

    public void analytics() {
        System.out.println("\n=====================");
        System.out.println("===== ANALYTICS =====");
        System.out.println("=====================");
        System.out.println("Total Resources: " + resources.size());
        System.out.println("Views: " + views);
        System.out.println("Searches: " + searches);
    }

    // ====================================================
    // SAVE RESOURCES ,ANALYTICS AND ADMIN ACTIVITY TO FILE
    // ====================================================

    public void saveToFile() {

        try (PrintWriter writer = new PrintWriter("resources.txt")) {

            writer.println("Type,ID,Title,Description,Status,Author,Year,Journal,Organization");

            for (Resource r : resources) {

                if (r instanceof ResearchResource) {

                    ResearchResource rr = (ResearchResource) r;

                    writer.println("Research," +
                            rr.getId() + "," +
                            rr.getTitle() + "," +
                            rr.getDescription() + "," +
                            rr.getStatus() + "," +
                            rr.getAuthor() + "," +
                            rr.getYear() + "," +
                            rr.getJournal());

                } else if (r instanceof AwarenessResource ) {

                    AwarenessResource ar = (AwarenessResource) r;

                    writer.println("Awareness," +
                            ar.getId() + "," +
                            ar.getTitle() + "," +
                            ar.getDescription() + "," +
                            ar.getStatus() + ",NA,0,NA," +
                            ar.getOrganization() + "," +
                            ar.getCategory() + "," +
                            ar.getYear());
                }
            }

        } catch (Exception e) {
            // No message (clean system)
        }
    }
    public void saveAnalytics() {
        try (PrintWriter writer = new PrintWriter("analytics.txt")) {
            writer.println("Views:" + views);
            writer.println("Searches:" + searches);
        } catch (Exception e) {
            System.out.println("Error saving analytics.");
        }
    }
    public void logAction(String action) {
        try (FileWriter fw = new FileWriter("adminlog.txt", true);
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println(action);
        } catch (Exception e) {
            System.out.println("Error writing log.");
        }
    }

    // ================================
    // LOAD FILE (FULLY SILENT)
    // ================================

    public void loadFromFile() {

        try {

            File file = new File("resources.txt");

            // If file doesn't exist → do nothing (NO message)
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);

            if (sc.hasNextLine()) sc.nextLine();

            while (sc.hasNextLine()) {

                String[] data = sc.nextLine().split(",");

                String type = data[0];
                String id = data[1];
                String title = data[2];
                String desc = data[3];
                String status = data[4];

                if (type.equals("Research")) {

                    String author = data[5];
                    int year = Integer.parseInt(data[6]);
                    String journal = data[7];

                    resources.add(new ResearchResource(id, title, desc, status, author, year, journal));

                    int num = Integer.parseInt(id.substring(1));
                    if (num >= researchCounter) {
                        researchCounter = num + 1;
                    }

                } else {

                    String org = data[8];
                    String category = data[9];
                    int year = Integer.parseInt(data[10]);

                    resources.add(new AwarenessResource(id, title, desc, status, org, category, year));

                    int num = Integer.parseInt(id.substring(1));
                    if (num >= awarenessCounter) {
                        awarenessCounter = num + 1;
                    }
                }
            }

            sc.close();

        } catch (Exception e) {
            // Completely silent (no error messages)
        }
    }
    public void loadAnalytics() {
        try (Scanner sc = new Scanner(new File("analytics.txt"))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.startsWith("Views:")) {
                    views = Integer.parseInt(line.split(":")[1]);
                }

                if (line.startsWith("Searches:")) {
                    searches = Integer.parseInt(line.split(":")[1]);
                }
            }

        } catch (Exception e) {
            // silent
        }
    }
}