package bo.custom;

import dto.BookDTO;
import dto.LibrarianDTO;

import java.util.ArrayList;

public interface LibrarianBo {
    public String getLatestLibrarianID() throws Exception;
    public boolean AddLibrarian(LibrarianDTO dto) throws Exception;
    public ArrayList<LibrarianDTO> SearchLibrarian(String Id) throws Exception;
    public ArrayList<LibrarianDTO> getAllLibrarians()throws Exception;
    public boolean deleteLibrarian(String Id)throws Exception;
    public boolean UpdateLibrarian(LibrarianDTO dto)throws Exception;
}
