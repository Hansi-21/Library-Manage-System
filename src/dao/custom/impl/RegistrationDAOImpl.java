package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.RegistrationDAO;
import entite.Registration;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {

    @Override
    public boolean save(Registration registration) throws Exception {
        return CrudUtil.execute("INSERT INTO registration VALUES(?,?,?,?)",
                registration.getRegID(),
                registration.getMID(),
                registration.getRegDate(),
                registration.getRegFee()
        );
    }

    @Override
    public boolean update(Registration registration) throws Exception {
        return CrudUtil.execute("UPDATE registration SET MID=?,RegDate=?,RegFee=? WHERE RegID=?",
                    registration.getMID(),
                    registration.getRegDate(),
                    registration.getRegFee(),
                    registration.getRegID()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM registration WHERE RegID=?",s);
    }

    @Override
    public List<Registration> get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT*FROM registration WHERE RegID=?",s);
        ArrayList<Registration> arrayList = new ArrayList<>();
        while(rst.next()){
            arrayList.add(new Registration(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
                    ));
        }
        return arrayList;
    }

    @Override
    public List<Registration> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT*FROM registration");
        ArrayList<Registration> arrayList = new ArrayList<>();
        while(rst.next()){
            arrayList.add(new Registration(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));
        }
        return arrayList;
    }
}
