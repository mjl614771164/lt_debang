package com.leting.statistics.service.impl;


import com.leting.statistics.dao.IStatisticsDao;
import com.leting.statistics.model.Statistics;
import com.leting.statistics.service.IStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {

    @Resource
    private IStatisticsDao statisticsDao;

    @Override
    public Map<String, Object> dataStatistics(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> retMap = new HashMap<String,Object>();
        retMap.put("code","200");
        retMap.put("msg","SUCCESS");
        try{
            String deviceid = request.getParameter("deviceid");
            String command = request.getParameter("command");
            if(deviceid == null ){
                retMap.put("code","400");
                retMap.put("msg","deviceid is null");
                return retMap;
            }
            if(command == null || "".equals(command)){
                retMap.put("code","400");
                retMap.put("msg","command is null");
                return retMap;
            }
            //获取参数，封装数据
            Statistics sta = new Statistics();
            sta.setDeviceid(deviceid);
            sta.setCommand(command);

            statisticsDao.addStatistics(sta);

        }catch (Exception e){
            e.printStackTrace();
            retMap.put("code","500");
            retMap.put("msg","service is error");
        }

        return retMap;
    }
}
