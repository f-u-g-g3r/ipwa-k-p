package com.ipwa.kp.repositories;

import com.ipwa.kp.models.Post;
import com.ipwa.kp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByUsername(String username);

    Optional<List<Student>> findAllByPostsId(Long postId);
}
