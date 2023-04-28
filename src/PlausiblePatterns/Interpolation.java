package PlausiblePatterns;

import ontology.MyMethods;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Bag;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.PLOWL;
import org.apache.jena.vocabulary.RDFS;

import settings.Settings;

//import sedan.plausible.vocabulary.*;

public class Interpolation {
	
	String namespaces = Settings.NAMESPACES;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* This section returns the StandsAfter class of a resource which is a subject or object	 */

	
	/** Returns the statements which "resource" stands after something*/
	public StmtIterator Get_StandsAfter_ASubject_AsStatements(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector( resource, PLOWL.standsAfter, (RDFNode) null);
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	/** Returns the statements which "resource" (as an RDFNode) stands after something*/
	public StmtIterator Get_StandsAfter_AnObject_AsStatements(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(rdfNode.asResource(), PLOWL.standsAfter, (RDFNode) null);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which stand after a resource*/
	public NodeIterator Get_StandsAfter_ASubject_AsRDFNodes(Model model, Resource resource)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(resource, PLOWL.standsAfter);
		
		return nodeIter;
	}
	
	/** Returns the RDFNodes which stand after a RDFNode*/
	public NodeIterator Get_StandsAfter_AnObject_AsRDFNodes(Model model, RDFNode rdfNode)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(rdfNode.asResource(), PLOWL.standsAfter);
		
		return nodeIter;
	}
	
	
	/* This section returns the StandsBefore class of a resource which is a subject or object	 */

	
	/** Returns the statements which "resource" stands before something*/
	public StmtIterator Get_StandsBefore_ASubject_AsStatements(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector( resource, PLOWL.standsBefore, (RDFNode) null);
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	/** Returns the statements which "resource" (as an RDFNode) stands before something*/
	public StmtIterator Get_StandsBefore_AnObject_AsStatements(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(rdfNode.asResource(), PLOWL.standsBefore, (RDFNode) null);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which stand before a resource*/
	public NodeIterator Get_StandsBefore_ASubject_AsRDFNodes(Model model, Resource resource)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(resource, PLOWL.standsBefore);
		
		return nodeIter;
	}
	
	/** Returns the RDFNodes which stand before a RDFNode*/
	public NodeIterator Get_StandsBefore_AnObject_AsRDFNodes(Model model, RDFNode rdfNode)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(rdfNode.asResource(), PLOWL.standsBefore);
		
		return nodeIter;
	}
	
	
	/* This section returns the StandsAfter and StnadsBefore classes 
	 * as a Bag
	 * of a resource which is a subject or object	 */

	
	/** Returns the statements which "resource" stands after something*/
	public StmtIterator Get_StandsAfterAndBefore_ASubject_AsStatements(Model model, Resource resource)
	{
		Model modelCopy = model;
		Bag interploationBag = modelCopy.createBag();
		
		SimpleSelector selector = new SimpleSelector( resource, PLOWL.standsAfter, (RDFNode) null);
		StmtIterator stmtIter = model.listStatements(selector);
		
		while(stmtIter.hasNext())
		{
			interploationBag.add(stmtIter.nextStatement().getSubject());
		}
		
		return stmtIter;
	}
	
	
	// Interpolation - StandsBefore relations
	// based on a ordered base relationships, retrieves two concepts/objects/resources that are relatively 
	// less/smaller/earlier/etc. and more/bigger/later/etc. of the concept/object/resource in the question.
	// lb: lower band, ub: upper band, <: standsBefore
	// lb < ?element < ub 
	public ResultSet RetrieveOrderedRelations_StandsBefore(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?lb ?ub WHERE { \n" + 
							"?lb ?p <?element>.\n" +
							"<?element> ?p ?ub.\n" +
							"?p rdf:type plowl:StandsBefore.}";
		Query = Query.replace("?element", element);
		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in Intp->RetrieveOrderedRelations_StandsBefore: \n" + ex.getMessage());
		}		
		return null;
	}
	
	// Interpolation - StandsAfter relations
	// based on a ordered base relationships, retrieves two concepts/objects/resources that are relatively 
	// less/smaller/earlier/etc. and more/bigger/later/etc. of the concept/object/resource in the question.
	// lb: lower band, ub: upper band, >: standsAfter
	// ub > ?element > lb 
	public ResultSet RetrieveOrderedRelations_StandsAfter(String element)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?lb ?ub WHERE { \n" + 
							"?ub ?p <?element>.\n" +
							"<?element> ?p ?lb.\n" +
							"?p rdf:type plowl:StandsAfter.}";
		Query = Query.replace("?element", element);
		
		try {
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)	{
			System.out.println(ex.getStackTrace().toString() + " - Exception in INTP->RetrieveOrderedRelations_StandsAfter: \n" + ex.getMessage());
		}		
		return null;
	}
	
}
