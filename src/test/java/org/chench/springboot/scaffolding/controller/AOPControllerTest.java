package org.chench.springboot.scaffolding.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * 验证AOP切面处理
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.AOPControllerTest
 * @date 2023.07.17
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AOPControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testM1() throws Exception {
        this.webTestClient.get().uri("/aop/m1").exchange().expectStatus().isOk();
    }

    @Test
    public void testM2() throws Exception{
        this.webTestClient.get().uri("/aop/m2").exchange().expectStatus().isOk();
    }
}
