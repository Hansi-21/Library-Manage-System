package bo.custom.impl;

import bo.custom.RegistrationBO;
import dao.DaoFactory;
import dao.custom.QuaryDAO;
import dao.custom.RegistrationDAO;
import dto.RegistrationDTO;
import entite.Registration;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    private RegistrationDAO registrationDAO=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Registration);
    private QuaryDAO Qdao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);

    @Override
    public String getLatestRegistrationID() throws Exception {
        return Qdao.getLatestRegisterID();
    }

    @Override
    public boolean MakeRegistration(RegistrationDTO dto) throws Exception {
        return registrationDAO.save(new Registration(dto.getRegID(),dto.getMID(),dto.getRegDate(),dto.getRegFee()));
    }

    @Override
    public boolean UpdateRegistration(RegistrationDTO dto) throws Exception {
        return registrationDAO.update(new Registration(dto.getRegID(),dto.getMID(),dto.getRegDate(),dto.getRegFee()));
    }

    @Override
    public ArrayList<RegistrationDTO> SearchRegistration(String Id) throws Exception {
        List<Registration> registrations = registrationDAO.get(Id);
        ArrayList<RegistrationDTO> arrayList = new ArrayList<>();
        for (Registration r:registrations) {
            arrayList.add(new RegistrationDTO(
                    r.getRegID(),
                    r.getMID(),
                    r.getRegDate(),
                    r.getRegFee()
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RegistrationDTO> GetAllRegistration() throws Exception {
        List<Registration> registrations = registrationDAO.getAll();
        ArrayList<RegistrationDTO> arrayList = new ArrayList<>();
        for (Registration r:registrations) {
            arrayList.add(new RegistrationDTO(
                    r.getRegID(),
                    r.getMID(),
                    r.getRegDate(),
                    r.getRegFee()
            ));
        }
        return arrayList;
    }

    @Override
    public boolean deleteRegistration(String Id) throws Exception {
        return registrationDAO.delete(Id);
    }
}
