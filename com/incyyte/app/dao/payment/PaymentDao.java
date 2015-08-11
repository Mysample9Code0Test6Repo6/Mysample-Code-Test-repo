package com.incyyte.app.dao.payment;


import com.incyyte.app.domain.User;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.PaymentModel;


public interface PaymentDao {

	boolean insertPaymentTableInfo(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception;
	Long getPaymentFailureCount(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception; 

}