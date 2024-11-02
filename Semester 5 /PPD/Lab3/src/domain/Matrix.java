package domain;

import java.util.Random;

public class Matrix {
    public final int rowCount, columnCount;
    public int[][] matrix;
    public int flag;

    public Matrix(int rowCount, int columnCount, int flag) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        matrix = new int[rowCount][columnCount];
        this.flag = flag;
        generateMatrix(flag);
    }

    private void generateMatrix(int flag) {
        if(flag == 1){
            for(int i = 0; i < rowCount; i++){
                for(int j = 0; j < columnCount; j++){
                    matrix[i][j] = 1;
                }
            }
        }
        else{
            Random random = new Random();
            for(int i = 0; i < rowCount; i++) {
                for(int j = 0; j < columnCount; j++) {
                    matrix[i][j] = random.nextInt(10) +1;
                }
            }
        }
    }

    public int getElement(int row, int column) {
        return matrix[row][column];
    }

    public void setElement(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public static int computeElement(Matrix a, Matrix b, int i, int j) throws Exception {
        if (i < a.rowCount && j < b.columnCount) {
            int result = 0;
            for (int k = 0; k < a.columnCount; k++) {
                result += a.getElement(i, k) * b.getElement(k, j);
            }
            return result;
        } else {
            throw new Exception("Out of bounds");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < columnCount; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
