package EventDrivenPrototype.Events;

public interface Consumer<T extends Event> {
    void accept(T event);

}
