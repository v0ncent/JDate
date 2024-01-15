package JDate.Exceptions;

public class NoScenesException extends JDateException{
    public NoScenesException() {
        super();
    }

    @Override
    protected String giveErrorMessage() {
        return "The Script list is empty! Please add scenes to the list.";
    }
}
