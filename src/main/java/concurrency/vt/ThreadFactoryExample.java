package concurrency.vt;

import concurrency.commons.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class ThreadFactoryExample {
    static void main() {
        startTasksWithThreadFactory(Thread.ofVirtual().factory());
    }

    private static void startTasksWithThreadFactory(ThreadFactory factory) {
        for(var i = 0; i < 3; i++) {
            var thread = factory.newThread(() -> {
                log.info("Task started. {}", Thread.currentThread());
                var childThread = factory.newThread(() -> {
                   log.info("Child task started. {}", Thread.currentThread());
                    CommonUtils.sleep(Duration.ofSeconds(1));
                   log.info("Task finished. {}", Thread.currentThread());
                });
                childThread.start();
                log.info("Task finished. {}", Thread.currentThread());
            });
            thread.start();
        }
    }
}
