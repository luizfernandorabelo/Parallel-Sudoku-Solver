import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridReader {

    public static int[][] read(String filePath) {
        int [][] grid = new int[9][9];
        try {
            Scanner scanner = new Scanner(new File(filePath));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return grid;
    }

}
