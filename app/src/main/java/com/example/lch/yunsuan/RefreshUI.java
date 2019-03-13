package com.example.lch.yunsuan;

import android.app.Activity;

import android.widget.EditText;
import android.widget.TextView;

public class RefreshUI {
 private int result;
 private int guankam;

 //初始化问题
 public RefreshUI(Activity activity,int guanka){
     guankam=guanka;
     final Guanka g = new Guanka();
     final TextView questest = activity.findViewById(R.id.mainTextView1);
     result = g.getGuanka(guanka);
     questest.setText(g.getS());
 }
    public RefreshUI(Activity activity,int score,int guanka){

        guankam=guanka;
        TextView questest = activity.findViewById(R.id.mainTextView1);
        TextView guan =  activity.findViewById(R.id.guanka);
        EditText editText =  activity.findViewById(R.id.mainEditText1);
        editText.setText("");
        TextView sco = activity.findViewById(R.id.showscore);
        sco.setText("分数:" + String.valueOf(score));
        if (guankam <= score / 15) {
            guankam++;
        }
        guan.setText("第" + String.valueOf(guanka) + "关");
        //下一题
        Guanka g = new Guanka();
        this.result = g.getGuanka(guanka);
        questest.setText(g.getS());


    }

    public int getGuankam() {
        return guankam;
    }

    public int getResult() {
        return result;
    }

}
