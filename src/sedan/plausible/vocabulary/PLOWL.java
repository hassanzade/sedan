package sedan.plausible.vocabulary;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.OWL.Init;

public class PLOWL extends org.apache.jena.vocabulary.OWL{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/** The namespace of the vocabulary as a string. */
	public static final String NS= "http://niche.cs.dal.ca/2017/06/plowl#";
	
	/** The namespace of the vocabulary as a string */
    public static String getURI() {return NS;}
    
    /** The namespace of the vocabulary as a resource */
    public static final Resource NAMESPACE = ResourceFactory.createResource( NS );
    
    // Vocabulary properties
    ///////////////////////////

    
    public static final Property hasContext = Init.hasContext();
    
    public static final Property standsBefore = Init.standsBefore();
    
    public static final Property standsAfter = Init.standsAfter();
    
    public static final Property hasSubject = Init.hasSubject();
    
    public static final Property hasObject = Init.hasObject();
    
    public static final Property hasPredicate = Init.hasPredicate();	
    
    public static final Property inferredThroughPattern = Init.inferredThroughPattern();
    
    public static final Property inTheContextOf = Init.inTheContextOf();
    
    public static final Property isDefaultContext = Init.isDefaultContext();

    
//    public static final Property hasMore = Init.hasMore();
//    
//    public static final Property hasLess = Init.hasLess();
//    
//    public static final Property hasNext = Init.hasNext();
//    
//    public static final Property hasPrevious = Init.hasPrevious();	
//    
//    public static final Property hasMoreThan = Init.hasMoreThan();
//    
//    public static final Property hasLessThan = Init.hasLessThan();
    

    
    // Vocabulary classes
    ///////////////////////////

    public static final Resource Context = Init.Context();
    
    public static final Resource OrderedProperty = Init.OrderedProperty();
    
    public static final Resource PlausiblePattern = Init.PlausiblePattern();
    
    public static final Resource PlausibleAnswer = Init.PlausibleAnswer();
    
    
    // Vocabulary individuals
    ///////////////////////////
    
    /** OWL constants are used during Jena initialization.
     * <p>
     * If that initialization is triggered by touching the OWL class,
     * then the constants are null.
     * <p>
     * So for these cases, call this helper class: Init.function()   
     */
    public static class Init {
        // JENA-PLOWL
        // Version that calculate the constant when called.
        public static Property hasContext()             { return property( "hasContext" ); }
        public static Property standsBefore()             { return property( "standsBefore" ); }
        public static Property standsAfter()             { return property( "standsAfter" ); }
        public static Property hasSubject()             { return property( "hasSubject" ); }   
        public static Property hasObject()             { return property( "hasObject" ); } 
        public static Property hasPredicate()             { return property( "hasPredicate" ); } 
        public static Property inferredThroughPattern()             { return property( "inferredThroughPattern" ); } 
        public static Property inTheContextOf()             { return property( "inTheContextOf" ); } 
        public static Property isDefaultContext()             { return property( "isDefaultContext" ); } 
        
//        public static Property hasMore()             { return property( "hasMore" ); }
//        public static Property hasLess()             { return property( "hasLess" ); }
//        public static Property hasNext()             { return property( "hasNext" ); }
//        public static Property hasPrevious()             { return property( "hasPrevious" ); }
//        public static Property hasMoreThan()             { return property( "hasMoreThan" ); }
//        public static Property hasLessThan()             { return property( "hasLessThan" ); }

        public static Resource Context()                      { return resource( "Context" ); }
        public static Resource OrderedProperty()             { return resource( "OrderedProperty" ); }
        public static Resource PlausiblePattern()             { return resource( "PlausiblePattern" ); }
        public static Resource PlausibleAnswer()             { return resource( "PlausibleAnswer" ); 
        
       }
    }
    
}
