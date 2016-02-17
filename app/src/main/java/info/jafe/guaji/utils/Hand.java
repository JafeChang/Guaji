package info.jafe.guaji.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import info.jafe.guaji.app.App;
import info.jafe.guaji.ui.MainActivity;

/**
 * Created by jianfei on 2016/1/28.
 */
public class Hand extends Handler{
    public interface What {
        int SHOW_TOAST = 0;
    }

    public Hand(){
        super();
    }

    private static Hand hand;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case What.SHOW_TOAST:{
                String str = msg.obj.toString();
                int duration = msg.arg2!=0?Toast.LENGTH_LONG:Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(MainActivity.instance,str,duration);
                toast.show();
                break;
            }
            default:{
                break;
            }
        }
    }

    public static void t(String str){
        if(hand==null){
            return;
        }
        Message msg = new Message();
        msg.obj = str;
        msg.arg2 = 0;
        hand.sendMessage(msg);
    }

    public static void send(Message message){
        hand.sendMessage(message);
    }

    public static void send(int what){
        hand.sendEmptyMessage(what);
    }

    public static void bind(Hand hand){
        Hand.hand = hand;
    }

}
