import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class ProducerConsumerWithLocks {

  public static void main(String[] args) throws InterruptedException{

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(50);
    
    class Consumer implements Callable<String> {

      public String call() throws InterruptedException, TimeoutException {
        
        int count = 0;
        while (count++ < 50) {
          queue.take();//blocking call
        }
        return "consumed " + (count - 1);

      }//End call
      
    }//End Consumer
      
    class Producer implements Callable<String> {

      public String call() throws InterruptedException {
          
        int count = 0;
        while (count++ < 50) {
          queue.put(Integer.toString(count));
        }
        return "produced " + (count - 1);

      }
        
    }//End Producer
    
    System.out.println("Producers and Consumers launched");
    
    List<Consumer> consumers = new ArrayList<>();
    for(int i = 0; i < 4; i++) {
      consumers.add(new Consumer());
    }
    
    List<Producer> producers = new ArrayList<>();
    for(int i = 0; i < 4; i++) {
      producers.add(new Producer());
    }
    
    List<Callable<String>> producersAndConsumers = new ArrayList<>();
    producersAndConsumers.addAll(producers);
    producersAndConsumers.addAll(consumers);
    
    
    ExecutorService executorService = Executors.newFixedThreadPool(8);//test with both 8 and 4 number of threads
    try {
      List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);
      futures.forEach(
          future -> {
            try {
              System.out.println(future.get());
            } catch(InterruptedException | ExecutionException e) {
              System.out.println("Exception: " + e.getMessage());
            }
          });
      
    } finally {
        executorService.shutdown();
        System.out.println("Executor Service shut down");
    }
  }
  
  
}
