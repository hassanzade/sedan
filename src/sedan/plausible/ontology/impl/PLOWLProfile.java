package sedan.plausible.ontology.impl;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import sedan.plausible.vocabulary.*;

public class PLOWLProfile extends org.apache.jena.ontology.impl.OWLProfile{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    // Constants
    //////////////////////////////////


    // Instance variables
    //////////////////////////////////


    // Constructors
    //////////////////////////////////


    // External signature methods
    //////////////////////////////////
	
	@Override
    public String   NAMESPACE() {					return PLOWL.getURI(); }

	
    public Resource CONTEXT() {                     return PLOWL.Context; }
    
    public Resource ORDEREDPROPERTY() {             return PLOWL.OrderedProperty; }
    
    public Resource PLAUSIBLEPATTERN() {            return PLOWL.PlausiblePattern; }
    
    public Resource PLAUSIBLEANSWER() {             return PLOWL.PlausibleAnswer; }
    
    
    public Property HASCONTEXT() {         			return PLOWL.hasContext; }
    
    public Property STANDSBEFORE() {            	return PLOWL.standsBefore; }
    
    public Property STANDSAFTER() {               	return PLOWL.standsAfter; }
    
    public Property HASSUBJECT() {               	return PLOWL.hasSubject; }
    
    public Property HASOBJECT() {                   return PLOWL.hasObject; }
    
    public Property HASPREDICATE() {             	return PLOWL.hasPredicate; }
    
    public Property INFERREDTHROUGHPATTERN() {      return PLOWL.inferredThroughPattern; }
    
    public Property INTHECONTEXTOF() {              return PLOWL.inTheContextOf; }
    
//    public Property HASMORE() {          			return PLOWL.hasMore; }    
//    public Property HASLESS() {                     return PLOWL.hasLess; }    
//    public Property HASNEXT() {              		return PLOWL.hasNext; }    
//    public Property HASPREVIOUS() {            		return PLOWL.hasPrevious; }    
//    public Property HASMORETHAN() {                 return PLOWL.hasMoreThan; }    
//    public Property HASLESSTHAN() {             	return PLOWL.hasLessThan; }
    

    
    
}
