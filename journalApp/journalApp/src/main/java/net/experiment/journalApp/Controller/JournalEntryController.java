package net.experiment.journalApp.Controller;

import net.experiment.journalApp.entity.JournalEntity;
import net.experiment.journalApp.service.JournalEntryService;
import net.experiment.journalApp.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    // GET ALL JOURNAL ENTRIES
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<JournalEntity> all = user.getJournalEntity();
        if(all != null  && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK)
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET JOURNAL BY ID
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String myId) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // CREATE JOURNAL ENTRY
    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntry) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE JOURNAL BY ID
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {

    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry) {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
