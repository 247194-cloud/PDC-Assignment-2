
public class CacheLocalityBenchmark {

    private static final int SIZE = 2000;
    private static final int ITERATIONS = 10;

    private static volatile long checksum;

    public static void main(String[] args) {

        double[][] matrix = new double[SIZE][SIZE];

        // Initialize the matrix
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = i + j;
            }
        }

        // Warm-up iterations
        for (int i = 0; i < 2; i++) {
            traverseRowMajor(matrix);
            traverseColumnMajor(matrix);
        }

        // Measure Row-Major Traversal
        long rowStart = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            traverseRowMajor(matrix);
        }

        long rowTime = System.nanoTime() - rowStart;

        // Measure Column-Major Traversal
        long columnStart = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            traverseColumnMajor(matrix);
        }

        long columnTime = System.nanoTime() - columnStart;

        // Convert nanoseconds into milliseconds
        double rowMs = rowTime / 1_000_000.0;
        double columnMs = columnTime / 1_000_000.0;

        // Calculate speed ratio
        double speedup = (double) columnTime / rowTime;

        System.out.println("Matrix Cache Locality Benchmark");
        System.out.println("--------------------------------");
        System.out.println("Matrix Size: " + SIZE + " x " + SIZE);
        System.out.println("Benchmark Iterations: " + ITERATIONS);

        System.out.printf("Row-Major Total Time: %.2f ms%n", rowMs);
        System.out.printf("Column-Major Total Time: %.2f ms%n", columnMs);

        System.out.printf("Row-Major Average Time: %.2f ms%n",
                rowMs / ITERATIONS);

        System.out.printf("Column-Major Average Time: %.2f ms%n",
                columnMs / ITERATIONS);

        System.out.printf(
                "Column-Major / Row-Major Time Ratio: %.2fx%n",
                speedup);

        if (speedup > 1.0) {
            System.out.println(
                    "Result: Row-major traversal was faster.");
        } else if (speedup < 1.0) {
            System.out.println(
                    "Result: Column-major traversal was faster.");
        } else {
            System.out.println(
                    "Result: Both traversal methods took equal time.");
        }

        System.out.println("Checksum: " + checksum);
        System.out.println("Cache locality benchmark completed.");
    }

    // Row-Major Traversal
    public static long traverseRowMajor(double[][] matrix) {

        long sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += (long) matrix[i][j];
            }
        }

        checksum = sum;
        return sum;
    }

    // Column-Major Traversal
    public static long traverseColumnMajor(double[][] matrix) {

        long sum = 0;

        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                sum += (long) matrix[i][j];
            }
        }

        checksum = sum;
        return sum;
    }
}
