package c.c.quadraticfunction.solvers;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016-03-10.
 */
public class SolverFactory {
    private static SolverFactory sf = null;
    private SolverFactory() {
    }

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
    private int polynomialDegree(double... params){
        int maxDegree = 0;
        for(int i=0;i<params.length;i++){
            maxDegree = params[i]!=0? i:maxDegree;
        }
        return maxDegree;
    }

}
