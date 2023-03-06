package logging;

import java.io.FileWriter;

public class FileLogger implements ILogger{
    private String fileName;
    private FileWriter fileWriter;
    private static final TimeUnit timeUnit = TimeUnit.NANOSECONDS;
    public FileLogger(String fileName){
        this.fileName = fileName;
        try{
            fileWriter = new FileWriter(fileName);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void write(Object... values) {
        for(Object value : values)
            write(value.toString());
    }

    @Override
    public void write(String message) {
        try{
            fileWriter.write(message);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() {
        try{
            fileWriter.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void writeTime(String message, long time, TimeUnit unit) {
        write(message + " " + time + " " + unit);
    }
}
