/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build;

import java.util.Date;
import logic.Control;

/**
 *
 * @author MSIGAMING
 */
public class Events {

    private String event_ID;
    private String event_Name;
    private Date event_Date;
    private String event_Location;
    private int event_NumOfAttendees;
    private String event_Status;

    public Events() {
    }

    public Events(String event_ID, String event_Name, Date event_Date, String event_Location, int event_NumOfAttendees, String event_Status) {
        this.event_ID = event_ID;
        this.event_Name = event_Name;
        this.event_Date = event_Date;
        this.event_Location = event_Location;
        this.event_NumOfAttendees = event_NumOfAttendees;
        this.event_Status = event_Status;
    }

    public String getEvent_ID() {
        return event_ID;
    }

    public void setEvent_ID(String event_ID) {
        this.event_ID = event_ID;
    }

    public String getEvent_Name() {
        return event_Name;
    }

    public void setEvent_Name(String event_Name) {
        this.event_Name = event_Name;
    }

    public Date getEvent_Date() {
        return event_Date;
    }

    public void setEvent_Date(Date event_Date) {
        this.event_Date = event_Date;
    }

    public String getEvent_Location() {
        return event_Location;
    }

    public void setEvent_Location(String event_Location) {
        this.event_Location = event_Location;
    }

    public int getEvent_NumOfAttendees() {
        return event_NumOfAttendees;
    }

    public void setEvent_NumOfAttendees(int event_NumOfAttendees) {
        this.event_NumOfAttendees = event_NumOfAttendees;
    }

    public String getEvent_Status() {
        return event_Status;
    }

    public void setEvent_Status(String event_Status) {
        this.event_Status = event_Status;
    }

    @Override
    public String toString() {
        return event_ID + " , " + event_Name + " , " + Control.formatDateToString(event_Date) + " , " + event_Location + " , " + event_NumOfAttendees + " , " + event_Status;
    }

}
