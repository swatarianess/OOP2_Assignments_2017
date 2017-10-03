package test;

import Domain.handlers.BaseErrorHandler;
import Domain.handlers.IErrorHandler;
import Domain.handlers.PriorityRunnable;

/**
 * Created by Stephen Adu on 22/09/2017.
 */
public class Test1 implements PriorityRunnable {

    private BaseErrorHandler baseErrorHandler;
    private int priority;

    public Test1(int Priority) {
        super();
        baseErrorHandler = new BaseErrorHandler();
        this.priority = 0;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int gePriority() {
        return priority;
    }

    @Override
    public int getImportance() {
        return 0;
    }

    @Override
    public String giveErrorDetails() {
        return "error details";
    }

    @Override
    public void setErrorHandler(IErrorHandler e) {
       this.baseErrorHandler = (BaseErrorHandler) e;
    }

    @Override
    public void stop() {
        System.out.println("Stopping!");
    }

    @Override
    public void run() {
        System.out.println(String.format("%s: %s "));
    }
}
