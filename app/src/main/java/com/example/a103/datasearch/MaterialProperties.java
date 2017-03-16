package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/3/10.
 */

public class MaterialProperties {
    //材料属性
    private final String name;                              //材料名称
    private final String categories;                       //种类
    private final String ingredient;                       //成分
    private final String hardness;                         //硬度
    private final String density;                          //密度
    private final String thermalConductivity;            //导热系数
    private final String specificHeatCapacity;           //比热容
    private final String youngsModulus;                  //杨氏模量
    private final String impactStrength;                 //冲击强度
    private final String extension;                       //延伸量
    private final String areaReduction;                  //面积减少量
    private final String conductiveCoefficient;          //导电系数
    private final String condition;                       //条件
    private final String tensileStrength;                //拉伸强度
    private final String yieldStrength;                  //屈服强度
    private final String shearStrength;                  //剪切强度
    private final String heatTreatment;                  //热处理
    private final String lowMeltingPoint;                //熔点(低)
    private final String highMeltingPoint;               //熔点(高)
    private final String thermalExpansionCoefficient;  //热膨胀系数
    private final String standard;            //材料的标准

    public MaterialProperties(final String name,
                              final String categories,
                              final String ingredient,
                              final String hardness,
                              final String density,
                              final String thermalConductivity,
                              final String specificHeatCapacity,
                              final String youngsModulus,
                              final String impactStrength,
                              final String extension,
                              final String areaReduction,
                              final String conductiveCoefficient,
                              final String condition,
                              final String tensileStrength,
                              final String yieldStrength,
                              final String shearStrength,
                              final String heatTreatment,
                              final String lowMeltingPoint,
                              final String highMeltingPoint,
                              final String thermalExpansionCoefficient,
                              final String standard) {
        this.name = name;
        this.categories = categories;
        this.ingredient = ingredient;
        this.hardness = hardness;
        this.density = density;
        this.thermalConductivity = thermalConductivity;
        this.specificHeatCapacity = specificHeatCapacity;
        this.youngsModulus = youngsModulus;
        this.impactStrength = impactStrength;
        this.extension = extension;
        this.areaReduction = areaReduction;
        this.conductiveCoefficient = conductiveCoefficient;
        this.condition = condition;
        this.tensileStrength = tensileStrength;
        this.yieldStrength = yieldStrength;
        this.shearStrength = shearStrength;
        this.heatTreatment = heatTreatment;
        this.lowMeltingPoint = lowMeltingPoint;
        this.highMeltingPoint = highMeltingPoint;
        this.thermalExpansionCoefficient = thermalExpansionCoefficient;
        this.standard = standard;
    }

    public String getName() {
        return name;
    }

    public String getCategories() {
        return categories;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getHardness() {
        return hardness;
    }

    public String getDensity() {
        return density;
    }

    public String getThermalConductivity() {
        return thermalConductivity;
    }

    public String getSpecificHeatCapacity() {
        return specificHeatCapacity;
    }

    public String getYoungsModulus() {
        return youngsModulus;
    }

    public String getImpactStrength() {
        return impactStrength;
    }

    public String getExtension() {
        return extension;
    }

    public String getAreaReduction() {
        return areaReduction;
    }

    public String getConductiveCoefficient() {
        return conductiveCoefficient;
    }

    public String getCondition() {
        return condition;
    }

    public String getTensileStrength() {
        return tensileStrength;
    }

    public String getYieldStrength() {
        return yieldStrength;
    }

    public String getShearStrength() {
        return shearStrength;
    }

    public String getHeatTreatment() {
        return heatTreatment;
    }

    public String getLowMeltingPoint() {
        return lowMeltingPoint;
    }

    public String getHighMeltingPoint() {
        return highMeltingPoint;
    }

    public String getThermalExpansionCoefficient() {
        return thermalExpansionCoefficient;
    }

    public String getStandard() {
        return standard;
    }


    @Override
    public String toString() {
        return name+","+categories+","+ingredient+","+hardness+","+density+","+thermalConductivity+
                ","+specificHeatCapacity+","+youngsModulus+","+impactStrength+","+extension+","+
                areaReduction+","+conductiveCoefficient+","+condition+","+tensileStrength+","+
                yieldStrength+","+shearStrength+","+heatTreatment+","+lowMeltingPoint+","+
                highMeltingPoint+","+thermalExpansionCoefficient+","+standard;
    }
}
