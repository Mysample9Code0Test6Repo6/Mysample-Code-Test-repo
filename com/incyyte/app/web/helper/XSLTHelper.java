/*
 * XSLTHelper.java
 *
 * Created on 28 September 2006, 11:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.incyyte.app.web.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.incyyte.app.service.util.Logger;

/**
 * @author XOseniR
 */
public class XSLTHelper {

	private static XSLTHelper helper = null;

	/** Creates a new instance of XSLTHelper */
	private XSLTHelper() {
	}

	public static XSLTHelper getXSLTHelper() {
		if (helper == null) {
			helper = new XSLTHelper();
		}

		return helper;
	}

	public String transform(String xmlString, String stylesheet,
			ClassLoader classLoader) {

		TransformerFactory factory = null;
		Transformer trans = null;
		Source source = null;
		String returnString = "";
		Result result = null;
		StringWriter stringWriter = new StringWriter();

		try {

			factory = TransformerFactory.newInstance();

			source = getSource(stylesheet, classLoader);

			trans = factory.newTransformer(source);

			result = new StreamResult(stringWriter);

            Logger.debug(result.toString());

			try {

				trans.transform(xmlStringToSource(xmlString), result);

			} catch (TransformerException ex) {
				Logger.error("TransformerException: An error occurred while applying the XSL file",
								ex);
			}

		} catch (TransformerConfigurationException ex) {
			Logger.error("TransformerConfigurationException: An error occurred in the XSL file ",
							ex);
		} catch (TransformerFactoryConfigurationError ex) {
			Logger.error("TransformerFactoryConfigurationError: Unable to transform",
							ex);
		} finally {
		}

		returnString = stringWriter.toString();
		return returnString;
	}

	public String transform(Source xmlSource, String stylesheet,
			ClassLoader classLoader) {

		TransformerFactory factory = null;
		Transformer trans = null;
		Source source = null;
		String returnString = "";
		Result result = null;
		StringWriter stringWriter = new StringWriter();

		try {

			factory = TransformerFactory.newInstance();

			source = getSource(stylesheet, classLoader);

			trans = factory.newTransformer(source);

			result = new StreamResult(stringWriter);

			try {

				trans.transform(xmlSource, result);

			} catch (TransformerException ex) {
				Logger.error("TransformerException: An error occurred while applying the XSL file",
								ex);
			}

		} catch (TransformerConfigurationException ex) {
			Logger.error("TransformerConfigurationException: An error occurred in the XSL file ",
							ex);
		} catch (TransformerFactoryConfigurationError ex) {
			Logger.error("TransformerFactoryConfigurationError: Unable to transform",
							ex);
		} finally {
		}

		returnString = stringWriter.toString();
		return returnString;
	}

	public Source xmlStringToSource(String xmlString) {

		Source source = null;

		source = new StreamSource(new StringReader(xmlString));

		return source;
	}

	public String updateXMLString(String xmlString) {

		try {

			Document doc = buildInputQuery(xmlString);

			Transformer transformer =
				TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// initialize StreamResult with File object to save to file
			StreamResult result = new StreamResult(new StringWriter());
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);

			xmlString = result.getWriter().toString();

		} catch (TransformerException ex) {
			Logger.error("updateXMLString: TransformerException: An error occurred during the transformation process ",
							ex);
		} catch (SAXException ex) {
			Logger.error("updateXMLString: SAXException: An error occurred during the XML parser ",
							ex);
		} catch (IOException ex) {
			Logger.error("updateXMLString: IOException: An error occurred during the XML parser input File ",
							ex);
		}
		return xmlString;

	}

	public Source getSource(String stylesheetName, ClassLoader classLoader) {

		Source source = null;
		Properties env = null;

		String Loggerinmodule_properties =
			"com.incyyte.app.web.properties.application";

		String propName = "xsl." + stylesheetName;
		String path = null;

		env =
			PropertyLoader.loadProperties(Loggerinmodule_properties,
					classLoader);

		Logger.debug("env size- > " + env.size());

		path = env.getProperty(propName);

        Logger.debug("path - > " + path);
		source = new StreamSource(classLoader.getResourceAsStream(path));

		return source;
	}

	public Document buildInputQuery(InputStream inputFile) throws IOException,
			SAXException {

		// Obtain a new instance of a DocumentBuilderFactory
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

		// Specifies that the parser produced by this code will provide support
		// for XML namespaces.
		fac.setNamespaceAware(true);

		// initialize DocumentBuilder
		DocumentBuilder parser = null;

		try {
			// Creates a new instance of a DocumentBuilder using the currently
			// configured parameters.
			parser = fac.newDocumentBuilder();
		} catch (Exception e) {
            Logger.error("Exception:", e);
        }

		// Parse the content of the given file as an XML document and return a
		// new DOM Doc object.
		Document doc = parser.parse(inputFile);

		return doc;

	}

	public Document buildInputQuery(String inputFile) throws IOException,
			SAXException {

		// Obtain a new instance of a DocumentBuilderFactory
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

		// Specifies that the parser produced by this code will provide support
		// for XML namespaces.
		fac.setNamespaceAware(true);

		// initialize DocumentBuilder
		DocumentBuilder parser = null;

		try {
			// Creates a new instance of a DocumentBuilder using the currently
			// configured parameters.
			parser = fac.newDocumentBuilder();
		} catch (Exception e) {
			Logger.error("parser config error: ", e);
		}

		// Parse the content of the given file as an XML document and return a
		// new DOM Doc object.
		Document doc = parser.parse(new InputSource(new StringReader(inputFile)));
		return doc;
	}

	/**
	 * The document is transformed and output is sent to a StringWriter. The
	 * StringWriter's "toString" method then returns contents of the document as
	 * a string.
	 */
	public String documentToString(InputStream inputFile)
			throws TransformerException, IOException, SAXException {

		org.w3c.dom.Document doc = buildInputQuery(inputFile);

		// Create dom source for the document
		DOMSource domSource = new DOMSource(doc);

		// Create a string writer
		StringWriter stringWriter = new StringWriter();

		// Create the result stream for the transform
		StreamResult result = new StreamResult(stringWriter);

		// Create a Transformer to serialize the document
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty("indent", "yes");

		// Transform the document to the result stream
		transformer.transform(domSource, result);
		return stringWriter.toString();
	}

}
