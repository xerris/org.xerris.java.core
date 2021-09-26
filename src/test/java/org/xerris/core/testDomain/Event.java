package org.xerris.core.testDomain;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime when;

    public Event() { }

    public Event(String name, LocalDateTime when) {
        this.name = name;
        this.when = when;
    }

    public String getName() { return this.name; }
    public LocalDateTime getWhen() { return this.when; }
}
