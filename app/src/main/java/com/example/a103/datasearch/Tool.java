package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/2/22.
 * 超类:Tool 描述通用整体立铣刀的数据模型
 */

public class Tool {

    //刀具基本信息描述
    private int id;                                  //刀具ID号
    private String name;                             //刀具名称
    private String type;                             //刀具类型
    private String serial;                           //刀具型号
    private String brand;                            //刀具制造商

    private String cuttingDiameter;                //切削直径
    private String depthOfCutMaximum;              //最大切深
    private String maxRampingAngle;                  //最大坡走铣角度
    private String usableLength;                   //有用长度
    private String peripheralEffectiveCuttingEdgeCount;   //周边有效切削刃数
    private String adaptiveInterfaceMachineDirection;     //机床侧适配接口
    private String connectionDiameterTolerance;  //刀柄直径公差
    private String grade;                          //材质
    private String substrate;                     //基底
    private String coating;                       //涂层
    private String basicStandardGroup;           //基本标准组
    private String coolantEntryStyleCode;       //冷却液入口型式代码
    private String connectionDiameter;          //连接直径
    private String functionalLength;            //功能长度
    private String fluteHelixAngle;             //容屑槽螺旋角
    private String radialRakeAngle;             //径向前角
    private String axialRakeAngle;              //轴向前角
    private String maximumRegrinds;             //最大重新研磨次数
    private String maxRotationalSpeed;         //最大转速
    private String weight;                       //部件重量
    private String lifeCycleState;             //寿命周期状态
    private String suitableForMaterial;       //适合加工材料
    private String application;                //适用场合

    public Tool(int id, String name, String type, String serial, String brand, String cuttingDiameter,
                String depthOfCutMaximum, String maxRampingAngle, String usableLength,
                String peripheralEffectiveCuttingEdgeCount, String adaptiveInterfaceMachineDirection,
                String connectionDiameterTolerance, String grade, String substrate, String coating,
                String basicStandardGroup, String coolantEntryStyleCode, String connectionDiameter,
                String functionalLength, String fluteHelixAngle, String radialRakeAngle, String axialRakeAngle,
                String maximumRegrinds, String maxRotationalSpeed, String weight, String lifeCycleState,
                String suitableForMaterial, String application) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.serial = serial;
        this.brand = brand;
        this.cuttingDiameter = cuttingDiameter;
        this.depthOfCutMaximum = depthOfCutMaximum;
        this.maxRampingAngle = maxRampingAngle;
        this.usableLength = usableLength;
        this.peripheralEffectiveCuttingEdgeCount = peripheralEffectiveCuttingEdgeCount;
        this.adaptiveInterfaceMachineDirection = adaptiveInterfaceMachineDirection;
        this.connectionDiameterTolerance = connectionDiameterTolerance;
        this.grade = grade;
        this.substrate = substrate;
        this.coating = coating;
        this.basicStandardGroup = basicStandardGroup;
        this.coolantEntryStyleCode = coolantEntryStyleCode;
        this.connectionDiameter = connectionDiameter;
        this.functionalLength = functionalLength;
        this.fluteHelixAngle = fluteHelixAngle;
        this.radialRakeAngle = radialRakeAngle;
        this.axialRakeAngle = axialRakeAngle;
        this.maximumRegrinds = maximumRegrinds;
        this.maxRotationalSpeed = maxRotationalSpeed;
        this.weight = weight;
        this.lifeCycleState = lifeCycleState;
        this.suitableForMaterial = suitableForMaterial;
        this.application = application;
    }
//构造函数

    //设置刀具的基本信息
    public void setId(int id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setType(String type){
        this.type=type;
    }

    public void setSerial(String serial){
        this.serial=serial;
    }

    public void setBrand(String brand){
        this.brand=brand;
    }

    public void setCuttingDiameter(String cuttingDiameter){
        this.cuttingDiameter=cuttingDiameter;
    }

    public void setDepthOfCutMaximum(String depthOfCutMaximum){
        this.depthOfCutMaximum=depthOfCutMaximum;
    }

    public void setMaxRampingAngle(String maxRampingAngle){
        this.maxRampingAngle=maxRampingAngle;
    }

    public void setUsableLength(String usableLength){
        this.usableLength=usableLength;
    }

    public void setPeripheralEffectiveCuttingEdgeCount(String peripheralEffectiveCuttingEdgeCount){
        this.peripheralEffectiveCuttingEdgeCount=peripheralEffectiveCuttingEdgeCount;
    }

    public void setAdaptiveInterfaceMachineDirection(String adaptiveInterfaceMachineDirection){
        this.adaptiveInterfaceMachineDirection=adaptiveInterfaceMachineDirection;
    }

    public void setConnectionDiameterTolerance(String connectionDiameterTolerance){
        this.connectionDiameterTolerance=connectionDiameterTolerance;
    }

    public void setGrade(String grade){
        this.grade=grade;
    }

    public void setSubstrate(String substrate){
        this.substrate=substrate;
    }

    public void setCoating(String coating){
        this.coating=coating;
    }

    public void setBasicStandardGroup(String basicStandardGroup){
        this.basicStandardGroup=basicStandardGroup;
    }

    public void setCoolantEntryStyleCode(String coolantEntryStyleCode){
        this.coolantEntryStyleCode=coolantEntryStyleCode;
    }

    public void setConnectionDiameter(String connectionDiameter){
        this.connectionDiameter=connectionDiameter;
    }

    public void setFunctionalLength(String functionalLength){
        this.functionalLength=functionalLength;
    }

    public void setFluteHelixAngle(String fluteHelixAngle){
        this.fluteHelixAngle=fluteHelixAngle;
    }

    public void setRadialRakeAngle(String radialRakeAngle){
        this.radialRakeAngle=radialRakeAngle;
    }

    public void setAxialRakeAngle(String axialRakeAngle){
        this.axialRakeAngle=axialRakeAngle;
    }

    public void setMaximumRegrinds(String maximumRegrinds){
        this.maximumRegrinds=maximumRegrinds;
    }

    public void setMaxRotationalSpeed(String maxRotationalSpeed){
        this.maxRotationalSpeed=maxRotationalSpeed;
    }

    public void setWeight(String weight){
        this.weight=weight;
    }

    public void setLifeCycleState(String lifeCycleState){
        this.lifeCycleState=lifeCycleState;
    }

    public void setSuitableForMaterial(String suitableForMaterial){
        this.suitableForMaterial=suitableForMaterial;
    }

    public void setApplication(String application){
        this.application=application;
    }

    //获取刀具的基本信息

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSerial() {
        return serial;
    }

    public String getBrand() {
        return brand;
    }

    public String getCuttingDiameter() {
        return cuttingDiameter;
    }

    public String getDepthOfCutMaximum() {
        return depthOfCutMaximum;
    }

    public String getMaxRampingAngle() {
        return maxRampingAngle;
    }

    public String getUsableLength() {
        return usableLength;
    }

    public String getPeripheralEffectiveCuttingEdgeCount() {
        return peripheralEffectiveCuttingEdgeCount;
    }

    public String getAdaptiveInterfaceMachineDirection() {
        return adaptiveInterfaceMachineDirection;
    }

    public String getConnectionDiameterTolerance() {
        return connectionDiameterTolerance;
    }

    public String getGrade() {
        return grade;
    }

    public String getSubstrate() {
        return substrate;
    }

    public String getCoating() {
        return coating;
    }

    public String getBasicStandardGroup() {
        return basicStandardGroup;
    }

    public String getCoolantEntryStyleCode() {
        return coolantEntryStyleCode;
    }

    public String getConnectionDiameter() {
        return connectionDiameter;
    }

    public String getFunctionalLength() {
        return functionalLength;
    }

    public String getFluteHelixAngle() {
        return fluteHelixAngle;
    }

    public String getRadialRakeAngle() {
        return radialRakeAngle;
    }

    public String getAxialRakeAngle() {
        return axialRakeAngle;
    }

    public String getMaximumRegrinds() {
        return maximumRegrinds;
    }

    public String getMaxRotationalSpeed() {
        return maxRotationalSpeed;
    }

    public String getWeight() {
        return weight;
    }

    public String getLifeCycleState() {
        return lifeCycleState;
    }

    public String getSuitableForMaterial() {
        return suitableForMaterial;
    }

    public String getApplication() {
        return application;
    }
}
