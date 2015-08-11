/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class implements the addressLocatorManager 
 * For example:
 * <pre>
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 30 Dec 2010
 */
package com.incyyte.app.manager;

import java.util.List;


import com.incyyte.app.dao.address.AddressLocatorDao;
import com.incyyte.app.domain.Address;
import com.incyyte.app.domain.AddressList;
import com.incyyte.app.domain.Postcode;

import com.incyyte.app.service.Exceptions.PostcodeNotFoundException;
import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;

public class AddressLocatorServiceManager implements AddressLocatorManager {
	
	private AddressLocatorDao addressDaoImpl;
	
	public List<Address> getAddressList(String postcode) throws PostcodeNotFoundException{
		List<Address> addresses = null;
		
		//1.query inCyyte Database	
		Postcode inCyytepc = addressDaoImpl.isPostcodeInCyyte(postcode);
		if (inCyytepc != null){
		//2.if found on Incyyte Database return Addresses
			addresses = addressDaoImpl.getAddressById(new Long(inCyytepc.getId()));
			Logger.debug("Postode on inCyyte DB with "+ addresses.size() +" addresses.  ");
			return addresses;
		}
		
		//3.Not found on Incyyte Database Goto PostCodeAnywhere
		java.util.Hashtable[] data;
		try{
			data = PostcodeAnywhere_Interactive_FindByPostcode_v1_00(Constants.PCA_KEY, 
					postcode, 
					Constants.PCA_USERNAME);
		}catch(Exception e){
			Logger.error("ERROR: Postcode not found in PostcodeAnywhere ",e);
			throw new PostcodeNotFoundException("Postcode not found.");
		}

        Logger.debug("Retrieving all values from the Hashtable");
		AddressList addressList = new AddressList();

		Postcode newPostcode = new Postcode(postcode);
		Long postcodeId = addressDaoImpl.addPostcode(newPostcode);
		newPostcode.setId(postcodeId.longValue());

		for(int i=0; i<data.length; i++){
			Address address = new Address();
			address.setPca_id( (new Double((String)data[i].get("Id"))).doubleValue());
			address.setPlace((String)data[i].get("Place"));
			address.setStreetAddress((String)data[i].get("StreetAddress"));
			address.setPostcode(newPostcode);
			addressList.addItem(address);				
		}
		
		addresses = addressList.getAddresses();
		Logger.debug("Postode on PostcodeAnywhere with "+ addresses.size() +" addresses.  ");
		//4.Found on postcode Anywhere Add to Incyyte Db
		addressDaoImpl.addAddresses(addresses);
		//5.and return Addresses		
		return addresses;
	}

	private static java.util.Hashtable[] PostcodeAnywhere_Interactive_FindByPostcode_v1_00(String Key, String Postcode, String UserName) throws Exception {

	  String requestUrl = new String();
	  String key = new String();
	  String value = new String();

	  //Build the url
	  requestUrl = "http://services.postcodeanywhere.co.uk/PostcodeAnywhere/Interactive/FindByPostcode/v1.00/xmla.ws?";
	  requestUrl += "&Key=" + java.net.URLEncoder.encode(Key);
	  requestUrl += "&Postcode=" + java.net.URLEncoder.encode(Postcode);
	  requestUrl += "&UserName=" + java.net.URLEncoder.encode(UserName);

	  //Get the data
	  java.net.URL url = new java.net.URL(requestUrl);
	  java.io.InputStream stream = url.openStream();
	  javax.xml.parsers.DocumentBuilder docBuilder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
	  org.w3c.dom.Document dataDoc = docBuilder.parse(stream);

	  //Get references to the schema and data
	  org.w3c.dom.NodeList schemaNodes = dataDoc.getElementsByTagName("Column");
	  org.w3c.dom.NodeList dataNotes = dataDoc.getElementsByTagName("Row");

	  //Check for an error
	  if (schemaNodes.getLength()==4 && schemaNodes.item(0).getAttributes().getNamedItem("Name").getNodeValue().equals("Error"))
	  {
	    throw new Exception(dataNotes.item(0).getAttributes().getNamedItem("Description").getNodeValue());
	  };

	  //Work though the items in the response
	  java.util.Hashtable[] results = new java.util.Hashtable[dataNotes.getLength()];
	  for (int rowCounter=0; rowCounter<dataNotes.getLength(); rowCounter++)
	  {
	    java.util.Hashtable rowData = new java.util.Hashtable();
	    for (int colCounter=0; colCounter<schemaNodes.getLength(); colCounter++)
	    {
	      key = (String)schemaNodes.item(colCounter).getAttributes().getNamedItem("Name").getNodeValue();
	      if(dataNotes.item(rowCounter).getAttributes().getNamedItem(key)==null)
	        {
	          value="";
	        }
	      else
	        {
	          value = (String)dataNotes.item(rowCounter).getAttributes().getNamedItem(key).getNodeValue();
	        };
	      rowData.put (key, value);
	    }
	    results[rowCounter] = rowData;
	  }

	  //Return the results
	  return results;
	}

	public void setAddressDaoImpl(AddressLocatorDao addressDaoImpl) {
		this.addressDaoImpl = addressDaoImpl;
	}

	
}
