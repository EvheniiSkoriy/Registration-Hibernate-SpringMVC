package source.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import source.entity.User;
import source.crm.UserCRM;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
    void save(UserCRM userCRM);
}
