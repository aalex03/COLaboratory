package bench;

import java.util.Random;

public class DemoBenchmark implements IBenchmark{
    private Random random = new Random();
    private int size;
    private int[] data;
    @Override
    public void initialize(Object... params) {
        size = (int)params[0];
        data = new int[size];
        for(int i = 0; i < size; i++)
            data[i] = random.nextInt();
    }

    @Override
    public void warmUp() {

    }

    private void bubbleSort(){
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size - 1; j++)
                if(data[j] > data[j + 1]){
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
    }
    @Override
    public void run() {
        //bubbleSort();
        try{
            Thread.sleep(size);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(Object... options) {

    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }
}
