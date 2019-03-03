import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


class Equation {
    private static final double ERROR = 0.00000001;

    private List<Double> equation;

    Equation() {
        equation = new ArrayList<>();
    }

    Equation(Equation eq) {
        equation = new ArrayList<>(eq.equation);
    }

    void add(Double num) {
        equation.add(num);
    }

    Double get(int i) {
        return equation.get(i);
    }

    void mult(Double coefficient) {
        ListIterator<Double> iterator = equation.listIterator();

        while (iterator.hasNext()) {
            Double number = iterator.next();
            iterator.set(number * coefficient);
        }
    }

    Double reduceCoef() {
        int i = 0;
        while (equation.get(i) == 0) {
            i++;

            if (i >= equation.size()) {
                return 0.0;
            }
        }
        Double coef = 1 / equation.get(i);
        mult(coef);
        return coef;
    }

    int size() {
        return equation.size();
    }

    ListIterator<Double> getIterator() {
        return equation.listIterator();
    }

    void print() {
        for (Double num : equation) {
            System.out.printf("%5.2f ", num);
        }
    }

    boolean equals(Equation equation) {
        for (int i = 0; i < this.size(); i++) {
            if (!this.equation.get(i).equals(equation.get(i))) {
                return false;
            }
        }
        return true;
    }

    Equation plusEquation(Equation eqAdded) {
        Equation eq = new Equation(eqAdded);
        int indexFirstNotZero = eq.indexFirstNotZero();
        Double coef = -this.equation.get(indexFirstNotZero);
        eq.mult(coef);

        if (Main.printActions) {
            if (coef > 0) {
                System.out.printf(" + %.2f", coef);
            } else {
                System.out.printf(" - %.2f", Math.abs(coef));
            }
        }

        Equation newEq = new Equation();
        for (int i = 0; i < this.equation.size(); i++) {
            newEq.add(this.equation.get(i) + eq.get(i));
        }

        return newEq;
    }

    private int indexFirstNotZero() {
        for (int i = 0; i < this.equation.size(); i++) {
            if (Math.abs(equation.get(i)) > ERROR) {
                return i;
            }
        }

        return 0;
    }
}
