package StableMatching;

@FunctionalInterface
public interface UserSelectedEventHandler {
    void dispatch(User user);
}
