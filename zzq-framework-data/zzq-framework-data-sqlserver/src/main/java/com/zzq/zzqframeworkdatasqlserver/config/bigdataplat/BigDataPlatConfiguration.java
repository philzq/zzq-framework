package com.zzq.zzqframeworkdatasqlserver.config.bigdataplat;

import com.zzq.zzqframeworkdatasqlserver.config.DataSourceConfigConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceConfigConstant.BIGDATAPLAT_BASE_PACKAGES,sqlSessionFactoryRef = DataSourceConfigConstant.BIGDATAPLAT_SQL_SESSION_FACTORY)
public class BigDataPlatConfiguration {

    @Autowired
    @Qualifier(DataSourceConfigConstant.BIGDATAPLAT_SHARDING_DATASOURCE)
    private DataSource dataSource;

    @Bean(name = "bigDataPlatDataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = DataSourceConfigConstant.BIGDATAPLAT_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory()
            throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfigConstant.BIGDATAPLAT_MAPPER_LOCATION));
        return sessionFactory.getObject();
    }


}
