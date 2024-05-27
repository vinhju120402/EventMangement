/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import build.Events;
import build.ListOfEvents;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author MSIGAMING
 */
public class Control {

    public static boolean checkEventID(String input) {
        Pattern p;
        p = Pattern.compile("^[E]\\d{3}$");
        return p.matcher(input).matches();
    }

    public static String inputEventIdAuto(List<Events> events) {
        int maxID = 0;
        for (Events event : events) {
            String evenID = event.getEvent_ID();
            int id = Integer.parseInt(evenID.substring(1));
            if (id > maxID) {
                maxID = id;
            }
        }
        int newID = maxID + 1;

        return "E" + String.format("%03d", newID);
    }

    public static String inputString(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        String input = sc.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Input is invalid.");
            return inputString(str);
        }
        return input;
    }

    public static Date inputDate(String content) {
        String dateStr = inputString(content);
        if (!checkDate(dateStr)) {
            System.err.println("Invalid Date Format. Please retype.");
            return inputDate(content);
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            System.err.println("Invalid Date format. Please retype.");
            return inputDate(content);
        }
    }

    public static boolean checkDate(String input) {
        Pattern p;
        p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        return p.matcher(input).matches();
    }

    public static String formatDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date dateParse(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static boolean checkInt(String inp) {
        try {
            Integer.parseInt(inp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int inputInt(String str) {
        Scanner sc = new Scanner(System.in);
        System.out.print(str);
        String choice = sc.nextLine();
        while (!checkInt(choice)) {
            System.err.println("Please enter an integer number!!");
            System.out.print(str);
            choice = sc.nextLine();
        }
        return Integer.parseInt(choice);
    }

    public static String inputIdEvent(List<Events> events) {
        Scanner sc = new Scanner(System.in);
        String idExistenceEvent;
        do {
            System.out.print("Input ID of event: ");
            idExistenceEvent = sc.nextLine().trim();
            if (!checkEventID(idExistenceEvent)) {
                System.out.println("Wrong Format.");
            } else {
                boolean hasID = false;
                for (Events event : events) {
                    if (event.getEvent_ID().equals(idExistenceEvent)) {
                        System.out.println("Found Id.");
                        hasID = true;
                        break;
                    }
                }
                if (!hasID) {
                    System.out.println("Event ID does not exsit.");
                }
            }
        } while (!checkEventID(idExistenceEvent));
        return idExistenceEvent;
    }

    public static String updateInputString(String prompt, String defaultValue) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        String input = sc.nextLine();
        return input.isEmpty() ? defaultValue : input;
    }

    public static int updateInputInt(String prompt, int defaultValue) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        String input = sc.nextLine();
        return input.isEmpty() ? defaultValue : Integer.parseInt(input);
    }

    public static Date updateInputDate(String prompt, Date defaultValue) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        System.out.print(prompt);
        String input = sc.nextLine().trim();

        try {
            return input.isEmpty() ? defaultValue : dateFormat.parse(input);
        } catch (ParseException e) {
            System.out.println("Wrong Format. Please Retype.");
            return updateInputDate(prompt, defaultValue);
        }
    }
}
