package com.example.a103.datasearch.forcesimulation.basicdata.datatool;
import java.lang.Math;
import com.example.a103.datasearch.utils.Constant;

import static com.example.a103.datasearch.utils.Constant.PI;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class DataToolHelix extends DataTool {

    public DataToolHelix(){

    }

    public DataToolHelix(double radius, double lenFlute, double lenGauge, double helix, double toolTeethNum, double lagFactor, String toolType, String toolMaterial, String toolNum) {
        super(radius, lenFlute, lenGauge, helix, toolTeethNum, lagFactor, toolType, toolMaterial, toolNum);
    }
}
