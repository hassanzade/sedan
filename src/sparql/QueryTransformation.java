package sparql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.channels.Selector;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.jena.arq.querybuilder.*;
import org.apache.jena.query.Query;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Resource;

import PlausiblePatterns.Afortiori;
import PlausiblePatterns.Dissimilarity;
import PlausiblePatterns.Generalization;
import PlausiblePatterns.Interpolation;
import PlausiblePatterns.Similarity;
import PlausiblePatterns.Specialization;
import ontology.MyMethods;
import settings.Settings;





//import pers.aprakash.spanqit.constraint.Expression;
//import pers.aprakash.spanqit.constraint.ExpressionOperands;
//import pers.aprakash.spanqit.constraint.Expressions;
//import pers.aprakash.spanqit.core.Assignment;
//import pers.aprakash.spanqit.core.Prefix;
//import pers.aprakash.spanqit.core.Spanqit;
//import pers.aprakash.spanqit.core.Variable;

public class QueryTransformation {
	
	private String local_OUTPUT_TRANSFORMED_QUERIES_ADDRESS = "";
	private String originalSparqlEndpoint = "";
	private String originalQuerySubject = "";
	private String originalQueryObject = "";
	private String originalQueryPredicate = "";
	private String elementToChange = "";
	private String elementToChange_Value = "";
	private String selectedPlausiblePatterns = "";
	private String originalQuery = "";
	private String currentPatternsSemantics = "";
	private String queryType = "";
	private int currentDepthOfPlausibility = 0;
	private int maxDepthOfPlausibility = Settings.MAX_DEPTH_OF_PLAUSIBILITY;
	private boolean noMoreTransformedQuery = false;
	private ArrayList<String> transformedQueries = new ArrayList<String>();
	private int curIndex = 0;
	Specialization specialization = new Specialization();
	Generalization generalization = new Generalization();
	Afortiori afortiori = new Afortiori();
	Interpolation interpolation = new Interpolation();
	Similarity similarity = new Similarity();
	Dissimilarity dissimilarity = new Dissimilarity();
	
	Settings setting = new Settings();
	
	long starttime01, starttime02, starttime03, endtime, globalStartTime01, globalStartTime02, globalEndTime;
	long plAnswerTime01, plAnswerTime02;
	
	MyMethods myMethods = new MyMethods();
	
	public QueryTransformation()
	{
		this.clearTransformedQueries();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sampleQuery = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
								"PREFIX ub: <http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#> " +
									"SELECT ?X, ?Y, ?Z \n WHERE {?X rdf:type ub:GraduateStudent . ?Y rdf:type ub:University . ?Z rdf:type ub:Department . " +
										"?X ub:memberOf ?Z . ?Z ub:subOrganizationOf ?Y . ?X ub:undergraduateDegreeFrom ?Y} ";
		
		String simpleQuery = "SELECT * WHERE {\n" +
								"  ?s a ?type .\n" +
									"  OPTIONAL { ?s rdfs:label ?label . }\n" +
										"}";
		
//		ParameterizedSparqlString pss = new ParameterizedSparqlString();
//		pss.setCommandText(sampleQuery);
//		System.out.println(pss.toString());
//		
//		pss.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
//		pss.append("LIMIT ");
//		pss.appendLiteral(50);
//		System.out.println(pss.toString());
//		System.out.println(pss.getParam("?s"));
		
		//System.out.println(sampleQuery.contains("\n"));
	}
	
	public String getOriginalSparqlEndpoint()
	{
		return this.originalSparqlEndpoint;
	}
	
	public void setOriginalSparqlEndpoint(String sparqlEndpoint)
	{
		this.originalSparqlEndpoint = sparqlEndpoint;
	}
	
	public String getOriginalQuerySubject()
	{
		return this.originalQuerySubject;
	}
	
	public void setOriginalQuerySubject(String subject)
	{
		this.originalQuerySubject = subject;
	}
	
	public String getOriginalQueryObject()
	{
		return this.originalQueryObject;
	}
	
	public void setOriginalQueryObject(String object)
	{
		this.originalQueryObject = object;
	}
	
	public String getOriginalQueryPredicate()
	{
		return this.originalQueryPredicate;
	}
	
	public void setOriginalQueryPredicate(String predicate)
	{
		this.originalQueryPredicate = predicate;
	}
	
	public String getElementToChange()
	{
		return this.elementToChange;
	}
	
	public void setElementToChange(String element)
	{
		this.elementToChange = element;
	}
	
	public String getSelectedPlausiblePatterns()
	{
		return this.selectedPlausiblePatterns;
	}
	
	public void setSelectedPlausiblePatterns(String selectedPlausiblePatterns)
	{
		this.selectedPlausiblePatterns = selectedPlausiblePatterns;
	}
	
	public String getOriginalQuery()
	{
		return this.originalQuery;
	}
	
	public void setOriginalQuery(String originalQuery)
	{
		this.originalQuery = originalQuery;
	}
	
	public String getQueryType()
	{
		return this.queryType;
	}
	
	public void setQueryType(String queryType)
	{
		this.queryType = queryType;
	}
	
	public int getDepthOfPlausibility()
	{
		return this.maxDepthOfPlausibility;
	}
	
	public void setDepthOfPlausibility(int depthOfPlausibility)
	{
		this.maxDepthOfPlausibility = depthOfPlausibility;
	}
	
	public void clearTransformedQueries()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter(settings.Settings.OUTPUT_TRANSFORMED_QUERIES_ADDRESS);
			writer.print("");
			writer.close();
			
			writer = new PrintWriter(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ADDRESS);
			writer.print("");
			writer.close();
			
			writer = new PrintWriter(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String createQuery()
	{
		String query = "";
		SelectBuilder sb = new SelectBuilder().addVar("*");
		// AbstractQueryBuilder q = sb.build();
			
		return query;
	}

	public List<String> retrieveVariables(String query){
		List<String> variablesList = new ArrayList<String>();
				
		return variablesList;
	}
	
	public String createQuery2()
	{
		String strquery = "";
		
//		SelectQuery query = Queries.SELECT();
//		Prefix base = Spanqit.prefix(iri("http://books.example/"));
//		Prefix foaf = Spanqit.prefix(iri("http://xmlns.com/foaf/0.1/"));
//		
//		query.prefix(prefixes) prefix("<http://xmlns.com/foaf/0.1/>").
		
		
		return strquery;
	}
	
//	public ElementTriplesBlock CreateTriplePattern(Resource subject, Resource predicate, Resource object)
//	{		
//		ElementTriplesBlock triplePattern = new ElementTriplesBlock();
////		triplePattern.addTriple(new Triple((Node)subject, (Node)predicate, (Node)object));
//
//		return triplePattern;
//	}
	
	public void retrieveGeneralizationSemantics(String element) throws IOException
	{
		int iCount = 0;
		String SupportingSemantic_Predicate = "";
		starttime01 = System.nanoTime(); 
				
		ResultSet resultsSparql = generalization.RetrieveHierarchicalRelationsBySubject(element);
		
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRetrieval_GEN");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext()) 
			{
				globalStartTime01 = System.nanoTime();
				
	            // Get Result
	            QuerySolution qs = resultsSparql.next();
	
	            // Get Variable Names
	            Iterator<String> itVars = qs.varNames();
	
	            // Count
	            iCount++;
	
	            if(iCount == 0)
	            	this.noMoreTransformedQuery = true;
	            else
	            // Display Result
		            while (itVars.hasNext()) {
		                String szVar = itVars.next().toString();
		                String szVal = qs.get(szVar).toString();
		                
		                if(szVar.equals("p"))
		                	SupportingSemantic_Predicate = szVal;
		                if(szVar.equals("o"))
		                	{
		                		this.printNewQueries("GEN", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
		                		SupportingSemantic_Predicate = "";
		                	}
		            }
	        }
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_GEN");
	}
	
	public void retrieveSpecializationSemantics(String element) throws IOException
	{
		int iCount = 0;
		String SupportingSemantic_Predicate = "";
		starttime01 = System.nanoTime();
		
		ResultSet resultsSparql = specialization.RetrieveHierarchicalRelationsByObject(element);
		
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRetrieval_SPEC");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext()) 
			{
				globalStartTime01 = System.nanoTime();
				
	            QuerySolution qs = resultsSparql.next();
	            Iterator<String> itVars = qs.varNames();
	            iCount++;
	
	            if(iCount == 0)
	            	this.noMoreTransformedQuery = true;
	            else
		            while (itVars.hasNext()) {
		                String szVar = itVars.next().toString();
		                String szVal = qs.get(szVar).toString();
		                if(szVar.equals("p"))
		                	SupportingSemantic_Predicate = szVal;
		                if(szVar.equals("s"))
		                	{
		                		this.printNewQueries("SPEC", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
		                		SupportingSemantic_Predicate = "";
		                	}
		            }	
	        }
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_SPEC");
	}
	
	public void retrieveAfortioriSemantics(String element) throws IOException
	{
		int iCount = 0;
		String SupportingSemantic_Predicate = "";
		starttime01 = System.nanoTime(); 
		// A Fortiori - A maiore ad minus (from More to less)
		ResultSet resultsSparql = afortiori.RetrieveOrderedRelations_More2Less(element);
		
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRetrieval_AFORTML");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("s") && szVal != null && szVal.length() > 0) // More to Less - Subject of <?s standsBefore element>
						{
							this.printNewQueries("AFORT-ML", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
	                		SupportingSemantic_Predicate = "";
						}
						else if(szVar.equals("o") && szVal != null && szVal.length() > 0) // More to Less - Object of <element standsAfter ?o>
						{
							this.printNewQueries("AFORT-ML", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
	                		SupportingSemantic_Predicate = "";
						}
					}
			}
		
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_AFORTML");
		
		starttime01 = System.nanoTime(); 
		// A Fortiori - A maiore ad minus (from More to less)
		resultsSparql = afortiori.RetrieveOrderedRelations_Less2More(element);
		
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRetrieval_AFORTLM");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("s") && szVal != null && szVal.length() > 0) // Less to More - Subject of <?s standsAfter element>
						{
							this.printNewQueries("AFORT-LM", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
	                		SupportingSemantic_Predicate = "";
						}
						else if(szVar.equals("o") && szVal != null && szVal.length() > 0) // Less to More - Object of <element standsBefore ?o>
						{
							this.printNewQueries("AFORT-LM", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
	                		SupportingSemantic_Predicate = "";
						}
					}
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_AFORTLM");
	}
	
	public void retrieveInterpolationSemantics(String element) throws IOException
	{
		int iCount = 0;
		String SupportingSemantic_Predicate = "";
		String[] newElments_bands = new String[2];
		String supportingSemantics = "";
		
		
		// Interpolation - StandsBefore relations
		starttime01 = System.nanoTime();
		ResultSet resultsSparql = interpolation.RetrieveOrderedRelations_StandsBefore(element);		
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRetrieval_INTPSB");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
				{
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("lb") && szVal != null && szVal.length() > 0) // lower band - Subject of <?lb standsBefore element>
						{
							// set the lower band element
							newElments_bands[0] = szVal;
							supportingSemantics += "(<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">), ";
						}
						else if(szVar.equals("ub") && szVal != null && szVal.length() > 0) // upper band - Object of <element standsBefore ?o>
						{
							// set the upper band element
							newElments_bands[1] = szVal;
							supportingSemantics += "(<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">)";
						}
					}
					// newElments_bands[0]: lower band, newElments_bands[1]: upper band
					this.printNewQueries("INTP", this.elementToChange, newElments_bands[0], newElments_bands[1], supportingSemantics);
					SupportingSemantic_Predicate = "";
					supportingSemantics = "";
					newElments_bands = new String[2];
				}
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_INTPSB");
		
		// Interpolation - StandsAfter relations
		starttime01 = System.nanoTime();
		resultsSparql = interpolation.RetrieveOrderedRelations_StandsAfter(element);		
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRetrieval_INTPSA");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("lb") && szVal != null && szVal.length() > 0) // lower band - Object of <element StandsAfter ?lb>
						{
							// set the lower band element
							newElments_bands[0] = szVal;
							supportingSemantics += "(<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal  + ">), ";
						}
						if(szVar.equals("ub") && szVal != null && szVal.length() > 0) // upper band - Subject of <?s standsAfter element>
						{
							// set the upper band element
							newElments_bands[1] = szVal;
							supportingSemantics += "(<" + szVal + "> <" + SupportingSemantic_Predicate + "> <" + element + ">)";
						}
					}
					// newElments_bands[0]: lower band, newElments_bands[1]: upper band
					this.printNewQueries("INTP", this.elementToChange, newElments_bands[0], newElments_bands[1], supportingSemantics);
					SupportingSemantic_Predicate = "";
					supportingSemantics = "";
					newElments_bands = new String[2];					
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_INTPSA");
	}
	
	public void retrieveSimilaritySemantics(String element) throws IOException
	{
		int iCount = 0;
		String SupportingSemantic_Predicate = "";
		
		// Similarity - Hierarchical Similarity Relations
		starttime01 = System.nanoTime(); 
		ResultSet resultsSparql = similarity.RetrieveSimilarityRelations_Hierarchical(element);
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRerieval_SIMHR");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("s") && szVal != null && szVal.length() > 0) // Hierarchical - Subject of <?s SimialrTo element>
						{
							this.printNewQueries("SIM-HR", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
	                		SupportingSemantic_Predicate = "";
						}
						else if(szVar.equals("o") && szVal != null && szVal.length() > 0) // Hierarchical - Object of <element SimilarTo ?o>
						{
							this.printNewQueries("SIM-HR", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
	                		SupportingSemantic_Predicate = "";
						}
					}
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_SIMHR");
		
		// Similarity - Ordered Similarity Relations
		starttime01 = System.nanoTime(); 
		resultsSparql = similarity.RetrieveSimilarityRelations_Ordered(element);
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRerieval_SIMOR");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("s") && szVal != null && szVal.length() > 0) // Ordered - Subject of <?s SimilarTo element>
						{
							this.printNewQueries("SIM-OR", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
	                		SupportingSemantic_Predicate = "";
						}
						else if(szVar.equals("o") && szVal != null && szVal.length() > 0) // Ordered - Object of <element SimilarTo ?o>
						{
							this.printNewQueries("SIM-OR", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
	                		SupportingSemantic_Predicate = "";
						}
					}
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_SIMOR");
	}
	
	public void retrieveDissimilaritySemantics(String element) throws IOException
	{
		int iCount = 0;
		String SupportingSemantic_Predicate = "";
		
		// Dissimilarity - Hierarchical Dissimilarity Relations
		starttime01 = System.nanoTime(); 
		ResultSet resultsSparql = dissimilarity.RetrieveDissimilarityRelations_Hierarchical(element);
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRerieval_DISHR");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("s") && szVal != null && szVal.length() > 0) // Hierarchical - Subject of <?s DissimialrTo element>
						{
							this.printNewQueries("DIS-HR", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
	                		SupportingSemantic_Predicate = "";
						}
						else if(szVar.equals("o") && szVal != null && szVal.length() > 0) // Hierarchical - Object of <element DissimilarTo ?o>
						{
							this.printNewQueries("DIS-HR", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
	                		SupportingSemantic_Predicate = "";
						}
					}
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_DISHR");
		
		// Dissimilarity - Ordered Dissimilarity Relations
		starttime01 = System.nanoTime(); 
		resultsSparql = dissimilarity.RetrieveDissimilarityRelations_Ordered(element);
		starttime02 = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, starttime02, this.queryType + ";" + this.currentDepthOfPlausibility + ";SemanticsRerieval_DISOR");
		
		if(resultsSparql != null)
			while (resultsSparql.hasNext())
			{
				globalStartTime01 = System.nanoTime();
				
				QuerySolution qs = resultsSparql.next();
				Iterator<String> itVars = qs.varNames();
				iCount++;
				
				if(iCount == 0)
					this.noMoreTransformedQuery = true;
				else
					while(itVars.hasNext())
					{
						String szVar = itVars.next().toString();
						String szVal = qs.get(szVar).toString();
						if(szVar.equals("p"))
							SupportingSemantic_Predicate = szVal;
						if(szVar.equals("s") && szVal != null && szVal.length() > 0) // Ordered - Subject of <?s DissimilarTo element>
						{
							this.printNewQueries("DIS-OR", this.elementToChange, szVal, null, "<" + szVal  + "> <" + SupportingSemantic_Predicate + "> <" + element + ">");
	                		SupportingSemantic_Predicate = "";
						}
						else if(szVar.equals("o") && szVal != null && szVal.length() > 0) // Ordered - Object of <element DissimilarTo ?o>
						{
							this.printNewQueries("DIS-OR", this.elementToChange, szVal, null, "<" + element + "> <" + SupportingSemantic_Predicate + "> <" + szVal + ">");
	                		SupportingSemantic_Predicate = "";
						}
					}
			}
		endtime = System.nanoTime();
		myMethods.StoreExecutionTime(starttime01, endtime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGenerationAndRunning_DISOR");
	}
	
	public void printInitalQuery(String initialQuery) throws IOException
	{
		//commenting prints
//		Files.write(Paths.get(settings.Settings.OUTPUT_TRANSFORMED_QUERIES_ADDRESS), initialQuery.getBytes(), StandardOpenOption.APPEND);
//		local_OUTPUT_TRANSFORMED_QUERIES_ADDRESS += initialQuery;
		transformedQueries.add(initialQuery);
	}
	
	// newElement02 is only used for Interpolation, other than that it is null
	// prints new query and runs the query as well
	public void printNewQueries(String plausiblePattern, String elementToChange, String newElement01, String newElement02, String supportingSemantic) throws IOException 
	{
		if(newElement01.contains("http://semmed.org/resource/Disease") 
				|| (newElement02 != null && newElement02.contains("http://semmed.org/resource/Disease")))
			return;
		
		// transformed queries include: level of plausibility; new query;{plausible pattern used,(supporting semantic)}
		// level of plausibility: the number of plausible patterns that have been used so far;
		String newQueryRecord = "";
		String newQueryResult = "";
		
		if(queryType.equals("Select"))
		{
			if(newElement02 == null) // All plausible patterns except Interpolation
			{
				if(elementToChange.equals("Subject"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";<" + newElement01 + "> <" + this.originalQueryPredicate + "> " + this.originalQueryObject + ";"
										+ this.currentPatternsSemantics + "{" + plausiblePattern + ",(" + supportingSemantic + ")};\n";					
				}
				else if (elementToChange.equals("Object"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" + this.originalQuerySubject + " <" + this.originalQueryPredicate + "> <" + newElement01 + ">;"
										+ this.currentPatternsSemantics + "{" + plausiblePattern + ",(" + supportingSemantic + ")};\n";					
				}
				else if (elementToChange.equals("Predicate"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";<" + this.originalQuerySubject + "> <" + newElement01 + "> <" + this.originalQueryObject + ">;"
										+ this.currentPatternsSemantics + "{" + plausiblePattern + ",(" + supportingSemantic + ")};\n";
				}
			} 
			else // Interpolation, with two statements to be replaced by the original query
			{
				if(elementToChange.equals("Subject"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" +  
										"<" + newElement01 + "> <" + this.originalQueryPredicate + "> " + this.originalQueryObject + ". " +
										"<" + newElement02 + "> <" + this.originalQueryPredicate + "> " + this.originalQueryObject + ";" +
										this.currentPatternsSemantics + "{" + plausiblePattern + "," + supportingSemantic + "};\n";
				}
				else if (elementToChange.equals("Object"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" +
										this.originalQuerySubject + " <" + this.originalQueryPredicate + "> <" + newElement01 + ">. " +
										this.originalQuerySubject + " <" + this.originalQueryPredicate + "> <" + newElement02 + ">;" +
										this.currentPatternsSemantics + "{" + plausiblePattern + "," + supportingSemantic + "};\n";					
				}
				else if (elementToChange.equals("Predicate"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" + 
										"<" + this.originalQuerySubject + "> <" + newElement01 + "> <" + this.originalQueryObject + ">. " +
										"<" + this.originalQuerySubject + "> <" + newElement02 + "> <" + this.originalQueryObject + ">;" +
										this.currentPatternsSemantics + "{" + plausiblePattern + "," + supportingSemantic + "};\n";
				}
			}	
		}
		else if (queryType.equals("Ask"))
		{
			if(newElement02 == null) // All plausible patterns except Interpolation
			{
				if(elementToChange.equals("Subject"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";<" + newElement01 + "> <" + this.originalQueryPredicate + "> <" + this.originalQueryObject + ">;"
										+ this.currentPatternsSemantics + "{" + plausiblePattern + ",(" + supportingSemantic + ")};\n";
				}
				else if (elementToChange.equals("Object"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";<" + this.originalQuerySubject + "> <" + this.originalQueryPredicate + "> <" + newElement01 + ">;"
										+ this.currentPatternsSemantics + "{" + plausiblePattern + ",(" + supportingSemantic + ")};\n";
				}
				else if (elementToChange.equals("Predicate"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";<" + this.originalQuerySubject + "> <" + newElement01 + "> <" + this.originalQueryObject + ">;"
										+ this.currentPatternsSemantics + "{" + plausiblePattern + ",(" + supportingSemantic + ")};\n";
				}
			} 
			else // Interpolation, with two statements to be replaced by the original query
			{
				if(elementToChange.equals("Subject"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" +  
										"<" + newElement01 + "> <" + this.originalQueryPredicate + "> <" + this.originalQueryObject + ">. " +
										"<" + newElement02 + "> <" + this.originalQueryPredicate + "> <" + this.originalQueryObject + ">;" +
										this.currentPatternsSemantics + "{" + plausiblePattern + "," + supportingSemantic + "};\n";
				}
				else if (elementToChange.equals("Object"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" +
										"<" + this.originalQuerySubject + "> <" + this.originalQueryPredicate + "> <" + newElement01 + ">. " +
										"<" + this.originalQuerySubject + "> <" + this.originalQueryPredicate + "> <" + newElement02 + ">;" +
										this.currentPatternsSemantics + "{" + plausiblePattern + "," + supportingSemantic + "};\n";
				}
				else if (elementToChange.equals("Predicate"))
				{
					newQueryRecord += this.currentDepthOfPlausibility + ";" + 
										"<" + this.originalQuerySubject + "> <" + newElement01 + "> <" + this.originalQueryObject + ">. " +
										"<" + this.originalQuerySubject + "> <" + newElement02 + "> <" + this.originalQueryObject + ">;" +
										this.currentPatternsSemantics + "{" + plausiblePattern + "," + supportingSemantic + "};\n";
				}
				
				//System.out.println(newQueryRecord);
			}
		}
		globalEndTime = System.nanoTime();
		myMethods.StoreExecutionTime(globalStartTime01, globalEndTime, this.queryType + ";" + this.currentDepthOfPlausibility + ";PlausibleQueryGeneration(QR)");
		
		if(!newQueryRetrievedBefore(newQueryRecord))
			try {
				// Write the query and store it in the list
				//commenting prints
//				Files.write(Paths.get(settings.Settings.OUTPUT_TRANSFORMED_QUERIES_ADDRESS), newQueryRecord.getBytes(), StandardOpenOption.APPEND);
//				local_OUTPUT_TRANSFORMED_QUERIES_ADDRESS += newQueryRecord;
				transformedQueries.add(newQueryRecord);
				
				// Run the query and print the results
				//newQueryResult = 
				this.RunQueryAndPrintResults(this.currentDepthOfPlausibility, newQueryRecord.split(";")[1], newQueryRecord.split(";")[2]);
				//Files.write(Paths.get(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ADDRESS), newQueryResult.getBytes(), StandardOpenOption.APPEND);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private boolean newQueryRetrievedBefore(String newQueryRecord)
	{
		newQueryRecord = newQueryRecord.split(";")[1]; // check only the new query (second element of the new record) to not be duplicate. 
		try {
//			if(FileUtils.readFileToString(new File(settings.Settings.OUTPUT_TRANSFORMED_QUERIES_ADDRESS)).contains(newQueryRecord))
//				return true;
			for(String queries : transformedQueries)
				if(queries.contains(newQueryRecord))
					return true;				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String[] retrieveQueryElements(String query)
	{
		String[] elements;
		
		elements = query.replace(">.", "").replace("<", "").replace(">", "").split(" ");
		
//		int itemno = 0;
		
//		for (String item : elements)
//		{
//			System.out.println("item " + itemno++ + " : " + item);
//		}
		return elements;
	}
	
	public void transformQuery() throws IOException
	{
		plAnswerTime01 = System.nanoTime();
		
		curIndex = 0;
		String[] curQueryRecordArr;
		String curQuery = "";
		String[] curQueryElementsArr;
		this.printInitalQuery(this.currentDepthOfPlausibility + ";" + this.originalQuerySubject + " <" + this.originalQueryPredicate + "> <" + this.originalQueryObject + ">; ;\n");
		while(this.currentDepthOfPlausibility <= this.maxDepthOfPlausibility && !this.noMoreTransformedQuery && curIndex < transformedQueries.size())
		{			
			curQueryRecordArr = transformedQueries.get(curIndex++).split(";");
			currentDepthOfPlausibility = Integer.parseInt(curQueryRecordArr[0]) + 1;
			if(currentDepthOfPlausibility > this.maxDepthOfPlausibility)
				break;
			curQuery = curQueryRecordArr[1]; 
			curQueryElementsArr = retrieveQueryElements(curQuery); //
			this.currentPatternsSemantics = curQueryRecordArr[2]; // contain the patterns and their supporting semantics
			
			//elementToChange_Value = curQueryElementsArr[0].contains("?") ? curQueryElementsArr[2] : curQueryElementsArr[0];
			if(elementToChange.equals("Subject"))
				elementToChange_Value = curQueryElementsArr[0];
			else if(elementToChange.equals("Object"))
				elementToChange_Value = curQueryElementsArr[2];
			
			myMethods.StoreCurrentTime(this.queryType + ";" + this.currentDepthOfPlausibility + ";CurrentTime");
			
//			if(elementToChange.equals("Object"))
			if(!this.currentPatternsSemantics.contains("INTP")) //based on what we agreed with Dr. Abidi, after an INTP there should not be any further plausible. since both wings should be rewritten simultaneously 
			{ 	
				if(selectedPlausiblePatterns.contains("Afort"))
				{
					this.retrieveAfortioriSemantics(elementToChange_Value);
				}
				if(selectedPlausiblePatterns.contains("Gen"))
				{
					this.retrieveGeneralizationSemantics(elementToChange_Value);
				}
				if(selectedPlausiblePatterns.contains("Spec"))
				{
					this.retrieveSpecializationSemantics(elementToChange_Value);
				}				
				if(selectedPlausiblePatterns.contains("Sim"))
				{
					this.retrieveSimilaritySemantics(elementToChange_Value);
				}
				if(selectedPlausiblePatterns.contains("Dis"))
				{
					this.retrieveDissimilaritySemantics(elementToChange_Value);
				}
				if(selectedPlausiblePatterns.contains("Intp"))
				{
					this.retrieveInterpolationSemantics(elementToChange_Value);
				}
			}
		}
		FinalPrintsToFiles();
	}
	
	public void RunQueryAndPrintResults(int currentDepthOfPlausibility, String statementsBlock, String supportingSemantics) throws IOException
	{
		String resultSelect = "";
		Boolean resultAsk = false;
		String seqOfPlausiblePatterns = this.RetrieveSequenceOfPlausiblePatterns(supportingSemantics);
		String newQuery = this.BuildInitialQuery(statementsBlock);
		String queryResult = "";
		String queryResult_onlyAnswers = "";
		MyMethods myMethods = new MyMethods();
		try
		{
			if(queryType.equals("Select"))
			{
				//ResultSet resultsSparql = myMethods.ExecuteQueryOverSparqlEndpoint(txtQuery.getText(), txt_SparqlEndpointAddress.getText());
				ResultSet resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(newQuery, this.originalSparqlEndpoint);
				if(resultsSparql != null)
					{
						queryResult_onlyAnswers = myMethods.ResultSetToStringWithoutVariable(resultsSparql);
						//queryResult = myMethods.ResultSetToString(resultsSparql);
					}
				else 
					queryResult = "No Result.";
			}
			else if (queryType.equals("Ask"))
			{
				//System.out.println("newQuery is: " + newQuery);
				resultAsk = myMethods.ExecuteAskQueryOverSparqlEndpoint(newQuery, this.originalSparqlEndpoint);
				if(resultAsk != null)
				{
					queryResult_onlyAnswers = resultAsk.toString();
				}
				else 
					queryResult = "No Result.";
			}
		}
		catch(Exception ex)
		{
			queryResult = "Exception in QueryTransformation-RunQueryAndPrintResults>: \n" + ex.getMessage();
		}
		
		plAnswerTime02 = System.nanoTime();
		
		String newQueryResult = "Depth of Plausibility: " + this.currentDepthOfPlausibility + 
									"\nSequence of Plausible Patterns: {" + seqOfPlausiblePatterns.substring(0, seqOfPlausiblePatterns.length() - 2) + "}" +
									"\nNew Query: \n" + statementsBlock +
									"\n\nSupporting Semantics: \n" + supportingSemantics + 
									"\n\nResults: \n" + ((queryResult_onlyAnswers.length() > 2) ? queryResult_onlyAnswers : "none") +
									"\n\nTotal rewriting time to get to the answer (micros): " + ((plAnswerTime02 - plAnswerTime01) / 1000) + 
									"\n\n";
		//commenting prints - results
		Files.write(Paths.get(settings.Settings.OUTPUT_NEW_QUERIES_RESULTS_ADDRESS), newQueryResult.getBytes(), StandardOpenOption.APPEND);
		
		//String OnlyAnswer = this.currentDepthOfPlausibility + ";" + 
	}
	
	private String BuildInitialQuery(String statementsBlock)
	{
		String newQuery = "";
		String subject = this.originalQuerySubject;
		String predicate = this.originalQueryPredicate;
		String object = this.originalQueryObject;
		
		if(!subject.contains("?") && !object.contains("?") && !predicate.contains("?"))
		{
			// it is an ASK question		
			SparqlQuery sparqlQuery = new SparqlQuery();
			//sparqlQuery.Statements = new String[]{"<" + subject + "> <" + predicate + "> <" + object + ">"};
			sparqlQuery.Statements = new String[]{statementsBlock};
			
			newQuery = sparqlQuery.BuildSimpleAskQeuryString();
			
		}
		else
		{
			// it is a SELECT question				
			String vars = "";
			if(this.originalQuerySubject.contains("?"))
				vars += this.originalQuerySubject;
			if(predicate.contains("?"))
				vars += this.originalQueryPredicate;
			else if(object.contains("?"))
				vars += this.originalQueryObject;
			
			if(vars.length() == 0)
				vars += "*";
			
			SparqlQuery sparqlQuery = new SparqlQuery();
			sparqlQuery.Variables = vars.split(",");
			sparqlQuery.Statements = new String[]{statementsBlock};
			
			newQuery = sparqlQuery.BuildSimpleSelectQueryString();
		}		
		return newQuery;
	}
	
	private String RetrieveSequenceOfPlausiblePatterns(String supportingSemantics)
	{
		String seqOfPlausiblePatterns = "";
		String supportingSemanticsArr[] = supportingSemantics.replace(" ", "").replace("}", "").split("\\{");
		for(String semantic : supportingSemanticsArr)
			if(semantic.split(",")[0].length() > 1)
				seqOfPlausiblePatterns += semantic.split(",")[0] + ", ";
		
		return seqOfPlausiblePatterns;
	}
	
	private void FinalPrintsToFiles() throws IOException {
		// TODO Auto-generated method stub
//		Files.write(Paths.get(settings.Settings.OUTPUT_TRANSFORMED_QUERIES_ADDRESS), local_OUTPUT_TRANSFORMED_QUERIES_ADDRESS.getBytes(), StandardOpenOption.APPEND);
	}
	
//	public void StoreExecutionTime(long startTime, long endTime, String description) throws IOException
//	{
//		long duration = (endTime - startTime) / 1000000;  //divided by 1000000 to get milliseconds.
//		Files.write(Paths.get(settings.Settings.OUTPUT_EXECUTION_TIME_ADDRESS), 
//					(this.queryType + ";" + this.currentDepthOfPlausibility + ";" + description + ";" + String.valueOf(duration) + ";\n").getBytes(), StandardOpenOption.APPEND);
//	}
	
}