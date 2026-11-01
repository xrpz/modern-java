package concurrency.vt;

import concurrency.commons.TaskSimulator;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CpuTask {
    private static final int TASKS_COUNT = 2 * Runtime.getRuntime().availableProcessors();

    static void main() throws InterruptedException {
        cpuIntensiveTask();
    }

    // 가상 스레드는 I/O 대기 등으로 실행을 양보(Thread.yield())하면 JVM이 다른 스레드로 전환한다.(협력형 스케줄링)
    private static void cpuIntensiveTask() throws InterruptedException {
        var latch = new CountDownLatch(TASKS_COUNT);
        for(var i = 0; i < TASKS_COUNT; i++) {
            Thread.ofVirtual().start(
                    () -> {
                        TaskSimulator.cpuIntensive(40);
                        latch.countDown();
                    }
            );
        }
        latch.await();
    }
}
