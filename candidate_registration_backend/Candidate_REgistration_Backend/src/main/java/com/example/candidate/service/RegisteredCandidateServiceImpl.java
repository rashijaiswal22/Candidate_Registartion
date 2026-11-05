package com.example.candidate.service;

import com.example.candidate.entity.RegisteredCandidates;
import com.example.candidate.repository.RegisteredCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredCandidateServiceImpl implements RegisteredCandidateService {

    @Autowired
    private RegisteredCandidateRepository candidateRepository;

    @Override
    public List<RegisteredCandidates> getAllCandidates(){
        return candidateRepository.getAllCandidates();
    }

    @Override
    public Optional<RegisteredCandidates> getCandidateById(Long registrationId){
        return candidateRepository.getCandidateById(registrationId);
    }

    @Override
    public RegisteredCandidates saveCandidate(RegisteredCandidates candidate){
        return candidateRepository.save(candidate);
    }

    @Override
    public void deleteCandidate(Long id){
        candidateRepository.deleteById(id);
    }
}