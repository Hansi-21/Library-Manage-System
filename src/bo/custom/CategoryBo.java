package bo.custom;

import dto.BookDTO;
import dto.CategoryDTO;

import java.util.ArrayList;

public interface CategoryBo {
    public String getLatestCategoryID() throws Exception;
    public boolean AddCategory(CategoryDTO dto) throws Exception;
    public ArrayList<CategoryDTO> SearchCategory(String Id) throws Exception;
    public ArrayList<CategoryDTO> getAllCategories()throws Exception;
    public boolean deleteCategory(String Id)throws Exception;
    public boolean UpdateCategory(CategoryDTO dto)throws Exception;
}
