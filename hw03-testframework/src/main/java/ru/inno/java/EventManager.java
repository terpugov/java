package ru.inno.java;

import ru.inno.java.annatations.AfterEach;
import ru.inno.java.annatations.Test;
import ru.inno.java.core.TestSuitImpl;
import ru.inno.java.listeners.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class EventManager {
    private Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {

        listeners.put(AfterEach.name, new ArrayList<>());
        listeners.put(Test.name, new ArrayList<>());
        listeners.put("Report", new ArrayList<>());
    }

    public void subscribe(String eventType, EventListener eventListener){
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.add(eventListener);
    }

    public void notify(String eventType, TestSuitImpl testDecriptor) {
        List<EventListener> users = listeners.get(eventType);
        if (users != null) {
            for (EventListener eventListener : users) {
                eventListener.onFinish(testDecriptor);
            }
        }
    }
}
