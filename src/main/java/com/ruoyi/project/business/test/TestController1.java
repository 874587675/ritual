package com.ruoyi.project.business.test;

import com.ruoyi.framework.web.domain.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName:testController
 * @description:
 * @author: zgc
 **/
@RestController
@RequestMapping("/test")
public class TestController1 {
    
    @Resource
    private Test testService;
    
    @RequestMapping("/test2")
    public R<Boolean> test() {
        try {
            return R.ok(testService.run());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.fail("执行失败");
    }
}
