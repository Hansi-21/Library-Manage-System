package dao.custom;

import dao.CrudDAO;
import dto.CategoryDTO;
import entite.*;

import java.util.ArrayList;

public interface QuaryDAO  {
    public ArrayList<Category> getCID() throws Exception;
    public ArrayList<Member> getMID() throws Exception;
    public String getLatestBookID() throws Exception;
    public String getLatestMemberID() throws Exception;
    public String getLatestRegisterID() throws Exception;
    public String getLatestLibrarianID() throws Exception;
    public ArrayList<Registration> getRID() throws Exception;
    public String getLatestPaymentID() throws Exception;
    public String getLatestCategoryID() throws Exception;
    public boolean UpdateBook(String bookid,String qty)throws Exception;
    public boolean UpdateBorrow(String ReturnDate,String Cost,String Qty,String BorrowID)throws Exception;
    public String getLatestBorrowID() throws Exception;
}
