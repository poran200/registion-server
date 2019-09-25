package com.aj.grade.gradeentries.services;

import com.aj.grade.gradeentries.model.StudentInfo;
import com.aj.grade.gradeentries.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentinfoService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentInfo> studentsinfo() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<StudentInfo>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/api/v1/students", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentInfo>>() {
                });
        List<StudentInfo> studentInfos = responseEntity.getBody();
        studentInfos.stream().forEach(student -> studentRepository.save(new StudentInfo(student.getId(), student.getName())));
        return studentInfos;
    }
}
