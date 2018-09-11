import sun.security.util.Length;

public class SudokuMain<i> {


    private static Box[][] sudoku = new Box[9][9];
    private static int[][] init =
            {{5, 0, 0, 0, 1, 2, 8, 0, 9},
                    {3, 0, 0, 0, 9, 0, 4, 6, 0},
                    {0, 9, 6, 2, 0, 0, 1, 0, 3},
                    {6, 0, 0, 0, 5, 0, 9, 0, 0},
                    {0, 0, 0, 7, 0, 9, 0, 0, 0},
                    {0, 0, 9, 0, 6, 0, 0, 0, 8},
                    {9, 0, 2, 0, 0, 5, 7, 8, 0},
                    {0, 4, 8, 0, 3, 0, 0, 0, 2},
                    {1, 0, 5, 8, 2, 0, 0, 0, 4}
            };

    public static void main(String[] args) {
        for (int r=0; r< init.length; r++)
        {
            for (int c=0; c< init[0].length; c++)
            {
                sudoku[r][c]=new Box(r, c, init[r][c]);
            }
        }
        for (int r = 0; r < init.length; r++) {
            for (int c = 0; c < init[0].length; c++) {
                if (init[r][c] == 0){
                    for (int i = 0; i < init.length; i++) {
                        if (init[r][i] != 0)
                            sudoku[r][i].removeCandidate(init[r][i]);
                    }
                }
            }
        }
    }
}
