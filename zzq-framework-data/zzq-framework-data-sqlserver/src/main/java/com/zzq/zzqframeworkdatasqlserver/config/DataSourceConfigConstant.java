package com.zzq.zzqframeworkdatasqlserver.config;

/**
 * 数据源配置常量
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-17 23:25
 */
public interface DataSourceConfigConstant {

    /*#############################bigdataplat#####################################*/
    //扫描包路径
    String BIGDATAPLAT_BASE_PACKAGES = "com.zzq.bigdataplatdatasqlserver.mapper.bigdataplat";
    //mapper.xml路径
    String BIGDATAPLAT_MAPPER_LOCATION = "classpath*:mybatis/bigdataplat/*.xml";
    //工厂bean名字
    String BIGDATAPLAT_SQL_SESSION_FACTORY = "BIGDATAPLAT_SQL_SESSION_FACTORY";
    //shardingDataSouce bean名字
    String BIGDATAPLAT_SHARDING_DATASOURCE = "BIGDATAPLAT_SHARDING_DATASOURCE";





}
