package pl.sda.todo.repository.memory;

import pl.sda.todo.model.Todo;
import pl.sda.todo.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InMemoryTodoRepository implements TodoRepository {

    private List<Todo> todos;

    public InMemoryTodoRepository() {
        this.todos = new ArrayList<>();
    }

    @Override
    public void save(Todo todo) {
        todos.add(todo);
    }

    @Override
    public Optional<Todo> findById(String id) {
        return todos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Todo> findById(Integer id) {
//        if (id>=0 && id<todos.size()){
//            return Optional.of(todos.get(id));
//        }
//        return Optional.empty();
        //TODO warunek trójargumentowy
        return (id>=0 && id<todos.size()) ?
                Optional.of(todos.get(id)) :
                Optional.empty();
    }

    @Override
    public List<Todo> findAll() {

        return new ArrayList<>(todos); //TODO kopiowanie listy!!
    }

    @Override
    public void remove(int todoIdToRemove) {
        todos.remove(todoIdToRemove);
    }
}
