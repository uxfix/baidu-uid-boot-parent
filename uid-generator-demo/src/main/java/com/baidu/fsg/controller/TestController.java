package com.baidu.fsg.controller;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: TestController
 * @author: Yuan
 * @create: 2022/10/27
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    private final UidGenerator uidGenerator;

    public TestController(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }

    @GetMapping("/uid")
    public void generatorUid(){
        for (int i = 0; i < 20; i++) {
            System.out.println(uidGenerator.getUID());
        }
    }
}
