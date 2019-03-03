import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ListIterator;

public class Main {

    public static final boolean printActions = true;

    public static void main(String[] args) throws IOException {
        LinearSystem system = new LinearSystem();

        try {
            Files.lines(Paths.get("input")).forEach(s -> {
                Equation eq = new Equation();
                for (String num : s.split(" ")) {
                    eq.add(Double.valueOf(num));
                }

                system.add(eq);
            });
        } catch (NumberFormatException e) {
            System.out.println("Check input date: " + e.getMessage().toLowerCase());
            return;
        }

        if (!system.validate()) {
            System.out.println("Check size equations");
            return;
        }


        System.out.println("Input matrix");
        system.print();

        if (deleteSameEquations(system)) {
            System.out.println("Matrix without same equations");
            system.print();
        }

        System.out.println("Direct course");
        directCourse(system);
        system.print();

        System.out.println("Revers course");
        reversCourse(system);
        system.print();
    }

    private static void directCourse(LinearSystem system) {
        ListIterator<Equation> equationListIterator = system.getIterator(0);
        int i = 1;
        while (equationListIterator.hasNext()) {
            Equation eq = equationListIterator.next();
            Double coef = eq.reduceCoef();

            if (printActions) {
                printAction(coef, i);
                system.print();
            }

            system.plusEquationsFromBegin(eq, i);

            i++;

            system.print();
        }
    }

    private static void reversCourse(LinearSystem system) {
        ListIterator<Equation> equationListIterator = system.getIterator(system.size());
        int i = system.size() - 1;
        while (equationListIterator.hasPrevious()) {
            Equation eq = equationListIterator.previous();
            system.plusEquationsFromEnd(eq, i);
            i--;

            if (i == 0) {
                return;
            }

            system.print();
        }
    }

    private static boolean deleteSameEquations(LinearSystem system) {
        boolean delete = false;
        for (int i = 0; i < system.size() - 1; i++) {
            if (system.getEquation(i).equals(system.getEquation(i + 1))) {
                system.delete(i + 1);
                i--;
                delete = true;
            }
        }

        return delete;
    }

    private static void printAction(Double coef, int indexEq) {
        System.out.printf("(%s * %.2f)=>\n", numToRim(indexEq), coef);
    }

    public static String numToRim(int num) {
        switch (num) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            default:
                return String.valueOf(num);
        }
    }

}


