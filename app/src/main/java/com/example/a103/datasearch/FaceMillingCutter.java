package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/2/22.
 * 整体硬质合金面铣刀数据模型
 */

public class FaceMillingCutter extends Tool {
    //定义面铣刀的特有信息成员变量
    private String cornerRadius1;            //圆角半径(RE1)
    private String cornerRadius2;            //圆角半径(RE2)
    private String cornerRadiusEquivalent;  //刀尖圆角半径等值(REEQ)
    private String toolCuttingEdgeAngle;           //切削刃角

    public FaceMillingCutter(int id, String name, String type, String serial, String brand,
                             String cuttingDiameter, String depthOfCutMaximum, String maxRampingAngle,
                             String usableLength, String peripheralEffectiveCuttingEdgeCount,
                             String adaptiveInterfaceMachineDirection, String connectionDiameterTolerance,
                             String grade, String substrate, String coating, String basicStandardGroup,
                             String coolantEntryStyleCode, String connectionDiameter, String functionalLength,
                             String fluteHelixAngle, String radialRakeAngle, String axialRakeAngle,
                             String maximumRegrinds, String maxRotationalSpeed, String weight,
                             String lifeCycleState, String suitableForMaterial, String application,int used) {
        super(id, name, type, serial, brand, cuttingDiameter, depthOfCutMaximum, maxRampingAngle, usableLength,
                peripheralEffectiveCuttingEdgeCount, adaptiveInterfaceMachineDirection,
                connectionDiameterTolerance, grade, substrate, coating, basicStandardGroup, coolantEntryStyleCode,
                connectionDiameter, functionalLength, fluteHelixAngle, radialRakeAngle, axialRakeAngle,
                maximumRegrinds, maxRotationalSpeed, weight, lifeCycleState, suitableForMaterial, application,used);
    }

    public String getCornerRadius1() {
        return cornerRadius1;
    }

    public void setCornerRadius1(String cornerRadius1) {
        this.cornerRadius1 = cornerRadius1;
    }

    public String getCornerRadius2() {
        return cornerRadius2;
    }

    public void setCornerRadius2(String cornerRadius2) {
        this.cornerRadius2 = cornerRadius2;
    }

    public String getCornerRadiusEquivalent() {
        return cornerRadiusEquivalent;
    }

    public void setCornerRadiusEquivalent(String cornerRadiusEquivalent) {
        this.cornerRadiusEquivalent = cornerRadiusEquivalent;
    }

    public String getToolCuttingEdgeAngle() {
        return toolCuttingEdgeAngle;
    }

    public void setToolCuttingEdgeAngle(String toolCuttingEdgeAngle){
        this.toolCuttingEdgeAngle=toolCuttingEdgeAngle;
    }
}
