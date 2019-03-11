import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LinearSystem {

    private List<Equation> system;

    LinearSystem() {
        system = new ArrayList<>();
    }

    void add(Equation equation) {
        system.add(equation);
    }

    void delete(int i) {
        system.remove(i);
    }

    Equation getEquation(int i) {
        return system.get(i);
    }

    @SuppressWarnings("Duplicates")
    void plusEquationsFromBegin(Equation eq, int start) {
        if (system.indexOf(eq) == system.size() - 1) {
            return;
        }

        ListIterator<Equation> equations = system.listIterator(start);
        while (equations.hasNext()) {
            Equation equation = equations.next();

            if (Main.printActions) {
                System.out.print(Utility.numToRim(system.indexOf(equation) + 1));
            }

            Equation newEq = equation.plusEquation(eq);

            if (Main.printActions) {
                System.out.println(" * " + Utility.numToRim(system.indexOf(eq) + 1));
            }

            equations.set(newEq);
        }
        if (Main.printActions) {
            System.out.println("        ⇓");
        }
    }

    @SuppressWarnings("Duplicates")
    void plusEquationsFromEnd(Equation eq, int start) {
        if (system.indexOf(eq) == 0) {
            return;
        }

        ListIterator<Equation> equations = system.listIterator(start);
        while (equations.hasPrevious()) {
            Equation equation = equations.previous();

            if (Main.printActions) {
                System.out.print(Utility.numToRim(system.indexOf(equation) + 1));
            }

            Equation newEq = equation.plusEquation(eq);

            if (Main.printActions) {
                System.out.println(" * " + Utility.numToRim(system.indexOf(eq) + 1));
            }

            equations.set(newEq);
        }

        if (Main.printActions) {
            System.out.println("        ⇓");
        }
    }

    int size() {
        return system.size();
    }

    void print() {
        for (Equation eq : system) {
            eq.print();
            System.out.println();
        }
        System.out.println();
    }

    boolean validate() {
        for (int i = 0; i < system.size() - 1; i++) {
            if (system.get(i).size() != system.get(i + 1).size()
                    || system.size() > system.get(i).size()) {
                return false;
            }
        }

        if (system.size() == system.get(0).size()) {
            return false;
        }

        for (Equation eq : system){
            if (!eq.validate()){
                System.out.println("Check equation №" + (system.indexOf(eq) + 1));
                return false;
            }
        }

        return true;
    }

    ListIterator<Equation> getIterator() {
        return getIterator(0);
    }

    ListIterator<Equation> getIterator(int start) {
        return system.listIterator(start);
    }
}