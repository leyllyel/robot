import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {
        int numThreads = 1000;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new RouteGenerator());
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        int maxFreq = 0;
        int maxFreqSize = 0;
        System.out.println("Самое частое количество повторений:");
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            int size = entry.getKey();
            int freq = entry.getValue();
            if (freq > maxFreq) {
                maxFreq = freq;
                maxFreqSize = size;
            }
            System.out.println("- " + size + " (" + freq + " раз)");
        }
        System.out.println("Другие размеры:");
        for (Map.Entry<Integer, Integer> entry : sizeToFreq.entrySet()) {
            int size = entry.getKey();
            int freq = entry.getValue();
            if (size != maxFreqSize) {
                System.out.println("- " + size + " (" + freq + " раз)");
            }
        }
    }
}
