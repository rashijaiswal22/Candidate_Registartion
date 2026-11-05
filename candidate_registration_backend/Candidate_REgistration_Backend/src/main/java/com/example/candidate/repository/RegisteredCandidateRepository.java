package com.example.candidate.repository;

import com.example.candidate.entity.RegisteredCandidates;

import java.util.List;
import java.util.Optional;

public interface RegisteredCandidateRepository {

    List<RegisteredCandidates> getAllCandidates();

    Optional<RegisteredCandidates> getCandidateById(Long registrationId);

    RegisteredCandidates save(RegisteredCandidates candidate);

    void deleteById(Long registrationId);
}