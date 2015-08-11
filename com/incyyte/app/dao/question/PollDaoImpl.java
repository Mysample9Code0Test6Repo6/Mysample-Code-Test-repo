package com.incyyte.app.dao.question;

import com.incyyte.app.dao.core.AbstractDao;
import com.incyyte.app.dao.core.util.QueryHelper;
import com.incyyte.app.dao.core.util.QueryParameters;
import com.incyyte.app.dao.question.rowmapper.PollRegionRowmapper;
import com.incyyte.app.domain.PeopleFilter;
import com.incyyte.app.domain.Person;
import com.incyyte.app.service.util.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PollDaoImpl implements PollDao {
    AbstractDao abstractDao;

    public AbstractDao getAbstractDao() {
        return abstractDao;
    }

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> getMembersByPostcode(String postcode, PeopleFilter filter) {
        String membersByPostcodeQuery = PollDaoQueries.getMembersByPostcodeQuery().toString() + addFilter(filter);
        List<Person> people = new ArrayList<Person>();
        try {
        	Logger.debug("membersByPostcodeQuery: " + PollDaoQueries.getMembersByPostcodeQuery().toString() + postcode + "%" + addFilter(filter));
            QueryParameters params = new QueryParameters();
            params.addParam(postcode + "%");
            people = QueryHelper.doQuery(abstractDao, "getMembersByPostcode", membersByPostcodeQuery, params, new PollRegionRowmapper());
        } catch (Exception e) {
            Logger.error("getMembersByPostcode: Failed ", e);
        }
        return people;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> getMembersByRegion(String postalRegion, PeopleFilter filter) {
        String membersByRegionQuery = PollDaoQueries.getMembersByRegionQuery().toString() + addFilter(filter);
        List<Person> people = new ArrayList<Person>();
        try {
        	Logger.debug("membersByRegionQuery: " + PollDaoQueries.getMembersByRegionQuery().toString() + postalRegion + "%" + addFilter(filter));
            QueryParameters params = new QueryParameters();
            params.addParam(postalRegion + "%");
            people = QueryHelper.doQuery(abstractDao, "getMembersByRegion", membersByRegionQuery, params, new PollRegionRowmapper());
        } catch (Exception e) {
            Logger.error("getMembersByRegion: Failed " ,e);
        }
        return people;
    }

    @Override
    public Long countMembersByPostcode(String postcode, PeopleFilter filter) {
        String countMembersByPostcodeQuery = PollDaoQueries.getCountMembersByPostcodeQuery().toString() + addFilter(filter);
        try {
            Logger.debug("countMembersByPostcodeQuery: " + PollDaoQueries.getCountMembersByPostcodeQuery().toString() + postcode + "%" + addFilter(filter));
            QueryParameters params = new QueryParameters();
            params.addParam(postcode + "%");
            return QueryHelper.doQueryForLong(abstractDao, "countMembersByPostcode", countMembersByPostcodeQuery, params);
        } catch (Exception e) {
            Logger.error("countMembersByPostcode: Failed " ,e);
            return null;
        }
    }

    @Override
    public Long countMembersByRegion(String postalRegion, PeopleFilter filter) {
        String countMembersByRegionQuery = PollDaoQueries.getCountMembersByRegionQuery().toString() + addFilter(filter);
        try {
            Logger.debug("countMembersByRegionQuery: " + PollDaoQueries.getCountMembersByRegionQuery().toString() + postalRegion + "%" + addFilter(filter));
            QueryParameters params = new QueryParameters();
            params.addParam(postalRegion + " %");
            return QueryHelper.doQueryForLong(abstractDao, "countMembersByRegion", countMembersByRegionQuery, params);
        } catch (Exception e) {
            Logger.error("countMembersByRegionQuery: Failed " , e);
            return null;
        }
    }

    private String addFilter(PeopleFilter filter) {
        if (filter == null) return "";

        String genderFilter = filter.getGender() == null ? "" : " AND gender = '" + filter.getGender() + "'";
        String ageGroupFilter = filter.getMinAge() == null ? "" : ageRangeFilter(filter);
        String categoryFilter = filter.getCategory() == null ? "" : categoryFilter(filter.getCategory());

        return genderFilter + ageGroupFilter + categoryFilter;
    }
    
    private String ageRangeFilter(PeopleFilter filter){    	
    	String ageRange = null;
    	
    	Calendar today = Calendar.getInstance();  
    	int minYear = today.get(Calendar.YEAR) - filter.getMinAge(); 
    	    	
    	if(filter.getMaxAge() != null){
    		Integer maxYear = today.get(Calendar.YEAR) - filter.getMaxAge();     		
    		ageRange = " AND birthYear >= " + maxYear + " AND birthYear  <= " + minYear ;
    	}else{
    		ageRange = " AND birthYear <= " + minYear ;
    	}
    	
    	return ageRange;
    	
    }
    
    private String categoryFilter(String category){    	
    	return " AND categories like '%" + category + "%'";
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Person> getMembersByCounty(String county, PeopleFilter filter) {
        String membersByCountyQuery = PollDaoQueries.getMembersByCountyQuery().toString() + addFilter(filter);
        List<Person> people = new ArrayList<Person>();
        try {
        	Logger.debug("membersByCountyQuery: " + PollDaoQueries.getMembersByCountyQuery().toString() + county + "%" + addFilter(filter));
            QueryParameters params = new QueryParameters();
            params.addParam(county + "%");
            people = QueryHelper.doQuery(abstractDao, "getMembersByCounty", membersByCountyQuery, params, new PollRegionRowmapper());
        } catch (Exception e) {
            Logger.error("getMembersByCounty: Failed ", e);
        }
        return people;
    }

    @Override
    public Long countMembersByCounty(String county, PeopleFilter filter) {
        String countMembersByCountyQuery = PollDaoQueries.getCountMembersByCountyQuery().toString() + addFilter(filter);
        try {
        	Logger.debug("countMembersByCountyQuery: " + PollDaoQueries.getCountMembersByCountyQuery().toString() + county + "%" + addFilter(filter));
            QueryParameters params = new QueryParameters();
            params.addParam(county + "%");
            return QueryHelper.doQueryForLong(abstractDao, "countMembersByCounty", countMembersByCountyQuery, params);
        } catch (Exception e) {
            Logger.error("countMembersByCountyQuery: Failed ", e);
            return null;
        }
    }
}
