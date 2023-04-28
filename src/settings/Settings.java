package settings;

public class Settings {
	public static String NAMESPACES = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"PREFIX plowl: <http://niche.cs.dal.ca/2017/06/plowl/>\n" +
			"PREFIX semp: <http://semmed.org/property/>\n" +
			"PREFIX semr: <http://semmed.org/resource/>\n";
	
	
	//public static String SPARQL_ENDPOINT =  "http://localhost:7200/repositories/SeDan";
	public static String SPARQL_ENDPOINT_ADDRESSONLY = "http://134.190.182.249:7200";
	public static String SPARQL_ENDPOINT =  "http://134.190.182.249:7200/repositories/SeDan";
	public static String SEDAN_ENDPOINT = "http://134.190.182.249:7200/repositories/SeDan";
	public static String OUTPUT_TRANSFORMED_QUERIES_ADDRESS = ".//OtherFiles//result//queries.txt";
	public static String OUTPUT_NEW_QUERIES_RESULTS_ADDRESS = ".//OtherFiles//result//results.txt";
	public static String OUTPUT_NEW_QUERIES_RESULTS_ANSWERSONLY_ADDRESS = ".//OtherFiles//result//results_AnswersOnly.txt";
	public static String OUTPUT_EXECUTION_TIME_ADDRESS = ".//OtherFiles//result//execution_time.txt";
	public static String OUTPUT_EXECUTION_TIME_ADDRESS_ALL = ".//OtherFiles//execution_time_all.txt";
	public static String OUTPUT_LAST_QUERY = ".//OtherFiles//last_query.txt";
	public static String LAST_QUERIED_SUBJECT = "";
	public static String LAST_QUERIED_OBJECT = "";
	public static String LAST_QUERIED_PREDICATE = "";
	public static int MAX_DEPTH_OF_PLAUSIBILITY = 2;
	
	public String SPARQLEndPoint()
	{
		return SPARQL_ENDPOINT;
	}
	
	public void SetSPARQLEndPoint(String endpoint)
	{
		this.SPARQL_ENDPOINT = endpoint;
	}
	

}
