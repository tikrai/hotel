# hotel
3DS recruitment task

-----------------------

Write a program to register guests in a hotel.

The program must be implemented in Java. Try to avoid open-source frameworks and stick with Core Java.
Requirements:

* Hotel:
   - has an even number of single rooms, e.g. 6
   - all rooms have a number: from 1 to 6
   - no more than one guest lives in the room
   - half of the rooms are in standard and half in business class:
     - the price of a simple room is 20 eur (per room regardless of the time)
     - the price of a business class room is 50% higher (implemented through inheritance)
   - room details:
     - number: from 1 to 6
     - the price
   - guest details:
     - name
     - surname
  - it must be easy to change the number of rooms and the price 

* Possible actions:
   1. Guest registration.
      - required information: name and surname of the guest
      - the system itself selects the ship's room (any regardless of class)
      - the user is informed about successful or unsuccessful registration (e.g. there are no empty rooms).
   2. Check-out of the guest.
   3. Review of room availability:
      - occupied rooms and who lives in them are listed.
   4. Room occupancy history and status:
      - the names and surnames of all guests who have stayed are displayed in order
      - the status of the room is displayed: free or occupied.
   5. Room occupancy report:
      - shows the room number, how many times it was occupied and how much profit it brought
      - rooms are sorted according to the amount of occupancy from MAX to MIN 

* Store domains in Java data structures (do not use a database) 

* You can use the user interface of your choice (choose only one):
   - Text mode: command line.
   - Graphical interface: Java Swing or awt.
   - Do not use the WEB graphical interface 

* Additional but not required functionality:
   - Save the consolidated data, so that the data does not have to be consolidated again after transferring the program
