package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "public_views")
public class PublicViews {

    @Id
    @Column(name = "public_views_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "public_views_name", nullable = false)
    private String name;
    @Column(name = "public_views_path", nullable = false)
    private String path;
    @Column(name = "public_views_is_soft_delete")
    private boolean softDelete;

    public PublicViews() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }

    @Override
    public String toString() {
        return "id=" + id +" name=" +name + " path=" + path + " isSofDelete=" + isSoftDelete();
    }
    
}
