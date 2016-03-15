package c.c.quadraticfunction.solvers;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2016-03-09.
 */
public class Linear implements PolynomialEquation {
    public Linear(double b, double a) {
        this.a = a;
        this.b = b;
    }

    private double a,b;

    @Override
    public List<Double> compute() throws Exception{
        if(a == 0){
            if(b == 0) {
                throw new Exception("InconsistentEquationException");
            }
            throw new Exception("NoSolutionException");
        }
        List<Double> results = new ArrayList<>(1);
        results.add((0-b)/a);
        return results;
    }
}
