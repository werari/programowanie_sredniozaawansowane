package pl.sda.todo.repository;

import pl.sda.todo.model.TodoUser;

public interface TodoUserRepository {
    boolean save(TodoUser user);

    TodoUser findByName(String name);

    boolean exists(String name);
}
