package concurrency.pt;

import concurrency.commons.TaskSimulator;

import java.util.concurrent.CountDownLatch;

public class IoTask {
    private static final int MAX_THREADS = 10;

    public static void main() throws InterruptedException {
        keepMainThreadAliveUntilDaemonTasksComplete();
    }

    private static void keepMainThreadAliveUntilDaemonTasksComplete() throws InterruptedException {
        var latch = new CountDownLatch(MAX_THREADS);
        var builder = Thread.ofPlatform().daemon().name("daemon-", 1);
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
