package org.chench.springboot.scaffolding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * 应用程序启动入口类
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.SpringbootScaffoldingApplication
 * @date 2023.07.17
 */
@EnableScheduling // 开启定时任务支持
@SpringBootApplication
public class SpringbootScaffoldingApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringbootScaffoldingApplication.class);

	public static void main(String[] args) {
	    logger.info("Start ...");
	    initEnv();
        addShutdsownHook();
		SpringApplication.run(SpringbootScaffoldingApplication.class, args);
		logger.info("Start DONE.");
	}

	// 初始化环境变量
	private static void initEnv() {
	    // 设置日志目录
	    String logDirKey = "log.dir";
	    String logDirValue = System.getProperty(logDirKey);
	    if(logDirValue == null || "".equals(logDirValue.trim())) {
	        logDirValue = "./logs";
        }
	    System.setProperty(logDirKey, logDirValue);
    }

    // 注册应用关闭钩子
    // 注：程序在执行过程中出现RuntimeException和OOM，不会触发关闭钩子执行
	private static void addShutdsownHook() {
        // 在Spring Boot应用中通过监听信号量和注册关闭钩子来实现在进程退出之前执行收尾工作
        // 监听信号量
        Signal sg = new Signal("TERM");
        Signal.handle(sg, new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                logger.info("do signal handle: {}", signal.getName());
                System.exit(0);
            }
        });

        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                // 执行收尾工作
                // to do something
                logger.info("do something on shutdown hook");
            }
        });
    }
}
