package app.test.swipebutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class SwipeButton extends Button {
    private static final String TAG = "SwipeButton";
    private float x1;
    private boolean buttonClicked, swiping = false;
    private float x2Start;
    private boolean started = false;
    private int width, height, buttonsize;

    private Drawable[] layers;


    private SwipeButtonItems swipeButtonCustomItems;

    public SwipeButton(Context context) {
        super(context);
    }

    public SwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSwipeButtonCustomItems(SwipeButtonItems swipeButtonCustomItems) {
        this.swipeButtonCustomItems = swipeButtonCustomItems;
        buttonsize = swipeButtonCustomItems.getButtonSize();
        layers = new Drawable[5];
        layers[0] = ContextCompat.getDrawable(getContext(), R.drawable.track);
        layers[1] = ContextCompat.getDrawable(getContext(), R.drawable.track);
        layers[2] = ContextCompat.getDrawable(getContext(), R.drawable.track);
        layers[3] = ContextCompat.getDrawable(getContext(), R.drawable.thumb);
        layers[4] = ContextCompat.getDrawable(getContext(), R.drawable.thumb);

    }

    public void setStartDrawable(float x2) {
        int leftBound = getLeftPlace(x2);
        int rightBound = getRightPlace(x2);
        layers[1] = new TextDrawable(swipeButtonCustomItems.getTrackText(), 16, leftBound, true, getContext()).setWidth(width).setHeight(height);
        layers[4] = new TextDrawable(swipeButtonCustomItems.getButtonText(), 16, leftBound + buttonsize, false, getContext()).setWidth(layers[3].getIntrinsicWidth()).setHeight(height);

        LayerDrawable composite = new LayerDrawable(layers);
        composite.setLayerInset(0, 0, 0, 0, 0);
        composite.setLayerInset(1, buttonsize, 0, 0, 0);
        composite.setLayerInset(2, 0, 0, rightBound, 0);
        composite.setLayerInset(3, leftBound, 0, rightBound, 0);
        composite.setLayerInset(4, 0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(composite);
        } else {
            this.setBackgroundDrawable(composite);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = event.getX();
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                if (x1 < (buttonsize * 2)) {
                    buttonClicked = true;
                }

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float x2 = event.getX();

                if (!swiping) {
                    x2Start = event.getX();
                    swiping = true;
                }

                if (x1 < x2 && buttonClicked) {
                    this.setBackgroundDrawable(null);
                    setStartDrawable(x2);
                }

                break;
            }
            case MotionEvent.ACTION_UP: {
                swiping = false;
                float x2 = event.getX();

                if ((x2 - x2Start) <= (width * swipeButtonCustomItems.getActionConfirmDistanceFraction())) {
                    swipeButtonCustomItems.onSwipeCancel();
                    setStartDrawable(0);

                } else {
                    swipeButtonCustomItems.onSwipeConfirm();
                    setStartDrawable(0);
                }
                buttonClicked = false;
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                swipeButtonCustomItems.onSwipeCancel();
                setStartDrawable(0);
            }
        }


        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(width, height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!started && width != 0) {
            setStartDrawable(0);
            started = true;
        }
    }

    private int getRightPlace(float x2) {
        int value = width - buttonsize - (int) x2;
        if (value > 0) {
            if (value > width - (buttonsize * 2)) {
                return width - (buttonsize * 2);
            }
            return value;
        } else {
            return 0;
        }
    }

    private int getLeftPlace(float x2) {
        int value = (int) x2 - buttonsize;
        if (value > 0) {
            if (value > width - (buttonsize * 2)) {
                return width - (buttonsize * 2);
            } else {
                return value;
            }
        } else {
            return 0;
        }
    }
}
