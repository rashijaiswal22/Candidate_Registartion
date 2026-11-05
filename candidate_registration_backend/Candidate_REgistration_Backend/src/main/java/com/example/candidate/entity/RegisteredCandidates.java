package com.example.candidate.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="trans_candidates_registration")
public class RegisteredCandidates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RegistrationId")
    private Long registrationId;

    @Column(name="FullName", nullable=false)
    private String fullName;

    @Column(name="Email", unique=true, nullable=false)
    private String email;

    @Column(name="Phone", unique=true, nullable=false)
    private Long phone;

    @Column(name="Age")
    private Integer age;

    @Column(name="DateOfBirth", nullable=false)
    private LocalDate dateOfBirth;

    @Column(name="Gender")
    private String gender;

    @Column(name="Address")
    private String address;

    @Column(name="PasswordDetails", nullable=false)
    private String passwordDetails;

    @Column(name="CourseId")
    private Integer courseId;

    @Column(name="CourseName")
    private String courseName;

    @Column(name="Skills") // <-- Ab direct database column se map hoga!
    private String skills;

    @Column(name="CounsellingTime")
    private LocalDateTime counsellingTime;

    @Column(name="ResumePath")
    private String resumePath;

    @Column(name="EmailNotification")
    private Boolean emailNotification = true;

    // Getters and Setters
    public Long getRegistrationId() { return registrationId; }
    public void setRegistrationId(Long registrationId) { this.registrationId = registrationId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getPhone() { return phone; }
    public void setPhone(Long phone) { this.phone = phone; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPasswordDetails() { return passwordDetails; }
    public void setPasswordDetails(String passwordDetails) { this.passwordDetails = passwordDetails; }

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public LocalDateTime getCounsellingTime() { return counsellingTime; }
    public void setCounsellingTime(LocalDateTime counsellingTime) { this.counsellingTime = counsellingTime; }

    public String getResumePath() { return resumePath; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }

    public Boolean getEmailNotification() { return emailNotification; }
    public void setEmailNotification(Boolean emailNotification) { this.emailNotification = emailNotification; }
}