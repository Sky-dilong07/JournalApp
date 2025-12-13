package net.experiment.journalApp.service;

import net.experiment.journalApp.entity.JournalEntity;
import net.experiment.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepo.save(journalEntity);
    }

    public List<JournalEntity> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepo.deleteById(id);
    }
}
