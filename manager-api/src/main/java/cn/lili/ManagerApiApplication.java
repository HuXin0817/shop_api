package cn.lili;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 运营后台 API
 *
 * @author Chopper
 * @since 2020/11/16 10:03 下午
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
public class ManagerApiApplication {

    @Primary
    @Bean
    public TaskExecutor primaryTask() {
        return new ThreadPoolTaskExecutor();
    }

    public static void main(String[] args) {
        // 设置系统属性，禁用 Elasticsearch 的 Netty 运行时可用处理器检测
        System.setProperty("es.set.netty.runtime.available.processors", "false");

        // 设置 RocketMQ 客户端使用 SLF4J 进行日志记录
        System.setProperty("rocketmq.client.logUseSlf4j", "true");

        // 启动 Spring Boot 应用程序
        SpringApplication.run(ManagerApiApplication.class, args);
    }

}