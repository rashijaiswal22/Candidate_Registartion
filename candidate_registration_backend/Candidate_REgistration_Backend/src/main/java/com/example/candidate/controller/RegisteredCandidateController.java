package com.example.candidate.controller;

import com.example.candidate.entity.RegisteredCandidates;
import com.example.candidate.service.RegisteredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "http://localhost:3000") // React ke liye CORS enable kiya
public class RegisteredCandidateController {

    @Autowired
    private RegisteredCandidateService candidateService;

    // Get all candidates
    @GetMapping
    public List<RegisteredCandidates> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // Get candidate by ID
    @GetMapping("/{id}")
    public ResponseEntity<RegisteredCandidates> getCandidateById(@PathVariable Long id) {
        Optional<RegisteredCandidates> candidate = candidateService.getCandidateById(id);
        return candidate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create candidate
    @PostMapping
    public RegisteredCandidates createCandidate(@RequestBody RegisteredCandidates candidate) {
        return candidateService.saveCandidate(candidate);
    }

    // Update candidate
    @PutMapping("/{id}")
    public ResponseEntity<RegisteredCandidates> updateCandidate(@PathVariable Long id, @RequestBody RegisteredCandidates candidateDetails) {
        Optional<RegisteredCandidates> optionalCandidate = candidateService.getCandidateById(id);
        if (optionalCandidate.isPresent()) {
            candidateDetails.setRegistrationId(id);
            RegisteredCandidates updatedCandidate = candidateService.saveCandidate(candidateDetails);
            return ResponseEntity.ok(updatedCandidate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok().build();
    }
}