package Domain.handlers;

/**
 * Created by Stephen Adu on 22/09/2017.
 */
public class BaseErrorHandler implements IErrorHandler {

    @Override
    public void handleError(PriorityRunnable observed, String message) {
        System.out.println(message);
        observed.stop();
    }

}
