package MultiThreading1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ShipmentProcessor implements Runnable {
    private final String shipmentId;

    public ShipmentProcessor(String id) {
        this.shipmentId = id;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        System.out.println("[PROCESSING] ID: " + shipmentId + 
                           " | Handling Thread: " + current.getName() + 
                           " | Priority Level: " + current.getPriority());
        
        try {
            Thread.sleep(800); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class LogisticsControlCenter {

    public static void main(String[] args) {
        System.out.println("=== GLOBAL LOGISTICS MANAGEMENT SYSTEM ===\n");

        ThreadGroup shippingDept = new ThreadGroup("ShippingDepartment");
        
        Thread expressShipment = new Thread(shippingDept, new ShipmentProcessor("EXP-9901"));
        Thread standardShipment = new Thread(shippingDept, new ShipmentProcessor("STD-1102"));

        expressShipment.setPriority(Thread.MAX_PRIORITY);
        standardShipment.setPriority(Thread.MIN_PRIORITY);
        
        expressShipment.start();
        standardShipment.start();

        ExecutorService globalFleetPool = Executors.newFixedThreadPool(4);
        
        System.out.println("\n--- Deploying Automated Delivery Fleet ---");
        for (int i = 1; i <= 6; i++) {
            globalFleetPool.submit(new ShipmentProcessor("AUTO-FLIGHT-" + i));
        }

        globalFleetPool.shutdown();
        
        try {
            if (!globalFleetPool.awaitTermination(10, TimeUnit.SECONDS)) {
                globalFleetPool.shutdownNow();
            }
            
            expressShipment.join();
            standardShipment.join();
            
        } catch (InterruptedException e) {
            System.err.println("System Shutdown Interrupted.");
        }

        System.out.println("\n=== ALL SHIPMENTS PROCESSED ===");
        System.out.println("Active Logistics Threads: " + shippingDept.activeCount());
    }
}