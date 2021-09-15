<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList,models.entities.Customer" %>
<%@ page import="models.entities.Account" %>
<%@ page import="models.entities.NullEntity" %>
<%@ page import="models.dao.DAOFactory" %>
<%@ page import="models.dao.CustomerDAO" %>
<%@ page import="models.dao.AccountDAO" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Heebo:wght@500&display=swap" rel="stylesheet">
	<title>Welcome</title>
    <style>
        body{
			font-family: 'Heebo', sans-serif;
            background-color: #FFF2E0;
        }
        tr, td, th{
            border: 1px solid;
			padding: 10px;
        }
        strong{
            color: red;
        }
        label{
            margin-right: 10px;
        }
		a{
			text-decoration: none;
			color: red;
			font-size:18px;
		}
		button{
		    top: 10px;
			right: 10px;
			float: right;
			width: 10em;
			height: 2.5em;
			position: fixed;
		}
		fieldset, form, table{
			padding: 15px;
			text-align: center;
			width: 600px;
			margin:auto;
		}
    </style>
</head>
<body>
	<button><a href="${pageContext.request.contextPath}/login.do">Logout</a></button>
	<form action="${pageContext.request.contextPath}/home" method="POST">
		<fieldset>
			<legend><strong>Operations on Customers</strong></legend>
			<br />
			<label>Read</label><input type="text" name="readStatementCustomer" placeholder="col=value">
			<input type="submit" name="operation" value="Read Customer" />
            <br /><br />
			<p style="color:red">${operationStatusCustomer}</p>
			<hr />
			<table>
			    <caption>Customers</caption>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Country</th>
					<th>Phone</th>
					<th>Email</th>
				</tr>
                <% if(request.getAttribute("record") instanceof Customer) {%>
                <% CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstanceByType("customer"); %>
		    	<% Customer customer = (Customer) request.getAttribute("record"); %>
                <% if(customer !=null) {%>
				<tr>
					<td><%= customerDAO.getValueByName(customer, "id") %></td>
					<td><%= customerDAO.getValueByName(customer, "name") %></td>
					<td><%= customerDAO.getValueByName(customer, "country") %></td>
					<td><%= customerDAO.getValueByName(customer, "phone") %></td>
					<td><%= customerDAO.getValueByName(customer, "email") %></td>
				</tr>
				<% } %>
				<% } %>
			</table>
		</fieldset>
	</form>
	<br /><br />
	<form action="/home" method="POST">
    	<fieldset>
    		<legend><strong>Operations on Accounts</strong></legend>
    		<br />
    		<label>Read</label><input type="text" name="readStatementAccount" placeholder="col=value">
   			<input type="submit" name="operation" value="Read Account" />
            <br /><br />
    		<p style="color:red">${operationStatusAccount}</p>
    		<hr />
    	    <table>
                <caption>Accounts</caption>
    		    <tr>
    		    	<th>Id</th>
    			    <th>Customer Id</th>
    			    <th>Username</th>
    			    <th>Password</th>
    		    </tr>
                <% if(request.getAttribute("record") instanceof Account) {%>
                    <% AccountDAO accountDAO = (AccountDAO) DAOFactory.getInstanceByType("account"); %>
    		        <% Account account = (Account) request.getAttribute("record"); %>
                    <% if(account !=null) {%>
    				    <tr>
    					    <td><%= accountDAO.getValueByName(account, "id") %></td>
    					    <td><%= accountDAO.getValueByName(account, "customerId") %></td>
    					    <td><%= accountDAO.getValueByName(account, "username") %></td>
    					    <td><%= accountDAO.getValueByName(account, "password") %></td>
    				    </tr>
    				<% } %>
    		    <% } %>
    	    </table>
        </fieldset>
    </form>

</body>
</html>