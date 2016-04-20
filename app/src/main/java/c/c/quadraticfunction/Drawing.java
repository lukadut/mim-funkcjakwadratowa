package c.c.quadraticfunction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import c.c.quadraticfunction.drawing.*;
import c.c.quadraticfunction.drawing.CanvasSettings;

public class Drawing extends AppCompatActivity {

    protected Double a,b,c;
    @Nullable
    protected Double x1,x2;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        Intent i = getIntent();
        a = i.getDoubleExtra("a",0);
        b = i.getDoubleExtra("b",0);
        c = i.getDoubleExtra("c",0);

        x1 = i.getDoubleExtra("x1",Double.NaN);
        x2 = i.getDoubleExtra("x2",Double.NaN);
    }


    public class MyView extends View
    {

        Paint paint;

        public MyView(Context context)
        {
            super(context);

            paint = new Paint();




        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            CanvasSettings.width = getWidth();
            CanvasSettings.height = getHeight();
            CanvasSettings.density = canvas.getDensity();
            Log.d("a ", "" + a );
            Log.d("b ", "" + b );
            Log.d("c ", "" + c );
            Log.d("x1", "" + (x1==null? "null" : x1));
            Log.d("x2", "" + (x2==null? "null" : x2));


            CanvasSettings.margin           = CanvasSettings.width/10;
            CanvasSettings.drawAreaHeight   = CanvasSettings.height - 2* CanvasSettings.margin;
            CanvasSettings.drawAreaWidth    = CanvasSettings.width  - 2* CanvasSettings.margin;
            Log.d("drawAreaHeight",CanvasSettings.drawAreaHeight +"");
            Log.d("drawAreaWidth",CanvasSettings.drawAreaWidth +"");
            plotCoordinateSystem(canvas);

            DrawerFactory.get(a,b,c,x1,x2).draw(canvas);

            /**
             * @Test
             */
            //drawScale(canvas,1,2,3,4,5,6,7,8);


//            Log.d("density","d = " + canvas.getDensity() );
//            int radius;
//            radius = 100;
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.FILL);
//            paint.setColor(Color.WHITE);
//            canvas.drawPaint(paint);
//            // Use Color.parseColor to define HTML colors
//            paint.setColor(Color.parseColor("#CD5C5C"));
//            canvas.drawCircle(x / 2, y / 2, radius, paint);
//
//            paint.setColor(Color.parseColor("#FFDB24"));
//            canvas.drawRect(1, 10, 150, 200, paint);
//
//            Path p = new Path();
//            RectF rectF = new RectF(300,300,480,480);
//            p.arcTo(rectF,0,90);
//            paint.setColor(Color.parseColor("#669BFF"));
//            canvas.drawPath(p, paint);
//
//            float[] points = new float[200];
//            Random r = new Random();
//            for(int i=0;i<200;i++){
//                points[i] = r.nextInt(300);
//            }
//            paint.setColor(Color.parseColor("#000000"));
//            canvas.drawLines(points,paint);
//
//            PointF mPoint1 = new PointF(x/1.2F, y/1.2F);
//            PointF mPoint2 = new PointF(x/24, y/1.2F);
//            Path myPath1;
//            paint.setAntiAlias(true);
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(10);
//            paint.setColor(Color.LTGRAY);
//
//            myPath1 = drawCurve(canvas, paint, mPoint1, mPoint2);
//            canvas.drawPath(myPath1, paint);
//
//
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(30);
//            // mpoint1 czerwony
//            paint.setColor(Color.parseColor("#FF0100"));
//            canvas.drawPoint(x / 1.2F, y / 1.2F, paint);
//
//            //mpoint2 pomaranczowy
//            paint.setColor(Color.parseColor("#FF6600"));
//            canvas.drawPoint(x/24, y/1.2F,paint);
//
//            //rozowy moveto
//            paint.setColor(Color.parseColor("#FF00B5"));
//            canvas.drawPoint(63*x/64, y/10,paint);
        }


        private void plotCoordinateSystem(Canvas canvas){
            Paint solid = new Paint();
            solid.setARGB(255, 64, 64,64);
            solid.setTextSize(20);
            Paint triangle = new Paint();
            triangle.setARGB(255, 64, 64,64);
            triangle.setStyle(Paint.Style.FILL);
            Paint dashed = new Paint();
            dashed.setARGB(255, 64, 64,64);
            dashed.setStyle(Paint.Style.STROKE);
            dashed.setPathEffect(new DashPathEffect(new float[] {5,5}, 0));

            float[] axisX = new float[]{0,CanvasSettings.drawAreaHeight,CanvasSettings.drawAreaWidth,CanvasSettings.drawAreaHeight};
            float[] axisY = new float[]{0,0,0,CanvasSettings.drawAreaHeight};
            float[] helpingX = new float[]{ 1*CanvasSettings.drawAreaWidth/4,0,1*CanvasSettings.drawAreaWidth/4,CanvasSettings.drawAreaHeight,
                    2*CanvasSettings.drawAreaWidth/4,0,2*CanvasSettings.drawAreaWidth/4,CanvasSettings.drawAreaHeight,
                    3*CanvasSettings.drawAreaWidth/4,0,3*CanvasSettings.drawAreaWidth/4,CanvasSettings.drawAreaHeight};
            float[] helpingY = new float[]{ 0,1*CanvasSettings.drawAreaHeight/4,CanvasSettings.drawAreaWidth,1*CanvasSettings.drawAreaHeight/4,
                    0,2*CanvasSettings.drawAreaHeight/4,CanvasSettings.drawAreaWidth,2*CanvasSettings.drawAreaHeight/4,
                    0,3*CanvasSettings.drawAreaHeight/4,CanvasSettings.drawAreaWidth,3*CanvasSettings.drawAreaHeight/4};

            canvas.drawLines(movePoints(axisX,   CanvasSettings.margin,CanvasSettings.margin), solid);
            canvas.drawLines(movePoints(axisY,   CanvasSettings.margin,CanvasSettings.margin), solid);
            canvas.drawLines(movePoints(helpingX,CanvasSettings.margin,CanvasSettings.margin),dashed);
            canvas.drawLines(movePoints(helpingY,CanvasSettings.margin,CanvasSettings.margin),dashed);

            Path axisXTriangle = new Path();
            axisXTriangle.moveTo(CanvasSettings.margin + CanvasSettings.drawAreaWidth, CanvasSettings.margin + CanvasSettings.drawAreaHeight - 10);
            axisXTriangle.lineTo(CanvasSettings.margin + CanvasSettings.drawAreaWidth + 20, CanvasSettings.margin + CanvasSettings.drawAreaHeight);
            axisXTriangle.lineTo(CanvasSettings.margin + CanvasSettings.drawAreaWidth, CanvasSettings.margin + CanvasSettings.drawAreaHeight + 10);
            axisXTriangle.close();

            Path axisYTriangle = new Path();
            axisYTriangle.moveTo(CanvasSettings.margin + 0 - 10, CanvasSettings.margin + 0     );
            axisYTriangle.lineTo(CanvasSettings.margin + 0, CanvasSettings.margin + 0 - 20);
            axisYTriangle.lineTo(CanvasSettings.margin + 0 + 10, CanvasSettings.margin + 0);
            axisYTriangle.close();

            canvas.drawPath(axisXTriangle, triangle);
            canvas.drawPath(axisYTriangle, triangle);

            canvas.drawText("Y", CanvasSettings.margin / 2, CanvasSettings.margin, solid);
            canvas.drawText("X", CanvasSettings.width-CanvasSettings.margin,CanvasSettings.height-CanvasSettings.margin/2,solid);
        }



        private float[] movePoints(float[] points,float vectorX,float vectorY){
            int pointsVectorLength = points.length;
            float[] moved = new float[pointsVectorLength];
            for(int i=0;i<pointsVectorLength;i=i+2){
                moved[i]= points[i]+vectorX;
                moved[i+1] = points[i+1]+vectorY;
            }
            return  moved;
        }
    }
}
