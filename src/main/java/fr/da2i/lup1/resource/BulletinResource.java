package fr.da2i.lup1.resource;

import java.sql.SQLException;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import fr.da2i.lup1.entity.note.Bulletin;
import fr.da2i.lup1.util.Restlet;

@Path("bulletins")
public class BulletinResource implements Restlet<String, Bulletin> {

	@Override
	public Response create(Bulletin entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response get(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response update(String id, Bulletin entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
