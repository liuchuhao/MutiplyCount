package com.example.lch.yunsuan;


public class Guanka {

    NumCount n;
    String getS(){
        StringBuffer s=new StringBuffer();
        int j=1;
        for(int a:n.getnum()){
            if(j==n.getnum().length){
                s.append(a+"=");
            }else{
                s.append(a+"*");}
            j++;
        }
        return s.toString();
    }
    int getGuanka(int guanka){

         n=new NumCount();
        switch(guanka){
            case 1:
                n.setnum(1,1);break;
            case 2:n.setnum(1,2);break;case 3:n.setnum(1,1,1);break;
            case 4:n.setnum(2,2);break; case 5:n.setnum(1,1,2);break;
            case 6:n.setnum(1,2,2);break;case 7:n.setnum(3,3);break;
            case 8:n.setnum(2,2,2);break;case 9:n.setnum(3,4);break;
            default:
                n.setnum(4,4);break;
        }
        return n.getproduct();
    }

}
