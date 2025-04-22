import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZooTicketingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create the components
        TicketBooth ticketBooth = new TicketBooth();
        ZooEntrance zooEntrance = new ZooEntrance(ticketBooth); // Pass ticketBooth as the required TicketInterface
        
        // List untuk menyimpan tiket yang sudah dibuat
        List<String> issuedTickets = new ArrayList<>();
        
        // Create the buttons
        PurchaseButton purchaseButton = new PurchaseButton(ticketBooth);
        ValidateButton validateButton = new ValidateButton(zooEntrance, ticketBooth, issuedTickets);
        
        // Turn on the components
        ticketBooth.turnOn();
        zooEntrance.turnOn();
        
        boolean running = true;
        String lastTicketId = null;
        
        System.out.println("\n===== Welcome to the Zoo Ticketing System =====");
        
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Buy Ticket");
            System.out.println("2. Validate Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    purchaseButton.press();
                    System.out.println("Select ticket type:");
                    System.out.println("1. Adult ($15.00)");
                    System.out.println("2. Child ($8.00)");
                    System.out.println("3. Senior ($10.00)");
                    System.out.print("Enter ticket type: ");
                    
                    String ticketType = scanner.nextLine().trim();
                    String type = "";
                    
                    switch (ticketType) {
                        case "1": type = "adult"; break;
                        case "2": type = "child"; break;
                        case "3": type = "senior"; break;
                        default:
                            System.out.println("Invalid selection.");
                            continue;
                    }
                    
                    System.out.print("Enter quantity: ");
                    String quantityInput = scanner.nextLine().trim();
                    
                    try {
                        int quantity = Integer.parseInt(quantityInput);
                        if (quantity <= 0) throw new NumberFormatException();
                        purchaseButton.handleUserInput(type + " " + quantity);
                        
                        // Simpan tiket yang dikeluarkan
                        lastTicketId = ticketBooth.receiveTicketData();
                        issuedTickets.add(lastTicketId);
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Please enter a positive number.");
                    }
                    break;
                    
                case "2":
                    validateButton.press();
                    if (lastTicketId != null) {
                        System.out.println("Last purchased ticket ID: " + lastTicketId);
                    }
                    System.out.print("Enter ticket ID to validate: ");
                    String validateInput = scanner.nextLine();
                    validateButton.handleUserInput(validateInput);
                    break;
                    
                case "3":
                    running = false;
                    System.out.println("Thank you for using the Zoo Ticketing System!");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        
        // Turn off the components
        ticketBooth.turnOff();
        zooEntrance.turnOff();
        
        scanner.close();
    }
}