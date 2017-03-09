package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/3/9.
 */

public class Material {

    //材料属性
    private String name;                              //材料名称
    private String categories;                       //种类
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
    //切削力系数参数
    private String forceModel;          //力模型
    private String Kte;                  //切向刃口力系数
    private String Kre;                  //径向刃口力系数
    private String Kae;                  //轴向刃口力系数
    private String Ktc;                  //切向铣削力系数
    private String Krc;                  //径向铣削力系数
    private String Kac;                  //轴向铣削力系数
    //极限约束参数
    private String minChipThickness;   //切屑厚度(最小)
    private String maxChipThickness;   //切屑厚度(最大)
    private String minCuttingSpeed;    //切屑厚度(最小)
    private String maxCuttingSpeed;    //切屑厚度(最大)
    private String minRakeAngle;        //前角(最小)
    private String maxRakeAngle;        //前角(最大)
    //材料遵循的标准
    private String standard;            //材料的标准

    public Material() {
    }

    /**
     * 参数太多，考虑用构建者模式
     * @param name
     * @param categories
     * @param ingredient
     * @param hardness
     * @param density
     * @param thermalConductivity
     * @param specificHeatCapacity
     * @param youngsModulus
     * @param impactStrength
     * @param extension
     * @param areaReduction
     * @param conductiveCoefficient
     * @param condition
     * @param tensileStrength
     * @param yieldStrength
     * @param shearStrength
     * @param heatTreatment
     * @param lowMeltingPoint
     * @param highMeltingPoint
     * @param thermalExpansionCoefficient
     * @param forceModel
     * @param kte
     * @param kre
     * @param kae
     * @param ktc
     * @param krc
     * @param kac
     * @param minChipThickness
     * @param maxChipThickness
     * @param minCuttingSpeed
     * @param maxCuttingSpeed
     * @param minRakeAngle
     * @param maxRakeAngle
     * @param standard
     */

    //TODO 构造函数参数太多，考虑无参构造或者构建者模式
    public Material(String name,
                    String categories,
                    String ingredient,
                    String hardness,
                    String density,
                    String thermalConductivity,
                    String specificHeatCapacity,
                    String youngsModulus,
                    String impactStrength,
                    String extension,
                    String areaReduction,
                    String conductiveCoefficient,
                    String condition,
                    String tensileStrength,
                    String yieldStrength,
                    String shearStrength,
                    String heatTreatment,
                    String lowMeltingPoint,
                    String highMeltingPoint,
                    String thermalExpansionCoefficient,
                    String forceModel,
                    String kte,
                    String kre,
                    String kae,
                    String ktc,
                    String krc,
                    String kac,
                    String minChipThickness,
                    String maxChipThickness,
                    String minCuttingSpeed,
                    String maxCuttingSpeed,
                    String minRakeAngle,
                    String maxRakeAngle,
                    String standard) {
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
        this.forceModel = forceModel;
        Kte = kte;
        Kre = kre;
        Kae = kae;
        Ktc = ktc;
        Krc = krc;
        Kac = kac;
        this.minChipThickness = minChipThickness;
        this.maxChipThickness = maxChipThickness;
        this.minCuttingSpeed = minCuttingSpeed;
        this.maxCuttingSpeed = maxCuttingSpeed;
        this.minRakeAngle = minRakeAngle;
        this.maxRakeAngle = maxRakeAngle;
        this.standard = standard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getThermalConductivity() {
        return thermalConductivity;
    }

    public void setThermalConductivity(String thermalConductivity) {
        this.thermalConductivity = thermalConductivity;
    }

    public String getSpecificHeatCapacity() {
        return specificHeatCapacity;
    }

    public void setSpecificHeatCapacity(String specificHeatCapacity) {
        this.specificHeatCapacity = specificHeatCapacity;
    }

    public String getYoungsModulus() {
        return youngsModulus;
    }

    public void setYoungsModulus(String youngsModulus) {
        this.youngsModulus = youngsModulus;
    }

    public String getImpactStrength() {
        return impactStrength;
    }

    public void setImpactStrength(String impactStrength) {
        this.impactStrength = impactStrength;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getAreaReduction() {
        return areaReduction;
    }

    public void setAreaReduction(String areaReduction) {
        this.areaReduction = areaReduction;
    }

    public String getConductiveCoefficient() {
        return conductiveCoefficient;
    }

    public void setConductiveCoefficient(String conductiveCoefficient) {
        this.conductiveCoefficient = conductiveCoefficient;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTensileStrength() {
        return tensileStrength;
    }

    public void setTensileStrength(String tensileStrength) {
        this.tensileStrength = tensileStrength;
    }

    public String getYieldStrength() {
        return yieldStrength;
    }

    public void setYieldStrength(String yieldStrength) {
        this.yieldStrength = yieldStrength;
    }

    public String getShearStrength() {
        return shearStrength;
    }

    public void setShearStrength(String shearStrength) {
        this.shearStrength = shearStrength;
    }

    public String getHeatTreatment() {
        return heatTreatment;
    }

    public void setHeatTreatment(String heatTreatment) {
        this.heatTreatment = heatTreatment;
    }

    public String getLowMeltingPoint() {
        return lowMeltingPoint;
    }

    public void setLowMeltingPoint(String lowMeltingPoint) {
        this.lowMeltingPoint = lowMeltingPoint;
    }

    public String getHighMeltingPoint() {
        return highMeltingPoint;
    }

    public void setHighMeltingPoint(String highMeltingPoint) {
        this.highMeltingPoint = highMeltingPoint;
    }

    public String getThermalExpansionCoefficient() {
        return thermalExpansionCoefficient;
    }

    public void setThermalExpansionCoefficient(String thermalExpansionCoefficient) {
        this.thermalExpansionCoefficient = thermalExpansionCoefficient;
    }

    public String getForceModel() {
        return forceModel;
    }

    public void setForceModel(String forceModel) {
        this.forceModel = forceModel;
    }

    public String getKte() {
        return Kte;
    }

    public void setKte(String kte) {
        Kte = kte;
    }

    public String getKre() {
        return Kre;
    }

    public void setKre(String kre) {
        Kre = kre;
    }

    public String getKae() {
        return Kae;
    }

    public void setKae(String kae) {
        Kae = kae;
    }

    public String getKtc() {
        return Ktc;
    }

    public void setKtc(String ktc) {
        Ktc = ktc;
    }

    public String getKrc() {
        return Krc;
    }

    public void setKrc(String krc) {
        Krc = krc;
    }

    public String getKac() {
        return Kac;
    }

    public void setKac(String kac) {
        Kac = kac;
    }

    public String getMinChipThickness() {
        return minChipThickness;
    }

    public void setMinChipThickness(String minChipThickness) {
        this.minChipThickness = minChipThickness;
    }

    public String getMaxChipThickness() {
        return maxChipThickness;
    }

    public void setMaxChipThickness(String maxChipThickness) {
        this.maxChipThickness = maxChipThickness;
    }

    public String getMinCuttingSpeed() {
        return minCuttingSpeed;
    }

    public void setMinCuttingSpeed(String minCuttingSpeed) {
        this.minCuttingSpeed = minCuttingSpeed;
    }

    public String getMaxCuttingSpeed() {
        return maxCuttingSpeed;
    }

    public void setMaxCuttingSpeed(String maxCuttingSpeed) {
        this.maxCuttingSpeed = maxCuttingSpeed;
    }

    public String getMinRakeAngle() {
        return minRakeAngle;
    }

    public void setMinRakeAngle(String minRakeAngle) {
        this.minRakeAngle = minRakeAngle;
    }

    public String getMaxRakeAngle() {
        return maxRakeAngle;
    }

    public void setMaxRakeAngle(String maxRakeAngle) {
        this.maxRakeAngle = maxRakeAngle;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
