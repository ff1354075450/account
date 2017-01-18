package com.test.AppApi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.test.common.BaseActionSupport;
import com.test.db.person.Workdao;

/**
 * Created by ff on 2017/1/18.
 */
public class workerApi extends BaseActionSupport {

    /**
     * 新增一个记录
     */
    public void addWorker(){
        String worker = request.getParameter("worker");
        String boss = request.getParameter("boss");
        if(Workdao.addworker(worker,boss)) {
            outSuccess();
        }else{
            outFailed();
        }
    }

    /**
     * 更新当天的工作量
     */
    public void update(){
        String worker = request.getParameter("worker");
        String boss = request.getParameter("boss");
        float worklen =Float.parseFloat(request.getParameter("worklen"));
        if(Workdao.update(worker,boss,worklen)){
            outSuccess();
        }else {
            outFailed();
        }
    }

    /**
     * 查询指定日期的账目
     */

    public void query(){
        String worker = request.getParameter("worker");
        String boss = request.getParameter("boss");
        JSONArray result = Workdao.query(worker,boss);
        if(result != null){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",1);
            jsonObject.put("worker",result);
            outPrint(jsonObject.toJSONString());
        }else {
            outFailed();
        }
    }

}
