package EventDrivenPrototype;

/*
set up
public class EventDispatcher {


  private Map<Class<? extends Event>, List<Consumer<Event>>> handlers = new HashMap<>();

  ? interesting type inferencing going on regarding the generic type of ArrayList<>
  public <E extends Event> void registerHandler(Class<E> eventType, Consumer<E> handler) {
    handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler::accept);
  }

  public void dispatch(Event event) {
    List<Consumer<Event>> eventHandlers = handlers.get(event.getClass());
    if (eventHandlers != null) {
      eventHandlers.forEach(handler -> handler.accept(event));
    }
  }
}

// Create an EventDispatcher
EventDispatcher dispatcher = new EventDispatcher();

// Register handlers for UserCreatedEvent and UserUpdatedEvent
dispatcher.registerHandler(UserCreatedEvent.class, new UserCreatedEventHandler()::onUserCreated);
dispatcher.registerHandler(UserUpdatedEvent.class, new UserUpdatedEventHandler()::onUserUpdated);

// Create a User
User user = new User("iluwatar");

// Dispatch UserCreatedEvent
dispatcher.dispatch(new UserCreatedEvent(user));

// Dispatch UserUpdatedEvent
dispatcher.dispatch(new UserUpdatedEvent(user));

 */
public class Main {

    public static void main(String args[]){

    }
}
