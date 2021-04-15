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
@MapperScan(basePackages = "gz.sw.mapper.read2", sqlSessionFactoryRef = "read2SqlSessionFactory")
public class Read2Config {
    @Bean(name = "read2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read2")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "read2SqlSessionFactory")
    public SqlSessionFactory read2SqlSessionFactory(@Qualifier("read2DataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/read2/*.xml"));
        return bean.getObject();
    }
    @Bean("read2SqlSessionTemplate")
    public SqlSessionTemplate read2SqlSessionTemplate(
            @Qualifier("read2SqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
