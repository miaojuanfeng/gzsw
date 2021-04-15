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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "gz.sw.mapper.write", sqlSessionFactoryRef = "writeSqlSessionFactory")
public class WriteConfig {
    // 将这个对象放入Spring容器中
    @Bean(name = "writeDataSource")
    // 表示这个数据源是默认数据源
    @Primary
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "writeSqlSessionFactory")
    // 表示这个数据源是默认数据源
    @Primary
    // @Qualifier表示查找Spring容器中名字为writeDataSource的对象
    public SqlSessionFactory writeSqlSessionFactory(@Qualifier("writeDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/write/*.xml"));
        return bean.getObject();
    }
    @Bean("writeSqlSessionTemplate")
    // 表示这个数据源是默认数据源
    @Primary
    public SqlSessionTemplate writeSqlSessionTemplate(
            @Qualifier("writeSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
