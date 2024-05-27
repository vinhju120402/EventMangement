/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package build;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author MSIGAMING
 */
public class ListOfEvents implements Serializable{
    private List<Events> events;

    public ListOfEvents() {
        events = new ArrayList<>();
    }

    public ListOfEvents(List<Events> events) {
        this.events = events;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }
}
