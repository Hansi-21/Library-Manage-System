package bo.custom.impl;

import bo.custom.CategoryBo;
import dao.DaoFactory;
import dao.custom.CategoryDAO;
import dao.custom.QuaryDAO;
import dto.CategoryDTO;
import entite.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryBoImpl implements CategoryBo {

    private CategoryDAO CDao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Category);
    private QuaryDAO Qdao=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);

    @Override
    public String getLatestCategoryID() throws Exception {
        return Qdao.getLatestCategoryID();
    }

    @Override
    public boolean AddCategory(CategoryDTO dto) throws Exception {
        return CDao.save(new Category(dto.getCID(),dto.getName()));
    }

    @Override
    public ArrayList<CategoryDTO> SearchCategory(String Id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategories() throws Exception {
        List<Category> all = CDao.getAll();
        ArrayList<CategoryDTO> arrayList = new ArrayList<>();
        for (Category c:all) {
            arrayList.add(new CategoryDTO(c.getCID(),c.getName()));
        }
        return arrayList;
    }

    @Override
    public boolean deleteCategory(String Id) throws Exception {
        return CDao.delete(Id);
    }

    @Override
    public boolean UpdateCategory(CategoryDTO dto) throws Exception {
        return CDao.update(new Category(dto.getCID(),dto.getName()));
    }
}
