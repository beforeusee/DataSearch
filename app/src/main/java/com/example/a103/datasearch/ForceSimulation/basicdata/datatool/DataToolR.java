package com.example.a103.datasearch.forcesimulation.basicdata.datatool;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataToolR extends DataTool {

    //圆角
    private double roundRadius;

    public DataToolR(){

    }

    public DataToolR(double radius, double lenFlute, double lenGauge, double helix, double toolTeethNum, double lagFactor, String toolType, String toolMaterial, String toolNum, double roundRadius) {
        super(radius, lenFlute, lenGauge, helix, toolTeethNum, lagFactor, toolType, toolMaterial, toolNum);
        this.roundRadius = roundRadius;
    }

    public double getRoundRadius() {
        return roundRadius;
    }

    public void setRoundRadius(double roundRadius) {
        this.roundRadius = roundRadius;
    }
}
