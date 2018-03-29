package com.example.a103.datasearch.forcesimulation.basicdata.datasimumax;

/**
 * Created by XiaoHu Zheng on 2018/3/27.
 * Email: 1050087728@qq.com
 *
 * 仿真数据最大值类
 */

public class DataSimulationMax {

    private double Fx;
    private double Fy;
    private double Fz;
    private double Fxy;
    private double Fxyz;
    private double Ft;
    private double Fr;
    private double Fa;
    private double torque;
    private double power;
    private double bend;

    public DataSimulationMax(double fx, double fy, double fz, double fxy, double fxyz, double ft, double fr, double fa, double torque, double power, double bend) {
        Fx = fx;
        Fy = fy;
        Fz = fz;
        Fxy = fxy;
        Fxyz = fxyz;
        Ft = ft;
        Fr = fr;
        Fa = fa;
        this.torque = torque;
        this.power = power;
        this.bend = bend;
    }

    public DataSimulationMax(){

    }

    public double getFx() {
        return Fx;
    }

    public void setFx(double fx) {
        Fx = fx;
    }

    public double getFy() {
        return Fy;
    }

    public void setFy(double fy) {
        Fy = fy;
    }

    public double getFz() {
        return Fz;
    }

    public void setFz(double fz) {
        Fz = fz;
    }

    public double getFxy() {
        return Fxy;
    }

    public void setFxy(double fxy) {
        Fxy = fxy;
    }

    public double getFxyz() {
        return Fxyz;
    }

    public void setFxyz(double fxyz) {
        Fxyz = fxyz;
    }

    public double getFt() {
        return Ft;
    }

    public void setFt(double ft) {
        Ft = ft;
    }

    public double getFr() {
        return Fr;
    }

    public void setFr(double fr) {
        Fr = fr;
    }

    public double getFa() {
        return Fa;
    }

    public void setFa(double fa) {
        Fa = fa;
    }

    public double getTorque() {
        return torque;
    }

    public void setTorque(double torque) {
        this.torque = torque;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getBend() {
        return bend;
    }

    public void setBend(double bend) {
        this.bend = bend;
    }
}
