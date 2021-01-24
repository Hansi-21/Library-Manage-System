package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PaymentDAO;
import entite.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtil.execute("INSERT INTO payment VALUES(?,?,?,?,?)",
                payment.getPID(),
                payment.getRegID(),
                payment.getDate(),
                payment.getType(),
                payment.getCost()
                );
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public List<Payment> get(String s) throws Exception {
        return null;
    }

    @Override
    public List<Payment> getAll() throws Exception {
        return null;
    }
}
