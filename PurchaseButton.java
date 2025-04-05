public class PurchaseButton implements UserInterface {
    private final PurchaseInterface purchaseInterface;
    
    public PurchaseButton(PurchaseInterface purchaseInterface) {
        this.purchaseInterface = purchaseInterface;
    }
    
    @Override
    public void handleUserInput(String input) {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            System.out.println("Invalid input. Format: <ticket_type> <quantity>");
            return;
        }
        
        String ticketType = parts[0];
        int quantity;
        
        try {
            quantity = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a number.");
            return;
        }
        
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }
        
        purchaseInterface.buyTicket(ticketType, quantity);
    }
    
    public void press() {
        System.out.println("Purchase button pressed. Please enter ticket type and quantity.");
    }
}