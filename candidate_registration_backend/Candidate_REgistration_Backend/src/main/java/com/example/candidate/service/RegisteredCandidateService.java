package com.example.candidate.service;

import com.example.candidate.entity.RegisteredCandidates;

import java.util.List;
import java.util.Optional;


public interface RegisteredCandidateService {

    List<RegisteredCandidates> getAllCandidates();
    Optional<RegisteredCandidates> getCandidateById(Long RegistrationId);
    RegisteredCandidates saveCandidate(RegisteredCandidates registeredCandidates);
    void deleteCandidate(Long RegistrationId);

}
