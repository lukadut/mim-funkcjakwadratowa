package c.c.quadraticfunction.solvers;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rozwiązująca równania kwadratowe
 */
public class Quadratic implements PolynomialEquation {
    private double a,b,c;

    Quadratic(double c, double b, double a) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Funkcja obliczająca deltę
     * @return delta
     */
    private double delta(){
        return b*b-4*a*c;
    }

    /**
     * Funkcja obliczająca pierwiastek z delty
     * @return pierwiastek z delty
     * @throws Exception
     */
    private double sqrtDelta() throws Exception {
        double delta = delta();
        if(delta<0){
            throw new Exception("NonrealSolution"); // pierwiastek nie jest liczbą rzeczywistą
        }
        return Math.sqrt(delta);
    }

    /**
     * Funkcja do obliczania rozwiązania równania kwadratowego
     * @return Wyniki w List&le;Double&ge;
     * @throws Exception
     */
    @Override
    public List<Double> compute() throws Exception{
        List<Double> results = new ArrayList<>(2);
        double sqrtDelta = sqrtDelta();
        results.add((0-b - sqrtDelta)/(2*a));
        results.add((0-b + sqrtDelta)/(2*a));
        return results;
    }
}
