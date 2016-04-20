package c.c.quadraticfunction.drawing;

import android.graphics.Canvas;
import android.graphics.Paint;


public class GraphDrawer {
    protected Paint paint;

    public GraphDrawer() {
        paint = new Paint();
        paint.setARGB(255, 0, 0,0);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

    }



    public void draw(Canvas canvas){

    }
    protected float addMargin(float point){
        return point+CanvasSettings.margin;
    }

    public void drawScale(Canvas canvas, float x1,float x2, float x3, float x4, float y1, float y2, float y3, float y4){
        Paint paint = new Paint();
        paint.setARGB(255, 64, 64, 64);
        paint.setTextSize(20);
        canvas.drawText(y4 + "", 5, CanvasSettings.margin + 1 * CanvasSettings.drawAreaHeight / 4, paint);
        canvas.drawText(y3 + "", 5, CanvasSettings.margin + 2 * CanvasSettings.drawAreaHeight / 4, paint);
        canvas.drawText(y2 + "", 5, CanvasSettings.margin + 3 * CanvasSettings.drawAreaHeight / 4, paint);
        canvas.drawText(y1 + "", 5, CanvasSettings.margin + 4 * CanvasSettings.drawAreaHeight / 4, paint);


        canvas.drawText(x1 + "", CanvasSettings.margin + 0 * CanvasSettings.drawAreaWidth / 4, CanvasSettings.height - CanvasSettings.margin/2,paint);
        canvas.drawText(x2 + "", CanvasSettings.margin + 1 * CanvasSettings.drawAreaWidth / 4, CanvasSettings.height - CanvasSettings.margin/2,paint);
        canvas.drawText(x3 + "", CanvasSettings.margin + 2 * CanvasSettings.drawAreaWidth / 4, CanvasSettings.height - CanvasSettings.margin/2,paint);
        canvas.drawText(x4 + "", CanvasSettings.margin + 3 * CanvasSettings.drawAreaWidth / 4, CanvasSettings.height - CanvasSettings.margin/2,paint);


    }
}
