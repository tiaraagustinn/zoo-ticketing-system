import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketBooth implements PurchaseInterface, TicketInterface {
    private boolean isOpen = false;
    private final Map<String, Double> ticketPrices = new HashMap<>();
    private final List<String> issuedTickets = new ArrayList<>();
    private String currentTicket = null;
    
    public TicketBooth() {
        // Initialize ticket prices
        ticketPrices.put("adult", 15.0);
        ticketPrices.put("child", 8.0);
        ticketPrices.put("senior", 10.0);
    }
    
    public void turnOn() {
        isOpen = true;
        System.out.println("Ticket Booth is now open.");
    }
    
    public void turnOff() {
        isOpen = false;
        System.out.println("Ticket Booth is now closed.");
    }
    
    @Override
    public void buyTicket(String ticketType, int quantity) {
        if (!isOpen) {
            System.out.println("Cannot purchase tickets. Ticket Booth is closed.");
            return;
        }
        
        if (!ticketPrices.containsKey(ticketType.toLowerCase())) {
            System.out.println("Invalid ticket type: " + ticketType);
            return;
        }
        
        double totalPrice = getPrice(ticketType) * quantity;
        System.out.printf("Purchasing %d %s ticket(s) for $%.2f\n", 
                          quantity, ticketType, totalPrice);
        
        // Generate ticket IDs
        for (int i = 0; i < quantity; i++) {
            String ticketId = generateTicketId(ticketType);
            issuedTickets.add(ticketId);
            System.out.println("Issued ticket: " + ticketId);
            currentTicket = ticketId;
        }
    }
    
    @Override
    public double getPrice(String ticketType) {
        return ticketPrices.getOrDefault(ticketType.toLowerCase(), 0.0);
    }
    
    private String generateTicketId(String ticketType) {
        return ticketType.substring(0, 1).toUpperCase() + 
               "-" + System.currentTimeMillis() % 10000 + 
               "-" + (int)(Math.random() * 1000);
    }
    
    @Override
    public void sendTicketData(String ticketId, String ticketType) {
        currentTicket = ticketId;
        System.out.println("Ticket data sent: " + ticketId + " (" + ticketType + ")");
    }
    
    @Override
    public String receiveTicketData() {
        return currentTicket;
    }
}