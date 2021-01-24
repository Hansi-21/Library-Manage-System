package bo.custom;

import dto.BookDTO;
import dto.PaymentDTO;
import dto.RegistrationDTO;
import entite.Payment;

import java.util.ArrayList;

public interface PaymentBo {
    public String getLatestPaymentID() throws Exception;
    public boolean AddPayment(PaymentDTO dto) throws Exception;
}
