package pl.sda.todo.views;

import pl.sda.todo.model.Todo;
import pl.sda.todo.model.TodoStatus;
import pl.sda.todo.model.TodoUser;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TodoConsoleView {
    private Scanner scanner;

    public TodoConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Todo application");
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Dodaj zadanie");
        System.out.println("4. Wyswietl zadania");
        System.out.println("0. Koniec");

        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String logInName() {
        System.out.println("Podaj nick");
        return scanner.nextLine();
    }

    public String logInPassword() {
        System.out.println("Podaj haslo");
        return scanner.nextLine();
    }

    public String registerName() {
        return logInName();
    }

    public String registerPassword() {
        return logInPassword();
    }

    public String createNewTodoName() {
        System.out.println("Podaj nazwe zadania");
        return scanner.nextLine();
    }

    public String createNewTodoDescription() {
        System.out.println("Podaj opis zadania");
        return scanner.nextLine();
    }

    public void displayError(String message) {
        System.out.println();
        System.out.println("Error");
        System.out.println(message);
        System.out.println("Error");
        System.out.println();
    }

    public void displaySuccesss(String message) {
        System.out.println();
        System.out.println("Success");
        System.out.println(message);
        System.out.println("Success");
        System.out.println();
    }

    public void exit() {
        System.out.println("Do widzenia");
    }

    public Integer showTodoListWithOptions(List<Todo> allTodo) {
        System.out.println("Lista zadan");
        System.out.println("----------------------");
        for (int i = 0; i < allTodo.size(); i++) {
            Todo todo = allTodo.get(i);//TODO wyciaganie danych z listy
            TodoUser creator = todo.getCreator();
            TodoStatus status = todo.getTodoStatus();
            Optional<TodoUser> owner = Optional.ofNullable(todo.getOwner());
            System.out.println((i + 1) +
                    ". |\"" + todo.getName() +
                    "\"|\"" + creator.getName() +
                    "\"|\"" + owner.orElse(TodoUser.unasigned()).getName() +
                    "\"| " + status.toString());
        }
        System.out.println("----------------------");
        System.out.println("1. Wyswietl");
        System.out.println("2. Usun");
        System.out.println("3. Przypisz");
        System.out.println("4. Zmien status");
        System.out.println("0. Wyjdz");

        Integer option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public Integer getTodoId() {
        System.out.println("Podaj numer zadania");
        int todoId = scanner.nextInt();
        scanner.nextLine();
        return todoId;
    }

    public void showTodoListWithDetails(Optional<Todo> todo) {
        String message = todo.map(e -> {
            TodoUser creator = e.getCreator();
            Optional<TodoUser> owner = Optional.ofNullable(e.getOwner());
            return e.getName() + " (" + e.getTodoStatus().toString() + ") (" + e.getCreationDate().toString() + ")\n" +
                    e.getDescription() + "\n" + "Tworca: " + creator.getName() + "\n" +
                    "Przypisane: " + owner.orElse(TodoUser.unasigned()).getName();
        }).orElse("zadanie nie istnieje");

        System.out.println(message);
    }
}
//