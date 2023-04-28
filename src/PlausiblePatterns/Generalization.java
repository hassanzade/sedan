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
import org.apache.jena.vocabulary.VCARD;

import ontology.MyMethods;
import settings.Settings;

public class Generalization {
	String namespaces = Settings.NAMESPACES;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generalization generalization = new Generalization();
		//generalization.ge

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
			System.out.println("Exception in Gen->RetrieveHierarchicalRelations: \n" + ex.getMessage());
		}
		
		return null;
	}
		
	public ResultSet RetrieveHierarchicalRelationsBySubject(String Subject)
	{
		MyMethods myMethods = new MyMethods();
		ResultSet resultsSparql;
		String Query = namespaces + 
						"SELECT ?p ?o WHERE { \n" + 
							"<?s> ?p ?o.\n" +
							"?p rdf:type plowl:HierarchicalProperty}";
		Query = Query.replace("?s", Subject);
		
//		System.out.println("Query is: \n" + Query);
		
		//JOptionPane.showMessageDialog(null, Query);
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
			System.out.println("Exception in Gen->RetrieveHierarchicalRelationsBySubject: \n" + ex.getMessage());
		}
		
		return null;
	}
	/* This section returns the supper class of a resource which is a subject or object	 */
	
	/** Returns the statements which "resource" is the SubClass of something*/
	public StmtIterator Get_SupperClassOf_ASubject_AsStatements(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector(resource, RDFS.subClassOf , (RDFNode) null);
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	
	/** Returns the statements which "resource" (as an RDFNode) is the SubClass of something*/
	public StmtIterator Get_SupperClassOf_AnObject_AsStatements(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(rdfNode.asResource(), RDFS.subClassOf, (RDFNode) null);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which are supper class of a resource*/
	public NodeIterator Get_SupperClassOf_ASubject_AsRDFNodes(Model model, Resource resource)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(resource, RDFS.subClassOf);
		
		return nodeIter;
	}
	
	/** Returns the RDFNodes which are supper class of an RDFNode*/
	public NodeIterator Get_SupperClassOf_AnObject_AsRDFNodes(Model model, RDFNode rdfNode)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(rdfNode.asResource(), RDFS.subClassOf);
		
		return nodeIter;
	}
	
		
	/* This section returns the supper class of a resource which is a property (predicate)	 */
	
	
	/** Returns the statements which "resource" is the SubProperty of something*/
	public StmtIterator Get_SupperPropertyOf_APredicate_AsStatements(Model model, Resource resource)
	{
		SimpleSelector selector = new SimpleSelector(resource, RDFS.subPropertyOf , (RDFNode) null);
		StmtIterator stmtIter = model.listStatements(selector);
		
		return stmtIter;
	}
	
	
	/** Returns the statements which "resource" (as an RDFNode) is the SubProperty of something*/
	public StmtIterator Get_SupperPropertyOf_APreidacte_AsStatements(Model model, RDFNode rdfNode)
	{
		if(rdfNode instanceof Resource)
		{					
			SimpleSelector selector = new SimpleSelector(rdfNode.asResource(), RDFS.subPropertyOf, (RDFNode) null);
			StmtIterator stmtIter = model.listStatements(selector);			
			 
			return stmtIter;
			
		}else // obejct is a literal
		{
			return null;
		}
	}
	
	/** Returns the RDFNodes which are supper property of a resource*/
	public NodeIterator Get_SupperPropertyOf_APredicate_AsRDFNodes(Model model, Resource resource)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(resource, RDFS.subPropertyOf);
		
		return nodeIter;
	}
	
	/** Returns the RDFNodes which are supper property of an RDFNode */
	public NodeIterator Get_SupperPropertyOf_APredicate_AsRDFNodes(Model model, RDFNode rdfNode)
	{
		NodeIterator nodeIter = model.listObjectsOfProperty(rdfNode.asResource(), RDFS.subPropertyOf);
		
		return nodeIter;
	}
	
	

//	/** !!! Not recommended to use - string value !!! Returns the statements which "resource" (as a string) is the SubClass of something*/
//	public StmtIterator GetSupperClassAsStatements(Model model, final String strResource)
//	{		
//		StmtIterator stmtIter = model.listStatements(new SimpleSelector(null, RDFS.subClassOf, (RDFNode)null) {
//			public boolean selects(Statement s){
//				return s.getString().contains(strResource);
//			}
//		});
//		
//		return stmtIter;
//	}
//
//	/** !!! Not recommended to use - string value !!! Returns the RDFNodes which are supper class of "resource" (as a string)*/
//	public NodeIterator GetSupperClassAsNodes(Model model, final String strResource)
//	{		
//		NodeIterator nodeIter = model.listObject (new SimpleSelector(null, RDFS.subClassOf, (RDFNode)null) {
//			public boolean selects(Statement s){
//				return s.getString().contains(strResource);
//			}
//		});
//		
//		return nodeIter;
//	}
	


}
