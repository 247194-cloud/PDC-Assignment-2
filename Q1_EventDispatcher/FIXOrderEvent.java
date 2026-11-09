public class FIXOrderEvent extends TradeEvent {
    protected String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp,
                         double basePrice, String clOrdId) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {
        double fee = basePrice * 0.0015;
        double total = basePrice + fee;

        System.out.println("FIX Stock Purchase Request");
        System.out.println("Sender ID: " + senderId);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Client Order ID: " + clOrdId);
        System.out.println("Purchase Amount: " + basePrice);
        System.out.println("Exchange Fee (0.15%): " + fee);
        System.out.println("Total Amount: " + total);
    }
}
