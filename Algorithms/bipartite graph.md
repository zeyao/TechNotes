# bipartite graph and hungarian algorithm

- What is a bipartite graph
  
  Two different type of node, each type of node can only match with a different type
  
  This is a bipartite graph
  
  
  <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hungarian%20algorithm%201.jpg" style="height:500px" />
  
- How this bipartite graph is related with Flight Linkage problem

  - Inbound flight linked with outbound flight can could only linked with outbound flight

  
  
- How to flight the maxiumn of linkage : hungarian algorithm

 - Approach : find Augmenting Path
 - How to find : if a node has a unmatched path, then match it with a different node, else, go for alternative path to find Augmenting Path
 - After we find the Augmenting path, then the max match size + 1
 
 - Step 1:
 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hungarian%20algorithm%202.jpg" style="height:500px" />
 
 - Step 2:
 <img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/hungarian%20algorithm%203.jpg" style="height:500px" />
 
 
- How the customer rule is working:

 - We are building a matrix of inbound of outbound flight, init linkage status is "unknown":
 - if we are apply a rule for SQ, only link flight with turn around time -> less than 90 minuts
 - For fight that is SQ and turn around time is more than 90 minuts -> "mark as forbidden"

