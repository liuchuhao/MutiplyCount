package com.example.lch.yunsuan;

import java.util.Random;

public class NumCount
{
    //随机生成N位数
    public int suijishu(int N){
        int sum=0,a,d=1;
        Random random=new Random();
        for(int i=0;i<N;i++){
            a=(random.nextInt(9)+1)*d;
            d=d*10;
            sum=sum+a;
        }
        return sum;
    }
    //N个参数的积
    public int product(int... N){
        int prod=1;
        for(int a:N){
            prod=prod*a;
        }
        return prod;
    }

    int newas[];
    int product;
    //设置N个N位数
    public void setnum(int ...N){
        newas=new int[N.length];
        int i=0;

        for(int a:N){
            newas[i]=suijishu(a);
            i++;
        }
        product=product(newas);
    }
    //得到N个N位数组成的数组
    public int[] getnum(){
        return newas;
    }
    //得到N个N位数的积
    public int getproduct(){
        return product;
    }
}