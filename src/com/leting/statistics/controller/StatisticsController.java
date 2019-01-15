package com.leting.statistics.controller;


import com.leting.statistics.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;

    /**
     * 数据统计
     * @param
     * @return
     */
    @RequestMapping(value = "/statistics")
    @ResponseBody
    public Map<String,Object> DataStatistics(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> retMap = new HashMap<String, Object>();

        retMap = statisticsService.dataStatistics(request,response);

        System.out.println(" 大盘鸡 ");
        System.out.println("吃没了");
        System.out.println("吃没了");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        return retMap;

    }
}
