package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhengxiaohu on 2017/3/10.
 */

@Entity
public class Material {
    //材料属性
    @Id(autoincrement = true)
    private Long id;                                  //主键:本材料的id
    private Long materialCategoriesId;              //外键:所属的材料分类的外键id

    private String name;                              //材料名称
    private String ingredient;                       //成分
    private String hardness;                         //硬度
    private String density;                          //密度
    private String thermalConductivity;            //导热系数
    private String specificHeatCapacity;           //比热容
    private String youngsModulus;                  //杨氏模量
    private String impactStrength;                 //冲击强度
    private String extension;                       //延伸量
    private String areaReduction;                  //面积减少量
    private String conductiveCoefficient;          //导电系数
    private String condition;                       //条件
    private String tensileStrength;                //拉伸强度
    private String yieldStrength;                  //屈服强度
    private String shearStrength;                  //剪切强度
    private String heatTreatment;                  //热处理
    private String lowMeltingPoint;                //熔点(低)
    private String highMeltingPoint;               //熔点(高)
    private String thermalExpansionCoefficient;  //热膨胀系数
    private String standard;                        //材料的标准
    @Generated(hash = 757852714)
    public Material(Long id, Long materialCategoriesId, String name,
            String ingredient, String hardness, String density,
            String thermalConductivity, String specificHeatCapacity,
            String youngsModulus, String impactStrength, String extension,
            String areaReduction, String conductiveCoefficient, String condition,
            String tensileStrength, String yieldStrength, String shearStrength,
            String heatTreatment, String lowMeltingPoint, String highMeltingPoint,
            String thermalExpansionCoefficient, String standard) {
        this.id = id;
        this.materialCategoriesId = materialCategoriesId;
        this.name = name;
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
    @Generated(hash = 1176792654)
    public Material() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMaterialCategoriesId() {
        return this.materialCategoriesId;
    }
    public void setMaterialCategoriesId(Long materialCategoriesId) {
        this.materialCategoriesId = materialCategoriesId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIngredient() {
        return this.ingredient;
    }
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public String getHardness() {
        return this.hardness;
    }
    public void setHardness(String hardness) {
        this.hardness = hardness;
    }
    public String getDensity() {
        return this.density;
    }
    public void setDensity(String density) {
        this.density = density;
    }
    public String getThermalConductivity() {
        return this.thermalConductivity;
    }
    public void setThermalConductivity(String thermalConductivity) {
        this.thermalConductivity = thermalConductivity;
    }
    public String getSpecificHeatCapacity() {
        return this.specificHeatCapacity;
    }
    public void setSpecificHeatCapacity(String specificHeatCapacity) {
        this.specificHeatCapacity = specificHeatCapacity;
    }
    public String getYoungsModulus() {
        return this.youngsModulus;
    }
    public void setYoungsModulus(String youngsModulus) {
        this.youngsModulus = youngsModulus;
    }
    public String getImpactStrength() {
        return this.impactStrength;
    }
    public void setImpactStrength(String impactStrength) {
        this.impactStrength = impactStrength;
    }
    public String getExtension() {
        return this.extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public String getAreaReduction() {
        return this.areaReduction;
    }
    public void setAreaReduction(String areaReduction) {
        this.areaReduction = areaReduction;
    }
    public String getConductiveCoefficient() {
        return this.conductiveCoefficient;
    }
    public void setConductiveCoefficient(String conductiveCoefficient) {
        this.conductiveCoefficient = conductiveCoefficient;
    }
    public String getCondition() {
        return this.condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getTensileStrength() {
        return this.tensileStrength;
    }
    public void setTensileStrength(String tensileStrength) {
        this.tensileStrength = tensileStrength;
    }
    public String getYieldStrength() {
        return this.yieldStrength;
    }
    public void setYieldStrength(String yieldStrength) {
        this.yieldStrength = yieldStrength;
    }
    public String getShearStrength() {
        return this.shearStrength;
    }
    public void setShearStrength(String shearStrength) {
        this.shearStrength = shearStrength;
    }
    public String getHeatTreatment() {
        return this.heatTreatment;
    }
    public void setHeatTreatment(String heatTreatment) {
        this.heatTreatment = heatTreatment;
    }
    public String getLowMeltingPoint() {
        return this.lowMeltingPoint;
    }
    public void setLowMeltingPoint(String lowMeltingPoint) {
        this.lowMeltingPoint = lowMeltingPoint;
    }
    public String getHighMeltingPoint() {
        return this.highMeltingPoint;
    }
    public void setHighMeltingPoint(String highMeltingPoint) {
        this.highMeltingPoint = highMeltingPoint;
    }
    public String getThermalExpansionCoefficient() {
        return this.thermalExpansionCoefficient;
    }
    public void setThermalExpansionCoefficient(String thermalExpansionCoefficient) {
        this.thermalExpansionCoefficient = thermalExpansionCoefficient;
    }
    public String getStandard() {
        return this.standard;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }

}
