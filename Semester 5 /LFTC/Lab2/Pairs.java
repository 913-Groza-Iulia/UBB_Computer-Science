package src;

public class Pairs<A,B> {
    private A firstElement;
    private B secondElement;

    public Pairs(A firstElement, B secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public A getFirstElemennt() {
        return firstElement;
    }

    public B getSecondElement() {
        return secondElement;
    }

    @Override
    public String toString() {
        return "Pair: " + "[First= " + firstElement + ", " + "Second= " + secondElement + "]" ;
    }
}
