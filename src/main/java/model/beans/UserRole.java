package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * entity java bean.
 *
 * @author skuarch
 */
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_role_name", nullable = false)    
    private String name;
    @Column(name = "public_views_is_soft_delete")
    private int isSoftDelete;

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsSoftDelete() {
        return isSoftDelete;
    }

    public void setIsSoftDelete(int isSoftDelete) {
        this.isSoftDelete = isSoftDelete;
    }

    @Override
    public String toString() {
        return "id= " + id + " name=" + name +" isSoftDelete=" +isSoftDelete;
    }

} // end class
