package com.example.a103.datasearch.forcesimulation.basicdata.datacutcoeffient;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataCutCoefficient {

    //工件材料类型
    private String materialType;
    //工件材料牌号
    private String material;
    //切向铣削力系数
    private double ktc;
    //径向铣削力系数
    private double krc;
    //轴向铣削力系数
    private double kac;
    //切向刃口力系数
    private double kte;
    //径向刃口力系数
    private double kre;
    //轴向刃口系数
    private double kae;

    public DataCutCoefficient(){

    }

    public DataCutCoefficient(String materialType, String material, double ktc, double krc, double kac, double kte, double kre, double kae) {
        this.materialType = materialType;
        this.material = material;
        this.ktc = ktc;
        this.krc = krc;
        this.kac = kac;
        this.kte = kte;
        this.kre = kre;
        this.kae = kae;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getKtc() {
        return ktc;
    }

    public void setKtc(double ktc) {
        this.ktc = ktc;
    }

    public double getKrc() {
        return krc;
    }

    public void setKrc(double krc) {
        this.krc = krc;
    }

    public double getKac() {
        return kac;
    }

    public void setKac(double kac) {
        this.kac = kac;
    }

    public double getKte() {
        return kte;
    }

    public void setKte(double kte) {
        this.kte = kte;
    }

    public double getKre() {
        return kre;
    }

    public void setKre(double kre) {
        this.kre = kre;
    }

    public double getKae() {
        return kae;
    }

    public void setKae(double kae) {
        this.kae = kae;
    }
}
