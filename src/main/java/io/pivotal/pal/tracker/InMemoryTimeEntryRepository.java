package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    HashMap<Long, TimeEntry> timeEntries = new HashMap<>();
    static long idGenerator;

    public InMemoryTimeEntryRepository(){
         idGenerator = 0;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        idGenerator = idGenerator +1;
        timeEntry.setId(idGenerator);
        timeEntries.put(idGenerator, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> valueList = new ArrayList<TimeEntry>(timeEntries.values());
        return valueList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry time) {
        time.setId(id);
        if(timeEntries.keySet().contains(id) ){
            timeEntries.put(id, time);
            return timeEntries.get(id);
        }
        else{
            return null;
        }
    }

    @Override
    public TimeEntry delete(long timeEntryId) {
        TimeEntry deleted = timeEntries.get(timeEntryId);
        timeEntries.remove(timeEntryId);
        return deleted;
    }
}
