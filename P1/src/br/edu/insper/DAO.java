package br.edu.insper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	private Connection connection = null;
	public DAO() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	connection = DriverManager.getConnection(
	"jdbc:mysql://localhost/tarefas", "root", "Estetica-1");
	}
	public List<Task> getLista() throws SQLException {
		
		List<Task> tarefas = new ArrayList<Task>();
		
		PreparedStatement stmt = connection.
				
		prepareStatement("SELECT * FROM Tarefas");
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Task tarefa = new Task();
			tarefa.setId(rs.getInt("id"));
			tarefa.setTarefa(rs.getString("tarefa"));
			String data = new Timestamp(System.currentTimeMillis()).toString();
			tarefa.setData(data);
			tarefas.add(tarefa);
		}
		
		rs.close();
		stmt.close();
		return tarefas;
		}
	public void close() throws SQLException {
		connection.close();
		}
	
	public void adiciona(String tarefa, String data) throws SQLException {
		String sql = "INSERT INTO Tarefas" +
		"(tarefa,data) values(?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,tarefa);
		stmt.setString(2, data);
		stmt.execute();
		stmt.close();
		}
	
	public void altera(Task tarefa) throws SQLException {
		String sql = "UPDATE Tarefas SET " +
		"tarefa=?, data=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tarefa.getTarefa());
		stmt.setString(2, tarefa.getData());
		stmt.setInt(3, tarefa.getId());
		stmt.execute();
		stmt.close();
		}
	public void remove(Integer id) throws SQLException {
		PreparedStatement stmt = connection
		.prepareStatement("DELETE FROM Tarefas WHERE id=?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
		}
}
