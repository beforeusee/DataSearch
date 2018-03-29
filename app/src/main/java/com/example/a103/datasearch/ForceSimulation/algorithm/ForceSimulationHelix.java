package com.example.a103.datasearch.forcesimulation.algorithm;

import android.support.annotation.NonNull;

import com.example.a103.datasearch.forcesimulation.basicdata.datacutcoeffient.DataCutCoefficient;
import com.example.a103.datasearch.forcesimulation.basicdata.datacuttingpara.DataCuttingPara;
import com.example.a103.datasearch.forcesimulation.basicdata.datasimumax.DataSimulationMax;
import com.example.a103.datasearch.forcesimulation.basicdata.datasimupara.DataSimulationPara;
import com.example.a103.datasearch.forcesimulation.basicdata.datatool.DataToolHelix;
import com.example.a103.datasearch.utils.Constant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math.*;
import java.util.Set;

import static com.example.a103.datasearch.utils.Constant.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by XiaoHu Zheng on 2018/3/26.
 * Email: 1050087728@qq.com
 */

public class ForceSimulationHelix {


    public ForceSimulationHelix(){


    }

    /**
     * 圆柱螺旋立铣刀铣削力时域仿真
     * 输入:圆柱螺旋立铣刀(半径，齿数，螺旋角)
     *      工件材料
     *      仿真参数（轴向仿真步长、角度仿真步长、仿真周期数）
     *      切削参数（转速，进给，切深，切宽，铣削类型）
     *      切削力系数（ktc,krc,kac,kte,kre,kae,rake,relief,wear）
     * 输出：x,y,z方向瞬时铣削力，t,r,a方向瞬时铣削力，xy平面合力，xyz三向合力
     *       功率，扭矩，刀尖变形，夹持面弯矩
     * @param dataToolHelix 圆柱螺旋立铣刀
     * @param cuttingPara 切削参数
     * @param simulationPara 仿真参数
     * @param coefficient 切削力系数
     */
    private void forceSimulationHelix(DataToolHelix dataToolHelix, DataCuttingPara cuttingPara, DataSimulationPara simulationPara, DataCutCoefficient coefficient){

        HashMap<String,Double> angleForce=new HashMap<>();

        for (int i=0;i<2*PI*simulationPara.getPeriods()/simulationPara.getdPhi();i++){

            angleForce=angleForceHelix(dataToolHelix,simulationPara,cuttingPara,coefficient,i);
        }
    }

    private HashMap<String, Double> angleForceHelix(DataToolHelix toolHelix,DataSimulationPara simulationPara,DataCuttingPara cuttingPara,DataCutCoefficient coefficient,int i){

        HashMap<String,Double> result=new HashMap<>();

        //当前角度弧度
        double locRad;
        //当前角度(°)
        double locAngle;
        //瞬时x方向的力
        double locFx=0;
        //瞬时y方向的力
        double locFy=0;
        //瞬时z方向的力
        double locFz=0;
        //瞬时xy方向合力
        double locFxy=0;
        //瞬时xyz方向合力
        double locFxyz=0;
        //瞬时切向力
        double locFt=0;
        //瞬时径向力
        double locFr=0;
        //瞬时轴向力
        double locFa=0;
        //瞬时功率
        double locP=0;
        //瞬时转矩
        double locT=0;
        //夹持面弯矩
        double locBend;

        ArrayList<Double> chip=new ArrayList<>();

        //累积转矩
        double tm=0;
        //累积功率
        double pm=0;
        //刀齿偏心
        double currentErr;
        //用于保留前一刀齿的偏心数据
        double preErr;

        //轴向积分步数
        int numAxial= (int) (cuttingPara.getCuttingDepth()/simulationPara.getDz());

        //齿间距角度
        double angSpacing=2*PI/toolHelix.getToolTeethNum();
        //每齿进给
        double fz=cuttingPara.getFeedRate()/(cuttingPara.getSpindleSpeed()*toolHelix.getToolTeethNum());

        //当前转角(弧度),2PI周期内
        locRad=SimuBase.mod(simulationPara.getPhiIn()+i*simulationPara.getdPhi(),2*PI);
        //当前角度（°）
        locAngle=(simulationPara.getdPhi()+i*simulationPara.getdPhi())*180/PI;
        //切削线速度(m/min)
        double velocity=toolHelix.getRadius()*2*PI*cuttingPara.getSpindleSpeed()/1000;

        //时间(s)

        //刀齿循环
        for (int j=0;j<toolHelix.getToolTeethNum();j++){

            //初始化切屑厚度
            chip.add(0.0);

            //刀齿j的接触角
            double phi=locRad+j*angSpacing;

            phi=SimuBase.mod(phi,2*PI);

            double phi2=phi;

            //轴向积分循环
            for (int k=0;k<numAxial;k++){

                //轴向位置
                double zPos=(k+0.5)*simulationPara.getDz();

                //接触角沿轴向变化
                phi2=phi-zPos*toolHelix.getLagFactor();

                phi2=SimuBase.mod(phi2,2*PI);

                //判断刀具是否参与切削
                if (phi2>=simulationPara.getPhiIn()&&phi2<=simulationPara.getPhiOut()){

                    //微元的力
                    double dFt=simulationPara.getDz()*(coefficient.getKtc()*fz+coefficient.getKte());
                    double dFr=simulationPara.getDz()*(coefficient.getKrc()*fz+coefficient.getKre());
                    double dFa=simulationPara.getDz()*(coefficient.getKac()*fz+coefficient.getKae());

                    double dFx=-dFt*cos(phi2)-dFr*sin(phi2);
                    double dFy=dFt* sin(phi2)-dFr*cos(phi2);
                    double dFz=dFa;

                    //累加
                    locFt+=dFt;
                    locFr+=dFr;
                    locFa+=dFa;

                    locFx+=dFx;
                    locFy+=dFy;
                    locFz+=dFz;
                }
            }
        }

        //计算xy合力
        locFxy=sqrt(locFx*locFx+locFy*locFy);
        //计算xyz合力
        locFxyz=sqrt(locFx*locFx+locFy*locFy+locFa*locFa);
        //计算扭矩(Nm)
        locT=locFt*toolHelix.getRadius()/1000;
        //计算功率(KW)
        locP=velocity*locFt/60000;

        //累积转矩
        tm+=locT;
        //累积功率
        pm+=locP;
        //弯矩(Nm)
        locBend=SimuBase.getToolBend(locFxy,toolHelix.getLenGauge()/1000);

        //存储结果至result中
        result.put("Fx",locFx);
        result.put("Fy",locFy);
        result.put("Fz",locFz);
        result.put("Fxy",locFxy);
        result.put("Fxyz",locFxyz);
        result.put("Fa",locFa);
        result.put("Fr",locFr);
        result.put("Ft",locFt);
        result.put("T",locT);
        result.put("P",locP);
        result.put("ToolBend",locBend);

        //返回结果
        return result;
    }

    public DataSimulationMax getSimulationMax(DataToolHelix dataToolHelix, DataCuttingPara cuttingPara, DataSimulationPara simulationPara, DataCutCoefficient coefficient){

        HashMap<String,Double> angleForce;

        List<Double> fxList=new ArrayList<>();
        List<Double> fyList=new ArrayList<>();
        List<Double> fzList=new ArrayList<>();
        List<Double> fxyList=new ArrayList<>();
        List<Double> fxyzList=new ArrayList<>();
        List<Double> ftList=new ArrayList<>();
        List<Double> frList=new ArrayList<>();
        List<Double> faList=new ArrayList<>();
        List<Double> TList=new ArrayList<>();
        List<Double> PList=new ArrayList<>();
        List<Double> toolBendList=new ArrayList<>();

        for (int i=0;i<2*PI*simulationPara.getPeriods()/simulationPara.getdPhi();i++){

            angleForce=angleForceHelix(dataToolHelix,simulationPara,cuttingPara,coefficient,i);

            fxList.add(angleForce.get("Fx"));
            fyList.add(angleForce.get("Fy"));
            fzList.add(angleForce.get("Fz"));
            fxyList.add(angleForce.get("Fxy"));
            fxyzList.add(angleForce.get("Fxyz"));
            ftList.add(angleForce.get("Ft"));
            frList.add(angleForce.get("Fr"));
            faList.add(angleForce.get("Fa"));
            TList.add(angleForce.get("T"));
            PList.add(angleForce.get("P"));
            toolBendList.add(angleForce.get("ToolBend"));
        }

        double fxMax=SimuBase.getMaxData(fxList);
        double fyMax=SimuBase.getMaxData(fyList);
        double fzMax=SimuBase.getMaxData(fzList);
        double fxyMax=SimuBase.getMaxData(fxyList);
        double fxyzMax=SimuBase.getMaxData(fxyzList);
        double ftMax=SimuBase.getMaxData(ftList);
        double frMax=SimuBase.getMaxData(frList);
        double faMax=SimuBase.getMaxData(faList);
        double TMax=SimuBase.getMaxData(TList);
        double PMax=SimuBase.getMaxData(PList);
        double toolBendMax=SimuBase.getMaxData(toolBendList);

        DataSimulationMax dataSimulationMax=new DataSimulationMax();

        dataSimulationMax.setFx(fxMax);
        dataSimulationMax.setFy(fyMax);
        dataSimulationMax.setFz(fzMax);
        dataSimulationMax.setFxy(fxyMax);
        dataSimulationMax.setFxyz(fxyzMax);
        dataSimulationMax.setFt(ftMax);
        dataSimulationMax.setFr(frMax);
        dataSimulationMax.setFa(faMax);
        dataSimulationMax.setTorque(TMax);
        dataSimulationMax.setPower(PMax);
        dataSimulationMax.setBend(toolBendMax);

        return dataSimulationMax;
    }


}
