package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository  timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreat) {
       TimeEntry result = timeEntryRepository.create(timeEntryToCreat);
       return new ResponseEntity<TimeEntry>(result, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry result = timeEntryRepository.find(timeEntryId);
        if(result != null){
            return new ResponseEntity<TimeEntry>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<TimeEntry>(result, HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> result = (List<TimeEntry>) timeEntryRepository.list();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry toSave) {
        TimeEntry updated = timeEntryRepository.update(timeEntryId, toSave);
        if(updated != null){
            return new ResponseEntity<TimeEntry>(updated, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<TimeEntry>(updated, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {
        TimeEntry deleted = timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(deleted, HttpStatus.NO_CONTENT);
    }
}
