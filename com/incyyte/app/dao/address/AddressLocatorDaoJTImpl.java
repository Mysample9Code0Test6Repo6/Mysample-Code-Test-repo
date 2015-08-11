package com.incyyte.app.dao.address;

import com.incyyte.app.domain.Address;
import com.incyyte.app.domain.Postcode;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;

public class AddressLocatorDaoJTImpl implements AddressLocatorDao {

    private JdbcTemplate jdbcTemplate;

    public Long addAddress(Address address) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addAddresses(Collection<Address> addresses) {
        // TODO Auto-generated method stub
    }

    public Long addPostcode(Postcode postcode) {
        // TODO Auto-generated method stub
        return null;
    }

    public Postcode getPostcodeById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Address> getAddressById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public Postcode isPostcodeInCyyte(String postcode) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
