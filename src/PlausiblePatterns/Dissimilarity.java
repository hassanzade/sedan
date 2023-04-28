package PlausiblePatterns;

import ontology.MyMethods;

import org.apache.jena.query.ResultSet;

import settings.Settings;

public class Dissimilarity {
	
	String namespaces = Settings.NAMESPACES;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	// Dissimilarity - Hierarchical Dissimilarity Relations
	// based on hierarchical-based relationships, retrieves the concepts/objects/resources that are dissimilar to the
	// concepts/objects/resources in the question.
	public ResultSet RetrieveDissimilarityRelations_Hierarchical(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?s ?o WHERE { \n" + 
							"{ <?element> ?p ?o .\n" +
							"  ?p rdf:type plowl:HierarchicalDissimProperty.} \n" +
							"UNION\n" +
							"{ ?s ?p <?element>.\n" +
							"  ?p rdf:type plowl:HierarchicalDissimProperty.} }";
		Query = Query.replace("?element", element);
		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in Dis->RetrieveDissimilarityRelations_Hierarchical: \n" + ex.getMessage());
		}		
		return null;
	}
	
	// Dissimilarity - Ordered Disimilarity Relations
	// based on hierarchical-based relationships, retrieves the concepts/objects/resources that are dissimilar to the
	// concepts/objects/resources in the question.
	public ResultSet RetrieveDissimilarityRelations_Ordered(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?s ?o WHERE { \n" + 
							"{ <?element> ?p ?o .\n" +
							"  ?p rdf:type plowl:OrderedDissimProperty.} \n" +
							"UNION\n" +
							"{ ?s ?p <?element>.\n" +
							"  ?p rdf:type plowl:OrderedDissimProperty.} }";
		Query = Query.replace("?element", element);
		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in Dis->RetrieveDissimilarityRelations_Hierarchical: \n" + ex.getMessage());
		}		
		return null;
	}
}
