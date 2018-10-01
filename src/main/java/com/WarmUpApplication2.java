package com;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WarmUpApplication2 {
    public static void main(String[] args) {
        List<Student> studentsList = createStudentsList();
        //wypisz tylko kobiety z age powyzej 20

        studentsList.stream()
                .filter(e -> e.getGender() == (Gender.FEMALE))
                .filter(e -> e.getAge() > 20)
                .forEach(e -> System.out.println(e));

        //wypisz tylko studentów o nazwisku Nowak

        studentsList.stream()
                // .filter(e-> e.getLastName().equals( "Nowak") // lepiej uzyc tego pponiej, bo moze byc name nullem
                .filter(e -> "Nowak".equals(e.getLastName()))
                .forEach(e -> System.out.println(e));

        //wypisz ilośc studentów mających powyzej 20 lat

        System.out.println(studentsList.stream()
                .filter(e -> e.getAge() > 20)
                .count());

        //wypisz sredni wiek
        System.out.println(studentsList.stream()
                .mapToInt(e -> e.getAge())
                .average()
                .getAsDouble());

        //wypisz dla każdego studenta srednia ocen

        studentsList.stream()
                .forEach(student -> {
                    Map<Subject, Double> grades = student.getGrades();
                    System.out.println("Student " + student.getFirstName() + " " + student.getLastName());
                    System.out.println(grades.entrySet().stream()
                            .mapToDouble(entry -> entry.getValue())
                            .average()
                            .getAsDouble());
                    System.out.println();
                });

        //wypisz tylko studentów którzy maja zaliczone wszystkie przedmioty

        studentsList.stream()
                .filter(student -> student.getGrades().entrySet().stream()
                        .map(grade -> grade.getValue())
                        .allMatch(grade -> grade >= 3.0)
                ).forEach(student -> System.out.println(student));
    }

    private static List<Student> createStudentsList() {
        Student student = Student.builder()
                .firstName("Julia")
                .lastName("Nowak")
                .gender(Gender.FEMALE)
                .age(18)
                .build()
                .addGrade(Subject.MATH, 3.5)
                .addGrade(Subject.PROGRAMMING, 4.0);
        Student student2 = Student.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .gender(Gender.MALE)
                .age(23)
                .build()
                .addGrade(Subject.MATH, 4.5)
                .addGrade(Subject.PROGRAMMING, 3.0);
        Student student3 = Student.builder()
                .firstName("Jacek")
                .lastName("Iksinski")
                .gender(Gender.MALE)
                .age(25)
                .build()
                .addGrade(Subject.MATH, 3.5)
                .addGrade(Subject.PROGRAMMING, 3.5);
        Student student4 = Student.builder()
                .firstName("Agatka")
                .lastName("Czarna")
                .gender(Gender.FEMALE)
                .age(24)
                .build()
                .addGrade(Subject.MATH, 4.5)
                .addGrade(Subject.PROGRAMMING, 4.5);

        return Arrays.asList(student, student2, student3, student4);
    }
}
