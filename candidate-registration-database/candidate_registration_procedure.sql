DELIMITER //
CREATE PROCEDURE master_get_courses()
BEGIN
SELECT CourseId AS courseId , CourseName AS courseName FROM master_courses;
END //


CREATE PROCEDURE master_insert_courses( IN P_CourseName VARCHAR(30))
BEGIN 
INSERT INTO master_courses(CourseName) VALUES (P_CourseName);
END //

-----------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE master_get_skills()
BEGIN
SELECT SkillId AS skillId, SkillName AS skillName FROM master_skills;
END //


CREATE PROCEDURE master_insert_skills(IN P_SkillName VARCHAR(30))
BEGIN
INSERT INTO master_skills(SkillName) VALUES (P_SkillName);
END //


------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE trans_get_candidates()
BEGIN
SELECT c.RegistrationId AS registrationId, 
        c.FullName AS fullName, 
        c.Email AS email, 
        c.Phone AS phone, 
        c.Age AS age, 
        c.DateOfBirth AS dateOfBirth, 
        c.Gender AS gender, 
        c.Address AS address, 
        c.PasswordDetails AS passwordDetails,
        c.CounsellingTime AS counsellingTime, 
        c.ResumePath AS resumePath, 
        c.EmailNotification AS emailNotification, 
        c.CourseId AS courseId, 
        m.CourseName AS courseName,
        c.Skills AS skills
    FROM trans_candidates_registration c
    LEFT JOIN master_courses m ON c.CourseId = m.CourseId;
END //


CREATE PROCEDURE trans_insert_candidates(
IN P_FullName VARCHAR(30), IN P_Email VARCHAR(50), IN P_Phone BIGINT, IN P_Age INT, IN P_DateOfBirth DATE, IN P_Gender VARCHAR(10),
IN P_Address VARCHAR(100), IN P_PasswordDetails VARCHAR(20), IN P_CounsellingTime DATETIME, IN P_ResumePath VARCHAR(200), IN P_EmailNotification BOOLEAN,
IN P_CourseId INT, IN P_SKILLS VARCHAR(255)
)
BEGIN
DECLARE v_CourseName VARCHAR(30);

SELECT CourseName INTO v_CourseName FROM master_courses WHERE CourseId = P_CourseId;

INSERT INTO trans_candidates_registration( FUllName, Email, Phone, Age, DateOfBirth, Gender, Address, PasswordDetails,
CounsellingTime, ResumePath, EmailNotification, CourseId , CourseName, Skills) VALUES
(P_FullName, P_Email, P_Phone, P_Age, P_DateOfBirth, P_Gender, P_Address, P_PasswordDetails, P_CounsellingTime,
P_ResumePath, P_EmailNotification, P_CourseId, v_CourseName, P_Skills);
END //
------------------------------------------------------------------------------------------
CREATE PROCEDURE trans_update_candidate(
IN P_RegistrationId INT, IN P_FullName VARCHAR(30), IN P_Email VARCHAR(50), IN P_Phone BIGINT, IN P_Age INT, IN P_DateOfBirth DATE, IN P_Gender VARCHAR(10),
IN P_Address VARCHAR(100), IN P_PasswordDetails VARCHAR(20), IN P_CounsellingTime DATETIME, IN P_ResumePath VARCHAR(200),
IN P_EmailNotification BOOLEAN, IN P_CourseId INT, IN P_Skills VARCHAR(255)
)
BEGIN
DECLARE v_CourseName VARCHAR(30);
SELECT CourseName INTO v_CourseName FROM master_courses WHERE CourseId = P_CourseId;

UPDATE trans_candidates_registration
SET
FullName=P_FullName, Email=P_Email, Phone=P_Phone, Age=P_Age, DateOfBirth=P_DateOfBirth, Gender=P_Gender, Address=P_Address, PasswordDetails=P_PasswordDetails,
CounsellingTime=P_CounsellingTime, ResumePath=P_ResumePath, EmailNotification=P_EmailNotification , CourseId=P_CourseId, CourseName=v_CourseName, Skills=P_Skills
WHERE RegistrationId=P_RegistrationId;
END //

-----------------------------------------------------------------------------------------------------
CREATE PROCEDURE trans_delete_candidate(IN P_RegistrationId INT)
BEGIN
DELETE FROM trans_candidates_registration WHERE RegistrationId = P_RegistrationId;
END //


DELIMITER ;

DROP PROCEDURE IF EXISTS master_get_courses;
DROP PROCEDURE IF EXISTS master_insert_courses;
DROP PROCEDURE IF EXISTS master_insert_skills;
DROP PROCEDURE IF EXISTS master_get_skills;
DROP PROCEDURE IF EXISTS trans_insert_candidates;
DROP PROCEDURE IF EXISTS trans_get_candidates;
DROP PROCEDURE IF EXISTS trans_candidates_registration;
DROP PROCEDURE IF EXISTS trans_delete_candidate;
DROP PROCEDURE IF EXISTS trans_update_candidate;

SHOW PROCEDURE STATUS WHERE Db = '';
