package com.example.bank.bank;

import java.util.ArrayList;

public record Ticket(String purpose, long timems) {
    public static ArrayList<Ticket> ticketList = new ArrayList<>();
    public static void getTicket(String purpose) {
        Ticket t = new Ticket(purpose, System.currentTimeMillis());
        ticketList.add(t);
    }
}