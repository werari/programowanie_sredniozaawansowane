package pl.sda.todo;

import pl.sda.todo.model.Todo;
import pl.sda.todo.model.TodoUser;
import pl.sda.todo.model.exception.InvalidPasswordException;
import pl.sda.todo.model.exception.TodoUserDoesNotExistsException;
import pl.sda.todo.repository.TodoRepository;
import pl.sda.todo.repository.TodoUserRepository;
import pl.sda.todo.repository.memory.InMemoryTodoRepository;
import pl.sda.todo.repository.memory.InMemoryTodoUserRepository;
import pl.sda.todo.service.TodoService;
import pl.sda.todo.views.TodoConsoleView;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class TodoApplication {

    private TodoService todoService;
    private TodoConsoleView todoConsoleView;
    private TodoUser currentUser;


    public static void main(String[] args) {
        TodoRepository todoRepository = new InMemoryTodoRepository();
        TodoUserRepository todoUserRepository = new InMemoryTodoUserRepository();
        TodoService todoService = new TodoService(todoRepository, todoUserRepository);

        Scanner scanner = new Scanner(System.in);
        TodoConsoleView todoConsoleView = new TodoConsoleView(scanner);

        TodoApplication todoApplication = new TodoApplication(todoService, todoConsoleView, null);
        todoApplication.start();
    }

    public void start() {
        Boolean flag = true;

        do {
            Integer menuOption = todoConsoleView.menu();
            switch (menuOption) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    addNewTodo();
                    break;
                case 4:
                    showTodoList();
                    break;
                case 0:
                default:
                    todoConsoleView.exit();
                    flag = false;
                    break;
            }
        } while (flag);
    }


    private void register() {

        String name = todoConsoleView.registerName();
        String password = todoConsoleView.registerPassword();
        TodoUser user = todoService.register(name, password);

        if (user == null) {
            todoConsoleView.displayError("Nie mozna zarejestrowac uzytkownika. \n" +
                    "Uzytkownik o podanej nazwie juz istnieje");
        } else {
            todoConsoleView.displaySuccesss("Udalo sie zarejestrowac nowego uzytkownaika");
        }

    }

    private void showTodoList() {
        Integer option = todoConsoleView.showTodoListWithOptions(todoService.findAllTodo());
        String possibleId= todoConsoleView.getPossibleId();
        //System.out.println("Wybrano opcje " + option);
        switch (option) {
            case 1:
                showTodoDescription(possibleId);
                break;
            case 2:
                removeTodo(possibleId);
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    private void removeTodo(String possibleId) {
        Integer todoId;
        todoId = extractTodoId(possibleId);
        Optional<Todo> removedTodo= todoService.removeTodo(todoId);
        todoConsoleView.displayTodoRemove(removedTodo);
    }

    private Integer extractTodoId(String possibleId) {
        Integer todoId;
        if(possibleId.length()==0){
           todoId= todoConsoleView.getTodoId()-1;//ktos wpisał dwa po sobie
        } else{
            todoId= Integer.valueOf(possibleId)-1;//ktos wpisał dwa ze spacja
        }
        return todoId;
    }

    private void showTodoDescription(String possibleId) {
        Integer todoId = extractTodoId(possibleId);
        Optional<Todo> todo = todoService.findTodoById(todoId);
        todoConsoleView.showTodoListWithDetails(todo);
    }

    private void login() {
        this.currentUser = null;
        String name = todoConsoleView.logInName();
        String password = todoConsoleView.logInPassword();
        try {
            this.currentUser = todoService.login(name, password);
        } catch (TodoUserDoesNotExistsException | InvalidPasswordException e) {
            todoConsoleView.displayError(e.getMessage());
        }
        if (this.currentUser != null) {
            todoConsoleView.displaySuccesss("Uzytkownik o nicku \"" + name + "\" zostal zalogowany");
        }
    }

    private void addNewTodo() {
        if (currentUser == null) {
            login();
        }
        if (currentUser != null) {

            String todoName = todoConsoleView.createNewTodoName();
            String todoDescription = todoConsoleView.createNewTodoDescription();
            Todo todo = new Todo(todoName, this.currentUser);
            todo.setDescription(todoDescription);

            todoService.save(todo);
        }
    }
}
