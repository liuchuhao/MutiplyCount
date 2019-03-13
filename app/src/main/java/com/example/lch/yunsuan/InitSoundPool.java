package com.example.lch.yunsuan;


import android.content.Context;
import android.media.*;

import java.util.HashMap;

public class InitSoundPool {
    SoundPool sp;
    HashMap<Integer,Integer> spMap;
    private Context context;
    public InitSoundPool(Context context){	//初始化声音池
         this.context=context;
         sp=new SoundPool(
                5, 				//maxStreams参数，该参数为设置同时能够播放多少音效
                AudioManager.STREAM_MUSIC,	//streamType参数，该参数设置音频类型，在游戏中通常设置为：STREAM_MUSIC
                0				//srcQuality参数，该参数设置音频文件的质量，目前还没有效果，设置为0为默认值。
        );
        //放入所有声音文件
        spMap=new HashMap<Integer,Integer>();
        spMap.put(1, sp.load(context,R.raw.key_sound01, 1));
        spMap.put(2, sp.load(context,R.raw.key_sound02, 1));
    }

    public void playSound(int sound,int number){	//播放声音,参数sound是播放音效的id，参数number是播放音效的次数
        AudioManager am=(AudioManager)context.getSystemService(context.AUDIO_SERVICE);//实例化AudioManager对象

        float audioMaxVolumn=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);	//返回当前AudioManager对象的最大音量值
        float audioCurrentVolumn=am.getStreamVolume(AudioManager.STREAM_MUSIC);//返回当前AudioManager对象的音量值
        float volumnRatio=audioCurrentVolumn/audioMaxVolumn;
        sp.play(
                spMap.get(sound),	//从声音池获取播放的音乐id
                volumnRatio, 		//左声道音量
                volumnRatio, 		//右声道音量
                1, 					//优先级，0为最低
                number, 		    //循环次数，0无不循环，-1无永远循环
                1					//回放速度 ，该值在0.5-2.0之间，1为正常速度
        );
    }


}
