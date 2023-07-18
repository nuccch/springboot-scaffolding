package org.chench.springboot.scaffolding.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 对Controller执行单元测试
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.controller.MvcTestContollerTest
 * @date 2023.07.17
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MvcTestContoller.class)
public class MvcTestContollerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMockAction() throws Exception {
        this.mockMvc.perform(get("/mvctest/action").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Test is ok"));
    }

    @Test
    public void testAccountList() throws Exception {
        this.mockMvc.perform(get("/account/list?start=0&offset=10").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
