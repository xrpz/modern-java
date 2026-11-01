package concurrency.pt;

import concurrency.commons.TaskSimulator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CpuTask {
    private static final int TASKS_COUNT = 2 * Runtime.getRuntime().availableProcessors();

    static void main() {
        cpuIntensiveTask();
    }

    // 플랫폼 스레드는 OS가 번갈아 실행하도록 제어한다.(선점형 스케줄링)
    private static void cpuIntensiveTask() {
        for(var i = 0; i < TASKS_COUNT; i++) {
            Thread.ofPlatform().start(
                    () -> {
                        TaskSimulator.cpuIntensive(40);
                    }
            );
        }
    }
}
