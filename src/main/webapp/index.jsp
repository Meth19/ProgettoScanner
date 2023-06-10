<?xml version="1.0" encoding="UTF-8"?>

<!--
  Codehaus Cargo, copyright 2004-2011 Vincent Massol, 2012-2023 Ali Tokmen.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  -->

<!-- The reason we don't use a simple index.html file instead of a JSP (even though this would
     execute more quickly) is because we want to ensure that the containers are correctly 
     configured to handle JSPs -->
     <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
   
     <!DOCTYPE html>
    <html>
    <head>
    <meta charset="ISO-8859-1">
    <title>JSP Actions Example</title>
    </head>
    <body>
    
    <h1> Student Registration Page</h1>
     <form action="<%= request.getContextPath() %>/StudentServlet" method="post">
      First Name: <input type="text" name="firstName">
      <br> <br> 
      
      Last Name: <input type="text" name="lastName">
      <br> <br> 
      
      Email ID: <input type="email" name="emailId">
      <br> <br> 
      
      Password: <input type="password" name="password"><br>
      
      <br> 
      <input type="submit" value="register">
     </form>
    </body>
</html>
