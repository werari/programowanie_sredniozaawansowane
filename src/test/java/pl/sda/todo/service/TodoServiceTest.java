package pl.sda.todo.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.sda.todo.model.Todo;
import pl.sda.todo.model.TodoUser;
import pl.sda.todo.repository.TodoRepository;
import java.util.Arrays;
import java.util.List;

public class TodoServiceTest {
    @Test
    public void findTodosByCreatorNameShouldReturnTodosForeExistingCreatorName() {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        Mockito.when(todoRepository.findAll()).thenReturn(createSampleTodoList());
        TodoService todoService = new TodoService(todoRepository, null);
        //when
        List<Todo> todos = todoService.findTodosByCreatorName("admin");
        //then
        Assert.assertEquals(2, todos.size());
        todos.forEach(todo -> {
            Assert.assertEquals("admin", todo.getCreator().getName());
        });
    }

    @Test
    public void findTodosByCreatorNameShouldReturnEmptyListForNonExistingCreatorName() {
        //given
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        Mockito.when(todoRepository.findAll()).thenReturn(createSampleTodoList());
        TodoService todoService = new TodoService(todoRepository, null);
        //when
        List<Todo> todos = todoService.findTodosByCreatorName("noOne");
        //then
        Assert.assertEquals(0, todos.size());

    }

    private List<Todo> createSampleTodoList() {
        TodoUser todoUser = new TodoUser("admin", "1234");
        TodoUser todoUser2 = new TodoUser("admin2", "1234");
        TodoUser todoUser3 = new TodoUser("admin3", "1234");
        Todo todo1 = new Todo("Zrobic zakupy", todoUser);
        Todo todo2 = new Todo("Zrobic pranie", todoUser);
        Todo todo3 = new Todo("Kupic mleko", todoUser2);
        Todo todo4 = new Todo("Kupic kubek", todoUser3);

        return Arrays.asList(todo1, todo2, todo3, todo4);
    }
}
