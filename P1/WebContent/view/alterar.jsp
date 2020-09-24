<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	
		<%@ page import="java.util.*, br.edu.insper.*"%>
		<meta charset="UTF-8">
		<title>Editar Tarefas</title>
		<% 
			
			String tarefa = request.getParameter("texto");
			Integer id = Integer.parseInt(request.getParameter("id"));
		
		%>
	
	</head>
	
	<body>
	
		<h1> Editar Tarefas </h1> <hr/>
		
		<form action="main" method="post">
			<input type="hidden" name="action" value="alterar"/>
			<textarea name="novoTexto"rows="5" cols="30"><%=tarefa%></textarea>
			
			<input type="hidden" name="id" value="<%=id%>"/>
			<input type="hidden" name="status" value="alterado">
			<input type="submit" value="Confirma">
			
		</form>
	
	</body>
</html>