package c.c.quadraticfunction.solvers;

import java.util.List;

/**
 * Fabryka rozwiązywaczy
 */
public class SolverFactory {
    private static SolverFactory sf = null;
    private SolverFactory() {
    }

    /**
     * Funkcja zwracająca odpowiedniego rozwiązywacza na podstawie przekazanych argumentów
     * @param params
     *      współczynniki równania, w kolejności rosnącego stopnia
     *      np. a0, a1, a2
     * @return PolynomialEquation
     *      rozwiązywacz do równania
     */
    static public PolynomialEquation getSolver(double... params){
        if(sf==null){
            sf = new SolverFactory();
        }
        int degree = sf.polynomialDegree(params);

        if(degree < 2){
            return new Linear(params[0],params[1]);
        }
        if(degree==2){
            return new Quadratic(params[0],params[1],params[2]);
        }
        return new PolynomialEquation() {
            @Override
            public List<Double> compute() throws Exception {
                throw new Exception("NoSolver");
            }
        };
    }

    /**
     * Wylicza stopień równania na podstawie argumentów. Sprawdza najwyższy niezerowy współczynnik
     * @param params
     * @return
     */
    private int polynomialDegree(double... params){
        int maxDegree = 0;
        for(int i=0;i<params.length;i++){
            maxDegree = params[i]!=0? i:maxDegree;
        }
        return maxDegree;
    }

}
