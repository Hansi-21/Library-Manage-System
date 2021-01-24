package dao;

import dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getInstance(){
        return(daoFactory==null) ? (daoFactory=new DaoFactory()):(daoFactory);
    }

    public enum DAOType{
       Book,Category, QUERY,Member,Librarian,Registration,Payment,borrow_details
    }

    public <T> T getDAO(DAOType type){
        switch (type){
            case Book:
                return (T) new BookDAOImpl();
            case Category:
                return (T) new CategoryDAOImpl();
            case QUERY:
                return (T) new QuaryDAOImpl();
            case Member:
                return (T) new MemberDAOImpl();
            case Librarian:
                return (T) new LibrarianDAOImpl();
            case Registration:
                return (T) new RegistrationDAOImpl();
            case Payment:
                return (T) new PaymentDAOImpl();
            case borrow_details:
                return (T) new BorrowDetailDAOImpl();
            default:
                return null;
        }
    }
}
