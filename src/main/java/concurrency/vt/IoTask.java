package concurrency.vt;

import concurrency.commons.TaskSimulator;

import java.util.concurrent.CountDownLatch;

public class IoTask {
    private static final int MAX_THREADS = 10000;

    public static void main() throws InterruptedException {
        keepMainThreadAliveUntilVirtualThreadComplete();
    }

    private static void keepMainThreadAliveUntilVirtualThreadComplete() throws InterruptedException {
        var latch = new CountDownLatch(MAX_THREADS);
        var builder = Thread.ofVirtual().name("virtual-", 1);
        for(var i = 0; i < MAX_THREADS; i++) {
            final int j = i;
            var thread = builder.unstarted(() -> {
                TaskSimulator.ioIntensive(j);
                latch.countDown();
            });
            thread.start();
        }
        latch.await();
    }
}
