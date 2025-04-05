public interface ValidateInterface {
    boolean validateTicket(String ticketId);
    void markAsUsed(String ticketId);
}