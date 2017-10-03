package Domain.handlers;

/**
 * Created by Stephen Adu on 22/09/2017.
 */
public interface IErrorHandler{

    void handleError(PriorityRunnable observed, String message);

}
