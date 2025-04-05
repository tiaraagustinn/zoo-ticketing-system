import java.util.List;

public class ValidateButton implements UserInterface {
    private final ValidateInterface validateInterface;
    private final TicketInterface ticketInterface;
    private final List<String> issuedTickets; // List tiket yang sudah dibuat
    
    public ValidateButton(ValidateInterface validateInterface, TicketInterface ticketInterface, List<String> issuedTickets) {
        this.validateInterface = validateInterface;
        this.ticketInterface = ticketInterface;
        this.issuedTickets = issuedTickets;
    }
    
    @Override
    public void handleUserInput(String input) {
        String ticketId = input.trim();
        
        if (ticketId.isEmpty()) {
            System.out.println("Please enter a valid ticket ID.");
            return;
        }

        // Mengecek apakah tiket ID benar-benar ada
        if (!issuedTickets.contains(ticketId)) {
            System.out.println("Invalid ticket ID! This ticket was not issued by the system.");
            return;
        }
        
        boolean isValid = validateInterface.validateTicket(ticketId);
        
        if (isValid) {
            validateInterface.markAsUsed(ticketId);
            ticketInterface.sendTicketData(ticketId, "used");
            System.out.println("Ticket successfully validated. Welcome to the zoo!");
        } else {
            System.out.println("Ticket validation failed. It may have already been used.");
        }
    }
    
    public void press() {
        System.out.println("Validate button pressed. Please scan or enter your ticket ID.");
    }
}
