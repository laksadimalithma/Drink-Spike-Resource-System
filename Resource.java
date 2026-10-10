//SID:2521157

package com.spikeaware;

/*
 * PARENT CLASS: Resource
 * --------------------------------------------------------------
 * This is the parent class for all resource types in the system.
 *
 * It follows Object-Oriented Programming concepts by:
 * - Using inheritance to avoid code duplication
 * - Providing shared attributes for all resources types
 * - Acting as a reusable template for child classes
 *
 * Child classes:
 * - ResearchResource
 * - AwarenessResource
 * ----------------------------------------------------------------
 */

public class Resource {

    // Unique identifier for each resource (R1, A1, etc.)
    protected String id;

    // Title of the resource
    protected String title;

    // Detailed description of the resource
    protected String description;

    // Status of the resource (Pending / Published)
    protected String status;

    /*
     * Constructor to initialize common resource attributes
     * Ensures every resource has basic required data
     */
    public Resource(String id, String title, String description, String status) {

        this.id = id;
        this.title = title;
        this.description = description;

        // Default status is "Pending" if not provided
        this.status = (status == null || status.isEmpty())
                ?"Pending"
                : status;
    }

    /*
     * Returns resource ID
     */
    public String getId() {
        return id;
    }

    /*
     * Returns current status of resource
     */
    public String getStatus() {
        return status;
    }

    /*
     * Updates resource status (e.g., Pending → Published)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * Returns title of the resource
     */
    public String getTitle() {
        return title;
    }

    /*
     * Returns description of the resource
     */
    public String getDescription() {
        return description;
    }

    /*
     * Displays basic resource information
     * This method is overridden in child classes for extra details
     */
    public void display() {

        System.out.println("\n----------------------------");
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
    }

    /*
     * Converts object to readable string format
     * Used mainly for searching functionality
     */
    @Override
    public String toString() {
        return title + " " + description;
    }
}