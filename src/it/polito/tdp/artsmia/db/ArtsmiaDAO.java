package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Corrispondenza;
import it.polito.tdp.artsmia.model.Esposizioni;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getAnni() {
		String sql = "SELECT DISTINCT  begin " + 
				"FROM exhibitions " + 
				"ORDER BY begin ASC ";

		List<Integer> result = new LinkedList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				     result.add(res.getInt("begin"));
				}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    public List <Integer> getMostre (int anno){
    	String sql = "SELECT DISTINCT  e.exhibition_id AS id " + 
    			"FROM exhibitions AS e " + 
    			"WHERE e.begin>=?";

		List<Integer> result = new LinkedList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				     result.add(res.getInt("id"));
				}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
	public List<Corrispondenza> getCorrispondenze(int anno) {
		String sql = "SELECT  e1.exhibition_id , e2.exhibition_id " + 
				"FROM exhibitions AS e1, exhibitions AS e2 " + 
				"WHERE e1.exhibition_id!=e2.exhibition_id AND  e1.begin< e2.begin AND e2.begin<e1.end AND e2.begin>=? AND e1.begin>=?";

		List<Corrispondenza> result = new LinkedList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, anno);
            st.setInt(2, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				     Corrispondenza c = new Corrispondenza (res.getInt("e1.exhibition_id")
				    		 , res.getInt("e2.exhibition_id"));
				     result.add(c);
				}

			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map <Integer, Esposizioni> getNOpere(int anno) {
		String sql = "SELECT eo.exhibition_id AS id , COUNT(object_id) AS c " + 
				"FROM exhibition_objects AS eo , exhibitions AS e " + 
				"WHERE eo.exhibition_id=e.exhibition_id AND e.`begin`>=? " + 
				"GROUP BY eo.exhibition_id";

		Map <Integer, Esposizioni> opere = new HashMap <Integer, Esposizioni>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, anno);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				    Esposizioni e = new Esposizioni (res.getInt("id"), res.getInt("c"));
				    opere.put(res.getInt("id"), e);
				}
			conn.close();
	        return opere;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
