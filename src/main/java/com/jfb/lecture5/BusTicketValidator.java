package com.jfb.lecture5;

import com.jfb.lecture5.model.BusTicket;

import java.time.LocalDate;

public class BusTicketValidator {

    public static int numberOfTypeViolations;
    public static int numberOfStartDateViolations;
    public static int numberOfPriceViolations;
    public static int numberOfValidTickets;
    public static String mostPopularViolation;

    public static void validateTicket(BusTicket ticket) {
        if (validateType(ticket) && validateStartDate(ticket) && validatePrice(ticket)) {
            numberOfValidTickets++;
        }
    }

    public static boolean validateType(BusTicket ticket) {
        String ticketType = ticket.getTicketType();
        if (ticketType == null) {
            numberOfTypeViolations++;
            return false;
        }

        if (ticketType.equals("DAY") || ticketType.equals("WEEK") ||
                ticketType.equals("MONTH") || ticketType.equals("YEAR")) {
            return true;
        } else {
            numberOfTypeViolations++;
            return false;
        }
    }

    public static boolean validateStartDate(BusTicket ticket) {
        String startDateStr = ticket.getStartDate();
        if (startDateStr == null || startDateStr.trim().isEmpty()) {
            numberOfStartDateViolations++;
            return false;
        }

        LocalDate ticketDate;
        try {
            ticketDate = LocalDate.parse(startDateStr);
        } catch (NullPointerException e) {
            numberOfStartDateViolations++;
            return false;
        }

        String ticketType;
        try {
            ticketType = ticket.getTicketType();
        } catch (NullPointerException e) {
            numberOfTypeViolations++;
            return false;
        }
        if (ticketType == null) {
            numberOfTypeViolations++;
            return false;
        }

        if ((ticketType.equals("DAY") || ticketType.equals("WEEK") ||
                ticketType.equals("YEAR")) && ticketDate.isAfter(LocalDate.now())) {
            numberOfStartDateViolations++;
            return false;
        }

        return true;
    }

    public static boolean validatePrice(BusTicket ticket) {
        int price;
        String priceStr = ticket.getPrice();
        if (priceStr == null || priceStr.trim().isEmpty()) {
            numberOfPriceViolations++;
            return false;
        }

        try {
            price = Integer.parseInt(ticket.getPrice());
        } catch (NumberFormatException e) {
            numberOfPriceViolations++;
            return false;
        }

        if ((price == 0) || (price % 2 != 0)) {
            numberOfPriceViolations++;
            return false;
        }

        return true;
    }
}
