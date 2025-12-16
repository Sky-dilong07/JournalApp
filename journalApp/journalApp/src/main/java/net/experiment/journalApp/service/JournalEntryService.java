package net.experiment.journalApp.service;

import net.experiment.journalApp.entity.JournalEntity;
import net.experiment.journalApp.entity.User;
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

    @Autowired
    private UserService userService;

    public JournalEntity saveEntry(JournalEntity journalEntity, String username) {
        User user = userService.findByUsername(username);
        JournalEntity saved = journalEntryRepo.save(journalEntity);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
        return saved;
    }

    public List<JournalEntity> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(j -> j.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepo.deleteById(id);
    }
}
