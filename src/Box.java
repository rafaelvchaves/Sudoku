import java.util.ArrayList;
//push
public class Box {
    private int row, col;
    private ArrayList<Integer> candidates = new ArrayList();

    public Box(int row, int col, ArrayList<Integer> candidates){
        this.row = row;
        this.col = col;
        this.candidates = candidates;
    }

    public void removeCandidate(int candidate){
        for (int c: candidates){
            if (c == candidate)
                candidates.remove(c);
        }
    }
}
