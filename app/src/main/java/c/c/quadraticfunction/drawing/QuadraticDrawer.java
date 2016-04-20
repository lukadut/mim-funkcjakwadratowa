package c.c.quadraticfunction.drawing;

import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.Log;

public class QuadraticDrawer extends GraphDrawer {
    private double a,b,c;
    @Nullable
    private Double x1,x2;
    private double p,q;
    private float absA, absB,absC,absX1,absX2;
    private float yScale,xScale;
    private float ratioY,ratioX;

    public QuadraticDrawer() {
        super();
    }

    public QuadraticDrawer(double a, double b, double c, @Nullable Double x1, @Nullable Double x2) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.x1 = x1;
        this.x2 = x2;
        this.p = -b/(2*a);
        this.q = -(b*b-4*a*c)/(4*a);
        if(!x1.equals(Double.NaN) && !x2.equals(Double.NaN)) {
            this.xScale = Math.abs(x1.floatValue() - x2.floatValue()) / 2;
        }
        else{this.xScale=1;}
        this.yScale = (float)Math.abs(-q);
        this.ratioX = CanvasSettings.drawAreaWidth/4/xScale;
        this.ratioY = CanvasSettings.drawAreaHeight / 4 / yScale;
    }

    @Override
    public void draw(Canvas canvas) {
        if(a>0) {
            if (x1 != x2) {
                drawScale(canvas, 0 - 2 * xScale, (x1 < x2 ? x1.floatValue() : x2.floatValue()), (float) p, (x1 < x2 ? x2.floatValue() : x1.floatValue()), 0 - 2 * yScale, (float) q, 0, yScale);
                Log.d("yScale", "" +  yScale);
                Log.d("xScale", "" + xScale);

                Log.d("ratioY", "" + ratioY);
                Log.d("ratioX", "" + ratioX);
                float scaler = (float)((CanvasSettings.drawAreaHeight / 4) / (((a * CanvasSettings.drawAreaWidth/4) + b )*CanvasSettings.drawAreaWidth/4));

                for(int i=-192;i<192;i=i+4) {
                    Log.d("dod", (float) (a * (i / ratioX) * (i / ratioX) + b * (i / ratioX) + c) * (ratioY) + "");
//                    Log.d("Y", "" + addMargin(2 * CanvasSettings.drawAreaHeight / 4 - (float) (((a * i / ratioX) + b) * (i / ratioX) - c) * (ratioY)));
//                    canvas.drawLine(addMargin(CanvasSettings.drawAreaWidth / 2 + i), addMargin(2 * CanvasSettings.drawAreaHeight / 4 - (float) (((a * i / ratioX) + b) * i / ratioX) * (ratioY)),
//                    canvas.drawLine(addMargin(CanvasSettings.drawAreaWidth / 2 + i), addMargin(3 * CanvasSettings.drawAreaHeight / 4 - (float) (a * (i / ratioX) * (i / ratioX) + b * (i / ratioX)) * (ratioY)),
//                            addMargin(CanvasSettings.drawAreaWidth / 2 + i + 4), addMargin(3 * CanvasSettings.drawAreaHeight / 4 - (float) (((a * (i + 4) / ratioX) + b) * ((i + 4) / ratioX) ) * (ratioY)),
//                            paint);

                    canvas.drawLine(addMargin(CanvasSettings.drawAreaWidth / 2 + i), addMargin(3 * CanvasSettings.drawAreaHeight / 4 - (float) (a * i * i + b * i) *scaler),
                            addMargin(CanvasSettings.drawAreaWidth / 2 + i + 4), addMargin(3 * CanvasSettings.drawAreaHeight / 4 - (float) (((a * (i + 4) ) + b) * ((i + 4) ) ) *scaler),
                            paint);

                }
            }
        }


    }
}
