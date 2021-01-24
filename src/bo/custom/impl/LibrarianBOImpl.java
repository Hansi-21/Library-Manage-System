package bo.custom.impl;


import bo.custom.LibrarianBo;
import dao.DaoFactory;
import dao.custom.LibrarianDAO;
import dao.custom.QuaryDAO;
import dto.LibrarianDTO;
import entite.Librarian;
import java.util.ArrayList;
import java.util.List;

public class LibrarianBOImpl implements LibrarianBo {

    private LibrarianDAO librarianDAO= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Librarian);
    private QuaryDAO Qdao=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);

    @Override
    public String getLatestLibrarianID() throws Exception {
        return Qdao.getLatestLibrarianID();
    }

    @Override
    public boolean AddLibrarian(LibrarianDTO dto) throws Exception {
        return librarianDAO.save(new Librarian(dto.getLID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public ArrayList<LibrarianDTO> SearchLibrarian(String Id) throws Exception {
        List<Librarian> librarians = librarianDAO.get(Id);
        ArrayList<LibrarianDTO> libList = new ArrayList<>();
        for (Librarian l:librarians) {
            libList.add(new LibrarianDTO(l.getLID(),l.getName(),l.getAddress(),l.getContact()));
        }
        return libList;
    }

    @Override
    public ArrayList<LibrarianDTO> getAllLibrarians() throws Exception {
        List<Librarian> allLibrarian = librarianDAO.getAll();
        ArrayList<LibrarianDTO> librarianList = new ArrayList<>();
        for (Librarian librarian:allLibrarian) {
            librarianList.add(new LibrarianDTO(librarian.getLID(),librarian.getName(),librarian.getAddress(),librarian.getContact()));
        }
        return librarianList;
    }

    @Override
    public boolean deleteLibrarian(String Id) throws Exception {
        return librarianDAO.delete(Id);
    }

    @Override
    public boolean UpdateLibrarian(LibrarianDTO dto) throws Exception {
        return librarianDAO.update(new Librarian(dto.getLID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }
}
