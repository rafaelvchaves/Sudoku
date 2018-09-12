import sun.security.util.Length;

import java.util.Arrays;

public class SudokuMain<i> {

    private static Box[][] sudoku = new Box[9][9];
    private static int[][] init =
            {       {5, 0, 0, 0, 1, 2, 8, 0, 9},
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
        int initSolved = 0;
        boolean zeroes=true;
        for (int r = 0; r < init.length; r++) {
            for (int c = 0; c < init[0].length; c++) {
                sudoku[r][c] = new Box(r, c, init[r][c]);
                if (init[r][c] != 0) {
                    initSolved++;
                }
            }
        }
        System.out.println(initSolved);
        while (zeroes==true) {
            zeroes=false;
            for (int r = 0; r < init.length; r++) {
                for (int c = 0; c < init[0].length; c++) {
                    if (init[r][c] == 0) {
                        for (int i = 0; i < init.length; i++) {
                            if (init[r][i] != 0) {
                                sudoku[r][c].removeCandidate(init[r][i]);
                            }
                        }
                        for (int i = 0; i < init.length; i++) {
                            if (init[i][c] != 0) {
                                sudoku[r][c].removeCandidate(init[i][c]);
                            }
                        }
                        if (r <= 2 && c <= 2) {
                            for (int r1 = 0; r1 <= 2; r1++) {
                                for (int c1 = 0; c1 <= 2; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r <= 2 && c >= 3 && c <= 5) {
                            for (int r1 = 0; r1 <= 2; r1++) {
                                for (int c1 = 3; c1 <= 5; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r <= 2 && c >= 6) {
                            for (int r1 = 0; r1 <= 2; r1++) {
                                for (int c1 = 6; c1 <= 8; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r >= 3 && r <= 5 && c <= 2) {
                            for (int r1 = 3; r1 <= 5; r1++) {
                                for (int c1 = 0; c1 <= 2; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r >= 3 && r <= 5 && c >= 3 && c <= 5) {
                            for (int r1 = 3; r1 <= 5; r1++) {
                                for (int c1 = 3; c1 <= 5; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r >= 3 && r <= 5 && c >= 6) {
                            for (int r1 = 3; r1 <= 5; r1++) {
                                for (int c1 = 6; c1 <= 8; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r >= 6 && c <= 2) {
                            for (int r1 = 6; r1 <= 8; r1++) {
                                for (int c1 = 0; c1 <= 2; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r >= 6 && c >= 3 && c <= 5) {
                            for (int r1 = 6; r1 <= 8; r1++) {
                                for (int c1 = 3; c1 <= 5; c1++) {
                                    if (init[r1][c1] != 0)
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                }
                            }
                        }
                        if (r >= 6 && c >= 6) {
                            for (int r1 = 6; r1 <= 8; r1++) {
                                for (int c1 = 6; c1 <= 8; c1++) {
                                    if (init[r1][c1] != 0) {
                                        sudoku[r][c].removeCandidate(init[r1][c1]);
                                    }
                                }
                            }
                        }


                    }
                    System.out.println("" + r + "," + c + "" + ":" + sudoku[r][c].getCandidates());
                    if (sudoku[r][c].getCandidates().size() == 1) {
                        init[r][c] = sudoku[r][c].getCandidates().get(0);
                    }

                }
            }



            for (int r = 0; r < init.length; r++) {
                for (int c = 0; c < init[0].length; c++) {
                    if (sudoku[r][c].getValue() == 0){
                        zeroes=true;
                    }
                }
            }
            for (int i = 0; i < init.length; i++) {
                System.out.println(Arrays.toString(init[i]));
            }
        }

//        System.out.println(sudoku[3][2].getCandidates());
        System.out.println("Solved");
    }
}



