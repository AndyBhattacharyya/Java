package StableMatching;

@FunctionalInterface
public interface UserLeaveLobbyEventHandler {
   void dispatch(User user);
}
