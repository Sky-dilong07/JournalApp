package net.experiment.journalApp.Controller;

import net.experiment.journalApp.Entity.JournalEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JournalEntryController {

    public Map<Long, JournalEntity> journalEntries = new HashMap<>();


    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
}
