package gz.sw.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "gz.sw.mapper.read3", sqlSessionFactoryRef = "read3SqlSessionFactory")
public class Read3Config {
    @Bean(name = "read3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read3")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "read3SqlSessionFactory")
    public SqlSessionFactory read3SqlSessionFactory(@Qualifier("read3DataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/read3/*.xml"));
        return bean.getObject();
    }
    @Bean("read3SqlSessionTemplate")
    public SqlSessionTemplate read3SqlSessionTemplate(
            @Qualifier("read3SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
