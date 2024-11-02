package threads;

import domain.Matrix;
import domain.Pairs;

import java.util.ArrayList;
import java.util.List;

public class KThread extends Thread {
    public Matrix a,b,c;
    public int startRow,startCol;
    public int elementCount;
    public List<Pairs<Integer,Integer>> pairs;
    public int k;

    public KThread(Matrix a, Matrix b, Matrix c, int startRow, int startCol, int elementCount, int k) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.startRow = startRow;
        this.startCol = startCol;
        this.elementCount = elementCount;
        this.pairs = new ArrayList<>();
        this.k = k;
        computeElementsForEveryThread();
    }

    public void computeElementsForEveryThread() {
        int i = startRow;
        int j=startCol;;
        int size = elementCount;

        while (i < c.rowCount && size > 0) {
            pairs.add(new Pairs<>(i, j));
            size--;
            i = i + (j + k) / c.columnCount;
            j = (j + k) % c.rowCount;
        }
    }

    @Override
    public void run() {
        for (Pairs<Integer, Integer> p : pairs) {
            int row = p.first;
            int column = p.second;
            try {
                c.setElement(row, column, Matrix.computeElement(a, b, row, column));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
