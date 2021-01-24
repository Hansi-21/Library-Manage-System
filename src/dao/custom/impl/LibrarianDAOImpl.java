package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LibrarianDAO;
import entite.Librarian;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAOImpl implements LibrarianDAO {

    @Override
    public boolean save(Librarian librarian) throws Exception {
        return CrudUtil.execute("INSERT INTO librarian VALUES(?,?,?,?)",librarian.getLID(),librarian.getName(),librarian.getAddress(),librarian.getContact());
    }

    @Override
    public boolean update(Librarian librarian) throws Exception {
        return CrudUtil.execute("UPDATE librarian SET Name=?,Address=?,Contact=? WHERE LID=?",
                    librarian.getName(),
                    librarian.getAddress(),
                    librarian.getContact(),
                    librarian.getLID()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM librarian WHERE LID=?",s);
    }

    @Override
    public List<Librarian> get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM librarian WHERE LID=?",s);
        ArrayList<Librarian> librarianList = new ArrayList<>();
        while(rst.next()){
            librarianList.add(new Librarian(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return librarianList;
    }

    @Override
    public List<Librarian> getAll() throws Exception {
         ResultSet rst = CrudUtil.execute("SELECT * FROM librarian");
         ArrayList<Librarian> librarianList = new ArrayList<>();
         while(rst.next()){
             librarianList.add(new Librarian(
                     rst.getString(1),
                     rst.getString(2),
                     rst.getString(3),
                     rst.getInt(4)
             ));
         }
         return librarianList;
    }
}
