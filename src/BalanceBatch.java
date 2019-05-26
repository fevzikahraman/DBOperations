import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

class BalanceBatch {
    private final List<RunnableBatch> threads = new ArrayList<>();


    public void startAll(int startIndex, int endIndex) {
        for (RunnableBatch t : threads) {
            new Thread(t).start();
        }
    }

    
}