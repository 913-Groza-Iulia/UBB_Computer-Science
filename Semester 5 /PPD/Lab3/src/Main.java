import domain.Matrix;
import threads.ColumnThread;
import threads.KThread;
import threads.RowThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int rowCounta = 1000;
    private static final int colCounta = 1000;
    private static final int rowCountb = 1000;
    private static final int colCountb = 1000;
    private static final int threadCount = 20;

/// each task computes consecutive elements, going row after row
    public static RowThread initializeRow(int index, Matrix a, Matrix b, Matrix c, int threadCount) {
        int finalMatrixSize = c.rowCount * c.columnCount;
        int elementCount = finalMatrixSize / threadCount; //number of elements each thread deals with

        int startRow = elementCount * index / c.columnCount;
        int startCol = elementCount * index % c.columnCount;

        if(index == threadCount - 1){
            elementCount += finalMatrixSize % threadCount;
            // the last thread takes the remaining elements that did not fit in previous operations
        }

        return new RowThread(a,b,c,startRow, startCol, elementCount);
    }

    public static void rowMultiplication(Matrix a, Matrix b, Matrix c) {
        float start = System.nanoTime();
        runRowThreadPerTask(a,b,c);
        runRowThreadPool(a,b,c);

        System.out.println("Time elapsed:" + ((System.nanoTime() - start) / 1_000_000_000.0 + "seconds"));
    }

    public static void runRowThreadPerTask(Matrix a, Matrix b, Matrix c) {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < threadCount; i++){
            threads.add(initializeRow(i,a,b,c,threadCount));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Row:\n" + c);
    }

    public static void runRowThreadPool(Matrix a, Matrix b, Matrix c) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for(int i = 0;i < threadCount; i++){
            executor.submit(initializeRow(i,a,b,c,threadCount));
        }

        executor.shutdown();
        try{
            if(!executor.awaitTermination(300, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
            System.out.println("Row:\n" + c);
        }catch(InterruptedException e){
            executor.shutdownNow();
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    /// each task computes consecutive elements, going column by column

    public static ColumnThread initializeColumn(int index, Matrix a, Matrix b, Matrix c, int threadCount) {
        int finalMatrixSize2 = c.rowCount * c.columnCount;
        int elementCount = finalMatrixSize2 / threadCount; //number of elements each thread deals with

        int startRow = elementCount * index / c.columnCount;
        int startCol = elementCount * index % c.columnCount;

        if(index == threadCount - 1){
            elementCount += finalMatrixSize2 % threadCount;
            // the last thread takes the remaining elements that did not fit in previous operations
        }

        return new ColumnThread(a,b,c,startRow, startCol, elementCount);
    }

    public static void colMultiplication(Matrix a, Matrix b, Matrix c) {
        float start = System.nanoTime();
        runColThreadPerTask(a,b,c);
        runColThreadPool(a,b,c);

        System.out.println("Time elapsed:" + ((System.nanoTime() - start) / 1_000_000_000.0 + "seconds"));
    }

    public static void runColThreadPerTask(Matrix a, Matrix b, Matrix c) {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < threadCount; i++){
            threads.add(initializeColumn(i,a,b,c,threadCount));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Column:\n" + c);
    }

    public static void runColThreadPool(Matrix a, Matrix b, Matrix c) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for(int i = 0;i < threadCount; i++){
            executor.submit(initializeColumn(i,a,b,c,threadCount));
        }

        executor.shutdown();
        try{
            if(!executor.awaitTermination(300, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
            System.out.println("Column :\n" + c);
        }catch(InterruptedException e){
            executor.shutdownNow();
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /// each task takes every k-th element(k=number of tasks), going row by row

    public static KThread initializeKThread(int index, Matrix a, Matrix b, Matrix c, int threadCount) {
        int finalMatrixSize = c.rowCount * c.columnCount;
        int elementCount = finalMatrixSize / threadCount;
        if(index < finalMatrixSize % threadCount){
            elementCount++;
        }

        int startRow = index / c.columnCount;
        int startCol = index % c.columnCount;

        return new KThread(a,b,c,startRow, startCol, elementCount, threadCount);
    }

    public static void kTask(Matrix a, Matrix b, Matrix c) {
        float start = System.nanoTime();
        runKThreadPerTask(a,b,c);
        runKThreadPool(a,b,c);

        System.out.println("Time elapsed: " + (System.nanoTime() - start) / 1_000_000_000.0 + " seconds");
    }

    public static void runKThreadPerTask(Matrix a, Matrix b, Matrix c) {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < threadCount; i++){
            threads.add(initializeKThread(i,a,b,c,threadCount));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("K-th element: \n" + c);
    }

    public static void runKThreadPool(Matrix a, Matrix b, Matrix c) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for(int i = 0;i < threadCount; i++){
            executor.submit(initializeKThread(i,a,b,c,threadCount));
        }

        executor.shutdown();
        try{
            if(!executor.awaitTermination(300, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
            System.out.println("Thread Pool K-th element:\n" + c);
        }catch(InterruptedException e){
            executor.shutdownNow();
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Matrix a = new Matrix(rowCounta, colCounta,1);
        Matrix b = new Matrix(rowCountb, colCountb,0);

        System.out.println("Matrix A:\n" + a);
        System.out.println("Matrix B:\n" + b);

        if(colCounta == rowCountb){
            Matrix c = new Matrix(rowCounta, colCountb,1);
//            rowMultiplication(a, b, c);
//            colMultiplication(a, b, c);
            kTask(a,b,c);
        }else{
            System.err.println("Impossible to multiply matrices");
        }
    }
}