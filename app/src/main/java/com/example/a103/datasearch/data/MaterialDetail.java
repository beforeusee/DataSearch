package com.example.a103.datasearch.data;

/**
 * Created by XiaoHu Zheng on 2017/6/22.
 * Email: 1050087728@qq.com
 */

/**
 * 材料详情，包括Material,MaterialCuttingLimits,CoefficientParameters三个参数
 */
public class MaterialDetail {
    private Material mMaterial;
    private CoefficientParameters mCoefficientParameters;
    private MaterialCuttingLimits mMaterialCuttingLimits;

    public Material getMaterial() {
        return mMaterial;
    }

    public void setMaterial(Material material) {
        mMaterial = material;
    }

    public CoefficientParameters getCoefficientParameters() {
        return mCoefficientParameters;
    }

    public void setCoefficientParameters(CoefficientParameters coefficientParameters) {
        mCoefficientParameters = coefficientParameters;
    }

    public MaterialCuttingLimits getMaterialCuttingLimits() {
        return mMaterialCuttingLimits;
    }

    public void setMaterialCuttingLimits(MaterialCuttingLimits materialCuttingLimits) {
        mMaterialCuttingLimits = materialCuttingLimits;
    }
}
