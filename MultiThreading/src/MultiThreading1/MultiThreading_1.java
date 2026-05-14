package MultiThreading1;
import java.util.concurrent.*;

class TaskRunner implements Runnable {
    private final String taskName;

    public TaskRunner(String name) {
        this.taskName = name;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        System.out.println("[EXECUTION] " + taskName + 
                           " | Thread: " + current.getName() + 
                           " | Priority: " + current.getPriority());
        
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MultiThreading_1 {

    public static void main(String[] args) {
        System.out.println("--- Starting Advanced Multithreading Demo ---\n");

        ThreadGroup appGroup = new ThreadGroup("ApplicationGroup");
        
        Thread highPriorityThread = new Thread(appGroup, new TaskRunner("UrgentTask"));
        Thread lowPriorityThread = new Thread(appGroup, new TaskRunner("BackgroundTask"));

        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        
        highPriorityThread.start();
        lowPriorityThread.start();

        ExecutorService pool = Executors.newFixedThreadPool(3);
        
        System.out.println("\n--- Submitting Tasks to ThreadPool ---");
        for (int i = 1; i <= 5; i++) {
            pool.submit(new TaskRunner("PoolTask-" + i));
        }

        pool.shutdown();
        
        try {
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
            
            highPriorityThread.join();
            lowPriorityThread.join();
            
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted.");
        }

        System.out.println("\n--- All Threads Completed ---");
        System.out.println("Active threads in group '" + appGroup.getName() + "': " + appGroup.activeCount());
    }
}