# SeDan: a Semantic Analytics Framework using Plausible Reasoning

SeDan is a Semantic-based Data Analytics (SeDan) framework  that implements our proposed plausible reasoning methods ([Ref. 01](https://ieeexplore.ieee.org/abstract/document/8495925), [Ref. 02](https://biodatamining.biomedcentral.com/articles/10.1186/s13040-017-0123-y)) to provide semantic analytics by inferring new knowledge from RDF knowledge graphs.

### SeDan’s Architecture
Figure below illustrates the architecture of SeDan, where the main component is the plausible reasoner that comprises, the pattern matching function and the query rewriting algorithm. The pattern matching function implements the PL-OWL and OWL QL constructs to select and apply plausible patterns for plausible reasoning. The query rewriting algorithm transforms a given query into a plausible query for plausible reasoning. In SeDan the knowledge graph is the source for both answering and rewriting the queries, where it combines both the domain knowledge (i.e., expressed in the form of (DL) ontologies) and the domain data (i.e., represented in RDF). The knowledge graph includes the material (i.e., assertional data) to answer a SPARQL query, while it provides the required semantics (i.e., retrieved from ontological constructs or semantic data) for query rewriting. 

![Figure 1](https://github.com/hassanzade/sedan/tree/main/images/Figure1.jpg?raw=true)

### How SeDa works

You can see the working of SeDan in the figure below. It demonstrates the reasoning approach of the SeDan framework when a new query arrives. In the first step (step 1), the user’s query will be evaluated using deductive reasoning (i.e., with no plausible reasoning) applied to the knowledge graph aiming to find an answer (step 2). If a solution to the query is possible through deductive reasoning, the retrieved knowledge will be reported (step 2.1). In case the query cannot be satisfied by deductive reasoning (step 2.2), then the plausible reasoner is invoked (i.e., in line with Definition 6). At this point, the plausible reasoner retrieves the applicable semantics from the knowledge graph to reformulate (step 3) the given query into plausible queries. The subsequent plausible queries along with the supporting semantics will be provided to the user for approval (step 3.1). The user can filter out queries that are incorrect or clinically irrelevant. The approved plausible queries are pursued by applying plausible reasoning to the knowledge graph. Knowledge statements that satisfy the plausible query are retrieved and presented to the user as a plausible answer(s) (step 4.1); in case no answer is retrieved the failed query will be considered for further rewriting by the plausible reasoner (step 4.2). The rewriting of the queries continues until there is no new plausible query to be generated or the reasoning process is halted by the user. 

![Figure 2](https://github.com/hassanzade/sedan/tree/main/images/Figure2.jpg?raw=true)

