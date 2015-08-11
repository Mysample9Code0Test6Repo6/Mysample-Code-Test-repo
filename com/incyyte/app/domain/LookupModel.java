package com.incyyte.app.domain;

public class LookupModel {
	 private String lookupType;

	    private String LookupCode;
	    
	    private long lookupValue;

	    
		public long getLookupValue() {
			return lookupValue;
		}

		public void setLookupValue(long lookupValue) {
			this.lookupValue = lookupValue;
		}

		public String getLookupType() {
			return lookupType;
		}

		public void setLookupType(String lookupType) {
			this.lookupType = lookupType;
		}

		public String getLookupCode() {
			return LookupCode;
		}

		public void setLookupCode(String lookupCode) {
			LookupCode = lookupCode;
		}

		@Override
		public String toString() {
			return "LookupModel [lookupType=" + lookupType + ", LookupCode="
					+ LookupCode + ", lookupValue=" + lookupValue + "]";
		}

		
}
