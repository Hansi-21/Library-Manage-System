package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BorrowDetailDAO;
import entite.BorrowDetail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowDetailDAOImpl implements BorrowDetailDAO {
    @Override
    public boolean save(BorrowDetail borrowDetail) throws Exception {
        return CrudUtil.execute("INSERT INTO borrow_details VALUES(?,?,?,?,?,?,?)",
                    borrowDetail.getBorrowID(),
                    borrowDetail.getBID(),
                    borrowDetail.getRegID(),
                    borrowDetail.getQty(),
                    borrowDetail.getIssuedDate(),
                    borrowDetail.getReturnDate(),
                    borrowDetail.getCost()
                );
    }

    @Override
    public boolean update(BorrowDetail borrowDetail) throws Exception {
        return CrudUtil.execute("UPDATE borrow_details SET BID=?, RegID=?,BorrowDate=?,ReturnDate=?,ReturnCost=?,BookQty=? WHERE BorrowID=?",
                    borrowDetail.getBID(),
                    borrowDetail.getRegID(),
                    borrowDetail.getIssuedDate(),
                    borrowDetail.getReturnDate(),
                    borrowDetail.getCost(),
                    borrowDetail.getQty(),
                    borrowDetail.getBorrowID()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM borrow_details WHERE BorrowID=?",s);
    }

    @Override
    public List<BorrowDetail> get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM borrow_details WHERE BorrowID=?",s);
        ArrayList<BorrowDetail> arrayList = new ArrayList<>();
        while(rst.next()){
            arrayList.add(new BorrowDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return arrayList;
    }

    @Override
    public List<BorrowDetail> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM borrow_details");
        ArrayList<BorrowDetail> arrayList = new ArrayList<>();
        while(rst.next()){
            arrayList.add(new BorrowDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)

            ));
        }
        return arrayList;
    }
}
