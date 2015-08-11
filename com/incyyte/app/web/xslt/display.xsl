<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="1.0">
	<xsl:output method="html" indent="yes" />
	
	<xsl:template match="/Empty">
		The search returned no data.
	</xsl:template>
	
	<xsl:template match="/Error">
		<xsl:value-of select="." />
	</xsl:template>
	
	<xsl:variable name="linkFileName" >
			 <xsl:value-of select="/incyyte/fileName"/>		
	</xsl:variable>
	
	<xsl:variable name="linkFileType" >
			 <xsl:value-of select="/incyyte/uploadedFileType"/>		
	</xsl:variable>
	
	<xsl:variable name="linkFileContentType" >
			 <xsl:value-of select="/incyyte/contentType"/>		
	</xsl:variable>		                  	
		
	<xsl:template match="incyyte">	
		
		<html>
  			<head>   
				<style type="text/css">
					.preview{
						width: 670px; height: 600px;
						background-image: url([background_image]);
						background-repeat:no-repeat;
						position: relative; left:30px; border-width:1px; border-right-style: solid; border-color: #EBEBEB;
					}
					.previewTable {
					    display: table;	position: relative;	top:70px; width: 600px; left:30px;
						
					}
										
					.questionCell {
						display:table-cell;
						width: 500px;
						border: 0px solid blue;
						padding: 1em;
						text-align:left;
						font-family:Arial, Helvetica, sans-serif;
						font-size:14px;
						color: #33527F;
						font-weight: bold;
					}
					
					.answButtonCell {
						display: table-cell; width: 80px; border: 0px solid blue; padding: 0.5em; text-align:right;	
					}
					.answerCell {
						display: table-cell; width: 420px; border: 0px solid blue; 	padding: 0.5em; text-align:left; padding-left: 30px; vertical-align:middle;
					}
					
					.spacerCell {
						display: table-cell;
						width: 50px;
						height: 10px;
						border: 0px solid blue;
					}
					
					.row {
						display: table-row;	margin:0px 0px 0px 0px;
					}
					
				</style>
  			</head>
 			<body>
 				<div style="width: 670px; height: 600px;background-image:url('[background_image]');
 						background-repeat:no-repeat; border-color: #EBEBEB;" >
 					<table style="display: table;	position: relative;	top:70px; width: 600px; left:30px;">
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								&#160;&#160;&#160;&#160;
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								&#160;&#160;&#160;&#160;
 							</td>
 						</tr>
 						<tr style="display:table-cell;width: 600px;border: 0px solid blue;padding: 1em;text-align:left;font-family:Arial, Helvetica, sans-serif;font-size:14px;color: #33527F;font-weight: bold;">
 							<td>
 								<xsl:apply-templates select="question" />
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								&#160;&#160;&#160;&#160;
 							</td>
 						</tr>
 						
 						<tr style="display: table;top:70px; width: 600px; left:30px;">
 							<td>
 								<xsl:apply-templates select="answers/answer"/>
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								&#160;&#160;&#160;&#160;
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								<xsl:apply-templates select="eLink" /> 
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								&#160;&#160;&#160;&#160;
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								<xsl:apply-templates select="uploadedFileLocation" />
 							</td>
 						</tr>
 						<tr style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;">
 							<td>
 								&#160;&#160;&#160;&#160;
 							</td>
 						</tr>
	            		<!-- <div style="display: table;	position: relative;	top:70px; width: 600px; left:30px;">
	            			<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                      	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                      	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                      	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                      	<div style="display: table-row;	margin:0px 0px 0px 0px;">
	                        	<div style="display:table-cell;width: 500px;border: 0px solid blue;padding: 1em;text-align:left;font-family:Arial, Helvetica, sans-serif;font-size:14px;color: #33527F;font-weight: bold;"> 
	                        		<xsl:apply-templates select="question" />
	                        	</div>
	                      	</div>
	                      	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>               
	                    </div>
	                    <div style="display: table;	position: relative;	top:70px; width: 600px; left:30px;">
	                    	
	                    	<xsl:apply-templates select="answers/answer"/>
	                    	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                     	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                     	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                     	<div style="display: table-row;	margin:0px 0px 0px 0px;"><div style="display: table-cell;width:50px;height: 10px;border: 0px solid blue;"></div></div>
	                     	<xsl:apply-templates select="eLink" /> 
							<xsl:apply-templates select="uploadedFileLocation" /> 
	                    </div> -->
                    </table>
 				</div> 			
 						
			</body>
		</html>
		
	</xsl:template>		
	
	
	<xsl:template match="question" >
		<label for="incyyte">
				<span><b><xsl:value-of select="."/></b></span>
				<br/>
		</label>
	</xsl:template>
	
	<xsl:template match="answer" >		
		<xsl:variable name="param" >incyyteId=<xsl:value-of select="quesId"/>&#38;memberId=[contactId]&#38;selectedAnswerId=<xsl:value-of select="ansId"/></xsl:variable>
		<div style="display: table-row;	margin:0px 0px 0px 0px;">
          <div class="answButtonCell">
          		<a href="[serverURL]/vote.cyt?{$param}"><img src="[vote_button]" width="67" height="32" /></a>
          		&#160;&#160;&#160;&#160;
          		<span><xsl:value-of select="answerOption"/></span>          		
		  </div>         
        </div>
        
        <!-- 		
		<tr>		  	
		  	<td>
		   		<a href="[serverURL]/vote.cyt?{$param}"><img src="http://www.panamedia.co.uk/incyyte/images/voting_button.png" width="67" height="32" /></a>
				&#160;&#160;&#160;&#160;
				<span><xsl:value-of select="answerOption"/></span>
		  	</td>
		  	<td></td>
		</tr>
		<tr colspan="2">
			<td>&#160;</td>
		</tr>
		 -->		
	</xsl:template>	
	
	<xsl:template match="eLink" >
		<xsl:variable name="param" ><xsl:value-of select="."/></xsl:variable>
   		<div class="row">                         
     		<span class="answButtonCell"><b>View Link: </b></span>&#160;&#160;&#160;&#160;
     		<span class="answerCell">
     			<a href="{$param}"  target="_blank"><span><xsl:value-of select="."/></span></a>
     		</span>
     	</div>		
		
		<!-- 
		<tr>		  	
		  	<td>
		  		<span><b>View Link: </b></span>		  	
		  		&#160;&#160;&#160;&#160;
		  		<a href="{$param}"  target="_blank"><span><xsl:value-of select="."/></span></a>
		  	</td>
		  	<td></td>
		</tr>
		 -->			
	</xsl:template>	
	
	<xsl:template match="uploadedFileLocation" >
		<xsl:variable name="param" ><xsl:value-of select="."/></xsl:variable>
  		<div class="row">	
  			<span class="answButtonCell"><b>File Link: </b></span>&#160;&#160;&#160;&#160;
    		<span class="answerCell">
    			<a href="[serverURL]/displayuploadfile.cyt?uploadfile={$param}&#38;ctype={$linkFileContentType}&#38;ftype={$linkFileType}&#38;fname={$linkFileName}" target="_blank"><span><xsl:value-of select="$linkFileName" /></span></a>
    		</span>
  		</div>	
  		<!-- 		
				<tr>		  	
				  	<td>
				  		<span><b>File Link: </b></span>		  	
				  		&#160;&#160;&#160;&#160;
				  		<a href="[serverURL]/displayuploadfile.cyt?uploadfile={$param}&#38;ctype={$linkFileContentType}&#38;ftype={$linkFileType}&#38;fname={$linkFileName}" target="_blank"><span><xsl:value-of select="$linkFileName" /></span></a>
				  	</td>
				  	<td></td>
				</tr>
		 -->	
	</xsl:template>	
	
</xsl:stylesheet>