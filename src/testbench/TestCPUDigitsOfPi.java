package testbench;

import bench.IBenchmark;
import bench.cpu.DigitsOfPiBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import logging.TimeUnit;
import timing.Timer;

public class TestCPUDigitsOfPi {
    public static void main(String[] args) {
        Timer timer = new Timer();
        ILogger logger = new ConsoleLogger();
        IBenchmark benchmark = new DigitsOfPiBenchmark();
        int digits = 2000;
        benchmark.initialize(digits);
        logger.write("Warming up");
        benchmark.warmUp();
        logger.write("Running");
        timer.start();
        for (int i = 0; i < 12; i++) {
            timer.resume();
            benchmark.run();
            long time = timer.pause();
            logger.writeTime(String.format("Run %d: ", i), time, TimeUnit.MILLISECONDS);
        }
        long time = timer.stop();
        logger.writeTime("Finished in: ", time, TimeUnit.MILLISECONDS);
        logger.write("Calculated Pi: " + ((DigitsOfPiBenchmark)benchmark).getCalculatedPi());
    }
}
