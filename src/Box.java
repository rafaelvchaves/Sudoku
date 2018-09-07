import java.util.ArrayList;

public class Box {
    private int row, col;
    private ArrayList<Integer> candidates = new ArrayList();

    public Box(int row, int col, ArrayList<Integer> candidates){
        this.row = row;
        this.col = col;
        this.candidates = candidates;
    }
}
