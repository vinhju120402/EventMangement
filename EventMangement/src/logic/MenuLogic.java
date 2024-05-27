/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import build.ListOfEvents;
import java.util.Scanner;

/**
 *
 * @author MSIGAMING
 */
public class MenuLogic {

    Scanner sc = new Scanner(System.in);
    Control control = new Control();
    Menu menu = new Menu();

    public int EnterChoices() {
        int choice = Control.inputInt("Choose a number: ");
        return choice;
    }

    public void mainMenu() {
        ListOfEvents managementEvents = new ListOfEvents();
        managementEvents = Function.loadData(managementEvents);
        boolean isContinute = true;
        while (isContinute) {
            menu.mainMenu();
            switch (EnterChoices()) {
                case 1:
                    managementEvents = Function.createEvent(managementEvents);
                    break;
                case 2:
                    managementEvents = Function.existenceOfEvent(managementEvents);
                    break;
                case 3:
                   managementEvents = Function.checkEventByLocation(managementEvents);
                    break;
                case 4:
                    menuUpdate();
                    break;
                case 5:
//                  managementEvents = Function.saveData(managementEvents);
                    break;
                case 6:
             
                    break;    
                case 7:
                    isContinute = false;
                    break;
                default:
                    System.out.println("Please enter a number from [1]-[7]");
                    break;
            }
        }
    }
    public void menuUpdate() {
        ListOfEvents managementEvents = new ListOfEvents();
        managementEvents = Function.loadData(managementEvents);
        boolean isContinute = true;
        while (isContinute) {
            menu.updateEvent();
            switch (EnterChoices()) {
                case 1:
                    managementEvents = Function.UpdateEvent(managementEvents);
                    break;
                case 2:
                    managementEvents = Function.deleteEvent(managementEvents);
                    break;
                case 3:
                    isContinute = false;
                    break;
                default:
                    System.out.println("Please enter a number from [1]-[3]");
            }
        }

    }
}
