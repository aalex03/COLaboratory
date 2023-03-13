package testbench;

import bench.DemoBenchmark;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.ITimer;
import timing.Timer;

public class TestDemoBenchmark {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger logger = new ConsoleLogger();
        IBenchmark benchmark = new DemoBenchmark();
        int sleepTime = 1000;
        benchmark.initialize(sleepTime);
        timer.start();
        for (int i = 0; i < 12; i++) {
            timer.resume();
            benchmark.run();
            long time = timer.pause();
            float offset = 100*(time - (float)sleepTime * 1000000) / ((float)sleepTime * 1000000);
            logger.writeTime("Run " + i + ": ", time, TimeUnit.MILLISECONDS);
            logger.write(String.format("Offset: %.2f%%", offset));
        }
        long time = timer.stop();
        logger.writeTime("Finished in: ", time, TimeUnit.SECONDS);
        logger.close();
        benchmark.clean();
    }
}
