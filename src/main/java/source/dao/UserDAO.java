package source.dao;

import source.entity.User;

public interface UserDAO {
    User findByUserName(String userName);
    void save(User user);

}
