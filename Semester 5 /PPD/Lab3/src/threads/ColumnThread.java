package threads;

import domain.Matrix;
import domain.Pairs;

import java.util.ArrayList;
import java.util.List;

public class ColumnThread  extends Thread {
    public Matrix a,b,c;
    public int startRow;
    public int startCol;
    public int elementCount;
    public List<Pairs<Integer,Integer>> pairs;

    public ColumnThread(Matrix a, Matrix b, Matrix c,int startRow, int startColumn, int elementCount){
        this.a = a;
        this.b = b;
        this.c = c;
        this.startRow = startRow;
        this.startCol = startColumn;
        this.elementCount = elementCount;
        this.pairs = new ArrayList<>();
        computeElementsForEachThread();
    }

    public void computeElementsForEachThread() {
        int elementsAdded = 0;

        for (int j = startCol; j < c.columnCount && elementsAdded < elementCount; j++) {
            int i = (j == startCol ? startRow : 0); // Start from startRow only for the first column

            while (i < c.rowCount && elementsAdded < elementCount) {
                pairs.add(new Pairs<>(i, j));
                elementsAdded++;
                i++;
            }
        }
    }

    @Override
    public void run() {
        for(Pairs<Integer,Integer> pair : pairs){
            int row = pair.first;
            int col = pair.second;
            try{
                c.setElement(row, col, Matrix.computeElement(a, b, row, col));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
