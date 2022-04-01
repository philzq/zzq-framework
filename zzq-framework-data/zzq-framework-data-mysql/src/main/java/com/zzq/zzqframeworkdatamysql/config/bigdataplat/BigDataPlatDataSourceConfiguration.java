package com.zzq.zzqframeworkdatamysql.config.bigdataplat;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zzq.zzqframeworkdatamysql.config.DataSourceConfigConstant;
import com.zzq.zzqframeworkdatamysql.config.properties.DruidProperties;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class BigDataPlatDataSourceConfiguration {

    @Bean(name = "bigDataPlatOneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.bigdataplat.one")
    public DataSource bigDataPlatOneDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.dataSource(dataSource);
    }

    @Bean(name = DataSourceConfigConstant.BIGDATAPLAT_SHARDING_DATASOURCE)
    @Primary
    public DataSource shardingDataSource(@Qualifier("bigDataPlatOneDataSource") DataSource bigDataPlatOneDataSource) {
        try {
            //分库设置
            Map<String, DataSource> dataSourceMap = new HashMap<>(2);
            //添加两个数据库database0和database1
            dataSourceMap.put("ds0", bigDataPlatOneDataSource);

            // 配置 sharding_table 表规则
            //TableRuleConfiguration shardingTableRuleConfiguration = new TableRuleConfiguration("sharding_table", "ds${0..1}.sharding_table${0..1}");
            // 行表达式分表规则
            //shardingTableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "sharding_table${id % 2}"));
            // 行表达式分库规则
            //shardingTableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "ds${id<2?0:1}"));
            // Sharding全局配
            ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
            shardingRuleConfiguration.setDefaultDataSourceName("ds0");
            //shardingRuleConfiguration.getTableRuleConfigs().add(shardingTableRuleConfiguration);
            // 创建数据源
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
