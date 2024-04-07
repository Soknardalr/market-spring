package ru.raspad.marketspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raspad.marketspring.dto.Student;
import ru.raspad.marketspring.repositories.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;

    public Student getStudent(Long id){
        return repository.findById(id);
    }

    public List<Student> getStudents(){
        return repository.getStudents();
    }
}
