package org.chench.springboot.scaffolding.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 在真实环境中测试Contoller接口：使用WebTestClient或者TestRestTemplate
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.AccountControllerTest
 * @date 2023.07.17
 */
@AutoConfigureWebTestClient
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TestRestTemplate testRestTemplate;

    // 在使用WebTestClient时，必须使用注解@AutoConfigureWebTestClient，同时必须引入依赖spring-webflux
    // 可以直接添加依赖：spring-boot-starter-webflux
    // 否则报错：No qualifying bean of type 'org.springframework.test.web.reactive.server.WebTestClient' available
    @Test
    public void accountListTestByWebTestClient() {
        this.webTestClient
                .get()
                .uri("/account/list?start=0&offset=10")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void accountListTestByRestTemplate() {
        Map<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("start", 0);
        urlVariables.put("offset", 10);
        int code = this.testRestTemplate.getForEntity("/account/list?start={start}&offset={offset}", Object.class, urlVariables).getStatusCode().value();
        assertThat(code).isEqualTo(200);
    }
}