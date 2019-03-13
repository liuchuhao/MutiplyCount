package com.example.lch.yunsuan;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;


import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends Activity {
    static int score = 0;
    static int guanka = 1;
    static int result;
    ResetThread resetThread = new ResetThread(MainActivity.this, 20);//线程就绪

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final InitSoundPool initSoundPool = new InitSoundPool(this);//音效
        //全屏窗口
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //初始化答案（int）和问题（String）
        initiateUI();
        threadInitiate();


        //匿名按钮监听
        Button summit = (Button) findViewById(R.id.mainButton);
        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p1) {
                HF();
                //判断答案是否正确，计算得分
                EditText editText = findViewById(R.id.mainEditText1);
                String answer = editText.getText().toString();
                if (answer.equals(String.valueOf(result))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "恭喜，答案正确！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -400);
                    toast.show();
                    initSoundPool.playSound(1, 3);
                    score = score + 10;
                    //更新UI  初始score，guanka
                    RefreshUI refreshUI = new RefreshUI(MainActivity.this, score, guanka);
                    guanka = refreshUI.getGuankam();//返回score，guanka
                    result = refreshUI.getResult();


                    resetThread.setSeconds(20);//重置时间

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "错误，再想想？", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -400);
                    toast.show();
                    initSoundPool.playSound(2, 1);
                }

            }
        });

    }

    //初始化
    public void initiateUI() {
        result = new RefreshUI(MainActivity.this, guanka).getResult();
    }

    public void HF() {
        resetThread.interrupt();
    }

    public void threadInitiate() {
        resetThread.start();//线程启动
    }
    public void showDialog(final Activity activity,String score){

        AlertDialog.Builder b= new AlertDialog.Builder(activity);
        b.setTitle("时间到！");
        b.setMessage("真棒，您本次获得"+score+"分,再接再励!");
        b.setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            //更新
                new MainActivity().result=new RefreshUI(activity, 0,1).getResult();
            }
        });
        b.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();

            }
        });
        AlertDialog dialog=b.create();
        dialog.show();

    }

    }


