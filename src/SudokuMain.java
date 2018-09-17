import sun.security.util.Length;

import java.util.Arrays;

public class SudokuMain<i> {

    private static Box[][] sudoku = new Box[9][9];
    private static int[][] init =
            {


                    {0, 5, 0, 0, 9, 0, 0, 0, 0},
                    {0, 0, 4, 8, 0, 0, 0, 0, 9},
                    {0, 0, 0, 1, 0, 7, 2, 8, 0},
                    {5, 6, 0, 0, 0, 0, 1, 3, 7},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 7, 3, 0, 0, 0, 0, 4, 2},
                    {0, 2, 1, 5, 0, 8, 0, 0, 0},
                    {6, 0, 0, 0, 0, 3, 8, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 6, 0}

//                    {2, 0, 6, 0, 0, 0, 0, 4, 9},
//                    {0, 3, 7, 0, 0, 9, 0, 0, 0},
//                    {1, 0, 0, 7, 0, 0, 0, 0, 6},
//                    {0, 0, 0, 5, 8, 0, 9, 0, 0},
//                    {7, 0, 5, 0, 0, 0, 8, 0, 4},
//                    {0, 0, 9, 0, 6, 2, 0, 0, 0},
//                    {9, 0, 0, 0, 0, 4, 0, 0, 1},
//                    {0, 0, 0, 3, 0, 0, 4, 9, 0},
//                    {4, 1, 0, 0, 0, 0, 2, 0, 8}


//                    {5, 0, 0, 0, 1, 3, 8, 0, 9},
//                    {3, 0, 0, 0, 9, 0, 4, 6, 0},
//                    {0, 9, 6, 2, 0, 0, 1, 0, 3},
//                    {6, 0, 0, 0, 5, 0, 9, 0, 0},
//                    {0, 0, 0, 7, 0, 9, 0, 0, 0},
//                    {0, 0, 9, 0, 6, 0, 0, 0, 8},
//                    {9, 0, 2, 0, 0, 5, 7, 8, 0},
//                    {0, 4, 8, 0, 3, 0, 0, 0, 2},
//                    {1, 0, 5, 8, 2, 0, 0, 0, 4}
//                    HARD:


            };

    public static void main(String[] args) {
        int initSolved = 0;
        boolean foundinRow = false;
        boolean foundinCol = false;
        boolean foundinBox = false;
        boolean zeroes = true;
        for (int r = 0; r < init.length; r++) {
            for (int c = 0; c < init[0].length; c++) {
                sudoku[r][c] = new Box(r, c, init[r][c]);
                if (init[r][c] != 0) {
                    initSolved++;
                }
            }
        }
        System.out.println(initSolved);
        while (zeroes) {
            zeroes = false;
            for (int r = 0; r < init.length; r++) {
                for (int c = 0; c < init[0].length; c++) {

                    if (init[r][c] == 0) {

                        //checking solved squares in same row to eliminate candidates from init[r][c]

                        for (int i = 0; i < init.length; i++) {
                            if (init[r][i] != 0) {
                                sudoku[r][c].removeCandidate(init[r][i]);
                            }
                        }
                        //checking solved squares in same column to eliminate candidates from init[r][c]

                        for (int i = 0; i < init[0].length; i++) {
                            if (init[i][c] != 0) {
                                sudoku[r][c].removeCandidate(init[i][c]);
                            }
                        }

                        //indexes of the top left hand corner of the 3x3 grid that init[r][c] is in

                        int initialRowIndex = sudoku[r][c].getInitialRowIndex();
                        int initialColIndex = sudoku[r][c].getInitialColIndex();

                        //checking solved squares in same 3x3 grid to eliminate candidates from init[r][c]

                        for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                            for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {
                                if (init[r1][c1] != 0)
                                    sudoku[r][c].removeCandidate(init[r1][c1]);
                            }
                        }

                        //looping through the candidates of init[r][c]

                        for (int candidate : sudoku[r][c].getCandidates()) {
                            foundinRow = false;
                            foundinCol = false;
                            foundinBox = false;

                            //checking to see if a candidate is found anywhere else in init[r][c]'s row

                            for (int j = 0; j < sudoku.length; j++) {
                                if (sudoku[r][j].containsCandidate(candidate) && j != c) {
                                    foundinRow = true;
                                }
                            }

                            //checking to see if this candidate is found anywhere else in init[r][c]'s column


                            for (int j = 0; j < sudoku.length; j++) {
                                if (sudoku[j][c].containsCandidate(candidate) && j != r) {
                                    foundinCol = true;
                                }
                            }

                            //checking to see if this candidate is found anywhere else in init[r][c]'s 3x3 grid


                            for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                                for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {
                                    if (sudoku[r1][c1].containsCandidate(candidate) && (r1 != r || c1 != c)) {
                                        foundinBox = true;
                                    }
                                }
                            }

                            //if this candidate has not been found in either init[r][c]'s row, column, or 3x3 grid,
                            //then set init[r][c] to this candidate

                            if (!foundinRow) {
                                sudoku[r][c].setValue(candidate);
                                init[r][c] = candidate;
                            }

                            if (!foundinCol) {
                                sudoku[r][c].setValue(candidate);
                                init[r][c] = candidate;
                            }
                            if (!foundinBox) {
                                sudoku[r][c].setValue(candidate);
                                init[r][c] = candidate;
                            }


                        }

                    }


                    System.out.println("" + r + "," + c + "" + ":" + sudoku[r][c].getCandidates());
                    if (sudoku[r][c].getCandidates().size() == 1) {
                        init[r][c] = sudoku[r][c].getCandidates().get(0);
                        sudoku[r][c].setValue(init[r][c]);
                    }
                    if (init[r][c] == 0)
                        zeroes = true;
                }
            }


            for (int i = 0; i < init.length; i++) {
                System.out.println(Arrays.toString(init[i]));
            }
        }

        System.out.println("Solved");
    }
}



