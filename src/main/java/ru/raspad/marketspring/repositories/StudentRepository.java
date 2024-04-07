package ru.raspad.marketspring.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.raspad.marketspring.dto.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository {
    private List<Student> students;

    @PostConstruct
    public void init(){
        students = new ArrayList<>(Arrays.asList(
                new Student(1L, "gashgash"),
                new Student(2L, "quincy"),
                new Student(3L, "cursed")
        ));
    }

    public Student findById(Long id){
        return students.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("student not found"));
    }

    public List<Student> getStudents() {
        return students;
    }
}
