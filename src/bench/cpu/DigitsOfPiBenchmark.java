package bench.cpu;

import bench.IBenchmark;
import timing.Timer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DigitsOfPiBenchmark implements IBenchmark {
    private int precision;
    private boolean cancellationRequested = false;
    private BigDecimal pi;

    @Override
    public void run() {
        computePi(precision);
    }

    @Override
    public void run(Object... params) {

    }

    @Override
    public void initialize(Object... params) {
        precision = (int)params[0];
    }

    @Override
    public void warmUp() {
        Timer timer = new Timer();
        double offset = 0;
        long previousTime = 0;
        timer.start();
        run();
        previousTime = timer.stop();
        int warmUpCounter = 12;
        do{
            timer.start();
            run();
            long time = timer.stop();
            offset = 100*(time - (float) previousTime) / ((float) previousTime);
        }while(offset < 0.1 && warmUpCounter-- > 0);  // warm up until offset is <10% or 12 times

    }

    @Override
    public void clean() {
        precision = 0;
        cancellationRequested = false;
    }

    @Override
    public void cancel() {
        cancellationRequested = true;
    }
    public BigDecimal getCalculatedPi(){
        return pi;
    }

    public BigDecimal computePi(int precision) {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = BigDecimal.ONE.divide(BigDecimal.valueOf(Math.sqrt(2)), precision, RoundingMode.HALF_UP);
        BigDecimal t = BigDecimal.valueOf(0.25);
        BigDecimal p = BigDecimal.ONE;

        for (int i = 0; i < precision; i++) {
            if (cancellationRequested) {
                clean();
                return null;
            }
            BigDecimal aNext = a.add(b).divide(BigDecimal.valueOf(2), precision, RoundingMode.HALF_UP);
            BigDecimal bNext = BigDecimal.valueOf(Math.sqrt(a.doubleValue() * b.doubleValue())).setScale(precision, RoundingMode.HALF_UP);
            BigDecimal tNext = t.subtract(p.multiply(a.subtract(aNext).pow(2))).setScale(precision, RoundingMode.HALF_UP);
            BigDecimal pNext = p.multiply(BigDecimal.valueOf(2)).setScale(precision, RoundingMode.HALF_UP);

            a = aNext;
            b = bNext;
            t = tNext;
            p = pNext;
        }

        BigDecimal sum = a.add(b).pow(2).setScale(precision, RoundingMode.HALF_UP);
        BigDecimal pi = sum.divide(t.multiply(BigDecimal.valueOf(4)), precision, RoundingMode.HALF_UP);
        this.pi = pi;
        return pi;
    }
}
