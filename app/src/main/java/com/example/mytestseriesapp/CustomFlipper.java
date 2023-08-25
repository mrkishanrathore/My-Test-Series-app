package com.example.mytestseriesapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class CustomFlipper extends ViewFlipper {

    Float startX;

    public CustomFlipper(Context context) {
        super(context);
        init();
    }

    public CustomFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        setClickable(true);
        setFocusable(true);
        // Initialize other attributes or behaviors
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("kishan", "onTouchEvent:inside ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                float deltaX = endX - this.startX;
                Log.d("kishan", "onTouchEvent: "+deltaX);
                if (deltaX > 0) {
                    // Right swipe detected
                   this.showPrevious();
                } else if (deltaX < 0) {
                    // Left swipe detected
                    this.showNext();
                }
                break;
        }
        return true; // Consumed the touch event
    }


    @Override
    public boolean performClick() {

        // Handle the click event here

        return super.performClick();
    }
}

