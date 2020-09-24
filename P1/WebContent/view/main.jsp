<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ page import="java.util.*, br.edu.insper.*"%>
		<meta charset="UTF-8">
		<title>Tarefas</title>
	</head>
	<body>
		<h1>Lista de Tarefas</h1>
		<%
		
		DAO dao = new DAO();
		
		String action = (String) request.getAttribute("action");
		List<Task> tarefas = new ArrayList<Task>();
		tarefas = dao.getLista();
		System.out.println(action);
		
		%>
		
		
		
		<table border="1">
			<% for (Task tarefa : tarefas) { 
					String texto = tarefa.getTarefa();
					int id = tarefa.getId();%>
				<tr>
					<td><%= tarefa.getTarefa() %></td>
					<td><%= tarefa.getData() %></td>
					
					<td><form action="main" method="post" >
						<input type="hidden" name="action" value="alterar"/>
						<input type="hidden" name="id" value="<%=id%>"/>
						<input type="hidden" name="texto" value="<%=texto%>"/>
						<input type="hidden" name="status" value="alterando">
						<input type="submit" value="Alterar">
					</form></td>
					
					<td><form action="main" method="post">
						<input type="hidden" name="action" value="del"/>
						<input type="hidden" name="id" value="<%=id%>"/>
						<input type="submit" value="Remover">
					</form></td>
				</tr>
			

			<% } %>
		</table>
		<form action="main" method="post">
		<input type="hidden" name="action" value="add"/>
			<textarea name="tarefa" placeholder="Adicionar tarefas" rows="2" cols="30"></textarea>
		
			<input type="submit" value="Adicionar nova tarefa">
		</form>
	</body>
		
</html>