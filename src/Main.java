public class Main {
    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0}
        };
        int startRow = 0, startCol = 0;
        int newColor = 2;

        floodFill(grid, startRow, startCol, grid[startRow][startCol], newColor);

        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void floodFill(int[][] grid, int row, int col, int targetColor, int newColor) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
            return;
        if (grid[row][col] != targetColor || grid[row][col] == newColor)
            return;

        grid[row][col] = newColor;

        floodFill(grid, row + 1, col, targetColor, newColor);
        floodFill(grid, row - 1, col, targetColor, newColor);
        floodFill(grid, row, col + 1, targetColor, newColor);
        floodFill(grid, row, col - 1, targetColor, newColor);
    }
}