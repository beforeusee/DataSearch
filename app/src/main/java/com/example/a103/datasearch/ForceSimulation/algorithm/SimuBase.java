package com.example.a103.datasearch.forcesimulation.algorithm;
import com.example.a103.datasearch.utils.Constant;

import static com.example.a103.datasearch.utils.Constant.DOWN_MILL;
import static com.example.a103.datasearch.utils.Constant.PI;
import static com.example.a103.datasearch.utils.Constant.UP_MILL;

import java.lang.Math.*;
import java.util.List;

/**
 * Created by XiaoHu Zheng on 2018/3/27.
 * Email: 1050087728@qq.com
 */

public class SimuBase {

    /**
     * 计算刀具的弯矩
     * @param force 切削力
     * @param length 刀具悬长
     * @return 弯矩
     */
    public static double getToolBend(double force,double length){

        return force*length;
    }

    /**
     * 获取切入角
     * @param ae 切宽
     * @param r 刀具半径
     * @param type 刀具铣削类型
     * @return 切入角
     */
    public static double getPhiIn(double ae,double r,String type){

        if (type.equals(UP_MILL)){

            double phiIn=PI-Math.acos(1-ae/r)+1;
            return (PI-Math.acos(1-ae/r));
        }

        return 0;
    }

    /**
     * 获取切出角
     * @param ae 切宽
     * @param r 刀具半径
     * @param type 切削类型
     * @return 切出角
     */
    public static double getPhiOut(double ae,double r,String type){

        if (type.equals(DOWN_MILL)){

            return Math.acos(1-ae/r);
        }

        return PI;
    }

    /**
     * 从链表中取最大值
     * @param dataList 链表
     * @return 最大值
     */
    public static double getMaxData(List<Double> dataList){

        double maxData=0;
        for (int i=0;i<dataList.size();i++){

            maxData=maxData>Math.abs(dataList.get(i))?maxData:Math.abs(dataList.get(i));
        }
        return maxData;
    }

    public static double mod(double num,double div){

        return num-((int)(num/div))*div;
    }
}
