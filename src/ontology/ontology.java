package ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import org.apache.xerces.impl.dtd.models.DFAContentModel;


public class ontology {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ontology ont = new ontology();
		ont.SimpleOntologyQuery();
		//ont.testOntologyModelReader();
	}
		
	public void SimpleOntologyQuery()
	{
		String filename = "./DataFiles/University0_0.owl";
		File file = new File(filename);
		if(file.exists() && !file.isDirectory()) { 
		    System.out.println("file exists.");
		}
		// Model model = ModelFactory.createDefaultModel();
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		try
		{
			file = new File(filename);
			FileReader reader = new FileReader(file);
			// FileInputStream reader = new FileInputStream(file);
			// model.read(reader,null,"OWL");
			model.read(new FileInputStream("./OntologyFiles/univ.owl"),null);
			String sparqlQuery = "select ?Subject ?Predicate ?Object where {?Subject ?Predicate  ?Object} LIMIT 2";
			// String sparqlQuery = "select ?Subject ?Predicate ?Object where {?Subject  <http://www.w3.org/2002/07/owl#someValuesFrom> ?Object} LIMIT 10";
			Query query = QueryFactory.create(sparqlQuery);
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			ResultSet results = qe.execSelect();
			
			// RDF format output
			//ResultSetFormatter.out(System.out, results);
			//System.out.println("end of RDF format output");
			
			// XML format output
			// ResultSetFormatter.outputAsXML(System.out, results);
			
			// Save to String format output
//			MyOutputStream myOutput = new MyOutputStream();
//			ResultSetFormatter.out(myOutput, results);
//			String sparqlResults = myOutput.getString();
//			System.out.println(sparqlResults);
			
			// RDF format
			QueryExecution qeRDF = QueryExecutionFactory.create(query, model);
			ResultSet resultsRDF = qeRDF.execSelect();
			List vars = resultsRDF.getResultVars();
			
//			for (int i = 0; i < vars.size(); i++) {
//				System.out.println(vars.get(i));
//			}
			
			while(resultsRDF.hasNext())
			{
				QuerySolution qs = resultsRDF.nextSolution();
				System.out.println("--------- solution ---------");

				for(int i = 0; i < vars.size(); i++)
				{
					String var = vars.get(i).toString();
					RDFNode node = qs.get(var);
					// node.asNode()
					System.out.println(var + "\t" + node.toString());
				}
			}
//			System.out.println("\n--------------------------------------  end of RDF format output\n\n");
			
			// RDF format w Prefix
//			QueryExecution qeRDFPrefix = QueryExecutionFactory.create(query, model);			
//			ResultSet resultsRDFPrefix = qeRDFPrefix.execSelect();
//			List varsRDFPrefix = resultsRDFPrefix.getResultVars();
//			PrefixMapping pm = query.getPrefixMapping();
//			
//			while(resultsRDFPrefix.hasNext())
//			{
//				QuerySolution qs = resultsRDFPrefix.nextSolution();
//				System.out.println("--------- solution ---------");
//
//				for(int i = 0; i < varsRDFPrefix.size(); i++)
//				{
//					String var = varsRDFPrefix.get(i).toString();
//					RDFNode node = qs.get(var);
//					String text = "";
//					if(node.isURIResource())
//					{
//						System.out.println("*** isURIResource: " + pm.shortForm(node.asNode().getNameSpace()));
//						text = pm.shortForm(node.asNode().getURI());						
//						
//					}else {
//						text = node.toString();
//					}
//					System.out.println(var + "\t" + text);
//				}
//			}
						

			
		}catch(Exception exc)
		{
			exc.printStackTrace();
			System.out.println("exception");
		}
	}	
	

	
	public void testOntologyModelReader()
	{

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("./OntologyFiles/univ.owl");
		if (in == null) {
		    throw new IllegalArgumentException(
		                                 "File: not found");
		}
		model.read(in, null);
		//model.write(System.out);
		
		
//		System.out.println("Statements Results");
//		
		StmtIterator stmtIter = model.listStatements(new SimpleSelector
				(null, RDFS.label, (RDFNode)null) {
			public boolean selects(Statement s){
				return s.getObject().toString().contains("phone");				
			}
		});
		
		
		//SimpleSelector 
		
		
//		SimpleSelector selector = new SimpleSelector(null, RDFS.label , (RDFNode) null);
//		StmtIterator stmtIter = model.listStatements(selector);
				
		while(stmtIter.hasNext())
		{
			//System.out.println("\n");
			Statement stmt = stmtIter.nextStatement();
			
//			System.out.println(stmt.toString());
//			System.out.println(stmt.getSubject());
//			System.out.println(stmt.getPredicate());
			System.out.println(stmt.getObject());
		}
		
	}

}


class MyOutputStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	StringBuffer buf;
	
	public MyOutputStream()
	{
		buf = new StringBuffer();
	}
	
	public void write1(int character) throws IOException {
		buf.append((char) character);
	}
	
	public String getString()
	{
		return buf.toString();
	}
}