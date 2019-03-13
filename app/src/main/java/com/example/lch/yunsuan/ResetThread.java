package com.example.lch.yunsuan;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;



public class ResetThread extends Thread {
    private Activity activity;
    private long seconds;

   public void setSeconds(long seconds){
       this.seconds=seconds;
   }

    public ResetThread(Activity activity,long time){
        this.activity=activity;
        seconds=time;
    }

    public void run(){

        while (seconds>=-1) {

            if (seconds==-1) {

                Message msg=new Message();
                msg.what=1;
                msg.obj=new MainActivity().score;
                mHandler.sendMessage(msg);
                MainActivity.score=0;
                MainActivity.guanka=1;

                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds=20;


            }//结束

            Message msg = new Message();
            msg.what = 0;
            msg.obj = "时间：" + seconds + "秒";
            //发送信息给Handler
            mHandler.sendMessage(msg);
            //减少1秒
            seconds--;
            try {
                //线程休眠1000毫秒，即1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
    //接收信息
    Handler mHandler =new Handler() {
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 0:
                    //为TextView设置显示的文字，即倒计时显示的数字
                    TextView time=activity.findViewById(R.id.timeOver);
                   time.setText(msg.obj.toString());
                    break;
                case 1:
                    new MainActivity().showDialog(activity,msg.obj.toString());
                    break;
                    default:
                        break;
            }
        }
    };


}
