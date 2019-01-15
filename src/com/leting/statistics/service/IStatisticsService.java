package com.leting.statistics.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IStatisticsService {
    //数据统计
    Map<String, Object> dataStatistics(HttpServletRequest request, HttpServletResponse response);
}
