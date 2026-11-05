package com.example.candidate.repository;

import com.example.candidate.entity.RegisteredCandidates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RegisteredCandidateRepositoryImpl implements RegisteredCandidateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RegisteredCandidates> getAllCandidates() {
        return entityManager.createNativeQuery("CALL trans_get_candidates()", RegisteredCandidates.class)
                .getResultList();
    }

    @Override
    public Optional<RegisteredCandidates> getCandidateById(Long registrationId) {
        RegisteredCandidates candidate = entityManager.find(RegisteredCandidates.class, registrationId);
        return Optional.ofNullable(candidate);
    }

    @Override
    @Transactional
    public RegisteredCandidates save(RegisteredCandidates candidate) {
        Long registrationId = candidate.getRegistrationId();

        if (registrationId == null) {
            // Insert Candidate
            entityManager.createNativeQuery(
                            "CALL trans_insert_candidates(:fullName, :email, :phone, :age, :dateOfBirth, :gender, :address, :passwordDetails, :counsellingTime, :resumePath, :emailNotification, :courseId, :skills)")
                    .setParameter("fullName", candidate.getFullName())
                    .setParameter("email", candidate.getEmail())
                    .setParameter("phone", candidate.getPhone())
                    .setParameter("age", candidate.getAge())
                    .setParameter("dateOfBirth", candidate.getDateOfBirth())
                    .setParameter("gender", candidate.getGender())
                    .setParameter("address", candidate.getAddress())
                    .setParameter("passwordDetails", candidate.getPasswordDetails())
                    .setParameter("counsellingTime", candidate.getCounsellingTime())
                    .setParameter("resumePath", candidate.getResumePath())
                    .setParameter("emailNotification", candidate.getEmailNotification())
                    .setParameter("courseId", candidate.getCourseId())
                    .setParameter("skills", candidate.getSkills())
                    .executeUpdate();

            Number lastId = (Number) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult();
            registrationId = lastId.longValue();
            candidate.setRegistrationId(registrationId);

        } else {
            // Update Candidate
            entityManager.createNativeQuery(
                            "CALL trans_update_candidate(:registrationId, :fullName, :email, :phone, :age, :dateOfBirth, :gender, :address, :passwordDetails, :counsellingTime, :resumePath, :emailNotification, :courseId, :skills)")
                    .setParameter("registrationId", registrationId)
                    .setParameter("fullName", candidate.getFullName())
                    .setParameter("email", candidate.getEmail())
                    .setParameter("phone", candidate.getPhone())
                    .setParameter("age", candidate.getAge())
                    .setParameter("dateOfBirth", candidate.getDateOfBirth())
                    .setParameter("gender", candidate.getGender())
                    .setParameter("address", candidate.getAddress())
                    .setParameter("passwordDetails", candidate.getPasswordDetails())
                    .setParameter("counsellingTime", candidate.getCounsellingTime())
                    .setParameter("resumePath", candidate.getResumePath())
                    .setParameter("emailNotification", candidate.getEmailNotification())
                    .setParameter("courseId", candidate.getCourseId())
                    .setParameter("skills", candidate.getSkills())
                    .executeUpdate();
        }

        return candidate;
    }

    @Override
    @Transactional
    public void deleteById(Long registrationId) {
        entityManager.createNativeQuery("CALL trans_delete_candidate(:registrationId)")
                .setParameter("registrationId", registrationId)
                .executeUpdate();
    }
}