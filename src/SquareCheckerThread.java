public class SquareCheckerThread extends CheckerThread {

    private final int row;
    private final int col;
    private final int[][] grid;

    public SquareCheckerThread(int topLeftRow, int topLeftCol, int[][] grid) {
        this.row = topLeftRow;
        this.col = topLeftCol;
        this.grid = grid;
        this.valid = true;
    }

    @Override
    public void run() {
        int[] count = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int numberIndex = grid[i + row][j + col] - 1;
                if (numberIndex < 0) continue;
                if (count[numberIndex] == 1) {
                    this.valid = false;
                    break;
                } else {
                    count[numberIndex]++;
                }
            }
        }
    }
}
