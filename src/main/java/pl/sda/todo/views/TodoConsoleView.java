package pl.sda.todo.views;

import org.assertj.core.internal.bytebuddy.description.field.FieldDescription;
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
        //scanner.nextLine();
        return option;
    }

    public Integer getTodoId() {

        System.out.println("Podaj numer zadania");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private Integer getIdFromMessage(String message) {
        return Integer.valueOf(message.substring(1));
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


    public String getPossibleId() {
        String possibleId = scanner.nextLine();
        return possibleId.length() > 0 ?
                possibleId.substring(1) :
                possibleId;
    }

    public void displayTodoRemove(Optional<Todo> removedTodo) {
        System.out.println(removedTodo.map(e -> "Usunięto zadanie " + e.getName())
                .orElse("Zadanie nie istenieje"));
    }

    public void displayAssigment(Optional<Todo> todo, TodoUser currentUser) {
        System.out.println(todo.map(e -> "Przypisano " + currentUser.getName() + "do zadania \"" + e.getName() + "\"")
                .orElse("Zadanie nie isteniej"));
    }

    public void displayChangeStatus(Optional<Todo> todoById) {
        System.out.println(todoById.map(e -> "Zmieniono status zadania " + e.getName() + " na " + e.getTodoStatus())
                .orElse("Zadanie nie istnieje"));
    }

    public TodoStatus getStatus() {
        System.out.println("Podaj Status (NEW, ACTIVE, CLOSED");
        String status = scanner.nextLine();
        return TodoStatus.valueOf(status);
    }
}
