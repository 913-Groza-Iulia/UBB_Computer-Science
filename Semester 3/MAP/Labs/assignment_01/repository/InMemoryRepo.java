package repository;

import model.Acvariu;
public class InMemoryRepo implements IRepository {
    private static final int MAXX = 100;
    private final Acvariu[] livingThings = new Acvariu[MAXX];
    private int size = 0;

    @Override
    public void add(Acvariu livingThing) {
        if(size<MAXX)
        {
            livingThings[size] = livingThing;
            size++;
        }
    }

    @Override
    public void remove(Acvariu livingThing) {
        int index = -1;
        for(int i = 0; i < size; i++)
        {
            if(livingThings[i].equals(livingThing))
            {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            for (int i = index; i < size - 1; i++) {
                livingThings[i] = livingThings[i + 1];
            }
            livingThings[size - 1] = null;
            size--;
        }

    }

    @Override
    public Acvariu[] getAll() {
        Acvariu[] res = new Acvariu[size];
        System.arraycopy(livingThings, 0, res, 0, size);
        return res;
    }

    @Override
    public Acvariu[] getAllOlderThan(int age) {
       int count = 0;
       for(int i = 0; i < size; i++)
       {
           if(livingThings[i].getAge() > age)
           {
               count++;
           }
       }
        Acvariu[] res = new Acvariu[count];
        int j = 0;
        for(int i = 0; i < size; i++)
        {
            if(livingThings[i].getAge()>age)
            {
                res[j] = livingThings[i];
                j++;
            }
        }
        return res;
    }

    @Override
    public int getSize() {
        return size;
    }
}
