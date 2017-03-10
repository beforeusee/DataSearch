package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/3/10.
 * 切削力系数参数
 */

public class CoefficientParameters {
    //切削力系数参数变量
    private final String forceModel;          //力模型
    private final String Kte;                  //切向刃口力系数
    private final String Kre;                  //径向刃口力系数
    private final String Kae;                  //轴向刃口力系数
    private final String Ktc;                  //切向铣削力系数
    private final String Krc;                  //径向铣削力系数
    private final String Kac;                  //轴向铣削力系数

    public CoefficientParameters(final String forceModel,
                                 final String kte,
                                 final String kre,
                                 final String kae,
                                 final String ktc,
                                 final String krc,
                                 final String kac) {
        this.forceModel = forceModel;
        this.Kte = kte;
        this.Kre = kre;
        this.Kae = kae;
        this.Ktc = ktc;
        this.Krc = krc;
        this.Kac = kac;
    }

    public String getForceModel() {
        return forceModel;
    }

    public String getKte() {
        return Kte;
    }

    public String getKre() {
        return Kre;
    }

    public String getKae() {
        return Kae;
    }

    public String getKtc() {
        return Ktc;
    }

    public String getKrc() {
        return Krc;
    }

    public String getKac() {
        return Kac;
    }

    @Override
    public String toString() {
        return forceModel+","+Kte+","+Kre+","+Kae+","+Ktc+","+Krc+","+Kac;
    }
}
