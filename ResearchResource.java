//SID:2521157

package com.spikeaware;

/*
 * CHILD CLASS: ResearchResource
 * -----------------------------------------------------------------------
 * This class represents research-based resources such as academic papers,
 * scientific studies, and reports related to drink spiking awareness.
 *
 * It extends the base Resource class and adds extra academic attributes
 * such as author, year of publication, and journal name.
 * ------------------------------------------------------------------------
 */

public class ResearchResource extends Resource {

    // Name of the author or researcher
    private final String author;

    // Year the research was published
    private final int year;

    // Name of the journal or publication source
    private final String journal;

    /*
     * Constructor initializes both base class and research-specific attributes
     */
    public ResearchResource(String id, String title, String description, String status,
                            String author, int year, String journal) {

        // Call parent class constructor (Resource)
        super(id, title, description, status);

        this.author = author;
        this.year = year;
        this.journal = journal;
    }

    /*
     * Getter method for year,author,journal(used in filtering functionality)
     */
    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public String getJournal() {
        return journal;
    }

    /*
     * Displays full research resource details
     * Overrides parent display method to include extra information
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Journal: " + journal);
    }
}