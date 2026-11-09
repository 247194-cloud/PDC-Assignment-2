public class CacheLocalityBenchmark {
    private static final int SIZE = 2000;
    private static final int ITERATIONS = 10;
    private static volatile long checksum;

    public static void main(String[] args) {
        int[][] matrix = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = i + j;
            }
        }

        for (int i = 0; i < 2; i++) {
            traverseRowMajor(matrix);
            traverseColumnMajor(matrix);
        }

        long rowStart = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            traverseRowMajor(matrix);
        }

        long rowTime = System.nanoTime() - rowStart;

        long columnStart = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            traverseColumnMajor(matrix);
        }

        long columnTime = System.nanoTime() - columnStart;

        double rowMs = rowTime / 1_000_000.0;
        double columnMs = columnTime / 1_000_000.0;
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
        System.out.printf("Column-Major / Row-Major Time Ratio: %.2fx%n",
                speedup);

        if (speedup > 1.0) {
            System.out.println("Result: Row-major traversal was faster.");
        } else if (speedup < 1.0) {
            System.out.println("Result: Column-major traversal was faster.");
        } else {
            System.out.println("Result: Both traversal methods took equal time.");
        }

        System.out.println("Checksum: " + checksum);
    }

    public static long traverseRowMajor(int[][] matrix) {
        long sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }

        checksum = sum;
        return sum;
    }

    public static long traverseColumnMajor(int[][] matrix) {
        long sum = 0;

        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][j];
            }
        }

        checksum = sum;
        return sum;
    }
}
