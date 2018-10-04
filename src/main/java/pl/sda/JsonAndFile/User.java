package pl.sda.JsonAndFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private int age;
//TODO mapowanie jsona- uzycie @json creator oraz @json property

    @JsonCreator
    public User(@JsonProperty ("firstName") String firstName,
                @JsonProperty (value = "lastName", defaultValue = "brak") String lastName,
                @JsonProperty ("age") int age,
                @JsonProperty ("phoneNumbers") List<PhoneNumber> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumbers = phoneNumbers;
    }

    private List<PhoneNumber> phoneNumbers;

    public static class PhoneNumber{
        private String number;
        private String name;

        @JsonCreator
        public PhoneNumber(@JsonProperty ("number") String number, @JsonProperty ("name") String name) {
            this.number = number;
            this.name = name;
        }
    }
}
