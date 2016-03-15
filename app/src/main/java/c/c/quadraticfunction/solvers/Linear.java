package c.c.quadraticfunction.solvers;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rozwiązująca równania liniowe
 */
public class Linear implements PolynomialEquation {
    private double a,b;

    Linear(double b, double a) {
        this.a = a;
        this.b = b;
    }

    /**
     * Funkcja do obliczania rozwiązania równania liniowego
     * @return Wyniki w List&le;Double&ge;
     * @throws Exception
     */
    @Override
    public List<Double> compute() throws Exception{
        if(a == 0){ // równanie 0 stopnia
            if(b == 0) { // wyraz wolny = 0
                throw new Exception("InconsistentEquationException"); // równanie nieoznaczone
            }
            throw new Exception("NoSolutionException"); // równanie sprzeczne
        }
        List<Double> results = new ArrayList<>(1);
        results.add((0-b)/a);
        return results;
    }
}
