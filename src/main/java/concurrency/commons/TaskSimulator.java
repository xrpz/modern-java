package concurrency.commons;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public final class TaskSimulator {
    private TaskSimulator() {}

    public static void ioIntensive(int i) {
        try {
            log.info("starting I/O task {}, Thread Info: {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(1));
            log.info("finished I/O task {}, Thread Info: {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cpuIntensive(int i) {
        log.info("starting CPU task, Thread Info: {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(() -> findFibonacci(i));
        log.info("finished CPU task, time taken: {}ms", timeTaken);
    }

    private static long findFibonacci(long n) {
        if (n < 2) {
            return n;
        }
        return findFibonacci(n - 1) + findFibonacci(n - 2);
    }
}
