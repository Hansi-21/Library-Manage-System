package bo.custom;

import dto.RegistrationDTO;

import java.util.ArrayList;

public interface RegistrationBO {
    public String getLatestRegistrationID() throws Exception;
    public boolean MakeRegistration(RegistrationDTO dto) throws Exception;
    public boolean UpdateRegistration(RegistrationDTO dto)throws Exception;
    public ArrayList<RegistrationDTO> SearchRegistration(String Id)throws Exception;
    public ArrayList<RegistrationDTO> GetAllRegistration()throws Exception;
    public boolean deleteRegistration(String Id)throws Exception;
}
