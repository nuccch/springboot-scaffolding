package org.chench.springboot.scaffolding;

import cn.hutool.core.util.StrUtil;

/**
 * @author chench
 * @desc org.chench.springboot.scaffolding.HuToolTest
 * @date 2023.07.17
 */
public class HuToolTest {
    public static void main(String[] args) {
        String str = null;
        System.out.println(StrUtil.hasBlank(str));
        str = " ";
        System.out.println(StrUtil.hasBlank(str));
    }
}
