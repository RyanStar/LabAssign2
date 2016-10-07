package ece.course.labassign2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class DisplayView extends SurfaceView{
    public final static int TYPE_BALL = 0;
    public final static int TYPE_SQUARE = 1;
    public final static int TYPE_DIAMOND = 2;
    public final static int TYPE_ARC = 3;

    private float mCenterX =0.0f;
    private float mCenterY =0.0f;
    private float mRadius =0.0f;

    private float mPtrCenterX = 100.0f;
    private float mPtrCenterY = 100.0f;
    private float mPtrRadius = 10.0f;
    private double d = 0.0;

    private int mPtrType = TYPE_BALL;
    private int mPtrColor = Color.WHITE;



    public void setPtrColor(int color){
        mPtrColor = color;

        invalidate();
    }

    public void setPtrType(int type){
        mPtrType = type;

        invalidate();
    }

    public void setPtr(float posX, float posY){
        mPtrCenterX = mCenterX;
        d = Math.sqrt((posY * mRadius * 0.9f + mCenterY)*(posY * mRadius * 0.9f + mCenterY) +
                (posX * mRadius * 0.9f + mCenterX)*(posX * mRadius * 0.9f + mCenterX));
        mPtrCenterY = (float)d-mRadius*3/4;
        invalidate();
    }

    public DisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas){

        if(canvas == null)
            return;

        canvas.drawColor(Color.BLACK);

        Paint paint = new Paint();
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(mCenterX-mRadius/4,mCenterY-mRadius/4,mCenterX+mRadius/4,mCenterY+mRadius/4,paint);
        paint1.setColor(Color.YELLOW);
        canvas.drawRect(mCenterX-mRadius/4,mCenterY+mRadius/4,mCenterX+mRadius/4,mCenterY+mRadius*3/4,paint1);
        canvas.drawRect(mCenterX-mRadius/4,mCenterY-mRadius*3/4,mCenterX+mRadius/4,mCenterY-mRadius/4,paint1);
        paint2.setColor(Color.RED);
        canvas.drawRect(mCenterX-mRadius/4,mCenterY+mRadius*3/4,mCenterX+mRadius/4,mCenterY+mRadius*5/4,paint2);
        canvas.drawRect(mCenterX-mRadius/4,mCenterY-mRadius*5/4,mCenterX+mRadius/4,mCenterY-mRadius*3/4,paint2);
        paint.setColor(mPtrColor);
        switch(mPtrType) {
            case TYPE_BALL:
                canvas.drawCircle(mPtrCenterX, mPtrCenterY, mPtrRadius, paint);
                break;
            case TYPE_SQUARE :
                canvas.drawRect(mPtrCenterX - mPtrRadius, mPtrCenterY - mPtrRadius,
                        mPtrCenterX + mPtrRadius, mPtrCenterY + mPtrRadius, paint);
                break;
            case TYPE_DIAMOND :
                Path path = new Path();
                path.moveTo(mPtrCenterX, mPtrCenterY - mPtrRadius);
                path.lineTo(mPtrCenterX - mPtrRadius, mPtrCenterY);
                path.lineTo(mPtrCenterX, mPtrCenterY + mPtrRadius);
                path.lineTo(mPtrCenterX + mPtrRadius, mPtrCenterY);
                path.close();
                canvas.drawPath(path, paint);
                break;
            case TYPE_ARC :
                canvas.drawArc(new RectF(mPtrCenterX - mPtrRadius, mPtrCenterY - mPtrRadius,
                        mPtrCenterX + mPtrRadius, mPtrCenterY + mPtrRadius), -45.0f, -90.0f, true, paint);
                break;
        }
    }
//        paint.setColor(Color.RED);
//        canvas.drawCircle(40.0f,40.0f,10.0f,paint);
//
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(70.0f,30.0f,90.0f,50.0f,paint);
//
//        paint.setColor(Color.GREEN);
//        Path path = new Path();
//        path.moveTo(40.0f,70.0f);
//        path.lineTo(30.0f,80.0f);
//        path.lineTo(40.0f,90.0f);
//        path.lineTo(50.0f,80.0f);
//        path.close();
//
//        canvas.drawPath(path,paint);
//
//        paint.setColor(Color.WHITE);
//        canvas.drawArc(new RectF(70.0f,70.0f,90.0f,90.0f),
//                -45.0f,-90.0f,true,paint);


    public void onSizeChanged(int width,int height,
                              int oldWidth,int oldHeight){
        mCenterX = width/2;
        mCenterY = height/2;
        mRadius = ((width < height)? width : height) * 3.0f / 8.0f;

        mPtrCenterX = mCenterX;
        mPtrCenterY = mCenterY;
        mPtrRadius = mRadius / 10.0f;
        invalidate();
    }
}
