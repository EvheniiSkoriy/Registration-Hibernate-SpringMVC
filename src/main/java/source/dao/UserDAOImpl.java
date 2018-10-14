package source.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import source.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;


    public User findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> users =
                session.createQuery("from User where username = :theName",User.class);
        users.setParameter("theName",userName);
        User theUser;
        try{
            theUser = users.getSingleResult();
        }catch (Exception ex){
            theUser = null;
        }
        return theUser;
    }


    public void save(User theUser) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theUser);
    }
}
