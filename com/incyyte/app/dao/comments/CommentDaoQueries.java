package com.incyyte.app.dao.comments;

public class CommentDaoQueries {

    private static StringBuffer getQuestionsQuery = new StringBuffer()
            .append("SELECT questionid,fk_userid,question FROM QUESTIONS ")
            .append("WHERE fk_userid = ? ");

    public static String getQuestionsQuery() {
        return getQuestionsQuery.toString();
    }
}