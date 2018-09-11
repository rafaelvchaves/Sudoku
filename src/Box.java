import java.util.ArrayList;
//push
public class Box {
    private int row, col, value;
    private ArrayList<Integer> candidates = new ArrayList();

    public Box(int row, int col, int value){
        this.row = row;
        this.col = col;
        this.value = value;
        if (value!=0){
            for (int i=1; i<10; i++){
                candidates.add(i);
            }
        }
    }
//
    public void removeCandidate(int candidate){
        for (int c: candidates){
            if (c == candidate)
                candidates.remove(c);
        }
    }
}
