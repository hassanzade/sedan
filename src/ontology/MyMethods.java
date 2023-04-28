package ontology;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.PublicKey;
import java.util.Iterator;

import javax.naming.ldap.ManageReferralControl;

import org.apache.jena.atlas.logging.Log;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import java.text.SimpleDateFormat;  
import java.util.Date;  


public class MyMethods {
	
	long starttime, endtime, duration; 
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    Date date = new Date();   
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		//public StatementIteration GetSubjects
//		MyMethods my = new MyMethods();
//		
//		Model model = my.ReadModel("./OntologyFiles/univ.owl");
//		
//		String strQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
//							"PREFIX ub: <http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#> " + 
//							"SELECT ?X ?Y" + 
//							"WHERE " + 
//							" {?X rdf:type ub:Student . " +
//							" ?Y rdf:type ub:Course . " +
//							" ?X ub:takesCourse ?Y . " +
//							" <http://www.Department0.University0.edu/AssociateProfessor0> ub:teacherOf ?Y} ";
//		
//		String strQuery2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
//							"PREFIX ub: <http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#> " +
//							"SELECT ?X WHERE {?X rdf:type ub:Student}";
//		
//		String strQuery4 = "SELECT ?X WHERE {?X rdf:type ub:Student}";
//		
//		String strQuery3 = "ASK { " + 
//								" <http://semmed.org/resource/Antibodies> <http://semmed.org/property/TREATS> " + 
//								" <http://semmed.org/resource/Hepatic_necrosis>. }";
		
//		System.out.println(my.CreateQuery(strQuery2));
		
		 
	}
	
	public Model ReadModel(String FileAddress)
	{
		Model model = ModelFactory.createDefaultModel();
		
		File file = new java.io.File(FileAddress);	    
		if(!file.exists())
			return null;
		
		InputStream in = FileManager.get().open(FileAddress);
		if (in == null) {
            throw new IllegalArgumentException( "File not found");
		}
		
		model.read(in, "");
		
		return model;
	}
	
	public ResultSet ExecuteQueryOverModel(String queryString, Model model)
	{
		ResultSet results = null;
		System.out.println("mm: here1");
		Query query = QueryFactory.create(queryString) ;
		System.out.println("mm: here2");
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			System.out.println("mm: entered try");
		    results = qexec.execSelect() ;
		    results = ResultSetFactory.copyResults(results) ;
		  } catch (Exception ex)
		{
			  System.out.println("MyMethods -> ExecuteQueryOverModel: \n" + ex.getMessage());
		}
		
		return results;
	}
	
	public ResultSet ExecuteSelectQueryOverSparqlEndpoint(String queryString, String sparqlEndpoint) throws MalformedURLException
	{
		ResultSet results = null;
		
		Query query = QueryFactory.create(queryString) ;
		
		try
		{
			starttime = System.nanoTime();
			QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query);			
		    results = qexec.execSelect();
		    endtime = System.nanoTime();
		    StoreExecutionTime(starttime, endtime, "Select;;QueryExecutionTime");
		    results = ResultSetFactory.copyResults(results) ;
		} catch (Exception ex)
		{
			  if (ex == null) {
			        Log.error("ex", "e is really null!!!");
			    }
			    else 
			    {
			        Log.error("ex", "e is not null, toString is " + ex + " and message is " + ex.getMessage());
			    }
				System.out.println(ex.getStackTrace().toString() + " - Exception in MyMethods -> ExecuteSelectQueryOverSparqlEndpoint: \n" + ex.getMessage());
		}
		
		return results;
	}
	
	public Boolean ExecuteAskQueryOverSparqlEndpoint(String queryString, String sparqlEndpoint) throws MalformedURLException
	{
		Boolean results = null;
		
		Query query = QueryFactory.create(queryString) ;
		//		System.out.println("Ask query is: " + queryString);
		try
		{
			starttime = System.nanoTime();
			QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlEndpoint, query);			
		    results = qexec.execAsk();
		    endtime = System.nanoTime();
		    StoreExecutionTime(starttime, endtime, "Ask;;QueryExecutionTime");
		} catch (Exception ex)
		{
			  if (ex == null) {
			        Log.error("ex", "e is really null!!!");
			    }
			    else 
			    {
			        Log.error("ex", "e is not null, toString is " + ex + " and message is " + ex.getMessage());
			    }
				System.out.println(ex.getStackTrace().toString() + " - Exception in MyMethods->ExecuteAskQueryOverSparqlEndpoint: \n" + ex.getMessage());
		}
		
		return results;
	}
	
	public String ResultSetToString(ResultSet resultSet)
	{
		String resultSetStr = "";
		int iCount = 0;
		
		while (resultSet.hasNext()) {
            // Get Result
            QuerySolution qs = resultSet.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            resultSetStr += "\nResult " + iCount + ": \n";
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();
                
                resultSetStr += "[" + szVar + "]: " + szVal + "\n";
            }
        }
		
		return resultSetStr;
	}
	
	public String ResultSetToStringWithoutVariable(ResultSet resultSet)
	{
		String resultSetStr = "";
		int iCount = 0;
		while (resultSet.hasNext()) {
            // Get Result
            QuerySolution qs = resultSet.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();
                
                resultSetStr += szVal + "\n";
            }
        }
		
		return resultSetStr;
	}
	
	public Query CreateQuery(String queryString)
	{
		return QueryFactory.create(queryString) ;
	}
	
	
	public void StoreExecutionTime(long startTime, long endTime, String description) throws IOException
	{
		duration = (endTime - startTime) / 1000;  //divided by 1000 to get microseconds.
//		Files.write(Paths.get(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS_ALL), 
//					(description + ";" + String.valueOf(duration) + ";\n").getBytes(), StandardOpenOption.APPEND);
		
//		commenting prints
		Files.write(Paths.get(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS), 
				(description + ";" + String.valueOf(duration) + ";\n").getBytes(), StandardOpenOption.APPEND);
		
//		if(description.contains("TotalRe"))
//		Files.write(Paths.get(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS), 
//				(description + ";" + String.valueOf(duration) + ";\n").getBytes(), StandardOpenOption.APPEND);
	}
	
	public void StoreCurrentTime(String description) throws IOException
	{
		
		date = new Date(); 
		String strDate = formatter.format(date);
	    //System.out.println(formatter.format(date));
	    
//		Files.write(Paths.get(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS_ALL), 
//				(description + ";" +  formatter.format(date) + ";\n").getBytes(), StandardOpenOption.APPEND);
	
		//commenting prints
		Files.write(Paths.get(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS), 
			(description + ";" +  formatter.format(date) + ";\n").getBytes(), StandardOpenOption.APPEND);
	}
}
