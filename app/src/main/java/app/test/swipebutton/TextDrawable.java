package app.test.swipebutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class TextDrawable extends Drawable {


    private final String text;
    private final Paint paint;
    private int width, height, leftBound;
    private boolean type;

    public TextDrawable(String text, int size, int leftBound, boolean type, Context context) {
        this.text = text;
        this.type = type;
        this.leftBound = leftBound;
        this.paint = new Paint();
        if (type) {
            paint.setColor(context.getResources().getColor(R.color.white_1));
        } else {
            paint.setColor(context.getResources().getColor(R.color.white_2));
        }
        paint.setTextSize(dpToPx(size));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void draw(Canvas canvas) {
        if (type) {
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, 2 * width / 3, 3 * height / 5, paint);
        } else {
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, leftBound, 3 * height / 5, paint);

        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public TextDrawable setWidth(int width) {
        this.width = width;
        return this;
    }

    public TextDrawable setHeight(int height) {
//        this.height = height;
        this.height = dpToPx(50);
        return this;
    }
}