import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


class Equation {
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
            String fractionNum = Utility.convertDecimalToFraction(num);
            System.out.printf("%7s ", fractionNum);
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
        if (indexFirstNotZero < 0) {
            return this;
        }
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
            if (Math.abs(equation.get(i)) > Utility.TOLERANCE) {
                return i;
            }
        }

        return -1;
    }

    void printExpressVar() {
        int index = indexFirstNotZero();
        if (index < 0) {
            return;
        }
        StringBuilder str = new StringBuilder("x"
                + (index) + " = "
                + Utility.convertDecimalToFraction(equation.get(equation.size() - 1)));

        index++;

        for (; index < equation.size() - 1; index++) {
            if (equation.get(index) == 0) {
                continue;
            }

            double num = equation.get(index);
            if (num > 0) {
                String numStr = Utility.convertDecimalToFraction(num);
                if (numStr.equals("1")) {
                    str.append(" - x").append(index + 1);
                } else {
                    str.append(" - ").append(numStr).append("*x").append(index + 1);
                }
            } else {
                String numStr = Utility.convertDecimalToFraction(Math.abs(num));
                if (numStr.equals("1")) {
                    str.append(" + x").append(index + 1);
                } else {
                    str.append(" + ").append(numStr).append("*x").append(index + 1);
                }
            }
        }

        System.out.println(str);
    }

    public boolean validate(){
        boolean allCoefZero = true;
        for (int i = 0; i < size() - 1; i++){
            if (Math.abs(equation.get(i)) > Utility.TOLERANCE){
                allCoefZero = false;
            }
        }

        return !allCoefZero;
    }

}