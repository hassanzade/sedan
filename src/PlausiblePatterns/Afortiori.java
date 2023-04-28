package PlausiblePatterns;

import ontology.MyMethods;

import org.apache.jena.query.ResultSet;

import settings.Settings;

public class Afortiori {
	
	String namespaces = Settings.NAMESPACES;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	}
	
	// A Fortiori - A maiore ad minus (from More to less)
	// based on a ordered base relationships, retrieves the concepts/objects/resources that are relatively more/bigger/later/etc. than the
	// concepts/objects/resources in the question.
	public ResultSet RetrieveOrderedRelations_More2Less(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
				"SELECT ?p ?s ?o WHERE { \n" + 
					"{ ?s ?p <?element>.\n" +
					"  ?p rdf:type plowl:StandsBefore.} \n" +
					"UNION\n" +
					"{ <?element> ?p ?o.\n" +
					"  ?p rdf:type plowl:StandsAfter.} }";
		Query = Query.replace("?element", element);
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex) {
			System.out.println(ex.getStackTrace().toString() + " - Exception in Afort->RetrieveOrderedRelations_More2Less: \n" + ex.getMessage());
			System.out.println("Afort-Query is: \n" + Query);
		}		
		return null;
	}
	
	// A Fortiori - A minori ad maius (from less to more)
	// based on a ordered base relationships, retrieves the concepts/objects/resources that are relatively less/smaller/earlier/etc. than the
	// concepts/objects/resources in the question.
	public ResultSet RetrieveOrderedRelations_Less2More(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?s ?o WHERE { \n" + 
							"{ <?element> ?p ?o .\n" +
							"  ?p rdf:type plowl:StandsBefore.} \n" +
							"UNION\n" +
							"{ ?s ?p <?element>.\n" +
							"  ?p rdf:type plowl:StandsAfter.} }";
		Query = Query.replace("?element", element);		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in Afort->RetrieveOrderedRelations_Less2More: \n" + ex.getMessage());
			System.out.println("Afort-L2M-Query is: \n" + Query);
		}		
		return null;
	}

}
