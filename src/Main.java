import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ListIterator;

public class Main {

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
        } catch (NumberFormatException e){
            System.out.println("Check input date: " + e.getMessage().toLowerCase());
            return;
        }

        if (!system.validate()){
            System.out.println("Check size equations");
            return;
        }


        System.out.println("Input matrix");
        system.print();

        System.out.println("Matrix without same equations");
        deleteSameEquations(system);
        system.print();

        System.out.println("Direct course");
        directCourse(system);
        system.print();

        System.out.println("Revers course");
        reversCourse(system);
        system.print();
    }

    private static void directCourse(LinearSystem system){
        ListIterator<Equation> equationListIterator = system.getIterator(0);
        int i = 1;
        while(equationListIterator.hasNext()){
            Equation eq = equationListIterator.next();
            Double coef = eq.reduceCoef();

            printAction(coef, i);
            system.print();

            system.plusEquationsFromBegin(eq, i);

            //TODO: make print actions for transformations


            i++;

            system.print();
        }
    }

    private static void reversCourse(LinearSystem system){
        ListIterator<Equation> equationListIterator = system.getIterator(system.size());
        int i = system.size() - 1;
        while(equationListIterator.hasPrevious()){
            Equation eq = equationListIterator.previous();
            system.plusEquationsFromEnd(eq, i);
            i--;
            //TODO: make print actions for transformations

            system.print();
        }
    }

    private static void deleteSameEquations(LinearSystem system){
        for (int i = 0; i < system.size() - 1; i++){
            if (system.getEquation(i).equals(system.getEquation(i + 1))){
                system.delete(i + 1);
                i--;
            }
        }
    }

    private static void printAction(Double coef, int indexEq){
        System.out.printf("(%s * %.2f)=>\n", numToRim(indexEq), coef);
    }

    private static void printAction(Double coef, int indexEqStart, int indexEqEnd){
        String result = "(";

        for (int i = indexEqStart; i < indexEqEnd; i++){

        }
    }

    private static String numToRim(int num){
        switch (num){
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


