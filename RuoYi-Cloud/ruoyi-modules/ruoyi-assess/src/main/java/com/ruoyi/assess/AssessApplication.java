package com.ruoyi.assess;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2021 xnew, Inc. All rights reserved. <p>
 * Company: 北京新纽科技有限公司<p>
 *
 * @author greenwave
 * @since 2021/4/22 11:29
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class AssessApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(AssessApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  考核模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
