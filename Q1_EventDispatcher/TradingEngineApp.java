public class TradingEngineApp {
    public static void main(String[] args) {
        TradeEvent event1 = new TradeEvent(
            "Gateway-01", System.currentTimeMillis(), 10000.0
        );

        TradeEvent event2 = new FIXOrderEvent(
            "FIX-Gateway", System.currentTimeMillis(),
            10000.0, "ORD101"
        );

        TradeEvent event3 = new ReutersQuoteEvent(
            "Reuters-01", System.currentTimeMillis(),
            150.0, 149.50, 150.25
        );

        TradeEvent[] events = {event1, event2, event3};

        for (TradeEvent event : events) {
            event.dispatch();
            System.out.println("--------------------");
        }
    }
}

