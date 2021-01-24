package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.MemberDAO;
import entite.Member;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public boolean save(Member member) throws Exception {
       return  CrudUtil.execute("INSERT INTO member VALUES(?,?,?,?)",
               member.getMID(),
               member.getName(),
               member.getAddress(),
               member.getContact()
       );

    }

    @Override
    public boolean update(Member member) throws Exception {
        return CrudUtil.execute("UPDATE member SET Name=?,Address=?,Contact=? WHERE MID=?",
                    member.getName(),
                    member.getAddress(),
                    member.getContact(),
                    member.getMID()
                );
    }

    @Override
    public boolean delete(String s) throws Exception {
       return CrudUtil.execute("DELETE FROM member WHERE MID=?",s);
    }

    @Override
    public ArrayList<Member> get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM member WHERE MID=?",s);
        ArrayList<Member> arrayList = new ArrayList<>();
        if(rst.next()){
            arrayList.add(new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)));
        }
        return arrayList;
    }

    @Override
    public List<Member> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM member");
        ArrayList<Member> arrayList = new ArrayList();
        while (rst.next()){
            arrayList.add(new Member(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)));
        }
        return arrayList;
    }
}
