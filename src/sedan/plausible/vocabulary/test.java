package sedan.plausible.vocabulary;
/*package vocabulary;

//Compiled from OWL.java (version 1.8 : 52.0, super bit)
public class test {

// Field descriptor #102 Ljava/lang/String;
public static final java.lang.String NS = "http://www.w3.org/2002/07/owl#";

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource NAMESPACE;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource FULL_LANG;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource DL_LANG;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource LITE_LANG;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property maxCardinality;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property versionInfo;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property equivalentClass;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property distinctMembers;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property oneOf;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property sameAs;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property incompatibleWith;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property minCardinality;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property complementOf;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property onProperty;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property equivalentProperty;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property inverseOf;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property backwardCompatibleWith;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property differentFrom;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property priorVersion;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property imports;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property allValuesFrom;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property unionOf;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property hasValue;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property someValuesFrom;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property disjointWith;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property cardinality;

// Field descriptor #110 Lorg/apache/jena/rdf/model/Property;
public static final org.apache.jena.rdf.model.Property intersectionOf;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource Thing;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource DataRange;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource Ontology;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource DeprecatedClass;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource AllDifferent;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource DatatypeProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource SymmetricProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource TransitiveProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource DeprecatedProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource AnnotationProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource Restriction;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource Class;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource OntologyProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource ObjectProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource FunctionalProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource InverseFunctionalProperty;

// Field descriptor #105 Lorg/apache/jena/rdf/model/Resource;
public static final org.apache.jena.rdf.model.Resource Nothing;

// Method descriptor #151 ()V
// Stack: 1, Locals: 1
public OWL();
 0  aload_0 [this]
 1  invokespecial java.lang.Object() [1]
 4  return
   Line numbers:
     [pc: 0, line: 30]
     [pc: 4, line: 153]
   Local variable table:
     [pc: 0, pc: 5] local: this index: 0 type: org.apache.jena.vocabulary.OWL

// Method descriptor #158 (Ljava/lang/String;)Lorg/apache/jena/rdf/model/Resource;
// Stack: 2, Locals: 1
protected static final org.apache.jena.rdf.model.Resource resource(java.lang.String uri);
  0  new java.lang.StringBuilder [2]
  3  dup
  4  invokespecial java.lang.StringBuilder() [3]
  7  ldc <String "http://www.w3.org/2002/07/owl#"> [5]
  9  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [6]
 12  aload_0 [uri]
 13  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [6]
 16  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [7]
 19  invokestatic org.apache.jena.rdf.model.ResourceFactory.createResource(java.lang.String) : org.apache.jena.rdf.model.Resource [8]
 22  areturn
   Line numbers:
     [pc: 0, line: 34]
   Local variable table:
     [pc: 0, pc: 23] local: uri index: 0 type: java.lang.String

// Method descriptor #161 (Ljava/lang/String;)Lorg/apache/jena/rdf/model/Property;
// Stack: 2, Locals: 1
protected static final org.apache.jena.rdf.model.Property property(java.lang.String uri);
 0  ldc <String "http://www.w3.org/2002/07/owl#"> [5]
 2  aload_0 [uri]
 3  invokestatic org.apache.jena.rdf.model.ResourceFactory.createProperty(java.lang.String, java.lang.String) : org.apache.jena.rdf.model.Property [9]
 6  areturn
   Line numbers:
     [pc: 0, line: 37]
   Local variable table:
     [pc: 0, pc: 7] local: uri index: 0 type: java.lang.String

// Method descriptor #163 ()Ljava/lang/String;
// Stack: 1, Locals: 0
public static java.lang.String getURI();
 0  ldc <String "http://www.w3.org/2002/07/owl#"> [5]
 2  areturn
   Line numbers:
     [pc: 0, line: 43]

// Method descriptor #151 ()V
// Stack: 1, Locals: 0
static {};
   0  ldc <String "http://www.w3.org/2002/07/owl#"> [5]
   2  invokestatic org.apache.jena.rdf.model.ResourceFactory.createResource(java.lang.String) : org.apache.jena.rdf.model.Resource [8]
   5  putstatic org.apache.jena.vocabulary.OWL.NAMESPACE : org.apache.jena.rdf.model.Resource [10]
   8  invokestatic org.apache.jena.vocabulary.OWL.getURI() : java.lang.String [11]
  11  invokestatic org.apache.jena.rdf.model.ResourceFactory.createResource(java.lang.String) : org.apache.jena.rdf.model.Resource [8]
  14  putstatic org.apache.jena.vocabulary.OWL.FULL_LANG : org.apache.jena.rdf.model.Resource [12]
  17  ldc <String "http://www.w3.org/TR/owl-features/#term_OWLDL"> [13]
  19  invokestatic org.apache.jena.rdf.model.ResourceFactory.createResource(java.lang.String) : org.apache.jena.rdf.model.Resource [8]
  22  putstatic org.apache.jena.vocabulary.OWL.DL_LANG : org.apache.jena.rdf.model.Resource [14]
  25  ldc <String "http://www.w3.org/TR/owl-features/#term_OWLLite"> [15]
  27  invokestatic org.apache.jena.rdf.model.ResourceFactory.createResource(java.lang.String) : org.apache.jena.rdf.model.Resource [8]
  30  putstatic org.apache.jena.vocabulary.OWL.LITE_LANG : org.apache.jena.rdf.model.Resource [16]
  33  invokestatic org.apache.jena.vocabulary.OWL$Init.maxCardinality() : org.apache.jena.rdf.model.Property [17]
  36  putstatic org.apache.jena.vocabulary.OWL.maxCardinality : org.apache.jena.rdf.model.Property [18]
  39  invokestatic org.apache.jena.vocabulary.OWL$Init.versionInfo() : org.apache.jena.rdf.model.Property [19]
  42  putstatic org.apache.jena.vocabulary.OWL.versionInfo : org.apache.jena.rdf.model.Property [20]
  45  invokestatic org.apache.jena.vocabulary.OWL$Init.equivalentClass() : org.apache.jena.rdf.model.Property [21]
  48  putstatic org.apache.jena.vocabulary.OWL.equivalentClass : org.apache.jena.rdf.model.Property [22]
  51  invokestatic org.apache.jena.vocabulary.OWL$Init.distinctMembers() : org.apache.jena.rdf.model.Property [23]
  54  putstatic org.apache.jena.vocabulary.OWL.distinctMembers : org.apache.jena.rdf.model.Property [24]
  57  invokestatic org.apache.jena.vocabulary.OWL$Init.oneOf() : org.apache.jena.rdf.model.Property [25]
  60  putstatic org.apache.jena.vocabulary.OWL.oneOf : org.apache.jena.rdf.model.Property [26]
  63  invokestatic org.apache.jena.vocabulary.OWL$Init.sameAs() : org.apache.jena.rdf.model.Property [27]
  66  putstatic org.apache.jena.vocabulary.OWL.sameAs : org.apache.jena.rdf.model.Property [28]
  69  invokestatic org.apache.jena.vocabulary.OWL$Init.incompatibleWith() : org.apache.jena.rdf.model.Property [29]
  72  putstatic org.apache.jena.vocabulary.OWL.incompatibleWith : org.apache.jena.rdf.model.Property [30]
  75  invokestatic org.apache.jena.vocabulary.OWL$Init.minCardinality() : org.apache.jena.rdf.model.Property [31]
  78  putstatic org.apache.jena.vocabulary.OWL.minCardinality : org.apache.jena.rdf.model.Property [32]
  81  invokestatic org.apache.jena.vocabulary.OWL$Init.complementOf() : org.apache.jena.rdf.model.Property [33]
  84  putstatic org.apache.jena.vocabulary.OWL.complementOf : org.apache.jena.rdf.model.Property [34]
  87  invokestatic org.apache.jena.vocabulary.OWL$Init.onProperty() : org.apache.jena.rdf.model.Property [35]
  90  putstatic org.apache.jena.vocabulary.OWL.onProperty : org.apache.jena.rdf.model.Property [36]
  93  invokestatic org.apache.jena.vocabulary.OWL$Init.equivalentProperty() : org.apache.jena.rdf.model.Property [37]
  96  putstatic org.apache.jena.vocabulary.OWL.equivalentProperty : org.apache.jena.rdf.model.Property [38]
  99  invokestatic org.apache.jena.vocabulary.OWL$Init.inverseOf() : org.apache.jena.rdf.model.Property [39]
 102  putstatic org.apache.jena.vocabulary.OWL.inverseOf : org.apache.jena.rdf.model.Property [40]
 105  invokestatic org.apache.jena.vocabulary.OWL$Init.backwardCompatibleWith() : org.apache.jena.rdf.model.Property [41]
 108  putstatic org.apache.jena.vocabulary.OWL.backwardCompatibleWith : org.apache.jena.rdf.model.Property [42]
 111  invokestatic org.apache.jena.vocabulary.OWL$Init.differentFrom() : org.apache.jena.rdf.model.Property [43]
 114  putstatic org.apache.jena.vocabulary.OWL.differentFrom : org.apache.jena.rdf.model.Property [44]
 117  invokestatic org.apache.jena.vocabulary.OWL$Init.priorVersion() : org.apache.jena.rdf.model.Property [45]
 120  putstatic org.apache.jena.vocabulary.OWL.priorVersion : org.apache.jena.rdf.model.Property [46]
 123  invokestatic org.apache.jena.vocabulary.OWL$Init.imports() : org.apache.jena.rdf.model.Property [47]
 126  putstatic org.apache.jena.vocabulary.OWL.imports : org.apache.jena.rdf.model.Property [48]
 129  invokestatic org.apache.jena.vocabulary.OWL$Init.allValuesFrom() : org.apache.jena.rdf.model.Property [49]
 132  putstatic org.apache.jena.vocabulary.OWL.allValuesFrom : org.apache.jena.rdf.model.Property [50]
 135  invokestatic org.apache.jena.vocabulary.OWL$Init.unionOf() : org.apache.jena.rdf.model.Property [51]
 138  putstatic org.apache.jena.vocabulary.OWL.unionOf : org.apache.jena.rdf.model.Property [52]
 141  invokestatic org.apache.jena.vocabulary.OWL$Init.hasValue() : org.apache.jena.rdf.model.Property [53]
 144  putstatic org.apache.jena.vocabulary.OWL.hasValue : org.apache.jena.rdf.model.Property [54]
 147  invokestatic org.apache.jena.vocabulary.OWL$Init.someValuesFrom() : org.apache.jena.rdf.model.Property [55]
 150  putstatic org.apache.jena.vocabulary.OWL.someValuesFrom : org.apache.jena.rdf.model.Property [56]
 153  invokestatic org.apache.jena.vocabulary.OWL$Init.disjointWith() : org.apache.jena.rdf.model.Property [57]
 156  putstatic org.apache.jena.vocabulary.OWL.disjointWith : org.apache.jena.rdf.model.Property [58]
 159  invokestatic org.apache.jena.vocabulary.OWL$Init.cardinality() : org.apache.jena.rdf.model.Property [59]
 162  putstatic org.apache.jena.vocabulary.OWL.cardinality : org.apache.jena.rdf.model.Property [60]
 165  invokestatic org.apache.jena.vocabulary.OWL$Init.intersectionOf() : org.apache.jena.rdf.model.Property [61]
 168  putstatic org.apache.jena.vocabulary.OWL.intersectionOf : org.apache.jena.rdf.model.Property [62]
 171  invokestatic org.apache.jena.vocabulary.OWL$Init.Thing() : org.apache.jena.rdf.model.Resource [63]
 174  putstatic org.apache.jena.vocabulary.OWL.Thing : org.apache.jena.rdf.model.Resource [64]
 177  invokestatic org.apache.jena.vocabulary.OWL$Init.DataRange() : org.apache.jena.rdf.model.Resource [65]
 180  putstatic org.apache.jena.vocabulary.OWL.DataRange : org.apache.jena.rdf.model.Resource [66]
 183  invokestatic org.apache.jena.vocabulary.OWL$Init.Ontology() : org.apache.jena.rdf.model.Resource [67]
 186  putstatic org.apache.jena.vocabulary.OWL.Ontology : org.apache.jena.rdf.model.Resource [68]
 189  invokestatic org.apache.jena.vocabulary.OWL$Init.DeprecatedClass() : org.apache.jena.rdf.model.Resource [69]
 192  putstatic org.apache.jena.vocabulary.OWL.DeprecatedClass : org.apache.jena.rdf.model.Resource [70]
 195  invokestatic org.apache.jena.vocabulary.OWL$Init.AllDifferent() : org.apache.jena.rdf.model.Resource [71]
 198  putstatic org.apache.jena.vocabulary.OWL.AllDifferent : org.apache.jena.rdf.model.Resource [72]
 201  invokestatic org.apache.jena.vocabulary.OWL$Init.DatatypeProperty() : org.apache.jena.rdf.model.Resource [73]
 204  putstatic org.apache.jena.vocabulary.OWL.DatatypeProperty : org.apache.jena.rdf.model.Resource [74]
 207  invokestatic org.apache.jena.vocabulary.OWL$Init.SymmetricProperty() : org.apache.jena.rdf.model.Resource [75]
 210  putstatic org.apache.jena.vocabulary.OWL.SymmetricProperty : org.apache.jena.rdf.model.Resource [76]
 213  invokestatic org.apache.jena.vocabulary.OWL$Init.TransitiveProperty() : org.apache.jena.rdf.model.Resource [77]
 216  putstatic org.apache.jena.vocabulary.OWL.TransitiveProperty : org.apache.jena.rdf.model.Resource [78]
 219  invokestatic org.apache.jena.vocabulary.OWL$Init.DeprecatedProperty() : org.apache.jena.rdf.model.Resource [79]
 222  putstatic org.apache.jena.vocabulary.OWL.DeprecatedProperty : org.apache.jena.rdf.model.Resource [80]
 225  invokestatic org.apache.jena.vocabulary.OWL$Init.AnnotationProperty() : org.apache.jena.rdf.model.Resource [81]
 228  putstatic org.apache.jena.vocabulary.OWL.AnnotationProperty : org.apache.jena.rdf.model.Resource [82]
 231  invokestatic org.apache.jena.vocabulary.OWL$Init.Restriction() : org.apache.jena.rdf.model.Resource [83]
 234  putstatic org.apache.jena.vocabulary.OWL.Restriction : org.apache.jena.rdf.model.Resource [84]
 237  invokestatic org.apache.jena.vocabulary.OWL$Init.Class() : org.apache.jena.rdf.model.Resource [85]
 240  putstatic org.apache.jena.vocabulary.OWL.Class : org.apache.jena.rdf.model.Resource [86]
 243  invokestatic org.apache.jena.vocabulary.OWL$Init.OntologyProperty() : org.apache.jena.rdf.model.Resource [87]
 246  putstatic org.apache.jena.vocabulary.OWL.OntologyProperty : org.apache.jena.rdf.model.Resource [88]
 249  invokestatic org.apache.jena.vocabulary.OWL$Init.ObjectProperty() : org.apache.jena.rdf.model.Resource [89]
 252  putstatic org.apache.jena.vocabulary.OWL.ObjectProperty : org.apache.jena.rdf.model.Resource [90]
 255  invokestatic org.apache.jena.vocabulary.OWL$Init.FunctionalProperty() : org.apache.jena.rdf.model.Resource [91]
 258  putstatic org.apache.jena.vocabulary.OWL.FunctionalProperty : org.apache.jena.rdf.model.Resource [92]
 261  invokestatic org.apache.jena.vocabulary.OWL$Init.InverseFunctionalProperty() : org.apache.jena.rdf.model.Resource [93]
 264  putstatic org.apache.jena.vocabulary.OWL.InverseFunctionalProperty : org.apache.jena.rdf.model.Resource [94]
 267  invokestatic org.apache.jena.vocabulary.OWL$Init.Nothing() : org.apache.jena.rdf.model.Resource [95]
 270  putstatic org.apache.jena.vocabulary.OWL.Nothing : org.apache.jena.rdf.model.Resource [96]
 273  return
   Line numbers:
     [pc: 0, line: 46]
     [pc: 8, line: 49]
     [pc: 17, line: 52]
     [pc: 25, line: 55]
     [pc: 33, line: 60]
     [pc: 39, line: 62]
     [pc: 45, line: 64]
     [pc: 51, line: 66]
     [pc: 57, line: 68]
     [pc: 63, line: 70]
     [pc: 69, line: 72]
     [pc: 75, line: 74]
     [pc: 81, line: 76]
     [pc: 87, line: 78]
     [pc: 93, line: 80]
     [pc: 99, line: 82]
     [pc: 105, line: 84]
     [pc: 111, line: 86]
     [pc: 117, line: 88]
     [pc: 123, line: 90]
     [pc: 129, line: 92]
     [pc: 135, line: 94]
     [pc: 141, line: 96]
     [pc: 147, line: 98]
     [pc: 153, line: 100]
     [pc: 159, line: 102]
     [pc: 165, line: 104]
     [pc: 171, line: 109]
     [pc: 177, line: 111]
     [pc: 183, line: 113]
     [pc: 189, line: 115]
     [pc: 195, line: 117]
     [pc: 201, line: 119]
     [pc: 207, line: 121]
     [pc: 213, line: 123]
     [pc: 219, line: 125]
     [pc: 225, line: 127]
     [pc: 231, line: 129]
     [pc: 237, line: 131]
     [pc: 243, line: 133]
     [pc: 249, line: 135]
     [pc: 255, line: 137]
     [pc: 261, line: 139]
     [pc: 267, line: 141]

Inner classes:
 [inner class info: #98 org/apache/jena/vocabulary/OWL$Init, outer class info: #4 org/apache/jena/vocabulary/OWL
  inner name: #99 Init, accessflags: 9 public static]
}
*/