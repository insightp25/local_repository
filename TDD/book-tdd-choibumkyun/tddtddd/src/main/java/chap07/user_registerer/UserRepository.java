package chap07.user_registerer;

public interface UserRepository {
    public void save(User user);

    User findById(String id);
}
