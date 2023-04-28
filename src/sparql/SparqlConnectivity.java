package sparql;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
	
public class SparqlConnectivity {

	public void PlainTextQuery(String szQuery, String szEndpoint)
	{
		// Create a Query with the given String
		Query query = QueryFactory.create(szQuery);
		
		
		// Create the Execution Factory using the given Endpoint
		QueryExecution qexec = QueryExecutionFactory.sparqlService(szEndpoint, query);
		
		
		// Set Timeout
        ((QueryEngineHTTP)qexec).addParam("timeout", "10000");
        
        
        
        // Execute Query
        int iCount = 0;
        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            // Get Result
            QuerySolution qs = rs.next();

            // Get Variable Names
            Iterator<String> itVars = qs.varNames();

            // Count
            iCount++;
            System.out.println("Result " + iCount + ": ");

            // Display Result
            while (itVars.hasNext()) {
                String szVar = itVars.next().toString();
                String szVal = qs.get(szVar).toString();
                
                //System.out.println("")
                System.out.println("[" + szVar + "]: " + szVal);
            }
        }
        }
        
        
        public static void main(String[] args) throws IOException {
            // SPARQL Query
            String szQuery = "select * where {?Subject  ?Predicate ?Object} LIMIT 10";

            // Arguments
            if (args != null && args.length == 1) {
                szQuery = new String(
                        Files.readAllBytes(Paths.get(args[0])),
                        Charset.defaultCharset());
            }

            // DBPedia Endpoint
            // String szEndpoint = "http://dbpedia.org/sparql";
              String szEndpoint = "http://localhost:2020/sparql";
            
            // Query DBPedia
            try {
            	SparqlConnectivity q = new SparqlConnectivity();
                q.PlainTextQuery(szQuery, szEndpoint);
            }
            catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
            
            System.out.println("Done!");
        }
        

        
	
	
}
	
	
	
	