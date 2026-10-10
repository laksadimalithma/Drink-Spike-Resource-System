Student ID: 2521157
Module- MOD003484
Assessment: 010-2 Component Design & Implementation

1.Project Name

Drink Spiking Awareness Resource Hub System

2.Project Description

The Java-based console application is intended for the management of awareness and research materials on the issue of preventing drink spiking. This application enables users to browse, search, and filter available publications, and provides the administrators with secure success for managing the resources through their log-in credentials.
This system serves the purpose of facilitating public safety awareness through well-organised educational content.

3.Features of the System

User Features
-Resource viewing
-Keyword searching for resources
-Filtering research resources based on year

Admin Features
-Secure login system
-Adding resources
-Approval or deletion of resources
-System analytics

System Features
-File storage
-Automatic ID generation
-Validation through try-catch statements
-Loading of persistent data

4.Installation and Set-Up of System

- Load project in IntelliJ IDEA IDE
-Open 2521157 folder 
-Verify all files are contained in package
     (com.spikeaware)
-Right click src select mark directory as and click sources root
-Execute
     (MainApp.java file)
-The System runs in console mode free from any errors

5.How to operate the System

-Navigate menu options using numerical numbers
-Admin login needed for changes
-Automatic data saving in file
-Published resources available to users

6.OOP Concepts Used 

-Encapsulation – Private/Protected variables
-Inheritance – Resource – Child classes
-Polymorphism – Overridden display ()
-Modularity – Separated Class design

7.System Architecture

-Resource – Base class
-Research Resource – Academic information
-Awareness Resource - Awareness campaigns
-Database – all operations management
-Admin – Login authentication
-Main App – UI controller


8.Data Storage

The system utilises file-based storage for persistent storage of data (resources.txt) 
Format – Type, ID, Description, Status, Author, Year, Journal, Organisation
	Data Persistency
	No Data loss upon exit from program

9.Security and Important Design Decisions

-Username/password authentication
-Only 5 login attempts allowed
-User cannot login after several failed attempts
-IDS generated automatically
-Pending/Published feature manages login attempts
-Try-catch mechanism prevents crashes in the system
-Administrators’ authentication ensures security of the system

10.Limitations

-System is console based
-System does not have any SQL database
-System does not provide any enhanced level password security
-Basic system analytics

11.Future Improvements

-Develop GUI based on JAVAFX
-Include SQL database in the system
-Password hashing algorithm
-User roles management
-Improve analytics dashboard

12.Conclusion and summary

The present version of the spike Awareness Resource Management Hub System illustrates excellent OOP concepts and design principles through its well-designed java application for managing awareness resources effectively.
