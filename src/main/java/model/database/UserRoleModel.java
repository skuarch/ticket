package model.database;

import model.beans.UserRole;
import org.hibernate.HibernateException;

/**
 * logic for UserRole table.
 * @author skuarch
 */
public class UserRoleModel {

    //==========================================================================
    /**
     * this class doesn't need a public constructor
     */
    private UserRoleModel(){
    } // end UserRoleModel
    
    //==========================================================================
    /**
     * create a new row in table user_role.
     * @param ur UserRole
     * @return long with id
     */
    public static long createUserRole(UserRole ur){
    
        if(ur == null){
            throw new NullPointerException("user role object is null");
        }
        
        long id = 0;
        
        try {
            
            id = DataAccessObject.create(ur);
            
        } catch (HibernateException e) {
            throw e;
        }       
        
        return id;
        
    } // end createUserRole
    
} // end class
