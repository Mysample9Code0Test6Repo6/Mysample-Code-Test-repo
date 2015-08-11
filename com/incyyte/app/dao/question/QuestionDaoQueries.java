package com.incyyte.app.dao.question;

public class QuestionDaoQueries {

	private static StringBuffer searchquery = new StringBuffer(	"SELECT questionid, fk_userid, question, category, upload, upload_type, upload_name, randomize, multi_selection, upload_ext, content_type, xmlString, background, viewchart_code, emailResponses, link "+
                     	"FROM questions where MATCH (question ) AGAINST ( ? ) " );

	private static StringBuffer dtlsquery = new StringBuffer("SELECT questionid, fk_userid, question, category, upload, upload_type, upload_name, randomize, multi_selection, upload_ext, content_type, xmlString, background, sendtype, page_name, viewchart_code, emailResponses, link "+
 	"FROM questions where questionid = ? " );

	/**
	 * @return the searchquery
	 */
	public static StringBuffer getSearchquery() {
		return searchquery;
	}
	/**
	 * @return the dtlsquery
	 */
	public static StringBuffer getDtlsquery() {
		return dtlsquery;
	}
}