
public class PacketSymmetryAuditor {
    public static boolean isSymmetrical(String payload) {
        int capacity = payload.length();

        if (capacity == 0) {
            return true;
        }

        TransactionStack stack = new TransactionStack(capacity);
        TransactionQueue queue = new TransactionQueue(capacity);

        for (int i = 0; i < payload.length(); i++) {
            char c = payload.charAt(i);
            stack.push(c);
            queue.enqueue(c);
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String payload1 = "ABBA";
        String payload2 = "ABCD";

        System.out.println("Packet Payload: " + payload1);
        System.out.println("Symmetrical: " + isSymmetrical(payload1));

        System.out.println("--------------------");

        System.out.println("Packet Payload: " + payload2);
        System.out.println("Symmetrical: " + isSymmetrical(payload2));
    }
}
