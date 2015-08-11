package com.incyyte.app.manager;

import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import com.incyyte.app.domain.UserType;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.PaymentModel;

public interface PaymentManager{

	boolean insertPaymentInfo(PaymentModel paymentModel, UserAddresses billingAddress, UserAddresses userAddress, User user) throws Exception;
	PaymentModel  getPaymentInfo(long userId, AddressType billing) throws Exception;
	String getUsersEmailInfo(User user)  throws Exception;
	boolean insertPaymentTableInfo(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception;
	boolean updateUserType(User user, UserType business_Sliver)	throws Exception;
	Long getPaymentFailureCount(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception;
	void sendMailToSender(PaymentModel paymentModel, User user, InCyyte incyyte) throws Exception;

}