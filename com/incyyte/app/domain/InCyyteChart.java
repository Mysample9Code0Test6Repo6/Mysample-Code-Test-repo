package com.incyyte.app.domain;

import com.incyyte.app.service.util.Constants;
import com.incyyte.app.service.util.Logger;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;

public class InCyyteChart implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
      * InCyyte Object contains the set of answers
      */
    private final InCyyte incyyte;

    /*
      * All responses to this InCyyte
      */
    private String question;

    private Long incyyteId;

    private final List<Response> responses;

    private List<CyyteResponse> cyyteResponses;

    private boolean incyyteClosed;

    private boolean voted;

    /*
      * InCyyte Answer and percentage of responses
      */
    //private final Map<Answer, Integer> chart = new HashMap<Answer, Integer>();
    private final Map<Answer, Double> chart = new HashMap<Answer, Double>();
    /*
      * InCyyte Gender and percentage of responses
      */
    //private final Map<String, Integer> genderChart = new HashMap<String, Integer>();
    private final Map<String, Double> genderChart = new HashMap<String, Double>();

    public InCyyteChart(InCyyte incyyte, List<Response> responses) {
        super();
        this.incyyte = incyyte;
        this.incyyteId = incyyte.getId();
        this.question = incyyte.getIncyyte();
        this.responses = responses;
        this.cyyteResponses = new ArrayList<CyyteResponse>();
        processChart();
        processGender();
    }

    private void processGender() {
    	// build a set of gender responses
    	List<String> genderResponses = new ArrayList<String>();
    	if (this.responses != null) {
    		for (Response resp : this.responses) {
    			if (null == resp.getGender())
    				resp.setGender(Constants.GENDER_NOT_SURE);
    			genderResponses.add(resp.getGender());
    		}
    		//MALE
    		int m_occurrences = Collections.frequency(genderResponses, Constants.GENDER_MALE);
    		double m_percentage = (((double) m_occurrences / (double) this.responses.size()) * 100.00);
    		genderChart.put(Constants.GENDER_MALE, new Double(roundTwoDecimals(m_percentage)));
    		//FEMALE
    		int f_occurrences = Collections.frequency(genderResponses, Constants.GENDER_FEMALE);
    		double f_percentage = (((double) f_occurrences / (double) this.responses.size()) * 100.00);
    		genderChart.put(Constants.GENDER_FEMALE, new Double(roundTwoDecimals(f_percentage)));
    		//NOT SURE
    		double null_percentage = (100 - (m_percentage + f_percentage));
    		genderChart.put(Constants.GENDER_NOT_SURE, new Double(roundTwoDecimals(null_percentage)));
    	}
    }

    private void processChart() {
        List<Answer> answers = this.incyyte.getAnswers();

        // build a set of response answers
        List<Long> responseToAnswers = new ArrayList<Long>();
        if (this.responses != null) {
            for (Response resp : this.responses) {
                responseToAnswers.add(resp.getAnswerId());
            }
        }

        if (answers != null && this.responses != null) {
            // iterate incyyte answers, get frequency of response made
            for (Answer aws : answers) {
                int occurrences = Collections.frequency(responseToAnswers, aws.getId());
                // calc percentage: (num of occurrences / Total responses) x 100
                double percentage = (((double) occurrences / (double) this.responses.size()) * 100.00);
                chart.put(aws, roundTwoDecimals(percentage));
                cyyteResponses.add(new CyyteResponse(aws.getId(), aws.getAnswerOption(), roundTwoDecimals(percentage)));
            }
        }
    }

    public Map<Answer, Double> getChart() {
        return chart;
    }

    public Map<String, Double> getGenderChart() {
        return genderChart;
    }

    public String getQuestion() {
        return this.question;
    }

    /**
     * @return Returns the cyyteResponses.
     */
    public List<CyyteResponse> getCyyteResponses() {
        return cyyteResponses;
    }

    /**
     * @param cyyteResponses The cyyteResponses to set.
     */
    public void setCyyteResponses(List<CyyteResponse> cyyteResponses) {
        this.cyyteResponses = cyyteResponses;
    }

    /**
     * @return Returns the incyyte.
     */
    public InCyyte getIncyyte() {
        return incyyte;
    }

    /**
     * @param question The question to set.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return Returns the responses.
     */
    public List<Response> getResponses() {
        return responses;
    }

    public Long getIncyyteId() {
        return incyyteId;
    }

    public void setIncyyteId(Long incyyteId) {
        this.incyyteId = incyyteId;
    }

    public boolean isIncyyteClosed() {
        incyyteClosed = false;
        if (incyyte.getIncyyteClosedDate() != null) {
            Date today = new Date();
            if (incyyte.getIncyyteClosedDate().before(today))
                incyyteClosed = true;
        }
        return incyyteClosed;
    }

    public void setIncyyteClosed(boolean incyyteClosed) {
        this.incyyteClosed = incyyteClosed;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public boolean checkAnswerImage() {
        boolean foundFlag = false;
        for (Answer ans : this.incyyte.getAnswers()) {
            if (ans.getUploadCDN_url() != null) {
                foundFlag = true;
                break;
            }
        }
        return foundFlag;
    }

    private double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        if (!Double.isNaN(d))
            return Double.valueOf(twoDForm.format(d)).doubleValue();
        else
            return 0.0;
    }

    public static void main(String[] args) {
        double val = 20.98999;
    }

    public Map<String, Object> toJSONMap() {
        Map<String, Object> jsonValues = new HashMap<String, Object>();
        jsonValues.put("voted", voted ? "Y" : "N");
        jsonValues.put("question", question);
        jsonValues.put("incyyteClosed", incyyteClosed ? "Y" : "N");
        if (incyyte != null) {
            jsonValues.put("incyyte", incyyte.toJSONMap());
        } else {
            jsonValues.put("incyyte", "");
        }
        return jsonValues;
    }

	@Override
	public String toString() {
		return "InCyyteChart [incyyte=" + incyyte + ", question=" + question
				+ ", incyyteId=" + incyyteId + ", responses=" + responses
				+ ", cyyteResponses=" + cyyteResponses + ", incyyteClosed="
				+ incyyteClosed + ", voted=" + voted + ", chart=" + chart
				+ ", genderChart=" + genderChart + "]";
	}
}
