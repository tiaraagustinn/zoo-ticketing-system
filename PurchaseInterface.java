public interface PurchaseInterface {
    void buyTicket(String ticketType, int quantity);
    double getPrice(String ticketType);
}