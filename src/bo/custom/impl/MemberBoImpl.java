package bo.custom.impl;

import bo.custom.MemberBo;
import dao.DaoFactory;
import dao.custom.MemberDAO;
import dao.custom.QuaryDAO;
import dto.MemberDTO;
import entite.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberBoImpl implements MemberBo {
    private MemberDAO dao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Member);
    private QuaryDAO Qdao=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);

    @Override
    public String getLatestMemberID() throws Exception {
        return Qdao.getLatestMemberID();
    }

    @Override
    public boolean AddMember(MemberDTO dto) throws Exception {
       return dao.save(new Member(dto.getMID(),dto.getName(),dto.getAdddress(),dto.getContact()));
    }

    @Override
    public ArrayList<MemberDTO> SearchMember(String Id) throws Exception {
        List<Member> members = dao.get(Id);
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        for (Member m:members) {
            memberList.add( new MemberDTO(m.getMID(),m.getName(),m.getAddress(),m.getContact()));
        }
        return memberList;

    }

    @Override
    public ArrayList<MemberDTO> getAllMember() throws Exception {
        List<Member> allMember = dao.getAll();
        ArrayList<MemberDTO> memberList = new ArrayList<>();
        for (Member m:allMember) {
            memberList.add( new MemberDTO(m.getMID(),m.getName(),m.getAddress(),m.getContact()));
        }
        return memberList;
    }

    @Override
    public boolean deleteMember(String Id) throws Exception {
       return dao.delete(Id);
    }

    @Override
    public boolean updateMember(MemberDTO dto) throws Exception {
        return dao.update(new Member(
                dto.getMID(),
                dto.getName(),
                dto.getAdddress(),
                dto.getContact()
        ));
    }


}
