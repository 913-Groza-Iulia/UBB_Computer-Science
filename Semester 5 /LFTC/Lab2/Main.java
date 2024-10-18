import src.Pairs;
import src.SymbolTable;

public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(15);
        Pairs<Integer, Integer> identifiersPosition;
        Pairs<Integer, Integer> constantsPosition;

        try{
            identifiersPosition = symbolTable.addIdentifier("exemplu");
            System.out.println("Identifier 'exemplu' added at:"+identifiersPosition);
            identifiersPosition = symbolTable.addIdentifier("altexemplu");
            System.out.println("\nIdentifier 'altexemplu' added at:"+identifiersPosition);

            constantsPosition = symbolTable.addConstant(123);
            System.out.println("\nConstant 123 added at: " + constantsPosition);

            constantsPosition = symbolTable.addConstant(456);
            System.out.println("\nConstant 456 added at: " + constantsPosition);

            constantsPosition = symbolTable.addConstant(138);
            System.out.println("\nConstant 138 added at: " + constantsPosition);

            identifiersPosition = symbolTable.findPositionIdentifier("exemplu");
            System.out.println("\nPosition of identifier 'exemplu': " + identifiersPosition);

            constantsPosition = symbolTable.findPositionConstant(138);
            System.out.println("\nPosition of constant 138  : " + constantsPosition);

            if (symbolTable.removeIdentifier("exemplu")) {
                System.out.println("\nIdentifier 'exemplu' removed successfully.");
            } else {
                System.out.println("\nIdentifier 'exemplu' not found.");
            }

            if (symbolTable.removeConstant(123)) {
                System.out.println("\nConstant 123 removed successfully.");
            } else {
                System.out.println("\nConstant 123 not found.");
            }

            identifiersPosition = symbolTable.findPositionIdentifier("exemplu");
            System.out.println("Position of identifier 'exemplu' after removal: " + identifiersPosition);

            constantsPosition = symbolTable.findPositionConstant(123);
            System.out.println("Position of constant 123 after removal: " + constantsPosition);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}