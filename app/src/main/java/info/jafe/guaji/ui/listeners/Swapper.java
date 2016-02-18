package info.jafe.guaji.ui.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;

import info.jafe.guaji.constant.GestureConstant;
import info.jafe.guaji.ui.MainActivity;
import info.jafe.guaji.utils.Logs;

/**
 * Created by jianfei on 2016/2/18.
 */
public class Swapper implements GestureDetector.OnGestureListener {
    private GestureDetector detector;
    private int drawerMinLeft;

    public Swapper() {
        detector = new GestureDetector(MainActivity.instance, this);
        final float density = MainActivity.instance.getResources().getDisplayMetrics().density;
        drawerMinLeft = (int) (16 * density + 0.5f);
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
        float start = e1.getX();
        float end = e2.getX();
//        Logs.d(start+"");
        if (Math.abs(start - end) > GestureConstant.FLING_MIN_DISTANCE
                && Math.abs(velocityX) > GestureConstant.FLING_MIN_VELOCITY
                && Math.abs(start-end)>1.5f*Math.abs(e1.getY()-e2.getY())
                && start> drawerMinLeft) {
            MainActivity.instance.swap((int) (start - end));
            return true;
        }
        return false;
    }

    public boolean onTouch(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

}
