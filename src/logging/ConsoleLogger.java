package logging;

public class ConsoleLogger implements ILogger {
    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public void write(Object... values) {
        for (var value : values) {
            System.out.println(value);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void writeTime(String message, long nanos, TimeUnit unit) {
        double time = TimeUnitHelper.convert(nanos, unit);
        write(String.format("%s %.3f %s", message, time, unit));
    }
}

