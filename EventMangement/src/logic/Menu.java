/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author MSIGAMING
 */
public class Menu {
    public  void mainMenu(){
        System.out.println("====== Event Management ======");
        System.out.println("[1] Create a new event.");
        System.out.println("[2] Check if an event exists.");
        System.out.println("[3] Search for event information by location.");
        System.out.println("[4] Update event.");
        System.out.println("[5] Save events to a file.");
        System.out.println("[6] Print the list of events from the file.");
        System.out.println("[7] Exit.");
        System.out.println("=====================================");
    }
    
    public void updateEvent(){
        System.out.println("========= Update event ==========");
        System.out.println("[1] Update event details.");
        System.out.println("[2] Delete event.");
        System.out.println("[3] Back to Main Menu.");
        System.out.println("======================================");
    }
}
