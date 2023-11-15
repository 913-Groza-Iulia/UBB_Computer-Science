package model.ADTS;

import java.util.List;

public interface MyIStack<T> {
    void push(T elem);
    T pop();
    boolean isEmpty();
    List<T> getReverse();
}
