package com.example.a103.datasearch.forcesimulation.basicdata.datasimupara;
import com.example.a103.datasearch.utils.Constant;

import static com.example.a103.datasearch.utils.Constant.PI;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataSimulationPara {

    //轴向仿真步长mm
    private double dz;
    //角度仿真步长
    private double dPhi;
    //仿真周期数
    private int periods;

    //切入角
    private double phiIn;
    //切出角
    private double phiOut;

    public DataSimulationPara(){

    }

    public DataSimulationPara(double dz, double dPhi, int periods, double phiIn, double phiOut) {
        this.dz = dz;
        this.dPhi = dPhi;
        this.periods = periods;
        this.phiIn = phiIn;
        this.phiOut = phiOut;
    }

    public double getDz() {
        return dz;
    }

    public void setDz(double dz) {
        this.dz = dz;
    }

    public double getdPhi() {
        return dPhi;
    }

    public void setdPhi(double dPhi) {
        this.dPhi = dPhi;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public double getPhiIn() {
        return phiIn;
    }

    public void setPhiIn(double phiIn) {
        this.phiIn = phiIn;
    }

    public double getPhiOut() {
        return phiOut;
    }

    public void setPhiOut(double phiOut) {
        this.phiOut = phiOut;
    }
}
