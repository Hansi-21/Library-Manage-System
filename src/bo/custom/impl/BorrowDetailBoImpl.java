package bo.custom.impl;

import bo.custom.BorrowDetailBo;
import dao.DaoFactory;
import dao.custom.BookDAO;
import dao.custom.BorrowDetailDAO;
import dao.custom.QuaryDAO;
import db.DBConnection;
import dto.BorrowDetailDTO;
import entite.Book;
import entite.BorrowDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BorrowDetailBoImpl implements BorrowDetailBo {

    private BorrowDetailDAO Bdao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.borrow_details);
    private BookDAO dao=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Book);
    private QuaryDAO Qdao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);


    @Override
    public String getLatestBorrowID() throws Exception {
        return Qdao.getLatestBorrowID();
    }

    @Override
    public boolean AddBorrow(BorrowDetailDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean save = Bdao.save(new BorrowDetail(
                    dto.getBorrowID(),
                    dto.getBID(),
                    dto.getRegID(),
                    dto.getIssuedDate(),
                    dto.getReturnDate(),
                    dto.getCost(),
                    dto.getQty()
            ));
            if(save){
                int temp=0;
                List<Book> books = dao.get(dto.getBID());
                for (Book book : books) {
                    System.out.println(book.getQty());
                    temp=Integer.parseInt(book.getQty())-Integer.parseInt(dto.getQty());

                    Qdao.UpdateBook(String.valueOf(temp),book.getBID());
                    System.out.println(temp);
                    connection.commit();
                }


            }else {
                connection.rollback();
            }
        }finally {
            connection.setAutoCommit(true);
        }

        return false;
    }

    @Override
    public ArrayList<BorrowDetailDTO> getAllIssueBook() throws Exception {
        List<BorrowDetail> allBorrow = Bdao.getAll();
        ArrayList<BorrowDetailDTO> arrayList = new ArrayList<>();
        for (BorrowDetail b:allBorrow) {
            arrayList.add(new BorrowDetailDTO(b.getBorrowID(),
                    b.getBID(),
                    b.getRegID(),
                    b.getQty(),
                    b.getIssuedDate(),
                    b.getReturnDate(),
                    b.getCost()
                    ));
        }
        return arrayList;
    }

    @Override
    public boolean deleteBorrow(String Id) throws Exception {
        return Bdao.delete(Id);
    }

    @Override
    public ArrayList<BorrowDetailDTO> SearchBorrow(String Id) throws Exception {
        List<BorrowDetail> borrowDetails = Bdao.get(Id);
        ArrayList<BorrowDetailDTO> arrayList = new ArrayList<>();
        for (BorrowDetail b:borrowDetails) {
            arrayList.add(new BorrowDetailDTO(
                    b.getBorrowID(),
                    b.getBID(),
                    b.getRegID(),
                    b.getQty(),
                    b.getIssuedDate(),
                    b.getReturnDate(),
                    b.getCost()));
        }
        return arrayList;
    }

    @Override
    public boolean updateBorrow(String ReturnDate, String Cost,String Qty,String BorrowID) throws Exception {
        boolean isUpdated = Qdao.UpdateBorrow(ReturnDate, Cost, Qty,BorrowID);
        int temp=0;
        if(isUpdated){
            List<Book> books = dao.get(BorrowID);
                for (Book book : books) {
                    System.out.println(book.getQty());
                    temp=Integer.parseInt(book.getQty())+Integer.parseInt(Qty);
                    boolean b = Qdao.UpdateBook(String.valueOf(temp), book.getBID());
                    if(b){
                        return true;
                    }
                    System.out.println(temp);
                }
        }
        return false;

    }

}



