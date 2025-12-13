package net.experiment.journalApp.Controller;

import net.experiment.journalApp.entity.JournalEntity;
import net.experiment.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    // GET ALL JOURNAL ENTRIES
    @GetMapping
    public List<JournalEntity> getAll() {
        return journalEntryService.getAll();
    }

    // GET JOURNAL BY ID
    @GetMapping("/id/{id}")
    public JournalEntity getById(@PathVariable ObjectId id) {
        return journalEntryService.findById(id).orElse(null);
    }

    // CREATE JOURNAL ENTRY
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity entity) {
        entity.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entity);
        return true;
    }

    // DELETE JOURNAL BY ID
    @DeleteMapping("/id/{id}")
    public boolean deleteById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return true;
    }

    // UPDATE JOURNAL ENTRY
    @PutMapping("/id/{id}")
    public JournalEntity updateEntry(@PathVariable ObjectId id,
                                     @RequestBody JournalEntity entity) {

        JournalEntity oldEntry = journalEntryService.findById(id).orElse(null);

        if (oldEntry != null) {
            if (entity.getTitle() != null && !entity.getTitle().isEmpty()) {
                oldEntry.setTitle(entity.getTitle());
            }
            journalEntryService.saveEntry(oldEntry);
        }

        return oldEntry;
    }
}
