package model.database;

import model.beans.User;
import org.hibernate.HibernateException;

/**
 *
 * @author skuarch
 */
public class UserModel {

    //==========================================================================
    private UserModel() {
    } // end UserModel

    //==========================================================================
    /**
     * save a new user in database.
     * @param user User
     * @return long id of the new user
     */
    public static long createUser(User user) {

        if (user == null) {
            throw new NullPointerException("user is null");
        }

        long id;

        try {

            id = DataAccessObject.create(user);

        } catch (HibernateException he) {
            throw he;
        }

        return id;
        
    } // end createUser

} // end class
