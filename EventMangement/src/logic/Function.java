/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import build.Events;
import build.ListOfEvents;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MSIGAMING
 */
public class Function {

    public static ListOfEvents loadData(ListOfEvents managementEvents) {
        managementEvents.setEvents(loadEvents());
        return managementEvents;
    }

    public static void saveEvents(List<Events> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src//data//Events.dat", false))) {
            for (Events events1 : events) {
                writer.write(events1.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static List<Events> loadEvents() {
        List<Events> menuEvents = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src//data//Events.dat"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] compenents = line.split(",");
                if (compenents.length == 6) {
                    String event_ID = compenents[0].trim();
                    String event_Name = compenents[1].trim();
                    Date event_Date = Control.dateParse(compenents[2].trim());
                    String event_Location = compenents[3].trim();
                    int event_NumOfAttendees = Integer.parseInt(compenents[4].trim());
                    String event_Status = compenents[5].trim();
                    menuEvents.add(new Events(event_ID, event_Name, event_Date, event_Location, event_NumOfAttendees, event_Status));
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return menuEvents;
    }

    public static void saveData(ListOfEvents managementEvents) {
        saveEvents(managementEvents.getEvents());
    }

    public static void saveDeleteEvent(Events event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src//data//DeleteEvent.dat", true))) {
            writer.write(event.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static ListOfEvents createEvent(ListOfEvents managementOfEvent) {
        Scanner sc = new Scanner(System.in);
        boolean continueAdding = true;
        do {
            String id_Event = Control.inputEventIdAuto(managementOfEvent.getEvents());
            String name_Event = Control.inputString("Input name event: ");
            Date date_Event = Control.inputDate("Input date event: ");
            String location_Event = Control.inputString("Input location: ");
            int numOfAttendees_Event = Control.inputInt("Input number of attendes: ");
            while (numOfAttendees_Event <= 0) {
                System.err.println("Please enter number of attendes more than 0");
                numOfAttendees_Event = Control.inputInt("Input number of attendes: ");
            }
            String status_Event = Control.inputString("Input status of event (0 or 1): ");
            while (!status_Event.equals("0") && !status_Event.equals("1")) {
                System.err.println("Please enter number 0 and 1");
                System.out.println("0 is Available, 1 is Not available");
                status_Event = Control.inputString("Input status of event (0 or 1): ");
            }
            if (status_Event.equals("1")) {
                System.out.println("Available");
                status_Event = "Available";
            } else {
                System.out.println("Not available");
                status_Event = "Not available";
            }
            List<Events> event = managementOfEvent.getEvents();
            event.add(new Events(id_Event, name_Event, date_Event, location_Event, numOfAttendees_Event, status_Event));
            managementOfEvent.setEvents(event);
            saveData(managementOfEvent);
            System.out.println("Create event successfully. ");
            System.out.print("Do you want to continue creating event (Y/N): ");
            String choice = sc.nextLine().trim().toUpperCase();
            continueAdding = choice.equals("Y");
        } while (continueAdding);
        return managementOfEvent;
    }

    public static ListOfEvents existenceOfEvent(ListOfEvents managementOfEvent) {
        boolean isContinute = true;
        Scanner sc = new Scanner(System.in);
        do {
            boolean check = false;
            String id_Event = Control.inputIdEvent(managementOfEvent.getEvents());
            for (Events event : managementOfEvent.getEvents()) {
                if (event.getEvent_ID().trim().equals(id_Event)) {
                    System.out.println("Exist Event");
                    System.out.println(event);
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("No Event Found");
            }
            System.out.println("Do you want to continue check existence Of other event (Y/N): ");
            String choice = sc.nextLine().trim().toUpperCase();
            isContinute = choice.equals("Y");
        } while (isContinute);
        return managementOfEvent;
    }

    public static ListOfEvents checkEventByLocation(ListOfEvents managementOfEvent) {
        boolean isContinute = true;
        Scanner sc = new Scanner(System.in);
        do {
            String inputLocation = Control.inputString("Input Location: ");
            boolean check = false;
            for (Events event : managementOfEvent.getEvents()) {
                if (event.getEvent_Location().toLowerCase().contains(inputLocation)) {
                    System.out.println(event);
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("No Event Found");
            }
            System.out.println("Do you want to continue check existence Of other event (Y/N): ");
            String choice = sc.nextLine().trim().toUpperCase();
            isContinute = choice.equals("Y");
        } while (isContinute);
        return managementOfEvent;
    }

    public static ListOfEvents UpdateEvent(ListOfEvents managementOfEvent) {
        boolean isContinute = true;
        Scanner sc = new Scanner(System.in);
        do {
            boolean check = false;
            String id_Event = Control.inputIdEvent(managementOfEvent.getEvents());
            if (id_Event != null) {
                List<Events> events = managementOfEvent.getEvents();
                boolean found = true;
                for (int i = 0; i < events.size(); i++) {
                    if (events.get(i).getEvent_ID().equals(id_Event)) {
                        Events event = events.get(i);
                        String name_Event = Control.updateInputString("Input name event (" + event.getEvent_Name() + ")", event.getEvent_Name());
                        event.setEvent_Name(name_Event);
                        
                        Date date_Event = Control.updateInputDate("input date event (" + Control.formatDateToString(event.getEvent_Date()) + ")", event.getEvent_Date());
                        event.setEvent_Date(date_Event);
                        
                        String location_Event = Control.updateInputString("Input location event (" + event.getEvent_Location() + ")", event.getEvent_Location());
                        event.setEvent_Location(location_Event);
                        
                        int attendes_Event = Control.updateInputInt("Input number of person attendence event (" + event.getEvent_NumOfAttendees() + ")", event.getEvent_NumOfAttendees());
                        while (attendes_Event <= 0) {
                            System.err.println("Please enter number of attendes more than 0");
                            attendes_Event = Control.updateInputInt("Input number of person attendence event (" + event.getEvent_NumOfAttendees() + ")", event.getEvent_NumOfAttendees());
                        }
                        event.setEvent_NumOfAttendees(attendes_Event);
                        
                        String status_Event = Control.updateInputString("Input status event (" + event.getEvent_Status() + ")", event.getEvent_Status());
                        while (!status_Event.equals("0") && !status_Event.equals("1")) { // bí quá dùng trick lỏ
                            System.err.println("Please enter number 0 and 1");
                            System.out.println("0 is Available, 1 is Not available");
                            status_Event = Control.updateInputString("Input status event (" + event.getEvent_Status() + ")", event.getEvent_Status());
                        }
                        if (status_Event.equals("1")) {
                            System.out.println("Available");
                            status_Event = "Available";
                        } else {
                            System.out.println("Not available");
                            status_Event = "Not available";
                        }
                        event.setEvent_Status(status_Event);
                        
                        events.set(i, event);
                        break;
                    }
                }
                if (found) {
                    managementOfEvent.setEvents(events);
                    saveData(managementOfEvent);
                    System.out.println("Update Event Successfully.");
                } else {
                    System.out.println("Update Event Fail.");
                }
            }
            System.out.println("Do you want to continue check existence Of other event (Y/N): ");
            String choice = sc.nextLine().trim().toUpperCase();
            isContinute = choice.equals("Y");
        } while (isContinute);
        return managementOfEvent;
    }
    
    public static ListOfEvents deleteEvent(ListOfEvents managemenOfEvent) {
        boolean isContinute = true;
        Scanner sc = new Scanner(System.in);
        do {
            String event_ID = Control.inputIdEvent(managemenOfEvent.getEvents());
            List<Events> events = managemenOfEvent.getEvents();
            boolean found = false;
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getEvent_ID().equals(event_ID)) {
                    Events event_Delete = events.get(i);
                    saveDeleteEvent(event_Delete);
                    event_Delete.setEvent_Name("");
                    saveData(managemenOfEvent);
                    System.out.println("Delete Event successfully.");
                    found = true;
                    break;
                }
                if (!found) {
                    System.out.println("Delete Event Fail");
                }
            }
            System.out.println("Do you want to continue check existence Of other event (Y/N): ");
            String choice = sc.nextLine().trim().toUpperCase();
            isContinute = choice.equals("Y");
        } while (isContinute);
        return managemenOfEvent;
    }
}
