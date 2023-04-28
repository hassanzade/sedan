package PlausiblePatterns;

import ontology.MyMethods;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;

import settings.Settings;

public class Similarity {
	
	String namespaces = Settings.NAMESPACES;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	/* This section returns the sub-class of a resource which is a subject or object	 */

	/** Returns the statements which something is the SubClass of "resource" */
	public StmtIterator Get_SimilarClassOf_Subject_AsStatement(Model model, Resource resource)
	{
		// SimpleSelector selector = new SimpleSelector(null, OWL.sameAs, resource.asResource());
		SimpleSelector selector = new SimpleSelector(null, OWL.equivalentClass , resource.asResource());
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	
	/** Returns the statements which something is the SubClass of "resource" (as an RDFNode) */
	public StmtIterator Get_SimilarClassOf_Object_AsStatement(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(null, OWL.equivalentClass, rdfNode);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which are supper class of a resource*/
	public ResIterator Get_SimilarClassOf_Subject_AsResource(Model model, Resource resource)
	{
		ResIterator resIter = model.listSubjectsWithProperty(OWL.equivalentClass, resource.asResource());
		
		return resIter;
	}
	
	/** Returns the RDFNodes which are supper class of a resource*/
	public ResIterator Get_SimilarClassOf_Object_AsReource(Model model, RDFNode rdfNode)
	{
		ResIterator resIter = model.listSubjectsWithProperty(OWL.equivalentClass, rdfNode);
		
		return resIter;
	}
	
		
	/* This section returns the similar-class of a resource which is a property	 */
	
	
	/** Returns the statements which "resource" is the Equivalent Property of something*/
	public StmtIterator Get_SimilarPropertyOf_Predicate_AsStatements(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector(null, OWL.equivalentProperty , resource.asResource());
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	
	/** Returns the statements which "resource" (as an RDFNode) is the Similar Property of something*/
	public StmtIterator Get_SimilarPropertyOf_Predicate_AsStatements(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(null, OWL.equivalentProperty, rdfNode);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which are similar property of a resource*/
	public ResIterator Get_SimilarPropertyOf_Predicate_AsResource(Model model, Resource resource)
	{
		ResIterator resIter = model.listSubjectsWithProperty(OWL.equivalentProperty, resource.asResource());
		
		return resIter;
	}
	
	/** Returns the RDFNodes which are similar property of a resource*/
	public ResIterator Get_SimilarPropertyOf_Predicate_AsResource(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{
			ResIterator resIter = model.listSubjectsWithProperty(OWL.equivalentProperty, rdfNode.asResource());
			
			return resIter;
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	
	// Similarity - Hierarchical Similarity Relations
	// based on hierarchical-based relationships, retrieves the concepts/objects/resources that are similar to the
	// concepts/objects/resources in the question.
	public ResultSet RetrieveSimilarityRelations_Hierarchical(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?s ?o WHERE { \n" + 
							"{ <?element> ?p ?o .\n" +
							"  ?p rdf:type plowl:HierarchicalSimProperty.} \n" +
							"UNION\n" +
							"{ ?s ?p <?element>.\n" +
							"  ?p rdf:type plowl:HierarchicalSimProperty.} }";
		Query = Query.replace("?element", element);
		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in Similairty->RetrieveSimilarityRelations_Hierarchical: \n" + ex.getMessage());
		}		
		return null;
	}
	
	// Similarity - Ordered Similarity Relations
	// based on hierarchical-based relationships, retrieves the concepts/objects/resources that are similar to the
	// concepts/objects/resources in the question.
	public ResultSet RetrieveSimilarityRelations_Ordered(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?s ?o WHERE { \n" + 
							"{ <?element> ?p ?o .\n" +
							"  ?p rdf:type plowl:OrderedSimProperty.} \n" +
							"UNION\n" +
							"{ ?s ?p <?element>.\n" +
							"  ?p rdf:type plowl:OrderedSimProperty.} }";
		Query = Query.replace("?element", element);
		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in Sim->RetrieveSimilarityRelations_Ordered: \n" + ex.getMessage());
		}		
		return null;
	}
}
