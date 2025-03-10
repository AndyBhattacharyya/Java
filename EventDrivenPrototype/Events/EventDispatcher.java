package EventDrivenPrototype.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcher {
    public static Map<Class<? extends Event>, List<Consumer<Event>>> handlers = new HashMap<>();

    public static <E extends Event> void registerHandler(Class<E> eventType, Consumer<E> handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    public static void dispatch(Event event) {
        List<Consumer<Event>> eventHandlers = handlers.get(event.getClass());
        if (eventHandlers != null) {
            eventHandlers.forEach(handler -> handler.accept(event));
        }
    }
}
