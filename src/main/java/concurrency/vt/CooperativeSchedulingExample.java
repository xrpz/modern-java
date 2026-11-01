package concurrency.vt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CooperativeSchedulingExample {

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    static void main() throws InterruptedException {
        var builder = Thread.ofVirtual();
        var t1 = builder.unstarted(() -> runTaskWithCooperativeYielding(1));
        var t2 = builder.unstarted(() -> runTaskWithCooperativeYielding(2));
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void runTaskWithCooperativeYielding(int threadNumber) {
        log.info("thread-{} started", threadNumber);
        for(var i = 0; i < 10; i++) {
            log.info("thread-{} is printing {}, Thread: {}", threadNumber, i, Thread.currentThread());
            Thread.yield();
        }
        log.info("thread-{} finished", threadNumber);
    }
}
