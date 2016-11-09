import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import threads_action.BalanceActions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jack on 06.11.2016.
 */
public class TestBalanceAtions {

    private BalanceActions balanceActions;
    private BalanceActions.AddThread addThread;
    private BalanceActions.SubThread subThread;
    private BalanceActions.IContainer iContainer;
    private int additional;

    ExecutorService service;

    @Before
    public void init() {

        service = Executors.newFixedThreadPool(2);
        additional = 777;

        balanceActions = new BalanceActions();
        iContainer = new BalanceActions.Container();

        addThread = new BalanceActions.AddThread(iContainer, 10, additional);
        subThread = new BalanceActions.SubThread(iContainer, 10, additional);

        service.submit(addThread);
        service.submit(subThread);
    }

    @After
    public void cleanData() {

        try {
            service.shutdownNow();
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void testZeroPoint() {

        service.shutdown();
        while (!service.isTerminated()) {
            try {
                service.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(0, iContainer.getBalance());
    }

    @Test
    public void testAdd() {

        new BalanceActions.AddThread(iContainer, 1, additional);
        Assert.assertEquals(777, iContainer.getBalance());
    }

    @Test
    public void testSub() {

        BalanceActions.SubThread subTr = new BalanceActions.SubThread(iContainer, 1, additional);
        Assert.assertEquals(-777, iContainer.getBalance());
    }

}
