package PlausiblePatterns;

import javax.swing.JOptionPane;

import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDFS;

import ontology.MyMethods;
import settings.Settings;

public class Specialization {
	String namespaces = Settings.NAMESPACES;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public ResultSet RetrieveHierarchicalRelations(String SPARQLEndoint)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?s ?p ?o WHERE { \n" + 
							"?s ?p ?o.\n" +
							"?p rdf:type plowl:OrderedProperty}";
		try
		{
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, SPARQLEndoint);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)
		{
			System.out.println("Exception in SPEC->RetrieveHierarchicalRelations: \n" + ex.getMessage());
		}
		
		return null;
	}
		
	public ResultSet RetrieveHierarchicalRelationsByObject(String Object)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?s ?p WHERE { \n" + 
							"?s ?p <?o>.\n" +
							"?p rdf:type plowl:HierarchicalProperty}";
		Query = Query.replace("?o", Object);
		
		try
		{
			resultsSparql = myMethods.ExecuteSelectQueryOverSparqlEndpoint(Query, settings.Settings.SPARQL_ENDPOINT);
			if(resultsSparql != null)
				return resultsSparql;
			else 
				System.out.println("No Result.");
		}
		catch(Exception ex)
		{
			System.out.println("Exception in SPEC->RetrieveHierarchicalRelationsByObject: \n" + ex.getMessage());
		}
		
		return null;
	}
	
	/* This section returns the sub-class of a resource which is a subject or object	 */
	
	
	/** Returns the statements which something is the SubClass of "resource" */
	public StmtIterator Get_SubClassOf_Subject_AsStatement(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector(null, RDFS.subClassOf , resource.asResource());
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	
	/** Returns the statements which something is the SubClass of "resource" (as an RDFNode) */
	public StmtIterator Get_SubClassOf_Object_AsStatement(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(null, RDFS.subClassOf, rdfNode);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which are supper class of a resource*/
	public ResIterator Get_SubClassOf_Subject_AsResource(Model model, Resource resource)
	{
		ResIterator resIter = model.listSubjectsWithProperty(RDFS.subClassOf, resource.asResource());
		
		return resIter;
	}
	
	/** Returns the RDFNodes which are supper class of a resource*/
	public ResIterator Get_SubClassOf_Object_AsReource(Model model, RDFNode rdfNode)
	{
		ResIterator resIter = model.listSubjectsWithProperty(RDFS.subClassOf, rdfNode);
		
		return resIter;
	}
	
		
	/* This section returns the sub-class of a resource which is a property	 */
	
	
	/** Returns the statements which "resource" is the SubProperty of something*/
	public StmtIterator Get_SubPropertyOf_Predicate_AsStatements(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector(null, RDFS.subPropertyOf , resource.asResource());
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	
	/** Returns the statements which "resource" (as an RDFNode) is the SubProperty of something*/
	public StmtIterator Get_SubPropertyOf_Predicate_AsStatements(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(null, RDFS.subPropertyOf, rdfNode);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which are supper property of a resource*/
	public ResIterator Get_SubPropertyOf_Predicate_AsRDFNodes(Model model, Resource resource)
	{
		ResIterator resIter = model.listSubjectsWithProperty(RDFS.subPropertyOf, resource.asResource());
		
		return resIter;
	}
	
	/** Returns the RDFNodes which are supper property of a resource*/
	public ResIterator GetSupperPropertyAsRDFNodes(Model model, RDFNode rdfNode)
	{
		ResIterator resIter = model.listSubjectsWithProperty(RDFS.subPropertyOf, rdfNode.asResource());
		
		return resIter;
	}
	
	
}
