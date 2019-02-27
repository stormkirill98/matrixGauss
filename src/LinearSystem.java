import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinearSystem {

    private List<Equation> system;

    LinearSystem(){
        system = new ArrayList<>();
    }

    void add(Equation equation){
        system.add(equation);
    }

    void delete(int i) {system.remove(i);}

    Equation getEquation(int i){
        return system.get(i);
    }

    void plusEquationsFromBegin(Equation eq, int start){
        ListIterator<Equation> equations = system.listIterator(start);
        while(equations.hasNext()){
            Equation equation = equations.next();

            Equation newEq = equation.plusEquation(eq);

            equations.set(newEq);
        }
    }

    void plusEquationsFromEnd(Equation eq, int start){
        ListIterator<Equation> equations = system.listIterator(start);
        while(equations.hasPrevious()){
            Equation equation = equations.previous();

            Equation newEq = equation.plusEquation(eq);

            equations.set(newEq);
        }
    }

    int size(){
        return system.size();
    }

    void print(){
        for (Equation eq : system){
            eq.print();
            System.out.println();
        }
        System.out.println();
    }

    boolean validate(){
        for (int i = 0; i < system.size() - 1; i++){
            if (system.get(i).size() != system.get(i + 1).size()){
                return  false;
            }
        }

        if (system.size() == system.get(0).size()){
            return false;
        }


        return true;
    }

    ListIterator<Equation> getIterator(int start){
        return system.listIterator(start);
    }
}
