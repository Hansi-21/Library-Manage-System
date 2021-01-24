package dao.custom.impl;

import dao.CrudUtil;
import dao.DaoFactory;
import dao.custom.CategoryDAO;
import dao.custom.QuaryDAO;
import entite.Category;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public ArrayList<Category> getCategoryID() throws Exception {
        QuaryDAO quaryDAO= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);
        ArrayList<Category> arrayList = new ArrayList();
        ArrayList<Category> CategoryID=quaryDAO.getCID();
        for (Category category:CategoryID) {
            arrayList.add(new Category(category.getCID(),category.getName()));
        }
        return arrayList;
    }

    @Override
    public boolean save(Category category) throws Exception {
        return CrudUtil.execute("INSERT INTO category VALUES(?,?)",
                category.getCID(),
                category.getName()
                );
    }

    @Override
    public boolean update(Category category) throws Exception {
        return CrudUtil.execute("UPDATE category SET Name=? WHERE CID=?",
                category.getName(),
                category.getCID()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM category WHERE CID=?",s);
    }

    @Override
    public List<Category> get(String s) throws Exception {
        return null;
    }

    @Override
    public List<Category> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM category");
        ArrayList<Category> arrayList = new ArrayList<>();
        if(rst.next()){
            arrayList.add(new Category(rst.getString(1),rst.getString(2)));
        }
        return arrayList;
    }
}
