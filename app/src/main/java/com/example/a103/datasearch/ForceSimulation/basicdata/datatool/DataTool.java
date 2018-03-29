package com.example.a103.datasearch.forcesimulation.basicdata.datatool;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataTool {

    //半径
    double radius;
    //刃长
    double lenFlute;
    //悬长
    double lenGauge;
    //螺旋角
    double helix;
    //齿数
    double toolTeethNum;

    public DataTool(double radius, double lenFlute, double lenGauge, double helix, double toolTeethNum) {
        this.radius = radius;
        this.lenFlute = lenFlute;
        this.lenGauge = lenGauge;
        this.helix = helix;
        this.toolTeethNum = toolTeethNum;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getLenFlute() {
        return lenFlute;
    }

    public void setLenFlute(double lenFlute) {
        this.lenFlute = lenFlute;
    }

    public double getLenGauge() {
        return lenGauge;
    }

    public void setLenGauge(double lenGauge) {
        this.lenGauge = lenGauge;
    }

    public double getHelix() {
        return helix;
    }

    public void setHelix(double helix) {
        this.helix = helix;
    }

    public double getToolTeethNum() {
        return toolTeethNum;
    }

    public void setToolTeethNum(double toolTeethNum) {
        this.toolTeethNum = toolTeethNum;
    }
}
