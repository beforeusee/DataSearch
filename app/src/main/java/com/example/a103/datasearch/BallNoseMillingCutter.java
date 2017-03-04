package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/2/24.
 * 整体硬质合金球头铣刀数据模型
 */

public class BallNoseMillingCutter extends Tool {
    //定义球头铣刀的特有信息成员变量
    private String lowerCuttingDiameterTolerance;  //切削直径公差下限
    private String upperCuttingDiameterTolerance;  //切削直径公差上限

    public BallNoseMillingCutter(int id, String name, String type, String serial, String brand,
                                 String cuttingDiameter, String depthOfCutMaximum, String maxRampingAngle,
                                 String usableLength, String peripheralEffectiveCuttingEdgeCount,
                                 String adaptiveInterfaceMachineDirection, String connectionDiameterTolerance,
                                 String grade, String substrate, String coating, String basicStandardGroup,
                                 String coolantEntryStyleCode, String connectionDiameter, String functionalLength,
                                 String fluteHelixAngle, String radialRakeAngle, String axialRakeAngle,
                                 String maximumRegrinds, String maxRotationalSpeed, String weight,
                                 String lifeCycleState, String suitableForMaterial, String application,int used) {
        super(id, name, type, serial, brand, cuttingDiameter, depthOfCutMaximum, maxRampingAngle, usableLength,
                peripheralEffectiveCuttingEdgeCount, adaptiveInterfaceMachineDirection, connectionDiameterTolerance,
                grade, substrate, coating, basicStandardGroup, coolantEntryStyleCode, connectionDiameter,
                functionalLength, fluteHelixAngle, radialRakeAngle, axialRakeAngle, maximumRegrinds,
                maxRotationalSpeed, weight, lifeCycleState, suitableForMaterial, application,used);
    }

    public String getLowerCuttingDiameterTolerance() {
        return lowerCuttingDiameterTolerance;
    }

    public void setLowerCuttingDiameterTolerance(String lowerCuttingDiameterTolerance) {
        this.lowerCuttingDiameterTolerance = lowerCuttingDiameterTolerance;
    }

    public String getUpperCuttingDiameterTolerance() {
        return upperCuttingDiameterTolerance;
    }

    public void setUpperCuttingDiameterTolerance(String upperCuttingDiameterTolerance) {
        this.upperCuttingDiameterTolerance = upperCuttingDiameterTolerance;
    }
}
