package com.leting.statistics.dao;


import com.leting.statistics.model.Statistics;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatisticsDao {
    //保存统计数据
    void addStatistics(Statistics sta);
}
