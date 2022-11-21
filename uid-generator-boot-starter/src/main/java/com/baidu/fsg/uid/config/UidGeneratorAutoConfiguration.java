package com.baidu.fsg.uid.config;

import com.baidu.fsg.uid.UidGenerator;
import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO;
import com.baidu.fsg.uid.worker.dao.impl.JdbcWorkerNodeDAOImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @desc: UidGeneratorAutoConfiguration
 * @author: Yuan
 * @create: 2022/10/27
 **/
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(UidGeneratorProperties.class)
public class UidGeneratorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WorkerNodeDAO workerNodeDAO(JdbcTemplate jdbcTemplate) {
        return new JdbcWorkerNodeDAOImpl(jdbcTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Bean
    @ConditionalOnProperty(prefix = "uid-generator", name = "class-name", havingValue = "com.baidu.fsg.uid.impl.DefaultUidGenerator")
    public UidGenerator defaultUidGenerator(UidGeneratorProperties properties, WorkerIdAssigner workerIdAssigner) {
        DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
        defaultUidGenerator.setWorkerIdAssigner(workerIdAssigner);
        defaultUidGenerator.setTimeBits(properties.getTimeBits());
        defaultUidGenerator.setWorkerBits(properties.getWorkerBits());
        defaultUidGenerator.setSeqBits(properties.getSeqBits());
        defaultUidGenerator.setEpochStr(properties.getEpochStr());
        return defaultUidGenerator;
    }


    @Bean
    @ConditionalOnProperty(prefix = "uid-generator", name = "class-name", havingValue = "com.baidu.fsg.uid.impl.CachedUidGenerator")
    public UidGenerator cachedUidGenerator(UidGeneratorProperties properties, WorkerIdAssigner workerIdAssigner) {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(workerIdAssigner);
        cachedUidGenerator.setTimeBits(properties.getTimeBits());
        cachedUidGenerator.setWorkerBits(properties.getWorkerBits());
        cachedUidGenerator.setSeqBits(properties.getSeqBits());
        cachedUidGenerator.setEpochStr(properties.getEpochStr());
        cachedUidGenerator.setBoostPower(properties.getBoostPower());
        cachedUidGenerator.setPaddingFactor(properties.getPaddingFactor());
        cachedUidGenerator.setScheduleInterval(properties.getScheduleInterval());
        cachedUidGenerator.setRejectedPutBufferHandler(properties.getRejectedPutBufferHandler());
        cachedUidGenerator.setRejectedTakeBufferHandler(properties.getRejectedTakeBufferHandler());
        return cachedUidGenerator;
    }
}
