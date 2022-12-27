package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/bancoagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "pedro";
	
	/** The password. */
	private String password = "PeRuMa.11231321";

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,telefone,email) values (?,?,?)";
		try {
			Connection con = conectar();

			PreparedStatement pst = con.prepareStatement(create);

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());

			pst.executeUpdate();

			con.close();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		String read = "select * from contatos order by nome";
		try {
			ArrayList<JavaBeans> contatos = new ArrayList<>();
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);

				contatos.add(new JavaBeans(idcon, nome, telefone, email));
			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(read2);
			ps.setString(1, contato.getIdcon());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				
			}
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(JavaBeans contato) {
		String update = "update contatos set nome=?, telefone=?, email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone());
			ps.setString(3, contato.getEmail());
			ps.setString(4, contato.getIdcon());
			ps.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon());
			pst.executeUpdate(); 
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

