import java.util.ArrayList;

public class Box {
    private int row, col, value, box;
    private ArrayList<Integer> candidates = new ArrayList();


    public Box(int row, int col, int value) {


        this.row = row;
        this.col = col;
        this.value = value;
        if (row <= 2 && col <= 2) {
            box = 1;
        }
        if (row <= 2 && col >= 3 && col <= 5) {
            box = 2;
        }
        if (row <= 2 && col >= 6) {
            box = 3;
        }
        if (row >= 3 && row <= 5 && col <= 2) {
            box = 4;
        }
        if (row >= 3 && row <= 5 && col >= 3 && col <= 5) {
            box = 5;
        }
        if (row >= 3 && row <= 5 && col >= 6) {
            box = 6;
        }
        if (row >= 6 && col <= 2) {
            box = 7;
        }
        if (row >= 6 && col >= 3 && col <= 5) {
            box = 8;
        }
        if (row >= 6 && col >= 6) {
            box = 9;
        }
        if (value == 0) {
            for (int i = 1; i < 10; i++) {
                candidates.add(i);
            }
        }
    }

    //

    public int getValue() {
        return value;
    }

    public int getInitialRowIndex() {
        //Grid #    initCol
        //1     0
        //2     0
        //3     0
        //4     3
        //5     3
        //6     3
        //7     6
        //8     6
        //9     6
        int var = ((getBox() - 1)/3)*3;
        if (var != 0 && var != 3 && var != 6) {
            System.out.println(var);
        }
        return var;


    }
    public int getInitialColIndex(){
        //Grid #    initCol
        //1     0
        //2     3
        //3     6
        //4     0
        //5     3
        return (getBox()-1)%3*3;
    }

    public void setValue(int val) {
        value = val;
        System.out.println("CANDIDATES: " + candidates);
//        for (int i = candidates.size() - 1; i >= 0; i--) {
//            if (candidates.get(i) != val)
//                candidates.remove(i);
//        }
        if (val!=0){
            candidates=new ArrayList<Integer>() {};
        }
    }
    public int getBox(){
        return box;
    }

    public void removeCandidate(int candidate) {
        for (int i = candidates.size() - 1; i >= 0; i--) {
            if (candidates.get(i) == candidate)
                candidates.remove(i);
        }
    }

    public boolean containsCandidate(int candidate){
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i) == candidate)
                return true;
        }
        return false;
    }


    public ArrayList<Integer> getCandidates(){
        return candidates;
    }
}
