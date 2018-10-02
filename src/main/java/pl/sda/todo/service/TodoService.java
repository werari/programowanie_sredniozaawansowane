package pl.sda.todo.service;

import pl.sda.todo.model.Todo;
import pl.sda.todo.model.TodoUser;
import pl.sda.todo.model.exception.InvalidPasswordException;
import pl.sda.todo.model.exception.TodoUserDoesNotExistsException;
import pl.sda.todo.repository.TodoRepository;
import pl.sda.todo.repository.TodoUserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;
    private TodoUserRepository todoUserRepository;

    public List<Todo> findTodosByCreatorName(String creatorName){
       return todoRepository.findAll()
                .stream()
                .filter(todo-> todo.getCreator()
                        .getName()
                        .equalsIgnoreCase(creatorName))
                .collect(Collectors.toList());
//TODO equalsIgnoreCase- metoda pozwaljąca na omijanie rózniwcy wynikajacej z duzych i małych liter w Stringach
    }



    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    public TodoUser register(String name, String password) {
        if (todoUserRepository.exists(name)) {
            return null;
        }
        TodoUser user = new TodoUser(name, password);
        todoUserRepository.save(user);
        return user;
    }

    public TodoUser login(String name, String password) {
        if (!todoUserRepository.exists(name)) {
            throw new TodoUserDoesNotExistsException("User with name \"" + name + "\" does not exists");
        }
        TodoUser user = todoUserRepository.findByName(name);
        if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException("Invalid password");
        }
        return user;
    }

    public List<Todo> findAllTodo() {

        return todoRepository.findAll();
    }

    public Optional <Todo> findTodoById(Integer todoId) {
        return todoRepository.findById(todoId);
    }

    public Optional<Todo> removeTodo(Integer todoIdToRemove) {
        Optional<Todo> todo = todoRepository.findById(todoIdToRemove);
        if (todo.isPresent()) {
            todoRepository.remove(todoIdToRemove);
        }
       return todo;
    }
}
