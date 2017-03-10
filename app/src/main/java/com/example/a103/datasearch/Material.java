package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/3/9.
 * 工件材料的类，除了才来的基本属性还包括切削力系数以及工件对材料的切削限制
 */

public class Material {
    private final MaterialProperties mMaterialProperties;       //材料基本属性
    private final CoefficientParameters mCoefficientParameters; //切削力系数
    private final MaterialCuttingLimits mMaterialLimits;         //对材料的切削限制

    public Material(final MaterialProperties materialProperties,
                    final CoefficientParameters coefficientParameters,
                    final MaterialCuttingLimits materialLimits) {
        mMaterialProperties = materialProperties;
        mCoefficientParameters = coefficientParameters;
        mMaterialLimits = materialLimits;
    }

    public MaterialProperties getMaterialProperties() {
        return mMaterialProperties;
    }

    public CoefficientParameters getCoefficientParameters() {
        return mCoefficientParameters;
    }

    public MaterialCuttingLimits getMaterialLimits() {
        return mMaterialLimits;
    }

}
