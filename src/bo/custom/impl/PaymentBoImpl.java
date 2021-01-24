package bo.custom.impl;

import bo.custom.PaymentBo;
import dao.DaoFactory;
import dao.custom.PaymentDAO;
import dao.custom.QuaryDAO;
import dto.PaymentDTO;
import entite.Payment;

public class PaymentBoImpl implements PaymentBo {

    private PaymentDAO Pdao= DaoFactory.getInstance().getDAO(DaoFactory.DAOType.Payment);
    private QuaryDAO Qdao=DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);

    @Override
    public String getLatestPaymentID() throws Exception {
        return Qdao.getLatestPaymentID();
    }

    @Override
    public boolean AddPayment(PaymentDTO dto) throws Exception {
        return Pdao.save(new Payment(
                dto.getPID(),
                dto.getRegID(),
                dto.getDate(),
                dto.getType(),
                dto.getCost()
                ));
    }


}
