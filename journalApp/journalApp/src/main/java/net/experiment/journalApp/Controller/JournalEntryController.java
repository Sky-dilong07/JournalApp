package net.experiment.journalApp.Controller;

import net.experiment.journalApp.Entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public Map<Long, JournalEntity> journalEntries = new HashMap<>();


    @GetMapping
    public List<JournalEntity> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("id/{myId}")
    public JournalEntity getEntityById(@PathVariable Long myId){
       return journalEntries.get(myId);
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntity entity){
        journalEntries.put(entity.getId(), entity);
        return true;
    }

    @DeleteMapping
    public JournalEntity deleteEntityById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }

    @PutMapping("id/{Id}")
    public JournalEntity putEntity(@PathVariable Long Id, @RequestBody JournalEntity entity){
        return journalEntries.put(Id,entity);
    }



}
