package other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;




import ontology.MyMethods;
import settings.Settings;
import sparql.QueryTransformation;

public class batchrun {
	
	public static String INPUT_QUERIES_TO_DO = ".//OtherFiles//ToDoQueries.txt";
	public static String SPARQL_ENDPOINT =  "http://localhost:7200/repositories/SeDan";
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();
    
	QueryTransformation queryTrans;
	MyMethods myMethods = new MyMethods();
	long starttime01, starttime02, endtime, duration;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		batchrun batch = new batchrun();
		batch.runQueiresInBatch();
//		batch.waite();
	}
	
	public void runQueiresInBatch() throws IOException
	{
		InputStreamReader inputStream = new InputStreamReader(new FileInputStream(INPUT_QUERIES_TO_DO));
		BufferedReader buffReader = new BufferedReader(inputStream);
		String curLine = "";
		int queryNo = 0;
		
		
		while((curLine = buffReader.readLine()) != null)
		{
			if(curLine.contains("BEGIN"))
				queryTrans = new QueryTransformation();
			else if (curLine.contains("query;"))
				queryNo = Integer.parseInt(curLine.split(";")[1]);
			else if (curLine.contains("subject;"))
				queryTrans.setOriginalQuerySubject(curLine.split(";")[1]);
			else if (curLine.contains("predicate;"))
				queryTrans.setOriginalQueryPredicate(curLine.split(";")[1]);
			else if (curLine.contains("object;"))
				queryTrans.setOriginalQueryObject(curLine.split(";")[1]);
			else if (curLine.contains("elementtochagne;"))
				queryTrans.setElementToChange(curLine.split(";")[1]);
			else if (curLine.contains("patterns;"))
				queryTrans.setSelectedPlausiblePatterns(curLine.split(";")[1]);
			else if (curLine.contains("depth;"))
			{
				queryTrans.setDepthOfPlausibility(Integer.parseInt(curLine.split(";")[1]));
				Settings.MAX_DEPTH_OF_PLAUSIBILITY = Integer.parseInt(curLine.split(";")[1]);
			}
			
			
			
			if(curLine.contains("END;"))
			{				
				queryTrans.setQueryType((!queryTrans.getOriginalQuerySubject().contains("?") && !queryTrans.getOriginalQueryObject().contains("?") ? "Ask" : "Select"));
				queryTrans.setOriginalSparqlEndpoint(SPARQL_ENDPOINT);
				
				System.out.println(queryNo + "-> " + queryTrans.getQueryType() +";" + queryTrans.getOriginalQuerySubject() + " " + queryTrans.getOriginalQueryPredicate() + " " + queryTrans.getOriginalQueryObject() + ";" +
						queryTrans.getElementToChange() + ";" + queryTrans.getSelectedPlausiblePatterns() + ";" + queryTrans.getDepthOfPlausibility());
				
				myMethods.StoreExecutionTime(0, 0, queryTrans.getQueryType() +";" + queryTrans.getOriginalQuerySubject() + " " + queryTrans.getOriginalQueryPredicate() + " " + queryTrans.getOriginalQueryObject() + ";" +
						queryTrans.getElementToChange() + ";" + queryTrans.getSelectedPlausiblePatterns() + ";" + queryTrans.getDepthOfPlausibility());
				
				starttime01 = System.nanoTime();
				
				queryTrans.transformQuery();
				
				starttime02 = System.nanoTime();
				myMethods.StoreExecutionTime(starttime01, starttime02, "TotalRewriteTime_WithoutPrint");
				
				saveResults(queryNo + "- d." + queryTrans.getDepthOfPlausibility() + "- all pat - " + 
							((queryTrans.getElementToChange().equals("Subject")) ? "sub" : "obj"));
				waite();
			}
			
		}	
		
	}
	
	
	public void saveResults(String folderName)
	{
		File srcDir = new File(".//OtherFiles//result");

		File destDir = new File(".//OtherFiles//" + folderName);

		try {
		    FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void waite()
	{
		date = new Date();
		System.out.println(formatter.format(date));
		
		for(int i = 0; i < 299999; i++)
		{
			for(int j = 0; j < 999999; j++)
			{
				for(int k = 0; k < 99999999; k++)
				{
					
				}
			}
		}
		date = new Date();
		System.out.println(formatter.format(date));	
	}
	

}
