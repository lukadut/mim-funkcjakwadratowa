package c.c.quadraticfunction.solvers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016-03-09.
 */
public class Quadratic implements PolynomialEquation {
    private double a,b,c;

    public Quadratic(double c, double b, double a) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double delta(){
        return b*b-4*a*c;
    }

    private double sqrtDelta() throws Exception {
        double delta = delta();
        if(delta<0){
            throw new Exception("NonrealSolution");
        }
        return Math.sqrt(delta);
    }

    @Override
    public List<Double> compute() throws Exception{
        List<Double> results = new ArrayList<>(2);
        double sqrtDelta = sqrtDelta();
        results.add((0-b - sqrtDelta)/(2*a));
        results.add((0-b + sqrtDelta)/(2*a));
        return results;
    }
}
