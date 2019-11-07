package com.example.doodledan;

import android.content.res.Resources;

public class Platform {
    private int xPos;
    private int yPos;

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Platform(){
        this.xPos = (int)(Math.random() * ((getScreenWidth() - 1) + 1)) + 1;
        this.yPos = (int)(Math.random() * ((getScreenHeight() + 1) - 1)) - 1;
    }


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
