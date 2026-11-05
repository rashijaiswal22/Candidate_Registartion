CREATE DATABASE candidate_registration_db
USE candidate_registration_db;

CREATE TABLE master_courses(
CourseId INT AUTO_INCREMENT PRIMARY KEY,
CourseName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE master_skills(
SkillId INT AUTO_INCREMENT PRIMARY KEY,
SkillName VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE trans_candidates_registration(
RegistrationId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
FullName VARCHAR(30) NOT NULL,
Email VARCHAR(50) UNIQUE NOT NULL,
Phone BIGINT UNIQUE NOT NULL,
Age INT,
DateOfBirth DATE NOT NULL,
Gender VARCHAR(10),
Address VARCHAR(100),
PasswordDetails VARCHAR(20) NOT NULL,
CourseId INT,
CourseName VARCHAR(30),
CounsellingTime DATETIME ,
ResumePath VARCHAR(200),
EmailNotification BOOLEAN DEFAULT TRUE,
Skills VARCHAR(255),

FOREIGN KEY(CourseId) REFERENCES master_courses(CourseId)
);



INSERT INTO master_courses (CourseId, CourseName) 
VALUES (1, 'Java Development'), (2, 'Python Development'), (3, 'React.Js'), (4, 'Data Science')
ON DUPLICATE KEY UPDATE CourseName=CourseName;

INSERT INTO master_skills (SkillId, SkillName) 
VALUES (1, 'Java'), (2, 'Python'), (3, 'React.Js'), (4, 'SQL')
ON DUPLICATE KEY UPDATE SkillName=SkillName;




select * from master_courses;
select * from master_skills;

SELECT * FROM trans_candidates_registration;

show tables;

DROP TABLE master_skills;
DROP TABLE master_courses;
DROP TABLE trans_candidates_registration;
DROP TABLE trans_candidate_skills;






SET SQL_SAFE_UPDATES = 0;






