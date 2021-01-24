package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QuaryDAO;
import entite.*;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuaryDAOImpl implements QuaryDAO {
    @Override
    public ArrayList<Category> getCID() throws Exception {
        ArrayList<Category> arrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT*FROM category");
        while (rst.next()){
            arrayList.add(new Category(rst.getString(1),rst.getString(2)));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Member> getMID() throws Exception {
        ArrayList<Member> arrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT*FROM member");
        while(rst.next()){
            arrayList.add(new Member(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4)));
        }
        return arrayList;
    }

    @Override
    public String getLatestBookID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT BID FROM book ORDER BY BID DESC LIMIT 1");
        return (rst.next()) ? (rst.getString("BID")):null;
    }

    @Override
    public String getLatestMemberID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT MID FROM member ORDER BY MID DESC LIMIT 1");
        return (rst.next())?(rst.getString("MID")):null;
    }

    @Override
    public String getLatestCategoryID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT CID FROM category ORDER BY CID DESC LIMIT 1");
        return (rst.next())?(rst.getString("CID")):null;
    }

    @Override
    public boolean UpdateBook(String qty, String bookid) throws Exception {
        return  CrudUtil.execute("UPDATE book SET Qty=? WHERE BID=?",
                qty,
                bookid
        );
    }

    @Override
    public boolean UpdateBorrow(String ReturnDate, String Cost,String Qty,String BorrowID) throws Exception {
        return CrudUtil.execute("UPDATE borrow_details SET ReturnDate=?, ReturnCost=?,BookQty=? WHERE BorrowID=?",
                ReturnDate,
                Cost,
                Qty,
                BorrowID
                );
    }

    @Override
    public String getLatestBorrowID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT BorrowID FROM borrow_details ORDER BY BorrowID DESC LIMIT 1");
        return (rst.next())?(rst.getString("BorrowID")):null;
    }


    @Override
    public String getLatestRegisterID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT RegID FROM registration ORDER BY RegID DESC LIMIT 1");
        return (rst.next())?(rst.getString("RegID")):null;
    }

    @Override
    public String getLatestLibrarianID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT LID FROM librarian ORDER BY LID DESC LIMIT 1");
        return (rst.next())?(rst.getString("LID")):null;
    }

    @Override
    public ArrayList<Registration> getRID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM registration");
        ArrayList<Registration> RIDList = new ArrayList<>();
        while (rst.next()){
            RIDList.add(new Registration(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    Double.parseDouble(rst.getString(4))
            ));
        }
        return RIDList;
    }

    @Override
    public String getLatestPaymentID() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT PID FROM payment ORDER BY PID DESC LIMIT 1");
        return (rst.next())?(rst.getString("PID")):null;
    }



}
