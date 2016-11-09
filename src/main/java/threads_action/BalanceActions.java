package threads_action;

import threads_action.rev.Threads1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jack on 06.11.2016.
 */
public class BalanceActions {

    private static final Logger LOG = Logger.getLogger(Threads1.class.getName());


    public static void main(String[] args) {

        LOG.setLevel(Level.INFO);
        LOG.addHandler(new ConsoleHandler());

        IContainer container = new Container();

        long start = System.currentTimeMillis();

        //create fixed pool for 2- threads
        ExecutorService service = Executors.newFixedThreadPool(20);

        int operationCount = 77;
        int additional = 777;

        //create 100 sub and 100 add threads
        for (int i = 0; i < 100; i++) {
            //Future future =
            service.submit(new AddThread(container, operationCount, additional));
            service.submit(new SubThread(container, operationCount, additional));
        }

        service.shutdown();

        while (!service.isTerminated()) {
            try {
                service.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        int balance2 = container.getBalance();
        System.out.println("getBalance: " + balance2);
    }


    public interface IContainer {

        void addBalance(int additional);

        void subBalance(int additional);

        int getBalance();
    }

    public static class Container implements IContainer {

        private volatile AtomicInteger balance = new AtomicInteger();

        //int additional = 777;

        @Override
        public void addBalance(int additional) {// monitor this

            int tempBalance = balance.addAndGet(additional);
            System.out.println(tempBalance);
        }

        @Override
        public void subBalance(int additional) {
            //int tempBalance = balance.getAndAdd(-(-additional));
//            AtomicInteger forSub = new AtomicInteger(-additional);
//            int tempBalance = balance.getAndAdd(forSub);
            int tempBalance = balance.getAndAdd(-(-additional));
            System.out.println(tempBalance);
        }

        @Override
        public int getBalance() {
            return balance.get();
        }

    }

    public static class AddThread implements Runnable {

        public IContainer container;
        private int operationCount;
        private int additional;

        public AddThread(IContainer container, int operationCount, int additional) {
            this.container = container;
            this.operationCount = operationCount;
            this.additional = additional;
        }

        @Override
        public void run() {
            for (int i = 0; i < operationCount; i++) {
                container.addBalance(additional);
            }
        }
    }

    public static class SubThread implements Runnable {

        private IContainer container;
        private int operationCount;
        private int additional;

        public SubThread(IContainer container, int operationCount, int additional) {
            this.container = container;
            this.operationCount = operationCount;
            this.additional = additional;
        }

        @Override
        public void run() {
            for (int i = 0; i < operationCount; i++) {
                container.subBalance(additional);
            }
        }
    }


}
