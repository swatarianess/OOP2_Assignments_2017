package Domain.handlers;

/**
 * Created by Stephen Adu on 22/09/2017.
 */

public interface PriorityRunnable extends Runnable {

    void setPriority(int priority);

    int gePriority();

    int getImportance();

    String giveErrorDetails();

    void setErrorHandler(IErrorHandler handler); // Todo: Must extend IErrorHandler?

    void stop();
}
