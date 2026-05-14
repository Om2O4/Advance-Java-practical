<jsp:useBean id="d" class="sample.Donation" scope="session"/> 
<jsp:setProperty name="d" property="*"/> 
<h2>Donation Details</h2> 
Donor Name: <jsp:getProperty name="d" property="name"/><br><br> 
Food Item: <jsp:getProperty name="d" property="food"/><br><br> 
 
Quantity: <jsp:getProperty name="d" property="qty"/><br><br> 
<a href="success.jsp">Confirm Donation</a> 