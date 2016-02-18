package info.jafe.guaji.ui.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import info.jafe.guaji.constant.GestureConstant;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Logs;

/**
 * Created by jianfei on 2016/2/18.
 */
public class Swapper implements GestureDetector.OnGestureListener {
    private GestureDetector detector;

    public Swapper() {
        detector = new GestureDetector(MainActivity.instance, this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Logs.d("");
        float start = e1.getX();
        float end = e2.getX();
        if (Math.abs(start - end) > GestureConstant.FLING_MIN_DISTANCE &&
                Math.abs(velocityX) > GestureConstant.FLING_MIN_VELOCITY) {
            MainActivity.instance.swap((int) (start - end));
            return true;
        }
        return false;
    }

    public boolean onTouch(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

}
