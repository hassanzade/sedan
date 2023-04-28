package sparql;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;

import settings.Settings;

public class SparqlQuery {
	

	public String SimpleSelectQueryTemplate = "PrefixBlock \n" +
												"SELECT DISTINCT VarsBlock WHERE { \n" +
													"StmtBlock" +
														"FilterBlock" +
												"}";
	
	public String SimpleAskQueryTemplate = "PrefixBlock \n" +
												"ASK { \n" +
													"StmtBlock" +
														"FilterBlock" +
												"}";
	
//	public String PrefixBlock = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
//									"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
//										"PREFIX owl: <http://www.w3.org/2002/07/owl#> \n";
	
	public String PrefixBlock = Settings.NAMESPACES;	
	public String[] Prefixes;
	
	public String VarsBlock = "";
	public String[] Variables;
	
	public String StmtBlock = "";
	public String[] Statements;
	
	public String FilterBlock = "";
	public String[] Filters;
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void BuildBlocks()
	{
		if(Prefixes != null)
			for(String prefix : Prefixes)
				PrefixBlock += prefix + " \n";
		if(Variables != null)
			for(String variable : Variables)
				VarsBlock += variable + " ";
		
		if(Statements != null)
			for(String statement : Statements)
				StmtBlock += statement + ". \n";
		
		if(Filters != null)
			for(String filter : Filters)
				FilterBlock += filter + ". \n";
	}
	
	public String BuildSimpleSelectQueryString()
	{
		BuildBlocks();
		String Query = SimpleSelectQueryTemplate.replace("PrefixBlock", PrefixBlock).replace("VarsBlock", VarsBlock)
						.replace("StmtBlock", StmtBlock).replace("FilterBlock", FilterBlock);
		return Query;
	}
	
	public String BuildSimpleAskQeuryString()
	{
		BuildBlocks();
		String Query = SimpleAskQueryTemplate.replace("PrefixBlock", PrefixBlock).replace("StmtBlock", StmtBlock)
						.replace("FilterBlock", FilterBlock);
		return Query;
	}
	
	public String SimpleQuery(Model model, String strQuery)
	{
		String Result = "";
		Query query = QueryFactory.create(strQuery);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		
		//ResultSetFormatter.outpu .outputAsXML(System.out, results);
		
		return Result;
	}
}
