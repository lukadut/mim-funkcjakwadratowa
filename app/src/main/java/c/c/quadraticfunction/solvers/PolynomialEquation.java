package c.c.quadraticfunction.solvers;

import java.util.List;

/**
 * Interfejs do tworzenia rozwiązywaczy
 */
public interface PolynomialEquation {

    /**
     * Rozwiązywanie równania
     * @return wynik w postaci List&le;Double&ge;
     * @throws Exception
     */
    public List<Double> compute() throws Exception;
}
