// File: ZooEntrance.java
import java.util.HashSet;
import java.util.Set;

public class ZooEntrance implements ValidateInterface {
    private boolean isOpen = false;
    private final Set<String> validTickets = new HashSet<>();
    private final Set<String> usedTickets = new HashSet<>();
    private TicketInterface ticketInterface; // Required interface
    
    public ZooEntrance(TicketInterface ticketInterface) {
        this.ticketInterface = ticketInterface;
    }
    
    public void turnOn() {
        isOpen = true;
        System.out.println("Zoo Entrance is now open.");
    }
    
    public void turnOff() {
        isOpen = false;
        System.out.println("Zoo Entrance is now closed.");
    }
    
    @Override
    public boolean validateTicket(String ticketId) {
        if (!isOpen) {
            System.out.println("Cannot validate tickets. Zoo Entrance is closed.");
            return false;
        }
        
        if (usedTickets.contains(ticketId)) {
            System.out.println("Ticket " + ticketId + " has already been used.");
            return false;
        }
        
        // For demonstration purposes, we'll consider tickets valid if they match our pattern
        boolean isValid = ticketId.matches("[A-Z]-\\d+-\\d+");
        
        if (isValid) {
            validTickets.add(ticketId);
            System.out.println("Ticket " + ticketId + " is valid.");
        } else {
            System.out.println("Ticket " + ticketId + " is invalid.");
        }
        
        return isValid;
    }
    
    @Override
    public void markAsUsed(String ticketId) {
        if (validTickets.contains(ticketId)) {
            usedTickets.add(ticketId);
            System.out.println("Ticket " + ticketId + " has been marked as used.");
            System.out.println("Welcome to the Zoo!");
        }
    }
    
    // Method to receive ticket data from the ticket interface
    public String getTicketData() {
        return ticketInterface.receiveTicketData();
    }
    
    // Method to send ticket data through the ticket interface
    public void setTicketData(String ticketId, String ticketType) {
        ticketInterface.sendTicketData(ticketId, ticketType);
    }
}