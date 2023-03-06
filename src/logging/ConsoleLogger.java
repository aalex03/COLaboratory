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
    public void writeTime(String message, long timeNano, TimeUnit unit) {
        int difference = unit.ordinal() - TimeUnit.NANOSECONDS.ordinal();  //diff = -2
        write(String.format("%s %.3f %s", message, timeNano / Math.pow(10, difference*3), unit));
    }
}

