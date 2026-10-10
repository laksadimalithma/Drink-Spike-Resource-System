//SID:2521157

package com.spikeaware;

/*
 * CHILD CLASS: AwarenessResource
 * ---------------------------------------------------
 * This class represents awareness campaigns
 * such as safety guides and public awareness content.
 * ---------------------------------------------------
 */

public class AwarenessResource extends Resource {

    // Organization responsible for awareness campaign in uk
    private final String organization;

    // Category of awareness (e.g., Safety, Prevention, Education)
    private final String category;

    // Year of the resource
    private final int year;

    /*
     * Constructor initializes both base and child attributes
     */
    public AwarenessResource(String id, String title, String description, String status,
                             String organization, String category, int year) {

        // Call parent constructor (Resource class)
        super(id, title, description, status);

        this.organization = organization;
        this.category = category;
        this.year = year;
    }
    public int getYear() {
        return year;
    }
    public String getCategory() {
        return category;
    }
    public String getOrganization() {
        return organization;
    }
    /*
     * Displays full awareness resource details
     * Overrides parent display method
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Organization: " + organization);
        System.out.println("Category: " + category);
        System.out.println("Year: " + year);
    }
}