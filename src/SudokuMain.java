import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuMain<i> {

    private static Box[][] sudoku = new Box[9][9];

    private static int[][] init =
            {
                    {0, 0, 4, 0, 2, 0, 0, 0, 0},
                    {0, 0, 0, 9, 0, 0, 2, 6, 0},
                    {0, 0, 0, 5, 0, 0, 0, 9, 8},
                    {0, 0, 0, 0, 0, 7, 0, 5, 0},
                    {7, 0, 5, 0, 1, 0, 3, 0, 2},
                    {0, 4, 0, 6, 0, 0, 0, 0, 0},
                    {3, 7, 0, 0, 0, 4, 0, 0, 0},
                    {0, 8, 2, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 9, 0, 7, 0, 0}

//                    {0, 0, 0, 0, 0, 0, 2, 0, 0},
//                    {0, 8, 3, 2, 0, 0, 0, 0, 1},
//                    {0, 7, 0, 5, 4, 0, 0, 8, 0},
//                    {0, 1, 0, 7, 0, 0, 0, 0, 0},
//                    {0, 4, 0, 0, 8, 0, 0, 7, 0},
//                    {0, 0, 0, 0, 0, 4, 0, 3, 0},
//                    {0, 5, 0, 0, 3, 6, 0, 2, 0},
//                    {4, 0, 0, 0, 0, 2, 3, 5, 0},
//                    {0, 0, 7, 0, 0, 0, 0, 0, 0}


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
        Reader reader = new Reader();
        init=reader.getIntArr("s01a.txt");
        int initSolved = 0;
        boolean foundinRow = false;
        boolean foundinCol = false;
        boolean foundinBox = false;
        boolean zeroes = true;

        for (int r = 0; r < init.length; r++) {
            for (int c = 0; c < init[0].length; c++) {
                sudoku[r][c] = new Box(r, c, init[r][c]);
            }
        }
        while (zeroes) {

            zeroes = false;
            for (int r = 0; r < init.length; r++) {
                for (int c = 0; c < init[0].length; c++) {

                    if (init[r][c] == 0) {

                        //Starting off with the most basic of sudoku techniques: if there's a value in sudoku[r][c]'s row, col, or 3x3 grid,
                        //then sudoku[r][c] cannot possibly be that value, thus we remove it from sudoku[r][c]'s candidate list.

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
                        //we will use the fori loop below many times. This loop is simply going through the 3x3 grid of sudoku[r][c].

                        for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                            for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {
                                if (init[r1][c1] != 0)
                                    sudoku[r][c].removeCandidate(init[r1][c1]);
                            }
                        }

                        //HIDDEN SINGLE TECHNIQUE:

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

                        //these three Arraylists will be used for the "hidden pair" technique later on

                        ArrayList<Integer> rowsWithCandidateVal = new ArrayList();
                        ArrayList<Integer> colsWithCandidateVal = new ArrayList();
                        ArrayList<Integer> commonCandidates = new ArrayList<Integer>();

                        //yet again, we will loop through the candidates of sudoku[r][c] in order to do two more solving technques:
                        //pointing pair and hidden pair

                        for (int i = 0; i < sudoku[r][c].getCandidates().size(); i++){

                            //POINTING PAIR TECHNIQUE:

                            //candCount will count how many boxes in sudoku[r][c]'s 3x3 grid have the same candidate value
                            //once a box is found in the 3x3 grid with the same candidate,
                            //rowWithCandidate and colWithCandidate will hold on to that box's row and column respectively

                            int candCount = 0;
                            int rowWithCandidate = -1;
                            int colWithCandidate = -1;
                            int candidateVal = sudoku[r][c].getCandidates().get(i);

                            for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                                for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {

                                    //if a square in sudoku[r][c]'s box has the same candidate value (and it's not the same box),
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


                            //HIDDEN PAIR TECHNIQUE:

                            //number of boxes in sudoku[r][c]'s 3x3 grid that have the candidate at index i of sudoku[r][c]'s candidate list

                            int boxesWithCandidate = 0;

                            //three ArrayList: holding on to the rows and cols of the boxes with the candidate, as well as the candidate values themselves.

//                            ArrayList<Integer> rowsWithCandidateVal = new ArrayList();
//                            ArrayList<Integer> colsWithCandidateVal = new ArrayList();
//                            ArrayList<Integer> commonCandidates = new ArrayList<Integer>();


                            //r = 1
                            //c = 4
                            //candidate1 = 4 (later 7)
                            //initialRowIndex = 0
                            //initialColIndex = 3
                            int candidatesRow = -1;
                            int candidatesCol = -1;
                            int candidate1 = sudoku[r][c].getCandidates().get(i);

                            for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                                for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {
                                    if (sudoku[r1][c1].containsCandidate(candidate1) && sudoku[r1][c1].notSameBoxAs(sudoku[r][c])){
                                        boxesWithCandidate++;
                                        candidatesRow = r1;
                                        candidatesCol = c1;
                                    }

                                }
                            }


                            //If statements for hidden pair technique:

                            //We're checking one candidate at a time: if there's only one box in sudoku[r][c]'s 3x3 grid with that same candidate,
                            //store that box's row, col, and the shared candidate in the respective Arraylists.

                            if (boxesWithCandidate == 1){
                                rowsWithCandidateVal.add(candidatesRow);
                                colsWithCandidateVal.add(candidatesCol);
                                commonCandidates.add(candidate1);
                            }

                            //This if statement will work if we have 2 instances of only 1 other box having the same candidate

                            if (rowsWithCandidateVal.size() == 2 && colsWithCandidateVal.size() == 2 && commonCandidates.size() == 2){
                                int possibleCand1 = commonCandidates.get(0);
                                int possibleCand2 = commonCandidates.get(1);
                                int row = rowsWithCandidateVal.get(0);
                                int col = colsWithCandidateVal.get(0);

                                //What this is doing is checking to see if the two added boxes are the same.
                                //If sudoku[r][c] and this box possess 2 candidates that no other box does in their 3x3 grid, then those two
                                //candidates can only possibly go in sudoku[r][c] and this box we've traced.
                                //Therefore, all other candidates must be removed from these two boxes except for these two candidates.

                                if (rowsWithCandidateVal.get(0) == rowsWithCandidateVal.get(1) && colsWithCandidateVal.get(0) == colsWithCandidateVal.get(1)){
                                    sudoku[r][c].removeAllCandidatesExcept(possibleCand1, possibleCand2);
                                    sudoku[row][col].removeAllCandidatesExcept(possibleCand1, possibleCand2);
                                }

                            }

                        }



                        //NAKED PAIR TECHNIQUE:

                        int cand1, cand2;

                        //First, we need to make sure that the empty box (sudoku[r][c]) we're examining has only has two candidates:

                        if (sudoku[r][c].getCandidates().size() == 2){

                            //Three loops: the first is for looping through the row of sudoku[r][c], the second for the column, and the third
                            //for the 3x3 grid

                            //row

                            for (int i = 0; i < sudoku.length; i++) {

                                //if the candidate list of sudoku[r][c] is identical
                                //to the candidate list of another box in the same row, remove those two candidates
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

                            //col

                            for (int i = 0; i < sudoku[0].length; i++) {

                                //if the candidate list of sudoku[r][c] is identical
                                //to the candidate list of another box in the same column, remove those two candidates
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

                            //3x3 grid

                            for (int r1 = initialRowIndex; r1 <= initialRowIndex + 2; r1++) {
                                for (int c1 = initialColIndex; c1 <= initialColIndex + 2; c1++) {

                                    //if the candidate list of sudoku[r][c] is identical
                                    //to the candidate list of another box in the same 3x3 grid, remove those two
                                    //candidates from every other box in that 3x3 grid.

                                    if (sudoku[r][c].hasSameCandidatesAs(sudoku[r1][c1]) && (r1 != r || c1 != c)){
                                        cand1 = sudoku[r][c].getCandidates().get(0);
                                        cand2 = sudoku[r][c].getCandidates().get(1);
                                        for (int r2 = initialRowIndex; r2 <= initialRowIndex + 2; r2++) {
                                            for (int c2 = initialColIndex; c2 <= initialColIndex + 2; c2++) {
                                                if (sudoku[r2][c2].notSameBoxAs(sudoku[r][c]) && sudoku[r2][c2].notSameBoxAs(sudoku[r1][c1])){
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

                    //simply checking if the puzzle is solved - if there are any zeroes, then the puzzle is not solved
                    //the while loop way above will only terminate if the puzzle is solved, meaning our techniques will continue to be applied

                    if (init[r][c] == 0)
                        zeroes = true;
                }
            }

            int counter = 0;
            ArrayList<Integer> amountInRow = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++) {
                for (Box[] row : sudoku){
                    for (Box b : row){
                        if (b.containsCandidate(i)){
                            counter++;
                        }
                    }
                    amountInRow.add(counter);
                    counter = 0;
                }
            }
            zeroes = false;

            for (int i = 0; i < init.length; i++) {
                System.out.println(Arrays.toString(init[i]));

            }
            System.out.println();
        }

        System.out.println("Solved");
    }

}




