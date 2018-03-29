package com.example.a103.datasearch.forcesimulation.basicdata.datatool;
import java.lang.Math;
import com.example.a103.datasearch.utils.Constant;

import static com.example.a103.datasearch.utils.Constant.PI;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataToolHelix extends DataTool {

    //滞后因子
    double lagFactor;

    public DataToolHelix(double radius, double lenFlute, double lenGauge, double helix, double toolTeethNum) {
        super(radius, lenFlute, lenGauge, helix, toolTeethNum);

        this.lagFactor =Math.tan(helix*PI/180)/radius;
    }

    public double getLagFactor() {
        return lagFactor;
    }

    public void setLagFactor(double lagFactor) {
        this.lagFactor = lagFactor;
    }
}
