package com.incyyte.app.service.util;

import com.incyyte.app.dao.contacts.ContactDaoImpl;
import com.incyyte.app.dao.core.Dao;
import com.incyyte.app.dao.login.RegisterDaoJTImpl;


 
public class UIDCodeGenerator {

	private RegisterDaoJTImpl rdao;
	private ContactDaoImpl cdao;
	
	private Object dao;
	
	public String generateCode(){
		String randomstring = Utility.getRandomActivationCd(40, 40);
		return randomstring;
	}
	
	
	//===================ACTIVATION CODE GENERATOR ===============================
	private boolean isActivationCodeExists(String actvCode){
		return rdao.verifyUniqueActivationCode(actvCode);
	}
	
	public String getActivationCode(){
		String activationCode = generateCode();
		//does code already exist in DB
		/*while (isActivationCodeExists(activationCode)){
			Logger.debug("Activation Code generated already exists: TRY AGAIN!");
			activationCode = generateCode();
		}	*/	
		Logger.debug("activationCode generated: "+ activationCode);
		return activationCode;
	}
	//===================END CODE GENERATOR ===============================
	
	
	
	//===================INVITATION CODE GENERATOR ===============================
	private boolean isInvitationCodeExists(String invCode){
		return cdao.verifyUniqueInvitationCode(invCode);
	}
	
	public String getInvitationCode(){
		String invitationCode = generateCode();
		//does code already exist in DB
		while (isInvitationCodeExists(invitationCode)){
			Logger.debug("Invitation Code generated already exists: TRY AGAIN!");
			invitationCode = generateCode();
		}		
		Logger.debug("invitationCode generated: "+ invitationCode);
		return invitationCode;
	}
	//===================END CODE GENERATOR ===============================

	//===================POLL REF CODE GENERATOR ===============================
		private boolean isPollRefCodeExists(String refCode){
			return cdao.verifyUniqueInvitationCode(refCode);
		}
		
		public String getPollRefCode(){
			String pollRefCode =  Utility.getRandomActivationCd(10, 10);
			//does code already exist in DB
			/*while (isPollRefCodeExists(pollRefCode)){
				Logger.debug("Poll Reference Code generated already exists: TRY AGAIN!");
				pollRefCode = generateCode();
			}	*/	
			Logger.debug("pollRef Code generated: "+ pollRefCode);
			return pollRefCode;
		}
		//===================END CODE GENERATOR ===============================

	public static void main(String args[]){
		UIDCodeGenerator gen = new UIDCodeGenerator();
		Logger.debug(gen.getActivationCode());
	}


	public void setDao(Dao dao) {
		Logger.debug("setDao (dao instanceof RegisterDaoJTImpl) : "+ (dao instanceof RegisterDaoJTImpl));
		Logger.debug("setDao (dao instanceof ContactDaoImpl) : "+ (dao instanceof ContactDaoImpl));
		
		if (dao instanceof RegisterDaoJTImpl)
			this.rdao = (RegisterDaoJTImpl)dao;
		if (dao instanceof ContactDaoImpl)
			this.cdao = (ContactDaoImpl)dao;
	}
	

}
