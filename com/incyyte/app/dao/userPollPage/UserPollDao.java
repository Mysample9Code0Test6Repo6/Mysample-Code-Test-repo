package com.incyyte.app.dao.userPollPage;

import com.incyyte.app.domain.AddressType;
import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.domain.PagePhoto;
import com.incyyte.app.domain.PollPage;
import com.incyyte.app.domain.User;
import com.incyyte.app.domain.UserAddresses;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserPollDao {

    boolean insertPollPageInfo(PollPage pollPage, JdbcTemplate template) throws Exception;

    boolean updatePollPageInfo(PollPage pollPage, JdbcTemplate template) throws Exception;

    PollPage getPollPageInfo(long userId) throws Exception;

    boolean insertUserAddressesInfo(UserAddresses userAddresses, JdbcTemplate template) throws Exception;

    boolean updateUserAddressesInfo(UserAddresses userAddresses, JdbcTemplate template) throws Exception;

    UserAddresses getUserAddressesInfo(long userId, AddressType addressType) throws Exception;

    boolean insertPagePhotoInfo(PagePhoto pagePhoto, JdbcTemplate template) throws Exception;

    List<PagePhoto> getPagePhotoInfo(long pageId) throws Exception;

    boolean deletePagePhotoInfo(PagePhoto pagePhoto, JdbcTemplate template) throws Exception;
    
    public List<InCyyte> getMyPollPageShareContacts(User user,String sendType) throws Exception;

	List<PollPage> getPollPagesInformation(long userId) throws Exception;

}
