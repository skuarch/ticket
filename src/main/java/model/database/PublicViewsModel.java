package model.database;

import java.util.ArrayList;
import model.beans.PublicViews;
import org.hibernate.HibernateException;

/**
 *
 * @author skuarch
 */
public class PublicViewsModel {

    private PublicViewsModel() {
    }

    //==========================================================================
    public static ArrayList<PublicViews> getAllPublicViews() {

        ArrayList<PublicViews> views = null;

        try {
            views = new ArrayList<>(DataAccessObject.getList(new PublicViews()));
        } catch (HibernateException e) {
            throw e;
        }

        return views;

    }

    //==========================================================================
    public static long createPublicView(PublicViews view) {

        if (view == null) {
            throw new NullPointerException("view is null");
        }

        long id = 0;

        try {

            id = DataAccessObject.create(view);

        } catch (HibernateException e) {
            throw e;
        }

        return id;
    }

} // end class
