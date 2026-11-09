
public class PacketSymmetryAuditor {

    public static boolean isSymmetrical(String payload) {

        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null");
        }

        int capacity = payload.length();

        if (capacity == 0) {
            return true;
        }

        TransactionStack stack = new TransactionStack(capacity);
        RequestQueue queue = new RequestQueue(capacity);

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

    private static void testCase(String payload) {
        System.out.println("Payload: " + payload);
        System.out.println("Symmetrical: " + isSymmetrical(payload));
        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        testCase("ABBA");
        testCase("ABCD");
        testCase("");
        testCase("A");
        testCase("ABCBA");
        testCase("ABCCBA");

        try {
            isSymmetrical(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Null Test: " + e.getMessage());
        }
    }
}
