package bo;

import bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        return (boFactory == null) ?
                (boFactory = new BoFactory()) : (boFactory);
    }

    public enum BOType {
       Book,Category,Member,Librarian,Registration,Payment,Book_detail
    }

    public <T> T getBo(BOType type) {
        switch (type) {
            case Book:
                return (T) new BookBoImpl();
            case Member:
                return (T) new MemberBoImpl();
            case Librarian:
                return (T) new LibrarianBOImpl();
            case Registration:
                return (T) new RegistrationBOImpl();
            case Payment:
                return (T) new PaymentBoImpl();
            case Book_detail:
                return (T) new BorrowDetailBoImpl();
            case Category:
                return (T) new CategoryBoImpl();
            default:
                return null;
        }
    }
}
