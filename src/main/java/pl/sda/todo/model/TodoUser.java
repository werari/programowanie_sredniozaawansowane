package pl.sda.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TodoUser {
    public static TodoUser unasigned(){
        return new TodoUser("Nie przypisano", null);
    }
    private String name;
    private String password;
}