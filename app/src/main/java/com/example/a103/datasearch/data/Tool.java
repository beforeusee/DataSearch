package com.example.a103.datasearch.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by XiaoHu Zheng on 2017/2/22.实现序列化接口Parcelable，以通过intent在activity之间传递Tool对象
 * 超类:Tool 描述通用整体立铣刀的数据模型
 */

@Entity
public class Tool implements Parcelable{

    //刀具基本信息描述
    @Id(autoincrement = true)
    private Long id;                                  //刀具ID号,设为数据库主键
    @Property(nameInDb = "NAME")
    private String name;                             //刀具名称
    private String type;                             //刀具类型
    private String serial;                           //刀具型号
    private String brand;                            //刀具制造商

    private String cuttingDiameter;                //切削直径
    private String cuttingDiameterTOLUpper;       //切削直径公差上限
    private String cuttingDiameterTOLLower;       //切削直径公差下限
    private String filletRadius;                    //圆角半径
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
    private String axialRakeAngle;              //轴向前角(周刃前角)
    private String radialRakeAngle;             //径向前角(底刃前角)
    private String axialRearAngle;              //轴向后角(周刃后角)
    private String radialRearAngle;             //径向后角(底刃后角)
    private String cuttingEdgeAngle;            //切削刃角(主偏角)
    private String faceContactDiameter;         //面接触直径      (方肩铣刀)
    private String tipChamfer;                    //刀尖倒角      (方肩铣刀)
    private String chamferWidth;                  //倒角宽度      (方肩铣刀)
    private String centerCuttingCapability;      //中心切削能力 (方肩铣刀 )
    private String maximumRegrinds;             //最大重新研磨次数
    private String maxRotationalSpeed;         //最大转速
    private String weight;                       //部件重量
    private String lifeCycleState;             //寿命周期状态
    private String suitableForMaterial;       //适合加工材料
    private String application;                //适用场合
    private int used;                              //刀具是否在刀库中使用

    @Generated(hash = 1657437486)
    public Tool(Long id, String name, String type, String serial, String brand,
            String cuttingDiameter, String cuttingDiameterTOLUpper,
            String cuttingDiameterTOLLower, String filletRadius, String depthOfCutMaximum,
            String maxRampingAngle, String usableLength,
            String peripheralEffectiveCuttingEdgeCount,
            String adaptiveInterfaceMachineDirection, String connectionDiameterTolerance,
            String grade, String substrate, String coating, String basicStandardGroup,
            String coolantEntryStyleCode, String connectionDiameter, String functionalLength,
            String fluteHelixAngle, String axialRakeAngle, String radialRakeAngle,
            String axialRearAngle, String radialRearAngle, String cuttingEdgeAngle,
            String faceContactDiameter, String tipChamfer, String chamferWidth,
            String centerCuttingCapability, String maximumRegrinds, String maxRotationalSpeed,
            String weight, String lifeCycleState, String suitableForMaterial,
            String application, int used) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.serial = serial;
        this.brand = brand;
        this.cuttingDiameter = cuttingDiameter;
        this.cuttingDiameterTOLUpper = cuttingDiameterTOLUpper;
        this.cuttingDiameterTOLLower = cuttingDiameterTOLLower;
        this.filletRadius = filletRadius;
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
        this.axialRakeAngle = axialRakeAngle;
        this.radialRakeAngle = radialRakeAngle;
        this.axialRearAngle = axialRearAngle;
        this.radialRearAngle = radialRearAngle;
        this.cuttingEdgeAngle = cuttingEdgeAngle;
        this.faceContactDiameter = faceContactDiameter;
        this.tipChamfer = tipChamfer;
        this.chamferWidth = chamferWidth;
        this.centerCuttingCapability = centerCuttingCapability;
        this.maximumRegrinds = maximumRegrinds;
        this.maxRotationalSpeed = maxRotationalSpeed;
        this.weight = weight;
        this.lifeCycleState = lifeCycleState;
        this.suitableForMaterial = suitableForMaterial;
        this.application = application;
        this.used = used;
    }

    @Generated(hash = 1059748735)
    public Tool() {
    }

    /**
     * 用以intent在activity之间传递对象
     * @param in in
     */
    private Tool(Parcel in) {
        id = in.readLong();
        name = in.readString();
        type = in.readString();
        serial = in.readString();
        brand = in.readString();
        cuttingDiameter = in.readString();
        cuttingDiameterTOLUpper=in.readString();
        cuttingDiameterTOLLower=in.readString();
        filletRadius=in.readString();
        depthOfCutMaximum = in.readString();
        maxRampingAngle = in.readString();
        usableLength = in.readString();
        peripheralEffectiveCuttingEdgeCount = in.readString();
        adaptiveInterfaceMachineDirection = in.readString();
        connectionDiameterTolerance = in.readString();
        grade = in.readString();
        substrate = in.readString();
        coating = in.readString();
        basicStandardGroup = in.readString();
        coolantEntryStyleCode = in.readString();
        connectionDiameter = in.readString();
        functionalLength = in.readString();
        fluteHelixAngle = in.readString();
        axialRakeAngle = in.readString();
        radialRakeAngle = in.readString();
        axialRearAngle=in.readString();
        radialRearAngle=in.readString();
        cuttingEdgeAngle=in.readString();
        faceContactDiameter=in.readString();
        tipChamfer=in.readString();
        chamferWidth=in.readString();
        centerCuttingCapability=in.readString();
        maximumRegrinds = in.readString();
        maxRotationalSpeed = in.readString();
        weight = in.readString();
        lifeCycleState = in.readString();
        suitableForMaterial = in.readString();
        application = in.readString();
        used=in.readInt();
    }

    public static final Creator<Tool> CREATOR = new Creator<Tool>() {
        @Override
        public Tool createFromParcel(Parcel in) {
            return new Tool(in);
        }

        @Override
        public Tool[] newArray(int size) {
            return new Tool[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(serial);
        dest.writeString(brand);
        dest.writeString(cuttingDiameter);
        dest.writeString(cuttingDiameterTOLUpper);
        dest.writeString(cuttingDiameterTOLLower);
        dest.writeString(filletRadius);
        dest.writeString(depthOfCutMaximum);
        dest.writeString(maxRampingAngle);
        dest.writeString(usableLength);
        dest.writeString(peripheralEffectiveCuttingEdgeCount);
        dest.writeString(adaptiveInterfaceMachineDirection);
        dest.writeString(connectionDiameterTolerance);
        dest.writeString(grade);
        dest.writeString(substrate);
        dest.writeString(coating);
        dest.writeString(basicStandardGroup);
        dest.writeString(coolantEntryStyleCode);
        dest.writeString(connectionDiameter);
        dest.writeString(functionalLength);
        dest.writeString(fluteHelixAngle);
        dest.writeString(axialRakeAngle);
        dest.writeString(radialRakeAngle);
        dest.writeString(axialRearAngle);
        dest.writeString(radialRearAngle);
        dest.writeString(cuttingEdgeAngle);
        dest.writeString(faceContactDiameter);
        dest.writeString(tipChamfer);
        dest.writeString(chamferWidth);
        dest.writeString(centerCuttingCapability);
        dest.writeString(maximumRegrinds);
        dest.writeString(maxRotationalSpeed);
        dest.writeString(weight);
        dest.writeString(lifeCycleState);
        dest.writeString(suitableForMaterial);
        dest.writeString(application);
        dest.writeInt(used);
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

    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCuttingDiameter() {
        return this.cuttingDiameter;
    }

    public void setCuttingDiameter(String cuttingDiameter) {
        this.cuttingDiameter = cuttingDiameter;
    }

    public String getCuttingDiameterTOLUpper() {
        return this.cuttingDiameterTOLUpper;
    }

    public void setCuttingDiameterTOLUpper(String cuttingDiameterTOLUpper) {
        this.cuttingDiameterTOLUpper = cuttingDiameterTOLUpper;
    }

    public String getCuttingDiameterTOLLower() {
        return this.cuttingDiameterTOLLower;
    }

    public void setCuttingDiameterTOLLower(String cuttingDiameterTOLLower) {
        this.cuttingDiameterTOLLower = cuttingDiameterTOLLower;
    }

    public String getFilletRadius() {
        return this.filletRadius;
    }

    public void setFilletRadius(String filletRadius) {
        this.filletRadius = filletRadius;
    }

    public String getDepthOfCutMaximum() {
        return this.depthOfCutMaximum;
    }

    public void setDepthOfCutMaximum(String depthOfCutMaximum) {
        this.depthOfCutMaximum = depthOfCutMaximum;
    }

    public String getMaxRampingAngle() {
        return this.maxRampingAngle;
    }

    public void setMaxRampingAngle(String maxRampingAngle) {
        this.maxRampingAngle = maxRampingAngle;
    }

    public String getUsableLength() {
        return this.usableLength;
    }

    public void setUsableLength(String usableLength) {
        this.usableLength = usableLength;
    }

    public String getPeripheralEffectiveCuttingEdgeCount() {
        return this.peripheralEffectiveCuttingEdgeCount;
    }

    public void setPeripheralEffectiveCuttingEdgeCount(
            String peripheralEffectiveCuttingEdgeCount) {
        this.peripheralEffectiveCuttingEdgeCount = peripheralEffectiveCuttingEdgeCount;
    }

    public String getAdaptiveInterfaceMachineDirection() {
        return this.adaptiveInterfaceMachineDirection;
    }

    public void setAdaptiveInterfaceMachineDirection(
            String adaptiveInterfaceMachineDirection) {
        this.adaptiveInterfaceMachineDirection = adaptiveInterfaceMachineDirection;
    }

    public String getConnectionDiameterTolerance() {
        return this.connectionDiameterTolerance;
    }

    public void setConnectionDiameterTolerance(String connectionDiameterTolerance) {
        this.connectionDiameterTolerance = connectionDiameterTolerance;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubstrate() {
        return this.substrate;
    }

    public void setSubstrate(String substrate) {
        this.substrate = substrate;
    }

    public String getCoating() {
        return this.coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public String getBasicStandardGroup() {
        return this.basicStandardGroup;
    }

    public void setBasicStandardGroup(String basicStandardGroup) {
        this.basicStandardGroup = basicStandardGroup;
    }

    public String getCoolantEntryStyleCode() {
        return this.coolantEntryStyleCode;
    }

    public void setCoolantEntryStyleCode(String coolantEntryStyleCode) {
        this.coolantEntryStyleCode = coolantEntryStyleCode;
    }

    public String getConnectionDiameter() {
        return this.connectionDiameter;
    }

    public void setConnectionDiameter(String connectionDiameter) {
        this.connectionDiameter = connectionDiameter;
    }

    public String getFunctionalLength() {
        return this.functionalLength;
    }

    public void setFunctionalLength(String functionalLength) {
        this.functionalLength = functionalLength;
    }

    public String getFluteHelixAngle() {
        return this.fluteHelixAngle;
    }

    public void setFluteHelixAngle(String fluteHelixAngle) {
        this.fluteHelixAngle = fluteHelixAngle;
    }

    public String getAxialRakeAngle() {
        return this.axialRakeAngle;
    }

    public void setAxialRakeAngle(String axialRakeAngle) {
        this.axialRakeAngle = axialRakeAngle;
    }

    public String getRadialRakeAngle() {
        return this.radialRakeAngle;
    }

    public void setRadialRakeAngle(String radialRakeAngle) {
        this.radialRakeAngle = radialRakeAngle;
    }

    public String getAxialRearAngle() {
        return this.axialRearAngle;
    }

    public void setAxialRearAngle(String axialRearAngle) {
        this.axialRearAngle = axialRearAngle;
    }

    public String getRadialRearAngle() {
        return this.radialRearAngle;
    }

    public void setRadialRearAngle(String radialRearAngle) {
        this.radialRearAngle = radialRearAngle;
    }

    public String getCuttingEdgeAngle() {
        return this.cuttingEdgeAngle;
    }

    public void setCuttingEdgeAngle(String cuttingEdgeAngle) {
        this.cuttingEdgeAngle = cuttingEdgeAngle;
    }

    public String getFaceContactDiameter() {
        return this.faceContactDiameter;
    }

    public void setFaceContactDiameter(String faceContactDiameter) {
        this.faceContactDiameter = faceContactDiameter;
    }

    public String getTipChamfer() {
        return this.tipChamfer;
    }

    public void setTipChamfer(String tipChamfer) {
        this.tipChamfer = tipChamfer;
    }

    public String getChamferWidth() {
        return this.chamferWidth;
    }

    public void setChamferWidth(String chamferWidth) {
        this.chamferWidth = chamferWidth;
    }

    public String getCenterCuttingCapability() {
        return this.centerCuttingCapability;
    }

    public void setCenterCuttingCapability(String centerCuttingCapability) {
        this.centerCuttingCapability = centerCuttingCapability;
    }

    public String getMaximumRegrinds() {
        return this.maximumRegrinds;
    }

    public void setMaximumRegrinds(String maximumRegrinds) {
        this.maximumRegrinds = maximumRegrinds;
    }

    public String getMaxRotationalSpeed() {
        return this.maxRotationalSpeed;
    }

    public void setMaxRotationalSpeed(String maxRotationalSpeed) {
        this.maxRotationalSpeed = maxRotationalSpeed;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLifeCycleState() {
        return this.lifeCycleState;
    }

    public void setLifeCycleState(String lifeCycleState) {
        this.lifeCycleState = lifeCycleState;
    }

    public String getSuitableForMaterial() {
        return this.suitableForMaterial;
    }

    public void setSuitableForMaterial(String suitableForMaterial) {
        this.suitableForMaterial = suitableForMaterial;
    }

    public String getApplication() {
        return this.application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public int getUsed() {
        return this.used;
    }

    public void setUsed(int used) {
        this.used = used;
    }
}
