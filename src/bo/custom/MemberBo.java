package bo.custom;

import dto.BookDTO;
import dto.MemberDTO;
import entite.Member;

import java.util.ArrayList;

public interface MemberBo {
    public String getLatestMemberID() throws Exception;
    public boolean AddMember(MemberDTO dto) throws Exception;
    public ArrayList<MemberDTO> SearchMember(String Id) throws Exception;
    public ArrayList<MemberDTO> getAllMember()throws Exception;
    public boolean deleteMember(String Id)throws Exception;
    public boolean updateMember(MemberDTO dto)throws Exception;
}
