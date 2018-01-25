package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by XiaoHu Zheng on 2017/7/11.
 * Email: 1050087728@qq.com
 */

@Entity
public class Machine {
    //机床id
    @Id(autoincrement = true)
    private Long id;

    //机床基本信息
    //机床名称
    private String name;
    //机床类型
    private String type;
    //机床型号
    private String series;
    //机床制造商
    private String brand;
    //坐标轴数
    private String axisNum;
    //控制系统
    private String controlSYS;
    //机床工作台尺寸
    private String machineTableSize;
    //最大工件重量
    private String maxWorkpieceWeight;
    //是否支持自动换刀
    private String autoToolChange;
    //是否有回转工作台
    private String rotaryTable;
    //机床重量
    private String weight;
    //机床外形尺寸
    private String size;
    //机床布局
    private String layout;

    //主轴信息
    //主轴类型
    private String spindleType;
    //主轴数
    private String spindleNum;
    //最高转速
    private String maxSpindleRpm;
    //最低转速
    private String minSpindleRpm;
    //主轴功率
    private String spindlePower;
    //主轴最大输出扭矩
    private String maxSpindleTorque;
    //主轴行程
    private String spindleTravel;
    //主轴距工作台尺寸
    private String spindleDistanceToTable;
    //支持的刀柄型号
    private String spindleToolAdopter;

    //进给轴信息
    //进给轴数
    private String feedAxisNum;
    //进给轴方向
    private String feedAxisDirection;
    //进给轴行程
    private String feedAxisTravel;
    //定位精度
    private String feedAxisAccuracy;
    //重复定位精度
    private String feedAxisRepeatability;
    //快速进给速度
    private String feedAxisFastSpeed;
    //切削进给
    private String feedAxisCuttingSpeed;
    //最大加速度
    private String feedAxisMaxAcceleration;
    //伺服电机额定功率
    private String feedAxisPower;
    //伺服电机额定扭矩
    private String feedAxisTorque;
    //伺服电机最大扭矩
    private String feedAxisMaxTorque;

    //刀库信息
    //刀库容量
    private String toolLibraryToolNum;
    //刀具最大直径
    private String toolLibraryMaxToolDiameter;
    //刀具最大长度
    private String toolLibraryMaxToolLength;
    //刀具最大重量
    private String toolLibraryMaxToolWeight;

    //机床说明
    private String machineIntroduction;

    //机床照片的路径
    private String imageUri;

    @Generated(hash = 1273920150)
    public Machine(Long id, String name, String type, String series, String brand,
            String axisNum, String controlSYS, String machineTableSize,
            String maxWorkpieceWeight, String autoToolChange, String rotaryTable,
            String weight, String size, String layout, String spindleType,
            String spindleNum, String maxSpindleRpm, String minSpindleRpm,
            String spindlePower, String maxSpindleTorque, String spindleTravel,
            String spindleDistanceToTable, String spindleToolAdopter,
            String feedAxisNum, String feedAxisDirection, String feedAxisTravel,
            String feedAxisAccuracy, String feedAxisRepeatability,
            String feedAxisFastSpeed, String feedAxisCuttingSpeed,
            String feedAxisMaxAcceleration, String feedAxisPower,
            String feedAxisTorque, String feedAxisMaxTorque,
            String toolLibraryToolNum, String toolLibraryMaxToolDiameter,
            String toolLibraryMaxToolLength, String toolLibraryMaxToolWeight,
            String machineIntroduction, String imageUri) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.series = series;
        this.brand = brand;
        this.axisNum = axisNum;
        this.controlSYS = controlSYS;
        this.machineTableSize = machineTableSize;
        this.maxWorkpieceWeight = maxWorkpieceWeight;
        this.autoToolChange = autoToolChange;
        this.rotaryTable = rotaryTable;
        this.weight = weight;
        this.size = size;
        this.layout = layout;
        this.spindleType = spindleType;
        this.spindleNum = spindleNum;
        this.maxSpindleRpm = maxSpindleRpm;
        this.minSpindleRpm = minSpindleRpm;
        this.spindlePower = spindlePower;
        this.maxSpindleTorque = maxSpindleTorque;
        this.spindleTravel = spindleTravel;
        this.spindleDistanceToTable = spindleDistanceToTable;
        this.spindleToolAdopter = spindleToolAdopter;
        this.feedAxisNum = feedAxisNum;
        this.feedAxisDirection = feedAxisDirection;
        this.feedAxisTravel = feedAxisTravel;
        this.feedAxisAccuracy = feedAxisAccuracy;
        this.feedAxisRepeatability = feedAxisRepeatability;
        this.feedAxisFastSpeed = feedAxisFastSpeed;
        this.feedAxisCuttingSpeed = feedAxisCuttingSpeed;
        this.feedAxisMaxAcceleration = feedAxisMaxAcceleration;
        this.feedAxisPower = feedAxisPower;
        this.feedAxisTorque = feedAxisTorque;
        this.feedAxisMaxTorque = feedAxisMaxTorque;
        this.toolLibraryToolNum = toolLibraryToolNum;
        this.toolLibraryMaxToolDiameter = toolLibraryMaxToolDiameter;
        this.toolLibraryMaxToolLength = toolLibraryMaxToolLength;
        this.toolLibraryMaxToolWeight = toolLibraryMaxToolWeight;
        this.machineIntroduction = machineIntroduction;
        this.imageUri = imageUri;
    }

    @Generated(hash = 1796908865)
    public Machine() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeries() {
        return this.series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAxisNum() {
        return this.axisNum;
    }

    public void setAxisNum(String axisNum) {
        this.axisNum = axisNum;
    }

    public String getControlSYS() {
        return this.controlSYS;
    }

    public void setControlSYS(String controlSYS) {
        this.controlSYS = controlSYS;
    }

    public String getMachineTableSize() {
        return this.machineTableSize;
    }

    public void setMachineTableSize(String machineTableSize) {
        this.machineTableSize = machineTableSize;
    }

    public String getMaxWorkpieceWeight() {
        return this.maxWorkpieceWeight;
    }

    public void setMaxWorkpieceWeight(String maxWorkpieceWeight) {
        this.maxWorkpieceWeight = maxWorkpieceWeight;
    }

    public String getAutoToolChange() {
        return this.autoToolChange;
    }

    public void setAutoToolChange(String autoToolChange) {
        this.autoToolChange = autoToolChange;
    }

    public String getRotaryTable() {
        return this.rotaryTable;
    }

    public void setRotaryTable(String rotaryTable) {
        this.rotaryTable = rotaryTable;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLayout() {
        return this.layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getSpindleType() {
        return this.spindleType;
    }

    public void setSpindleType(String spindleType) {
        this.spindleType = spindleType;
    }

    public String getSpindleNum() {
        return this.spindleNum;
    }

    public void setSpindleNum(String spindleNum) {
        this.spindleNum = spindleNum;
    }

    public String getMaxSpindleRpm() {
        return this.maxSpindleRpm;
    }

    public void setMaxSpindleRpm(String maxSpindleRpm) {
        this.maxSpindleRpm = maxSpindleRpm;
    }

    public String getMinSpindleRpm() {
        return this.minSpindleRpm;
    }

    public void setMinSpindleRpm(String minSpindleRpm) {
        this.minSpindleRpm = minSpindleRpm;
    }

    public String getSpindlePower() {
        return this.spindlePower;
    }

    public void setSpindlePower(String spindlePower) {
        this.spindlePower = spindlePower;
    }

    public String getMaxSpindleTorque() {
        return this.maxSpindleTorque;
    }

    public void setMaxSpindleTorque(String maxSpindleTorque) {
        this.maxSpindleTorque = maxSpindleTorque;
    }

    public String getSpindleTravel() {
        return this.spindleTravel;
    }

    public void setSpindleTravel(String spindleTravel) {
        this.spindleTravel = spindleTravel;
    }

    public String getSpindleDistanceToTable() {
        return this.spindleDistanceToTable;
    }

    public void setSpindleDistanceToTable(String spindleDistanceToTable) {
        this.spindleDistanceToTable = spindleDistanceToTable;
    }

    public String getSpindleToolAdopter() {
        return this.spindleToolAdopter;
    }

    public void setSpindleToolAdopter(String spindleToolAdopter) {
        this.spindleToolAdopter = spindleToolAdopter;
    }

    public String getFeedAxisNum() {
        return this.feedAxisNum;
    }

    public void setFeedAxisNum(String feedAxisNum) {
        this.feedAxisNum = feedAxisNum;
    }

    public String getFeedAxisDirection() {
        return this.feedAxisDirection;
    }

    public void setFeedAxisDirection(String feedAxisDirection) {
        this.feedAxisDirection = feedAxisDirection;
    }

    public String getFeedAxisTravel() {
        return this.feedAxisTravel;
    }

    public void setFeedAxisTravel(String feedAxisTravel) {
        this.feedAxisTravel = feedAxisTravel;
    }

    public String getFeedAxisAccuracy() {
        return this.feedAxisAccuracy;
    }

    public void setFeedAxisAccuracy(String feedAxisAccuracy) {
        this.feedAxisAccuracy = feedAxisAccuracy;
    }

    public String getFeedAxisRepeatability() {
        return this.feedAxisRepeatability;
    }

    public void setFeedAxisRepeatability(String feedAxisRepeatability) {
        this.feedAxisRepeatability = feedAxisRepeatability;
    }

    public String getFeedAxisFastSpeed() {
        return this.feedAxisFastSpeed;
    }

    public void setFeedAxisFastSpeed(String feedAxisFastSpeed) {
        this.feedAxisFastSpeed = feedAxisFastSpeed;
    }

    public String getFeedAxisCuttingSpeed() {
        return this.feedAxisCuttingSpeed;
    }

    public void setFeedAxisCuttingSpeed(String feedAxisCuttingSpeed) {
        this.feedAxisCuttingSpeed = feedAxisCuttingSpeed;
    }

    public String getFeedAxisMaxAcceleration() {
        return this.feedAxisMaxAcceleration;
    }

    public void setFeedAxisMaxAcceleration(String feedAxisMaxAcceleration) {
        this.feedAxisMaxAcceleration = feedAxisMaxAcceleration;
    }

    public String getFeedAxisPower() {
        return this.feedAxisPower;
    }

    public void setFeedAxisPower(String feedAxisPower) {
        this.feedAxisPower = feedAxisPower;
    }

    public String getFeedAxisTorque() {
        return this.feedAxisTorque;
    }

    public void setFeedAxisTorque(String feedAxisTorque) {
        this.feedAxisTorque = feedAxisTorque;
    }

    public String getFeedAxisMaxTorque() {
        return this.feedAxisMaxTorque;
    }

    public void setFeedAxisMaxTorque(String feedAxisMaxTorque) {
        this.feedAxisMaxTorque = feedAxisMaxTorque;
    }

    public String getToolLibraryToolNum() {
        return this.toolLibraryToolNum;
    }

    public void setToolLibraryToolNum(String toolLibraryToolNum) {
        this.toolLibraryToolNum = toolLibraryToolNum;
    }

    public String getToolLibraryMaxToolDiameter() {
        return this.toolLibraryMaxToolDiameter;
    }

    public void setToolLibraryMaxToolDiameter(String toolLibraryMaxToolDiameter) {
        this.toolLibraryMaxToolDiameter = toolLibraryMaxToolDiameter;
    }

    public String getToolLibraryMaxToolLength() {
        return this.toolLibraryMaxToolLength;
    }

    public void setToolLibraryMaxToolLength(String toolLibraryMaxToolLength) {
        this.toolLibraryMaxToolLength = toolLibraryMaxToolLength;
    }

    public String getToolLibraryMaxToolWeight() {
        return this.toolLibraryMaxToolWeight;
    }

    public void setToolLibraryMaxToolWeight(String toolLibraryMaxToolWeight) {
        this.toolLibraryMaxToolWeight = toolLibraryMaxToolWeight;
    }

    public String getMachineIntroduction() {
        return this.machineIntroduction;
    }

    public void setMachineIntroduction(String machineIntroduction) {
        this.machineIntroduction = machineIntroduction;
    }

    public String getImageUri() {
        return this.imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

}
