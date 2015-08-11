package com.incyyte.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.domain.UserType;
import com.incyyte.app.manager.PaymentManager;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.PaymentModel;

public class PaymentServiceImpl  implements PaymentService {
	@Autowired
	private PaymentManager paymentMgr;	

	@Override
	public boolean insertPaymentInfo(PaymentModel paymentModel, UserAddresses billingAddress, UserAddresses userAddress,User user ) throws Exception {
		return paymentMgr.insertPaymentInfo(paymentModel,billingAddress, userAddress, user) ;
	}

	@Override
	public PaymentModel getPaymentInfo(long userId,  AddressType billing) throws Exception {
		return paymentMgr.getPaymentInfo(userId , billing);
	}

	@Override
	public String getUsersEmailInfo(User user)  throws Exception{
		return paymentMgr.getUsersEmailInfo(user);
	}

	@Override
	public boolean insertPaymentTableInfo(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception {
		return paymentMgr.insertPaymentTableInfo(user, incyyteModel, paymentModel);
	}

	@Override
	public boolean updateUserType(User user,  UserType business_Sliver) throws Exception {
		return paymentMgr.updateUserType(user, business_Sliver);
	}

	@Override
	public Long getPaymentFailureCount(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception {
		return paymentMgr.getPaymentFailureCount(user, incyyteModel, paymentModel);
	}

	@Override
	public void sendMailToSender(PaymentModel paymentModel, User user, InCyyte incyyte) throws Exception {
		paymentMgr.sendMailToSender(paymentModel, user, incyyte);		
	}
}