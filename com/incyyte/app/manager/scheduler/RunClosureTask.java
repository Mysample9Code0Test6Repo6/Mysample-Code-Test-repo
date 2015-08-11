package com.incyyte.app.manager.scheduler;

import com.incyyte.app.dao.user.UserDao;
import com.incyyte.app.service.util.Logger;

public class RunClosureTask {
	private long incyyteId;
	private long groupId;
	private UserDao userdao;
	
	public void closeInCyyte() {
        Logger.debug("CLOSE InCyyte...  incyyteId: " + incyyteId + "  groupId:" + groupId);
		userdao.closeInCyyte(getIncyyteId(), getGroupId());
        Logger.debug("InCyyte CLOSED...  incyyteId: "+incyyteId +"  groupId:"+groupId);
	}

	public long getIncyyteId() {
		return incyyteId;
	}

	public void setIncyyteId(long incyyteId) {
		this.incyyteId = incyyteId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	
	
	
}
