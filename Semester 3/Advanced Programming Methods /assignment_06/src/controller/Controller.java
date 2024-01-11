package controller;

import model.ADTS.*;
import model.PrgState;
import model.exceptions.MyExc;
import model.values.RefValue;
import model.values.Value;
import repository.IRepository;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repository;
    private ExecutorService executor;
    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public List<PrgState> removeCompletePrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws MyExc {
        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyExc e) {
                System.out.println(e.getMessage());
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {
                    try {
                        return p.oneStep();
                    } catch (MyExc e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }))
                .collect(Collectors.toList());
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println("error -" + e);
                            System.exit(1);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            prgList.addAll(newPrgList);
        } catch (InterruptedException ii) {
            throw new MyExc("error - " + ii);
        }
        conservativeGarbageCollector(prgList);

        prgList.forEach(prg -> {
            try {
                repository.logPrgStateExec(prg);
            } catch (MyExc e) {
                throw new RuntimeException(e);
            }
        });
        repository.setPrgList(prgList);
    }

    Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr,List<Integer> heapAddress, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(elem->(symTableAddr.contains(elem.getKey()) || heapAddress.contains(elem.getKey())))
                .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));}

    List<Integer> getAddress(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getVal();})
                .collect(Collectors.toList()); }

    public void conservativeGarbageCollector(List<PrgState> programStates)
    {
        List<Integer> symbolTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p ->getAddress(p.getSymTable().getDict().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.get(0).getHeap().setContent(safeGarbageCollector(symbolTableAddresses,getAddress(programStates.get(0).getHeap().getContent().values()), programStates.get(0).getHeap().getContent()));;
    }

    public void allStep() throws MyExc {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletePrg(repository.getPrgList());
        while (prgList.size() > 0) {
            oneStepForAllPrg(prgList);
            prgList = removeCompletePrg(repository.getPrgList());
        }
        executor.shutdownNow();
        repository.setPrgList(prgList);
    }
}
