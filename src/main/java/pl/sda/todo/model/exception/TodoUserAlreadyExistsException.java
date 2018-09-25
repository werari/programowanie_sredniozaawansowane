package pl.sda.todo.model.exception;

public class TodoUserAlreadyExistsException extends TodoException {

    public TodoUserAlreadyExistsException(String message) {
        super(message);
    }
}
