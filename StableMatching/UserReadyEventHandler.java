package StableMatching;

@FunctionalInterface
public interface UserReadyEventHandler {
    void dispatch(User user);
}
