package c.c.quadraticfunction.drawing;

import android.support.annotation.Nullable;


public class DrawerFactory {
    public static GraphDrawer get(double a, double b, double c, @Nullable Double x1, @Nullable Double x2){
        if(a!=0){
            return new QuadraticDrawer(a,b,c,x1,x2);
        }
        if(a==0){
            return new LinearDrawer(b,c,x1);
        }
        return new GraphDrawer();
    }
}
