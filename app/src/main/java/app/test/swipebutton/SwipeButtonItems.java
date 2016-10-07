package app.test.swipebutton;

public abstract class SwipeButtonItems {
    public int trackColor1 = 0xFF50a5ce;
    public int trackColor2 = 0xFFabd4e9;

    public int thumbColor1 = 0xFF50a5ce;
    public int thumbColor2 = 0xFFabd4e9;

    public double actionConfirmDistanceFraction = 0.7;
    public String trackText = "SWIPE TO CONFIRM", buttonText;
    public int buttonSize;


    public int getTrackColor1() {
        return trackColor1;
    }

    public SwipeButtonItems setTrackColor1(int color) {
        this.trackColor1 = color;
        return this;
    }

    public int getTrackColor2() {
        return trackColor2;
    }

    public SwipeButtonItems setTrackColor2(int color) {
        this.trackColor2 = color;
        return this;
    }

    public int getThumbColor1() {
        return thumbColor1;
    }

    public SwipeButtonItems setThumbColor1(int color) {
        this.thumbColor1 = color;
        return this;
    }

    public int getThumbColor2() {
        return thumbColor2;
    }

    public SwipeButtonItems setThumbColor2(int color) {
        this.thumbColor2 = color;
        return this;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public SwipeButtonItems setButtonSize(int size) {
        this.buttonSize = size;
        return this;
    }


    public double getActionConfirmDistanceFraction() {
        return actionConfirmDistanceFraction;
    }

    public SwipeButtonItems setActionConfirmDistanceFraction(double actionConfirmDistanceFraction) {
        this.actionConfirmDistanceFraction = actionConfirmDistanceFraction;
        return this;
    }

    public String getTrackText() {
        return trackText;
    }

    public SwipeButtonItems setTrackText(String text) {
        this.trackText = text;
        return this;
    }

    public String getButtonText() {
        return buttonText;
    }

    public SwipeButtonItems setButtonText(String text) {
        this.buttonText = text;
        return this;
    }

    public void onSwipeCancel() {
    }


    abstract public void onSwipeConfirm();
}
