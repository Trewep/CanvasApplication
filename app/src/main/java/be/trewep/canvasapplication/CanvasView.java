package be.trewep.canvasapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.Random;


public class CanvasView extends View {
    int radius = 0;
    int in_circle = 0;
    int all_points = 0;
    Random rand = new Random();
    public CanvasView(Context context){
        super  (context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        radius = Math.min(canvas.getHeight(), canvas.getWidth())/2;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        canvas.drawCircle(radius, radius,radius, paint);
        canvas.drawRect(0,0, 2*radius, 2*radius, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (int i = 0; i < 100000; i++){
            generatePoint(canvas, paint);
        }
    }
        private void generatePoint(Canvas canvas, Paint paint){
            int x = rand.nextInt(radius*2);
            int y = rand.nextInt(radius*2);
            if (dist(x,y,radius,radius)<radius){
                paint.setColor(Color.RED);
                in_circle++;
            } else{
                paint.setColor(Color.GREEN);
            }
            all_points++;
            canvas.drawCircle(x,y,2, paint);
            if (all_points<100 || (all_points%1000 ==0)){
                float pi = 4f*(float)in_circle / (float)all_points;
                Log.d("MONTECARLO", String.format("pi = %.6f",pi));
            }
        }

        private double dist (float x1, float y1, float x2, float y2){
        return Math.sqrt(Math.pow(y2-y1,2)+ Math.pow(x2-x1,2));

        }

}
