package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.jfb.lecture5.BusTicketValidator.*;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int totalTickets = 0;
        String filePath = "src/main/resources/ticketData.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replace("“", "\"").replace("”", "\"");

                BusTicket busTicket = new ObjectMapper().readValue(line, BusTicket.class);

                validateTicket(busTicket);

                totalTickets++;

                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        if (numberOfPriceViolations > numberOfTypeViolations && numberOfPriceViolations > numberOfStartDateViolations) {
            mostPopularViolation = "Price Violation";
        } else if (numberOfTypeViolations > numberOfPriceViolations && numberOfTypeViolations > numberOfStartDateViolations) {
            mostPopularViolation = "Type Violation";
        } else {
            mostPopularViolation = "Start Date Violation";
        }

        System.out.println("Total = " + totalTickets);
        System.out.println("Valid = " + numberOfValidTickets);
        System.out.println("Most popular violation = " + mostPopularViolation);

        System.out.println("Type violations: " + numberOfTypeViolations);
        System.out.println("Start date violations: " + numberOfStartDateViolations);
        System.out.println("Price violations: " + numberOfPriceViolations);

    }
}
