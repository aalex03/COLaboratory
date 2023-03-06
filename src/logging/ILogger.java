package logging;

public interface ILogger {
    public void write(String message);
    public void write(Object ... values);
    public void close();
    public void writeTime(String message, long time, TimeUnit unit);
}
