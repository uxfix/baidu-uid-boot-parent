package com.baidu.fsg.uid.config;

import com.baidu.fsg.uid.buffer.RejectedPutBufferHandler;
import com.baidu.fsg.uid.buffer.RejectedTakeBufferHandler;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @desc: UidGeneratorProperties
 * @author: Yuan
 * @create: 2022/10/27
 **/
@ConfigurationProperties(prefix = "uid-generator")
@Data
public class UidGeneratorProperties {

    private String className;
    private Integer timeBits;
    private Integer workerBits;
    private Integer seqBits;
    private String epochStr;
    private Integer boostPower;
    private Integer paddingFactor;
    private Long scheduleInterval;
    private RejectedPutBufferHandler rejectedPutBufferHandler;
    private RejectedTakeBufferHandler rejectedTakeBufferHandler;

}
