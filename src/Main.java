import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int EMPTY_SPOT = 0;

    private static ArrayList<CheckerThread> createCheckerThreads(int row, int col, int[][] grid) {
        ArrayList<CheckerThread> threads = new ArrayList<>();
        threads.add(new RowCheckerThread(row, grid));
        threads.add(new ColumnCheckerThread(col, grid));
        threads.add(new SquareCheckerThread(row / 3 * 3, col / 3 * 3, grid));
        return threads;
    }

    private static void startCheckerThreads(ArrayList<CheckerThread> threads) {
        for (CheckerThread t : threads) {
            t.start();
        }
    }

    private static boolean joinCheckerThreads(ArrayList<CheckerThread> threads) {
        boolean valid = true;
        for (CheckerThread t : threads) {
            try {
                t.join();
                valid = valid && t.isValid();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return valid;
    }

    private static boolean isValidConfiguration(int[][] grid, int currRow, int currCol) {
        ArrayList<CheckerThread> threads = createCheckerThreads(currRow, currCol, grid);
        startCheckerThreads(threads);
        return joinCheckerThreads(threads);
    }

    private static boolean solve(int[][] grid, int currRow, int currCol) {
        if (currCol == 9) {
            currCol = 0;
            currRow++;
        }
        if (currRow == 9)
            return true;
        if (grid[currRow][currCol] == EMPTY_SPOT) {
            for (int insertedNumber = 1; insertedNumber <= 9; insertedNumber++) {
                grid[currRow][currCol] = insertedNumber;
                if (isValidConfiguration(grid, currRow, currCol)) {
                    if (solve(grid, currRow, currCol + 1))
                        return true;
                }
                grid[currRow][currCol] = EMPTY_SPOT;
            }
            return false;
        }
        return solve(grid, currRow, currCol + 1);
    }

    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int number : row) {
                System.out.print(number + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input file path: ");
        String filePath = scanner.nextLine();

        try {
            int[][] grid = GridReader.read(filePath);

            if (solve(grid, 0, 0)) {
                System.out.println("Solution found:");
                printGrid(grid);
            } else {
                System.out.println("Solution not found!");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
