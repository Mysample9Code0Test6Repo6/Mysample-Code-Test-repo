package com.incyyte.app.dao.payment;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.domain.User;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.IncyyteModel;
import com.incyyte.app.web.model.PaymentModel;

public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private AbstractDao abstractDao;

	@Override
	public boolean insertPaymentTableInfo(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception {
		String insertUserAddressInfo = " INSERT INTO PAYMENT " +
				" (USER_ID, QUESTION_ID, TRANSACTION_ORDER_ID, PAYMENT_REFERENCE, PAYMENT_STATUS, ERROR_REASON, TRANSACTION_AMOUNT, TRANSACTION_DATE) " +
				" VALUES(?,?,?,?,?,?,?,?)";
		QueryParameters params = new QueryParameters();
		params.addParam(user.getId());
		if(incyyteModel != null){
			params.addParam(incyyteModel.getId());
		}else{
			params.addParam(null);
		}
		params.addParam(paymentModel.getOrderID());
		params.addParam(paymentModel.getPaymentReference());
		params.addParam(paymentModel.getPaymentStatus());
		params.addParam(paymentModel.getErrorReason());
		params.addParam(paymentModel.getAmount());
		params.addParam(paymentModel.getTransactionDate());
		JdbcTemplate template = abstractDao.getJdbcTemplate("insertPaymentTableInfo");
		try {
			QueryHelper.doUpdate(template, insertUserAddressInfo, params);
		} catch (Exception e) {
			Logger.error("Exception", e);
			throw e;
		}finally{
			abstractDao.freedbpool(template.getDataSource(), "insertPaymentTableInfo");
		}
		return true;
	}

	@Override
	public Long getPaymentFailureCount(User user, IncyyteModel incyyteModel, PaymentModel paymentModel) throws Exception {
		Logger.debug("incyyteModel: " +incyyteModel );
		QueryParameters param = new QueryParameters();
		String failureCountQuery = null;
		if(incyyteModel != null){
			failureCountQuery = "select count(*) from payment where question_id = ? and user_id = ? ";
			param.addParam(incyyteModel.getId());
			param.addParam(user.getId());
		}else{
			failureCountQuery = "select count(*) from payment where user_id = ? and TRANSACTION_DATE = ? and question_id is null";
			param.addParam(user.getId());
			param.addParam(paymentModel.getTransactionDate());
		}
		try{
			Long failureCount = QueryHelper.doQueryForLong(abstractDao, "getPaymentFailureCount", failureCountQuery, param);
			Logger.debug("failureCount::" + failureCount);
			return failureCount;
		}catch (Exception e) {
			Logger.error("counting for payment failure was failed",e);
			throw e;
		}
	}

}