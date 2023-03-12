package logging;

import java.io.FileWriter;

public class FileLogger implements ILogger{
    private FileWriter fileWriter;

    public FileLogger(String fileName){
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
    public void writeTime(String message, long nanos, TimeUnit unit) {
        double time = TimeUnitHelper.convert(nanos, unit);
        write(String.format("%s %.3f %s", message, time, unit));
    }
}
