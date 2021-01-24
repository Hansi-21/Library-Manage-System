package bo.custom;

import dto.BookDTO;

import java.util.ArrayList;

public interface BookBo {
    public boolean AddBook(BookDTO dto) throws Exception;
    public ArrayList<BookDTO> SearchBook(String Id) throws Exception;
    public ArrayList<BookDTO> getAllBooks()throws Exception;
    public String getLatestBookID() throws Exception;
    public boolean deleteBook(String Id)throws Exception;
    public boolean UpdateBook(BookDTO dto)throws Exception;
}
