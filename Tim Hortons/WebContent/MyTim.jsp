 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <script src="https://code.highcharts.com/highcharts.js"></script>
   <style>
  h2
  {
  	text-align:center;
 	color:#512DA8;
  }
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
    background-color: #f1f1f1;
    
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}
body
{
	 background-color:#E8EAF6;
}
/* Change the link color on hover */
li a:hover {
    background-color:#00B0FF;
    color: #EF6C00;
}
</style>
</head>
<body>
<h2>Sales and Expenses</h2>

<ul>
  <li><a href="MyExpense.jsp">Maintenance of machine expenses</a></li>
  <li><a href="Profit.jsp">Profit</a></li>
  <li><a href="Pie.jsp">Salary of Emplyoees</a></li>
  <li><a href="MyTim.jsp">coffee Sales</a></li>
</ul>
<br/>
<br/>
</head>
<body>
<form action="TimView" method="post">
			
<select name="Customers:">
    <option>CustomerName</option>
    <option>Tim Hortons</option>
     <option>Star Bucks</option>
     <option>Central Perk</option>  
     <option>SubWay</option>
      <option>Mc Donals</option>
      <option>Qwitches</option>
    <c:forEach items="${data.getNames()}" var="d" >
      <option value="${d}">${d}</option>
    </c:forEach>
</select>

<select name="Coffee types:">
    <option>Coffee Types</option>
     <option>Café Mocha</option>
      <option>Dream Latte Caramel</option>
        <option>Dream Latte Chocolate</option>
          <option>Dream Latte Vanilla</option>
    <c:forEach items="${data1.getNames()}" var="d" >
      <option value="${d}">${d}</option>
    </c:forEach>
</select>

<select name="Coffee size:">
    <option>Coffee size</option>
       <option>Large</option>
        <option>Medium</option>
         <option>Small</option>
         <option>X-Large</option>
    <c:forEach items="${data2.getNames()}" var="d" >
      <option value="${d}">${d}</option>
    </c:forEach>
</select>

<input type="Submit" value="Submit"  name="Submit"/>
		</form>
			<div id="container"></div>
		<script type="text/javascript">
			var sales = ${info};
			
		    Highcharts.chart('container', {
		        title: {
		            text: 'coffee Sales'
		        },
		         xAxis: {
		         title: {
            text: 'Days'
            },
        categories: ['Mon', 'Tue', 'Wed', 'Thurs', 'Fri', 'Sat',
            'Sun']
    },
		        yAxis: {
		            title: {
		                text: 'cups'
		            }
		        },
		        
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle'
		        },
		        series: [{
		            name: 'Sales',
		            data: sales
		        }]
		    });
		</script>

</body>	
</html>