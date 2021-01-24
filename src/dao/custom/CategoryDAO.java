package dao.custom;

import dao.CrudDAO;
import dto.CategoryDTO;
import entite.Category;

import java.util.ArrayList;

public interface CategoryDAO extends CrudDAO<Category,String> {
    public ArrayList<Category> getCategoryID() throws Exception;
}
