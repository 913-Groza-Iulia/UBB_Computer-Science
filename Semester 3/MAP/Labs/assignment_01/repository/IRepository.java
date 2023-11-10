package repository;

import exceptions.AquariumFullException;
import exceptions.LivingThingNotFoundException;
import model.Acvariu;
public interface IRepository {
    void add(Acvariu livingThing) throws AquariumFullException;
    void remove(Acvariu livingThing) throws LivingThingNotFoundException;

    Acvariu[] getAll();
    Acvariu[] getAllOlderThan(int age);
    int getSize();
}
