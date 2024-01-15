package JDate.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JDateException extends Throwable {
    private final Logger logger = LoggerFactory.getLogger(JDateException.class);

    public JDateException() {
        throwError();
    }

    private void throwError() {
        String errorMessage;
        if (getErrorMessage() == null) {
            errorMessage = "No Message Given.";
        } else {
            errorMessage = getErrorMessage();
        }
        logger.error(errorMessage);
    }

    private String getErrorMessage() {
        return giveErrorMessage();
    }

   protected abstract String giveErrorMessage();
}
