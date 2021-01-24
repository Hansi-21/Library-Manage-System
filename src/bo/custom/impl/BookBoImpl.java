package bo.custom.impl;

import bo.custom.BookBo;
import dao.DaoFactory;
import dao.custom.BookDAO;
import dao.custom.QuaryDAO;
import dto.BookDTO;
import entite.Book;
import java.util.ArrayList;
import java.util.List;

public class BookBoImpl implements BookBo {
    private BookDAO dao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Book);
    private QuaryDAO Qdao=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);

    @Override
    public boolean AddBook(BookDTO dto) throws Exception {
        return dao.save(new Book(
                dto.getBID(),
                dto.getCID(),
                dto.getISBN(),
                dto.getName(),
                dto.getAuthor(),
                dto.getYear(),
                dto.getLanguage(),
                dto.getQty())
        );
    }

    @Override
    public ArrayList<BookDTO> SearchBook(String Id) throws Exception {
        List<Book> search = dao.get(Id);
        ArrayList<BookDTO> bookList = new ArrayList<>();
        for (Book book:search) {
            bookList.add(new BookDTO(
                    book.getBID(),
                    book.getCID(),
                    book.getISBN(),
                    book.getName(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getLanguage(),
                    book.getQty())
            );
        }
        return bookList;
    }

    @Override
    public ArrayList<BookDTO> getAllBooks() throws Exception {
        List<Book> allBooks = dao.getAll();
        ArrayList<BookDTO> arrayList = new ArrayList();
        for (Book book:allBooks) {
            arrayList.add(new BookDTO(
                    book.getBID(),
                    book.getCID(),
                    book.getISBN(),
                    book.getName(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getLanguage(),
                    book.getQty())
            );
        }
        return  arrayList;
    }

    @Override
    public String getLatestBookID() throws Exception {
        return Qdao.getLatestBookID();

    }

    @Override
    public boolean deleteBook(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public boolean UpdateBook(BookDTO dto) throws Exception {
        return dao.update(new Book(
                dto.getBID(),
                dto.getCID(),
                dto.getISBN(),
                dto.getName(),
                dto.getAuthor(),
                dto.getYear(),
                dto.getLanguage(),
                dto.getQty()));
    }
}
