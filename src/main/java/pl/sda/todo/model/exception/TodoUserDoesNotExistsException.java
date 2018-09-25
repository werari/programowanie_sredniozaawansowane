package pl.sda.todo.model.exception;

public class TodoUserDoesNotExistsException extends TodoException {

    public TodoUserDoesNotExistsException(String message) {
        super(message);
    }
}
