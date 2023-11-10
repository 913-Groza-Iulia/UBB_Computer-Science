package controller;

import exceptions.AquariumFullException;
import exceptions.LivingThingNotFoundException;
import model.Acvariu;
import repository.IRepository;

public class Controller {
    private final IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void add(Acvariu livingThing) throws AquariumFullException {
        repo.add(livingThing);
    }

    public void remove(Acvariu livingThing) throws LivingThingNotFoundException {
        repo.remove(livingThing);
    }

    public Acvariu[] getAll() {
        return repo.getAll();
    }

    public Acvariu[] getAllOlderThan(int age) {
        return repo.getAllOlderThan(age);
    }

    public int getSize() {
        return repo.getSize();
    }
}
