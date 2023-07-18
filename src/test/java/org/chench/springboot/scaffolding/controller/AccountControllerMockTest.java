package org.chench.springboot.scaffolding.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 在Mock环境测试Controller: 使用MockMvc
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.AccountControllerMockTest
 * @date 2023.07.17
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AccountControllerMockTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testAccountList() throws Exception {
        this.mockMvc.perform(get("/account/list?start=0&offset=10")).andExpect(status().isOk());
    }

}
