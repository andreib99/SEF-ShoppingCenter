package ShoppingCenter.Exceptions;

public class PasswordNeededException extends Exception{

    public PasswordNeededException()
    {
        super("You need to enter the password");
    }

}
