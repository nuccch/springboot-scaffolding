package org.chench.springboot.scaffolding;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chench
 * @desc org.chench.springboot.scaffolding.J2CacheTest
 * @date 2023.07.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"dev"})
public class J2CacheTest {

//    @Autowired
//    private CacheChannel cacheChannel;
//
//    @Test
//    public void testJ2CacheLocal() {
//        // 验证j2cache的本地缓存功能是否生效
//        String region = "test";
//        String key = "test_local_key";
//        cacheChannel.set(region, key, 20);
//
//        long start = System.nanoTime();
//        cacheChannel.get(region, key);
//        System.out.println("interval: " + (System.nanoTime() - start) + " ns");
//
//        start = System.nanoTime();
//        cacheChannel.get(region, key);
//        System.out.println("interval: " + (System.nanoTime() - start) + " ns");
//    }
}
