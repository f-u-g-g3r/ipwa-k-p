package com.ipwa.kp.controllers;


import com.ipwa.kp.controllers.exceptions.ClassGroupNotFoundException;
import com.ipwa.kp.controllers.exceptions.TeacherNotFoundException;
import com.ipwa.kp.controllers.requests.TeacherPatchRequest;
import com.ipwa.kp.models.ClassGroup;
import com.ipwa.kp.models.Teacher;
import com.ipwa.kp.repositories.ClassGroupRepository;
import com.ipwa.kp.repositories.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherRepository repository;
    private final ClassGroupRepository classGroupRepository;

    public TeacherController(TeacherRepository repository, ClassGroupRepository classGroupRepository) {
        this.repository = repository;
        this.classGroupRepository = classGroupRepository;
    }

    @GetMapping
    public Page<Teacher> all(@RequestParam Optional<String> sortBy,
                             @RequestParam Optional<Integer> page,
                             @RequestParam Optional<String> direction) {
        Sort.Direction sort = Sort.Direction.ASC;
        if (direction.isPresent() && direction.get().equals("DESC")) {
            sort = Sort.Direction.DESC;
        }
        return repository.findAll(PageRequest.of(
                page.orElse(0),
                5,
                sort, sortBy.orElse("id")
        ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('COORDINATOR', 'STUDENT') or #id == authentication.principal.id")
    public Teacher one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('COORDINATOR') or #id == authentication.principal.id and hasAuthority('TEACHER')")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody TeacherPatchRequest request) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));

        if (request.getEmail() != null) teacher.setEmail(request.getEmail());
        if (request.getUsername() != null) teacher.setUsername(request.getUsername());
        if (request.getFirstName() != null) teacher.setFirstName(request.getFirstName());
        if (request.getLastName() != null) teacher.setLastName(request.getLastName());

        return ResponseEntity.ok(repository.save(teacher));
    }

//    @PostMapping
//    public ResponseEntity<?> newTeacher(@RequestBody Teacher teacher) {
//        return ResponseEntity.ok(repository.save(teacher));
//    }

    @PatchMapping("/{groupId}/{teacherId}")
    @PreAuthorize("hasAuthority('COORDINATOR')")
    public ResponseEntity<?> addTeacherToGroup(@PathVariable Long groupId, @PathVariable Long teacherId) {
        Teacher teacher = repository.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException(teacherId));
        ClassGroup group = classGroupRepository.findById(groupId)
                .orElseThrow(() -> new ClassGroupNotFoundException(groupId));

        group.setTeacher(teacher);
        classGroupRepository.save(group);

        teacher.setClassGroup(group);

        return ResponseEntity.ok(repository.save(teacher));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('COORDINATOR')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("ok");
    }
}
