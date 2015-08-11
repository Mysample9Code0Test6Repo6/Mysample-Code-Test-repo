/**
 * 
 */
package com.incyyte.app.web.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import com.incyyte.app.domain.InCyyte;
import com.incyyte.app.service.QuickStartService;
import com.incyyte.app.service.Exceptions.CreateInCyyteException;
import com.incyyte.app.service.Exceptions.InCyyteExceptions;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.helper.MapUserProperty;
import com.incyyte.app.web.helper.XSLTHelper;
import com.incyyte.app.web.model.AnswerModel;
import com.incyyte.app.web.model.IncyyteModel;
import com.thoughtworks.xstream.XStream;

/**
 * @author XOseniR
 */
public class QuestionStyle {

	private QuickStartService quickStartSrv;

	private Map<String, String> conditionTypeMap;

	public String getQuestionXml(long incyyteId) throws FileNotFoundException,
			IOException, SAXException, CreateInCyyteException {

		String result = new String();

		try {
			Logger.debug("incyyteId  -- " + incyyteId);

			InCyyte cyyte = quickStartSrv.initChart(incyyteId);
			
			Logger.debug("%%%%%%% " + cyyte.toString());

			// Get xml from InCyyte
			String questionXml = getQuestionXml(cyyte);

			if (questionXml != null) {

				String xmlString = XSLTHelper.getXSLTHelper().updateXMLString(
						questionXml);

				Logger.debug("xmlString style- > " + xmlString);

				// Transform the XML Source to HTML results for email
				result = XSLTHelper.getXSLTHelper().transform(
						xmlString, "display",
						Thread.currentThread().getContextClassLoader());

				cyyte.setXmlString(result);
				quickStartSrv.addXmlToInCyyte(cyyte);
				
				Logger.debug("result style- > " + result);

			} else {
				result = "Question XML Empty";
			}
			
		} catch (InCyyteExceptions ex) {
			Logger.error("TransformerFactoryConfigurationError: Unable to transform",ex);
		
		} catch (TransformerFactoryConfigurationError ex) {
			Logger.error("TransformerFactoryConfigurationError: Unable to transform",ex);
		}

		return result;
	}

	private String getQuestionXml(InCyyte cyyte) {

		String xml = null;

		if (cyyte != null) {
			Logger.debug("question - > " + cyyte.getIncyyte());

			IncyyteModel model = MapUserProperty.getIncyyteModel(cyyte);

			XStream xstream = new XStream();
			xstream.alias("incyyte", IncyyteModel.class);
			xstream.alias("answer", AnswerModel.class);

			xstream.useAttributeFor(IncyyteModel.class, "id");

			xstream.aliasField("question", IncyyteModel.class, "incyyte");

			xml = xstream.toXML(model);

		} else
			Logger.debug("InCyyte is null");

		Logger.debug("Impl - > " + xml);
		return xml;
	}

	/**
	 * @return the conditionTypeMap
	 */
	public Map<String, String> getConditionTypeMap() {
		return conditionTypeMap;
	}

	/**
	 * @param conditionTypeMap
	 *            the conditionTypeMap to set
	 */
	public void setConditionTypeMap(Map<String, String> conditionTypeMap) {
		this.conditionTypeMap = conditionTypeMap;
	}

	/**
	 * @param quickStartSrv
	 *            The quickStartSrv to set.
	 */
	public void setQuickStartSrv(QuickStartService quickStartSrv) {
		this.quickStartSrv = quickStartSrv;
	}

}
