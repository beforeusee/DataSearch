package com.example.a103.datasearch;

/**
 * Created by zhengxiaohu on 2017/3/10.
 * 工件材料的切削限制
 */

public class MaterialCuttingLimits {
    //材料极限参数声明
    private final String minChipThickness;   //切屑厚度(最小)
    private final String maxChipThickness;   //切屑厚度(最大)
    private final String minCuttingSpeed;    //切削速度(最小)
    private final String maxCuttingSpeed;    //切削速度(最大)
    private final String minRakeAngle;        //前角(最小)
    private final String maxRakeAngle;        //前角(最大)

    public MaterialCuttingLimits(final String minChipThickness,
                                 final String maxChipThickness,
                                 final String minCuttingSpeed,
                                 final String maxCuttingSpeed,
                                 final String minRakeAngle,
                                 final String maxRakeAngle) {
        this.minChipThickness = minChipThickness;
        this.maxChipThickness = maxChipThickness;
        this.minCuttingSpeed = minCuttingSpeed;
        this.maxCuttingSpeed = maxCuttingSpeed;
        this.minRakeAngle = minRakeAngle;
        this.maxRakeAngle = maxRakeAngle;
    }

    public String getMinChipThickness() {
        return minChipThickness;
    }

    public String getMaxChipThickness() {
        return maxChipThickness;
    }

    public String getMinCuttingSpeed() {
        return minCuttingSpeed;
    }

    public String getMaxCuttingSpeed() {
        return maxCuttingSpeed;
    }

    public String getMinRakeAngle() {
        return minRakeAngle;
    }

    public String getMaxRakeAngle() {
        return maxRakeAngle;
    }

    @Override
    public String toString() {
        return minChipThickness+","+maxChipThickness+","+minCuttingSpeed+","+maxCuttingSpeed+","+
                minRakeAngle+","+maxRakeAngle;
    }
}
