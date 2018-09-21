import sun.security.util.Length;

import java.util.Arrays;

public class SudokuMain<i> {

    private static Box[][] sudoku = new Box[9][9];
    private static int[][] init =
            {
                    {0, 0, 0, 0, 0, 0, 2, 0, 0},
                    {0, 8, 3, 2, 0, 0, 0, 0, 1},
                    {0, 7, 0, 5, 4, 0, 0, 8, 0},
                    {0, 1, 0, 7, 0, 0, 0, 0, 0},
                    {0, 4, 0, 0, 8, 0, 0, 7, 0},
                    {0, 0, 0, 0, 0, 4, 0, 3, 0},
                    {0, 5, 0, 0, 3, 6, 0, 2, 0},
                    {4, 0, 0, 0, 0, 2, 3, 5, 0},
                    {0, 0, 7, 0, 0, 0, 0, 0, 0}


                    //HARD:

//                    {0, 5, 0, 0, 9, 0, 0, 0, 0},
//                    {0, 0, 4, 8, 0, 0, 0, 0, 9},
//                    {0, 0, 0, 1, 0, 7, 2, 8, 0},
//                    {5, 6, 0, 0, 0, 0, 1, 3, 7},
//                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                    {1, 7, 3, 0, 0, 0, 0, 4, 2},
//                    {0, 2, 1, 5, 0, 8, 0, 0, 0},
//                    {6, 0, 0, 0, 0, 3, 8, 0, 0},
//                    {0, 0, 0, 0, 1, 0, 0, 6, 0}

                    //MEDIUM 2:

//                    {0, 5, 0, 2, 0, 0, 0, 0, 0},
//                    {3, 0, 0, 0, 0, 5, 0, 8, 0},
//                    {9, 6, 0, 0, 7, 8, 2, 0, 0},
//                    {0, 0, 0, 0, 3, 0, 0, 2, 0},
//                    {7, 0, 8, 0, 0, 0, 1, 0, 3},
//                    {0, 4, 0, 0, 8, 0, 0, 0, 0},
//                    {0, 0, 1, 6, 4, 0, 0, 3, 2},
//                    {0, 7, 0, 5, 0, 0, 0, 0, 1},
//                    {0, 0, 0, 0, 0, 9, 0, 5, 0}


                    //MEDIUM 1:

//                    {2, 0, 6, 0, 0, 0, 0, 4, 9},
//                    {0, 3, 7, 0, 0, 9, 0, 0, 0},
//                    {1, 0, 0, 7, 0, 0, 0, 0, 6},
//                    {0, 0, 0, 5, 8, 0, 9, 0, 0},
//                    {7, 0, 5, 0, 0, 0, 8, 0, 4},
//                    {0, 0, 9, 0, 6, 2, 0, 0, 0},
//                    {9, 0, 0, 0, 0, 4, 0, 0, 1},
//                    {0, 0, 0, 3, 0, 0, 4, 9, 0},
//                    {4, 1, 0, 0, 0, 0, 2, 0, 8}

                    //EASY:

//                    {5, 0, 0, 0, 1, 3, 8, 0, 9},
//                    {3, 0, 0, 0, 9, 0, 4, 6, 0},
//                    {0, 9, 6, 2, 0, 0, 1, 0, 3},
//                    {6, 0, 0, 0, 5, 0, 9, 0, 0},
//                    {0, 0, 0, 7, 0, 9, 0, 0, 0},
//                    {0, 0, 9, 0, 6, 0, 0, 0, 8},
//                    {9, 0, 2, 0, 0, 5, 7, 8, 0},
//                    {0, 4, 8, 0, 3, 0, 0, 0, 2},
//                    {1, 0, 5, 8, 2, 0, 0, 0, 4}


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
                        for (int i = 0; i < sudoku[r][c].getCandidates().size(); i++) {

                            int candidate = sudoku[r][c].getCandidates().get(i);

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

                        //looping through all of the candidates again, except this time we're using the hidden pair technique

                        for (int i = 0; i < sudoku[r][c].getCandidates().size(); i++){

                            //candCount will count how many boxes in sudoku[r][c]'s 3x3 grid have the same candidate value
                            //once a box is found in the 3x3 grid with the same candidate,
                            //rowWithCandidate and colWithCandidate will hold on to that box's row and column respectively

                            int candCount = 0;
                            int rowWithCandidate = -1;
                            int colWithCandidate = -1;
                            int candidateVal = sudoku[r][c].getCandidates().get(i);

                            //loop through the 3x3 grid of sudoku[r][c]

                            for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                                for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {

                                    //if a square in sudoku[r][c]'s box has the same candidate val (and it's not the same box),
                                    //then the candidate counter will go up by 1
                                    //additionally, we will hold on to the row and column of this box in case we need it later

                                    if (sudoku[r1][c1].containsCandidate(candidateVal) && (r1 != r || c1 != c)) {
                                        candCount++;
                                        rowWithCandidate = r1;
                                        colWithCandidate = c1;
                                    }
                                }
                            }

                            //two if statements, both of which require that only 1 other box in sudoku's 3x3 grid contains the same candidate
                            //the first if statement will also check to see if this other box has the same row value as sudoku[r][c], and
                            //the second if statement will also check to see if this other box has the same column value as sudoku[r][c]
                            //the third boolean in each of these if statements checks to make sure we're talking about 2 different boxes:
                            //only a row index OR a column index can be shared, not both

                            if (candCount == 1 && r == rowWithCandidate && c != colWithCandidate){

                                //loop through the rest of the column values with our common row value and remove the candidate value from all
                                //boxes in the row

                                for (int col = 0; col < sudoku[0].length; col++) {
                                    if (col != c && col != colWithCandidate)
                                        sudoku[rowWithCandidate][col].removeCandidate(candidateVal);
                                }
                            }
                            if (candCount == 1 && r != rowWithCandidate && c == colWithCandidate){

                                //loop through the rest of the row values with our common column value and remove the candidate value from all
                                //boxes in the column


                                for (int row = 0; row < sudoku.length; row++) {
                                    if (row != r && row != rowWithCandidate)
                                        sudoku[row][colWithCandidate].removeCandidate(candidateVal);
                                }
                            }

                        }

                        //Pointing pair technique
                        //Only works if two boxes share the same 2 candidates and they are in the same row, column, or 3x3 grid

                        int cand1, cand2; //these are the 2 candidates we are checking

                        //First, we need to make sure that the empty box sudoku[r][c] we found only has two candidates:

                        if (sudoku[r][c].getCandidates().size() == 2){

                            //Three loops: the first is for looping through the row of sudoku[r][c], the second for the column, and the third
                            //for the 3x3 grid

                            for (int i = 0; i < sudoku.length; i++) {

                                //if the candidate list of sudoku[r][c] is identical to another box in the same row, remove those two candidates
                                //from every other box in that row.

                                if (sudoku[r][c].hasSameCandidatesAs(sudoku[r][i]) && i != c){
                                    cand1 = sudoku[r][c].getCandidates().get(0);
                                    cand2 = sudoku[r][c].getCandidates().get(1);
                                    for (int j = 0; j < sudoku.length; j++) {
                                        if (j != c && j != i) {
                                            sudoku[r][j].removeCandidate(cand1);
                                            sudoku[r][j].removeCandidate(cand2);
                                        }
                                    }
                                }
                            }

                            for (int i = 0; i < sudoku[0].length; i++) {

                                //if the candidate list of sudoku[r][c] is identical to another box in the same column, remove those two candidates
                                //from every other box in that column.

                                if (sudoku[r][c].hasSameCandidatesAs(sudoku[i][c]) && i != r){
                                    cand1 = sudoku[r][c].getCandidates().get(0);
                                    cand2 = sudoku[r][c].getCandidates().get(1);
                                    for (int j = 0; j < sudoku[0].length; j++) {
                                        if (j != r && j != i) {
                                            sudoku[j][c].removeCandidate(cand1);
                                            sudoku[j][c].removeCandidate(cand2);
                                        }
                                    }
                                }
                            }
                            for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                                for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {

                                    //if the candidate list of sudoku[r][c] is identical to another box in the same 3x3 grid, remove those two
                                    //candidates from every other box in that 3x3 grid.

                                    if (sudoku[r][c].hasSameCandidatesAs(sudoku[r1][c1]) && (r1 != r || c1 != c)){
                                        cand1 = sudoku[r][c].getCandidates().get(0);
                                        cand2 = sudoku[r][c].getCandidates().get(1);
                                        for (int r2 = initialRowIndex; r2 <= initialRowIndex + 2; r2++) {
                                            for (int c2 = initialColIndex; c2 <= initialColIndex + 2; c2++) {
                                                if (r2 != r && r2 != r1 && c2 != c && c2 != c1){
                                                    sudoku[r2][c2].removeCandidate(cand1);
                                                    sudoku[r2][c2].removeCandidate(cand2);
                                                }

                                            }
                                        }
                                    }
                                }
                            }


                        }


                    }


                    System.out.println(r + "," + c + "" + ": " + "value = " + init[r][c] + " candidates = " + sudoku[r][c].getCandidates());
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




