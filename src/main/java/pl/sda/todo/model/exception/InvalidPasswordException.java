package pl.sda.todo.model.exception;

public class InvalidPasswordException extends TodoException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
