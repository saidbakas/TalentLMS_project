package com.peaksoft.controller;

import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import com.peaksoft.service.GroupService;
import com.peaksoft.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class     StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping
    public String getAllStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "student/students";
    }
    @GetMapping("/addStudent")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        return "student/addStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/update")
    public String updateGroup(Model model, @PathVariable("id")long id){
        model.addAttribute("updateStudent",studentService.getStudentById(id));
        return "student/updateStudent";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("studentUpdate") Student student,
                         @PathVariable("id") long id) {
        studentService.updateStudent(student,id);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        Student student = studentService.getStudentById(id);
        studentService.deleteStudent(student);
        return "redirect:/students";
    }

}
