package timing;

public class Timer implements ITimer {
    private long startTime;
    private long elapsedTime;
    private long totalTime;
    private void reset(){
        startTime = 0;
        elapsedTime = 0;
        totalTime = 0;
    }
    @Override
    public void start() {
        reset();
        startTime = System.nanoTime();
    }

    @Override
    public long stop() {
        elapsedTime = System.nanoTime() - startTime;
        //totalTime += elapsedTime;
        return totalTime;
    }
    @Override
    public void resume(){
        startTime = System.nanoTime();
    }
    @Override
    public long pause(){
        elapsedTime = System.nanoTime() - startTime;
        totalTime += elapsedTime;
        return elapsedTime;
    }
}
