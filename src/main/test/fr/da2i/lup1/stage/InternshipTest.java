package fr.da2i.lup1.stage;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.Internship;
import fr.da2i.lup1.util.DaoProvider;

public class InternshipTest {

	@Test
	public void test() {
		Dao<Internship, Integer> internshipDao = DaoProvider.getDao(Internship.class);
		
		try {
			System.out.println("All:" + internshipDao.queryForAll());
			System.out.println("Test:" + internshipDao.queryBuilder().where().eq("begin_date", Date.valueOf("2015-10-05")).query());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
