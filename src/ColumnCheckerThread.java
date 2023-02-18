public class ColumnCheckerThread extends CheckerThread {

    private final int col;
    private final int[][] grid;

    public ColumnCheckerThread(int col, int[][] grid) {
        this.col = col;
        this.grid = grid;
        this.valid = true;
    }

    @Override
    public void run() {
        int[] count = new int[9];
        for (int row = 0; row < 9; row++) {
            int numberIndex = grid[row][col] - 1;
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
