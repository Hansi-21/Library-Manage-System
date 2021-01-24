package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BookDAO;
import entite.Book;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean save(Book book) throws Exception {
        return CrudUtil.execute("INSERT INTO book VALUES(?,?,?,?,?,?,?,?)",
                book.getBID(),
                book.getCID(),
                book.getISBN(),
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                book.getLanguage(),
                book.getQty()
        );
    }

    @Override
    public boolean update(Book book) throws Exception {
        return CrudUtil.execute(
                "UPDATE book SET CID=?,ISBN=?,Name=?,Author=?,Year=?,Language=?,Qty=? WHERE BID=?",
                    book.getCID(),
                    book.getISBN(),
                    book.getName(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getLanguage(),
                    book.getQty(),
                    book.getBID()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM book WHERE BID=?",s);
    }

    @Override
    public ArrayList<Book> get(String s) throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT*FROM book WHERE BID=?",s);
        ArrayList<Book> bookList = new ArrayList<>();
        while(rst.next()){
             bookList.add(new Book(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                     rst.getString(8)
             ));
        }
        return bookList;

    }

    @Override
    public List<Book> getAll() throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT * FROM book");
        ArrayList<Book> arrayList=new ArrayList();
        while(rst.next()){
            arrayList.add(new Book(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                    rst.getString(8)
            ));
        }
        return arrayList;
    }

}
