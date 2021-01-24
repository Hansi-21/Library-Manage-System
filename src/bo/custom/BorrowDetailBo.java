package bo.custom;

import dto.BookDTO;
import dto.BorrowDetailDTO;
import dto.MemberDTO;

import java.util.ArrayList;

public interface BorrowDetailBo {
    public String getLatestBorrowID() throws Exception;
    public boolean AddBorrow(BorrowDetailDTO dto) throws Exception;
    public ArrayList<BorrowDetailDTO> getAllIssueBook()throws Exception;
    public boolean deleteBorrow(String Id)throws Exception;
    public ArrayList<BorrowDetailDTO> SearchBorrow(String Id) throws Exception;
    public boolean updateBorrow(String ReturnDate,String Cost,String Qty,String BorrowID)throws Exception;

}
