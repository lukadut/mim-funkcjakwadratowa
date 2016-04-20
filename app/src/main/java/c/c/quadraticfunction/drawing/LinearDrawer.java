package c.c.quadraticfunction.drawing;

import android.graphics.Canvas;


public class LinearDrawer extends   GraphDrawer {
    private float a, b,x;
    private float absA, absB,absX;


    private LinearDrawer() {
        super();
    }

    public LinearDrawer(double a, double b, double x) {
        super();
        this.a = (float)a;
        this.b = (float)b;
        this.x = (float)x;


    }

    @Override
    public void draw(Canvas canvas) {
        if(a == 0){
            canvas.drawLine(addMargin(0),addMargin(CanvasSettings.drawAreaHeight/2),addMargin(CanvasSettings.drawAreaWidth),addMargin(CanvasSettings.drawAreaHeight/2),paint);
            drawScale(canvas,-1,0,1,2,b-2,b-1,b,b+1);
        }
        if(a > 0){
            if(b>0){
                canvas.drawLine(addMargin(0),addMargin(3*CanvasSettings.drawAreaHeight/4),addMargin(3*CanvasSettings.drawAreaWidth/4),addMargin(0),paint);
            }
            if(b==0){
                canvas.drawLine(addMargin(0),addMargin(4*CanvasSettings.drawAreaHeight/4),addMargin(4*CanvasSettings.drawAreaWidth/4),addMargin(0),paint);
            }
            if(b<0){
                canvas.drawLine(addMargin(1*CanvasSettings.drawAreaWidth/4),addMargin(4*CanvasSettings.drawAreaHeight/4),addMargin(4*CanvasSettings.drawAreaWidth/4),addMargin(1*CanvasSettings.drawAreaHeight/4),paint);
            }
            this.absB = b == 0? Math.abs(a) : Math.abs(this.b);
            this.absX = x == 0? 1 : Math.abs(this.x);
            drawScale(canvas,-2*absX,0-absX,0,absX,-absB*2,-absB,0,absB);
        }
        if(a < 0){
            if(b>0){
                canvas.drawLine(addMargin(1 * CanvasSettings.drawAreaWidth / 4),addMargin(0*CanvasSettings.drawAreaHeight/4),addMargin(4*CanvasSettings.drawAreaWidth/4),addMargin(3*CanvasSettings.drawAreaHeight/4),paint);
            }
            if(b==0){
                canvas.drawLine(addMargin(0),addMargin(0*CanvasSettings.drawAreaHeight/4),addMargin(4*CanvasSettings.drawAreaWidth/4),addMargin(4*CanvasSettings.drawAreaHeight/4),paint);
            }
            if(b<0){
                canvas.drawLine(addMargin(0 * CanvasSettings.drawAreaWidth / 4), addMargin(1 * CanvasSettings.drawAreaHeight / 4), addMargin(3 * CanvasSettings.drawAreaWidth / 4), addMargin(4 * CanvasSettings.drawAreaHeight / 4), paint);
            }

            this.absB = b == 0? Math.abs(a) : Math.abs(this.b);
            this.absX = x == 0? 1 : Math.abs(this.x);
            drawScale(canvas,-2*absX,0-absX,0,absX,-absB*2,-absB,0,absB);
        }

    }

}
