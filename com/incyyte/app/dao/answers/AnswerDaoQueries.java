package com.incyyte.app.dao.answers;

public class AnswerDaoQueries {

	 public static String storeAnswerInfo(){
		 StringBuffer answers = new StringBuffer();
		 answers.append(" insert into answers(" );
		 answers.append(" answerid ");
		 answers.append(" ,fk_questionid ");
		 answers.append(" ,response ");
		 answers.append(" ,upload_type ");
		 answers.append(" ,upload_name ");
		 answers.append(" ,upload_ext ");
		 answers.append(" ,upload_location");
         answers.append(" ,cdn_file_name  ");
         answers.append(" ,youtube_url  ");
         answers.append(" , url_link) ");
		 answers.append(" values(?,?,?,?,?,?,?,?,?,?) ");
		 return answers.toString();
		 
	 }

}
