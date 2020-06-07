package io.edukativ.myskoolin.infrastructure.config.dbmigrations.dev.sixieme;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

@ChangeLog(order = "014")
public class ChangeSet014TimeTables {

    @ChangeSet(order = "01", author = "sch", id = "01-schoolclass")
    public void addSchoolRoom100() {
    }
}
