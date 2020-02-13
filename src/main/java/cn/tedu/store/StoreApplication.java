package cn.tedu.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    /**
     * 设置上传文件的最大值和请求参数的最大值
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大

//		factory.setMaxFileSize("10MB"); //设置上传文件的最大值，已经过期了
        factory.setMaxFileSize(DataSize.ofMegabytes(10));//设置为10M
//		设置总上传数据总大小
//		factory.setMaxRequestSize("30MB");//设置请求的最大值，已经过期了
        factory.setMaxRequestSize(DataSize.ofMegabytes(50));//设置为50兆
        return factory.createMultipartConfig();
    }
}
