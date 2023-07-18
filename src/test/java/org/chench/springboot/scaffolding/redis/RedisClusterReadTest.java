package org.chench.springboot.scaffolding.redis;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证Redis Cluster是否会自动读写分离
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.redis.RedisClusterReadTest
 * @date 2023.07.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RedisClusterReadTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    String key = "name";
    String value = "chench";

    int batch = 1024;
    long sleepOfMGet = 50;
    long sleepOfGet = 10;
    private List<String> idList = null;
    String addr1 = "10.122.190.86";
    int port1 = 7000;
    String addr2 = "10.122.190.87";
    int port2 = 7000;
    String addr3 = "10.122.190.88";
    int port3 = 7000;

    int poolSize = 200;
    ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);

    boolean stop = false;

    @Before
    public void setup() throws IOException {
        String path = "/Users/chench/slot.log";
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        idList = new LinkedList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String id = line.split("\t")[0].split(":")[1];
            idList.add(id);
        }
    }

    @Test
    public void testWrite() {
        redisTemplate.opsForValue().set(key, value);
    }

    @Test
    public void testRead() {
        String value = redisTemplate.opsForValue().get(key);
        Assert.assertNull(value);
    }

    /////////////////////////////////////////////////////////////////////////////

    @Test
    public void testReadMGetByRedisTemplate() {
        for (int i = 0; i < poolSize; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<String> batchList = new ArrayList<String>(batch);
                    while (!stop) {
                        for (String id : idList) {
                            String key = buildKey(id);
                            batchList.add(key);
                            if (batchList.size() >= batch) {
                                List<String> valueList = redisTemplate.opsForValue().multiGet(batchList);
                                System.out.println(valueList);
                                batchList.clear();
                                //sleepOfMillionSeconds(sleepOfMGet);
                            }
                        }
                    }// end while
                }
            }).start();
        }
//        String result = redisTemplate.opsForValue().get(key);
//        Assert.assertEquals(result, value);
    }

    @Test
    public void testReadGetByRedisTemplate() {
        for (String id : idList) {
            String key = buildKey(id);
            redisTemplate.opsForValue().get(key);
            sleepOfMillionSeconds(sleepOfGet);
        }
    }

    /////////////////////////////////////////////////////////////////////////

    /**
     * 使用Lettuce进行批量读取
     */
    @Test
    public void testReadMGetByLettuce() {
        RedisURI node1 = RedisURI.create(addr1, port1);
        RedisURI node2 = RedisURI.create(addr2, port2);
        RedisURI node3 = RedisURI.create(addr3, port3);
        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1, node2, node3));
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        List<String> batchList = new ArrayList<String>(batch);
        for (String id : idList) {
            String key = buildKey(id);
            batchList.add(key);
            if (batchList.size() >= batch) {
                String[] keys = new String[batch];
                batchList.toArray(keys);
                syncCommands.mget(keys);
                batchList.clear();
                sleepOfMillionSeconds(sleepOfMGet);
            }
        }
        connection.close();
        clusterClient.shutdown();
    }

    @Test
    public void testReadGetByLettuce() {
        RedisURI node1 = RedisURI.create(addr1, port1);
        RedisURI node2 = RedisURI.create(addr2, port2);
        RedisURI node3 = RedisURI.create(addr3, port3);
        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1, node2, node3));
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        for (String id : idList) {
            String key = buildKey(id);
            syncCommands.get(key);
            sleepOfMillionSeconds(sleepOfGet);
        }
        connection.close();
        clusterClient.shutdown();
    }

    /**
     * 使用Jedis批量读取
     */
    @Test
    public void testReadMGetByJedis() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort(addr1, port1));
        jedisClusterNodes.add(new HostAndPort(addr2, port2));
        jedisClusterNodes.add(new HostAndPort(addr3, port3));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        List<String> batchList = new ArrayList<String>(batch);
        for (String id : idList) {
            String key = buildKey(id);
            batchList.add(key);
            if (batchList.size() >= batch) {
                String[] keys = new String[batch];
                batchList.toArray(keys);
                jc.mget(keys);
                batchList.clear();
                sleepOfMillionSeconds(sleepOfMGet);
            }
        }
    }

    @Test
    public void testReadGetByJedis() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort(addr1, port1));
        jedisClusterNodes.add(new HostAndPort(addr2, port2));
        jedisClusterNodes.add(new HostAndPort(addr3, port3));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        List<String> batchList = new ArrayList<String>(batch);
        for (String id : idList) {
            String key = buildKey(id);
            jc.get(key);
            sleepOfMillionSeconds(sleepOfGet);
        }
    }

    ////////////////////////////////////////////////////////

    private String buildKey(String id) {
        return new StringBuilder().append("streamtopic:").append(id).toString();
    }

    private void sleepOfMillionSeconds(long millions) {
        try {
            Thread.sleep(millions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
