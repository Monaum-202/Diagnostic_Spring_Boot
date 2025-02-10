package com.example.diagnostic_test.service;

import com.example.diagnostic_test.entity.doctor.DoctorNote;
import com.example.diagnostic_test.repository.DoctorNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorNoteService {

    @Autowired
    private DoctorNoteRepository doctorNoteRepository;

    // Get all notes
    public List<DoctorNote> getAllNotes() {
        return doctorNoteRepository.findAll();
    }

    // Get note by ID
    public Optional<DoctorNote> getNoteById(Long id) {
        return doctorNoteRepository.findById(id);
    }

    // Create new note
    public DoctorNote createNote(DoctorNote doctorNote) {
        return doctorNoteRepository.save(doctorNote);
    }

    // Update note
    public DoctorNote updateNote(Long id, DoctorNote newNoteData) {
        return doctorNoteRepository.findById(id).map(note -> {
            note.setMessage(newNoteData.getMessage());
            return doctorNoteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("DoctorNote not found"));
    }

    // Delete note
    public void deleteNoteById(Long id) {
        doctorNoteRepository.deleteById(id);
    }
}