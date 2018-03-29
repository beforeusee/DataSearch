package com.example.a103.datasearch.forcesimulation.basicdata.datacuttingpara;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataCuttingPara {

    //铣削方式，枚举，顺铣和逆铣
    private String cuttingType;
    //主轴转速
    private double spindleSpeed;
    //进给速度
    private double feedRate;
    //轴向切深
    private double cuttingDepth;
    //径向切宽
    private double cuttingWidth;

    public DataCuttingPara(){

    }

    public DataCuttingPara(String cuttingType, double spindleSpeed, double feedRate, double cuttingDepth, double cuttingWidth) {
        this.cuttingType = cuttingType;
        this.spindleSpeed = spindleSpeed;
        this.feedRate = feedRate;
        this.cuttingDepth = cuttingDepth;
        this.cuttingWidth = cuttingWidth;
    }

    public String getCuttingType() {
        return cuttingType;
    }

    public void setCuttingType(String cuttingType) {
        this.cuttingType = cuttingType;
    }

    public double getSpindleSpeed() {
        return spindleSpeed;
    }

    public void setSpindleSpeed(double spindleSpeed) {
        this.spindleSpeed = spindleSpeed;
    }

    public double getFeedRate() {
        return feedRate;
    }

    public void setFeedRate(double feedRate) {
        this.feedRate = feedRate;
    }

    public double getCuttingDepth() {
        return cuttingDepth;
    }

    public void setCuttingDepth(double cuttingDepth) {
        this.cuttingDepth = cuttingDepth;
    }

    public double getCuttingWidth() {
        return cuttingWidth;
    }

    public void setCuttingWidth(double cuttingWidth) {
        this.cuttingWidth = cuttingWidth;
    }
}
