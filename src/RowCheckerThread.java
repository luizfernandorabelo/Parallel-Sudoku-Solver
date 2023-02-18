public class RowCheckerThread extends CheckerThread {

    private final int row;
    private final int[][] grid;

    public RowCheckerThread(int row, int[][] grid) {
        this.row = row;
        this.grid = grid;
        this.valid = true;
    }

    @Override
    public void run() {
        int[] count = new int[9];
        for (int col = 0; col < 9; col++) {
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
