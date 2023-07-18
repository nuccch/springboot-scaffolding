# 开发者文档


## 本地调试

添加如下JVM参数：

-Dlog.dir=./logs                // 设置日志保存目录
-Dspring.profiles.active=dev    // 设置profile


## 如何打包发布

在项目根目录下有一个打包脚本：build.sh，直接运行该脚本即可完成打包。
```shell
bash build.sh
```


## 关于数据库表自动初始化

在Spring Boot中可以通过属性“spring.datasource.schema”设置在应用启动时执行的sql文件，来达到初始化数据表的目的。
**值得注意的是：** 如果sql文件的中的语句存在语法错误，并不会影响应用的正常启动，但是也无法正常执行数据库表的初始化操作。
为了在应用启动时明确知道sql语句是否存在语法错误，可以设置spring.jdbc的日志级别为debug。
`logging.level.org.springframework.jdbc=DEBUG`



## 关于集成J2cache组件的说明

在使用J2cache时，关于Redis的连接信息直接在j2cache.properties文件中配置（虽然j2cache与springboot集成时使用了spring-data-redis，也不需要单独针对Redis进行其他配置）。
值得注意的是：
如果在项目中依赖了模块`spring-boot-starter-actuator`，则程序在启动时会执行一个Redis健康检查，虽然在j2cache.properties中配置了正确的指定主机IP地址，但是依然会以localhost作为redis连接地址，此时会报错，但是不影响j2cache的正常使用。
报错信息如下：
```text
org.springframework.data.redis.RedisConnectionFailureException: Cannot get Jedis connection; nested exception is redis.clients.jedis.exceptions.JedisConnectionException: Failed connecting to host localhost:6379
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.fetchJedisConnector(JedisConnectionFactory.java:281) ~[spring-data-redis-2.1.10.RELEASE.jar:2.1.10.RELEASE]
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.getConnection(JedisConnectionFactory.java:464) ~[spring-data-redis-2.1.10.RELEASE.jar:2.1.10.RELEASE]
	at org.springframework.data.redis.core.RedisConnectionUtils.doGetConnection(RedisConnectionUtils.java:132) ~[spring-data-redis-2.1.10.RELEASE.jar:2.1.10.RELEASE]
	at org.springframework.data.redis.core.RedisConnectionUtils.getConnection(RedisConnectionUtils.java:95) ~[spring-data-redis-2.1.10.RELEASE.jar:2.1.10.RELEASE]
	at org.springframework.data.redis.core.RedisConnectionUtils.getConnection(RedisConnectionUtils.java:82) ~[spring-data-redis-2.1.10.RELEASE.jar:2.1.10.RELEASE]
	at org.springframework.boot.actuate.redis.RedisHealthIndicator.doHealthCheck(RedisHealthIndicator.java:55) ~[spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.health.AbstractHealthIndicator.health(AbstractHealthIndicator.java:82) ~[spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.health.CompositeHealthIndicator.health(CompositeHealthIndicator.java:95) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.health.CompositeHealthIndicator.health(CompositeHealthIndicator.java:95) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.health.HealthEndpoint.health(HealthEndpoint.java:50) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_181]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_181]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_181]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_181]
	at org.springframework.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:282) [spring-core-5.1.9.RELEASE.jar:5.1.9.RELEASE]
	at org.springframework.boot.actuate.endpoint.invoke.reflect.ReflectiveOperationInvoker.invoke(ReflectiveOperationInvoker.java:76) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.endpoint.annotation.AbstractDiscoveredOperation.invoke(AbstractDiscoveredOperation.java:60) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.endpoint.jmx.EndpointMBean.invoke(EndpointMBean.java:121) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at org.springframework.boot.actuate.endpoint.jmx.EndpointMBean.invoke(EndpointMBean.java:96) [spring-boot-actuator-2.1.7.RELEASE.jar:2.1.7.RELEASE]
	at com.sun.jmx.interceptor.DefaultMBeanServerInterceptor.invoke(DefaultMBeanServerInterceptor.java:819) [na:1.8.0_181]
	at com.sun.jmx.mbeanserver.JmxMBeanServer.invoke(JmxMBeanServer.java:801) [na:1.8.0_181]
	at javax.management.remote.rmi.RMIConnectionImpl.doOperation(RMIConnectionImpl.java:1468) [na:1.8.0_181]
	at javax.management.remote.rmi.RMIConnectionImpl.access$300(RMIConnectionImpl.java:76) [na:1.8.0_181]
	at javax.management.remote.rmi.RMIConnectionImpl$PrivilegedOperation.run(RMIConnectionImpl.java:1309) [na:1.8.0_181]
	at javax.management.remote.rmi.RMIConnectionImpl.doPrivilegedOperation(RMIConnectionImpl.java:1401) [na:1.8.0_181]
	at javax.management.remote.rmi.RMIConnectionImpl.invoke(RMIConnectionImpl.java:829) [na:1.8.0_181]
	at sun.reflect.GeneratedMethodAccessor69.invoke(Unknown Source) ~[na:na]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_181]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_181]
	at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:357) [na:1.8.0_181]
	at sun.rmi.transport.Transport$1.run(Transport.java:200) [na:1.8.0_181]
	at sun.rmi.transport.Transport$1.run(Transport.java:197) [na:1.8.0_181]
	at java.security.AccessController.doPrivileged(Native Method) [na:1.8.0_181]
	at sun.rmi.transport.Transport.serviceCall(Transport.java:196) [na:1.8.0_181]
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:573) [na:1.8.0_181]
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:834) [na:1.8.0_181]
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:688) [na:1.8.0_181]
	at java.security.AccessController.doPrivileged(Native Method) [na:1.8.0_181]
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:687) [na:1.8.0_181]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) ~[na:1.8.0_181]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) ~[na:1.8.0_181]
	at java.lang.Thread.run(Thread.java:748) ~[na:1.8.0_181]
Caused by: redis.clients.jedis.exceptions.JedisConnectionException: Failed connecting to host localhost:6379
	at redis.clients.jedis.Connection.connect(Connection.java:207) ~[jedis-2.9.3.jar:na]
	at redis.clients.jedis.BinaryClient.connect(BinaryClient.java:101) ~[jedis-2.9.3.jar:na]
	at redis.clients.jedis.BinaryJedis.connect(BinaryJedis.java:1844) ~[jedis-2.9.3.jar:na]
	at org.springframework.data.redis.connection.jedis.JedisConnectionFactory.fetchJedisConnector(JedisConnectionFactory.java:276) ~[spring-data-redis-2.1.10.RELEASE.jar:2.1.10.RELEASE]
	... 41 common frames omitted
Caused by: java.net.ConnectException: Connection refused (Connection refused)
	at java.net.PlainSocketImpl.socketConnect(Native Method) ~[na:1.8.0_181]
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350) ~[na:1.8.0_181]
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206) ~[na:1.8.0_181]
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188) ~[na:1.8.0_181]
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392) ~[na:1.8.0_181]
	at java.net.Socket.connect(Socket.java:589) ~[na:1.8.0_181]
	at redis.clients.jedis.Connection.connect(Connection.java:184) ~[jedis-2.9.3.jar:na]
	... 44 common frames omitted
```
如果一定避免这个报错信息的出现，就需要手动在Spring容器中注入一个`RedisConnectionFactory`对象，并明确配置Redis的连接信息为指定的主机IP。
如下手动注入`RedisConnectionFactory`对象示例：
```java
@Bean
public RedisConnectionFactory redisConnectionFactory() {
    JedisPoolConfig poolConfig=new JedisPoolConfig();
    poolConfig.setMaxIdle(10);
    poolConfig.setMinIdle(0);
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestOnReturn(true);
    poolConfig.setTestWhileIdle(true);
    poolConfig.setNumTestsPerEvictionRun(10);
    poolConfig.setTimeBetweenEvictionRunsMillis(60000);
    JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
    factory.getStandaloneConfiguration().setHostName("localhost");
    factory.getStandaloneConfiguration().setPort(6379);
    return factory;
}
```









