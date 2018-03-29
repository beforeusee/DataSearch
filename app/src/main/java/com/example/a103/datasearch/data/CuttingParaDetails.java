package com.example.a103.datasearch.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by XiaoHu Zheng on 2018/3/12.
 * Email: 1050087728@qq.com
 */

@Entity
public class CuttingParaDetails {

    //切削参数的主键
    @Id(autoincrement = true)
    private Long id;

    //CuttingParaDetails的外键
    private Long cuttingConditionsId;

    //主轴转速
    private String spindleSpeed;

    //进给速度
    private String feedRate;

    //径向切宽
    private String cuttingWidth;

    //轴向切深
    private String cuttingDepth;

    //每齿进给量
    private String feedPerTooth;

    //切削线速度
    private String cuttingLinearVelocity;

    //Y向切削力峰值
    private String maxForceY;

    //XY向切削力峰值
    private String maxForceXY;

    //切向力峰值
    private String maxForceTangential;

    //最大转矩
    private String maxTorque;

    //最大切削功率
    private String maxCuttingPower;

    //材料去除率
    private String materialRemovalRate;

    //刀具寿命
    private String toolLife;

    //数据来源
    private String dataSource;

    //数据成熟度
    private String dataMaturity;

    @Generated(hash = 1393497476)
    public CuttingParaDetails(Long id, Long cuttingConditionsId,
            String spindleSpeed, String feedRate, String cuttingWidth,
            String cuttingDepth, String feedPerTooth, String cuttingLinearVelocity,
            String maxForceY, String maxForceXY, String maxForceTangential,
            String maxTorque, String maxCuttingPower, String materialRemovalRate,
            String toolLife, String dataSource, String dataMaturity) {
        this.id = id;
        this.cuttingConditionsId = cuttingConditionsId;
        this.spindleSpeed = spindleSpeed;
        this.feedRate = feedRate;
        this.cuttingWidth = cuttingWidth;
        this.cuttingDepth = cuttingDepth;
        this.feedPerTooth = feedPerTooth;
        this.cuttingLinearVelocity = cuttingLinearVelocity;
        this.maxForceY = maxForceY;
        this.maxForceXY = maxForceXY;
        this.maxForceTangential = maxForceTangential;
        this.maxTorque = maxTorque;
        this.maxCuttingPower = maxCuttingPower;
        this.materialRemovalRate = materialRemovalRate;
        this.toolLife = toolLife;
        this.dataSource = dataSource;
        this.dataMaturity = dataMaturity;
    }

    @Generated(hash = 1866863473)
    public CuttingParaDetails() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuttingConditionsId() {
        return this.cuttingConditionsId;
    }

    public void setCuttingConditionsId(Long cuttingConditionsId) {
        this.cuttingConditionsId = cuttingConditionsId;
    }

    public String getSpindleSpeed() {
        return this.spindleSpeed;
    }

    public void setSpindleSpeed(String spindleSpeed) {
        this.spindleSpeed = spindleSpeed;
    }

    public String getFeedRate() {
        return this.feedRate;
    }

    public void setFeedRate(String feedRate) {
        this.feedRate = feedRate;
    }

    public String getCuttingWidth() {
        return this.cuttingWidth;
    }

    public void setCuttingWidth(String cuttingWidth) {
        this.cuttingWidth = cuttingWidth;
    }

    public String getCuttingDepth() {
        return this.cuttingDepth;
    }

    public void setCuttingDepth(String cuttingDepth) {
        this.cuttingDepth = cuttingDepth;
    }

    public String getFeedPerTooth() {
        return this.feedPerTooth;
    }

    public void setFeedPerTooth(String feedPerTooth) {
        this.feedPerTooth = feedPerTooth;
    }

    public String getCuttingLinearVelocity() {
        return this.cuttingLinearVelocity;
    }

    public void setCuttingLinearVelocity(String cuttingLinearVelocity) {
        this.cuttingLinearVelocity = cuttingLinearVelocity;
    }

    public String getMaxForceY() {
        return this.maxForceY;
    }

    public void setMaxForceY(String maxForceY) {
        this.maxForceY = maxForceY;
    }

    public String getMaxForceXY() {
        return this.maxForceXY;
    }

    public void setMaxForceXY(String maxForceXY) {
        this.maxForceXY = maxForceXY;
    }

    public String getMaxForceTangential() {
        return this.maxForceTangential;
    }

    public void setMaxForceTangential(String maxForceTangential) {
        this.maxForceTangential = maxForceTangential;
    }

    public String getMaxTorque() {
        return this.maxTorque;
    }

    public void setMaxTorque(String maxTorque) {
        this.maxTorque = maxTorque;
    }

    public String getMaxCuttingPower() {
        return this.maxCuttingPower;
    }

    public void setMaxCuttingPower(String maxCuttingPower) {
        this.maxCuttingPower = maxCuttingPower;
    }

    public String getMaterialRemovalRate() {
        return this.materialRemovalRate;
    }

    public void setMaterialRemovalRate(String materialRemovalRate) {
        this.materialRemovalRate = materialRemovalRate;
    }

    public String getToolLife() {
        return this.toolLife;
    }

    public void setToolLife(String toolLife) {
        this.toolLife = toolLife;
    }

    public String getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataMaturity() {
        return this.dataMaturity;
    }

    public void setDataMaturity(String dataMaturity) {
        this.dataMaturity = dataMaturity;
    }
}
