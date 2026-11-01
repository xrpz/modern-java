package concurrency.commons;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public final class CommonUtils {
    private CommonUtils() {}

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static long timer(Runnable runnable) {
        var start = System.currentTimeMillis();
        runnable.run();
        var end = System.currentTimeMillis();
        return end - start;
    }
}
