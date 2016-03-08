package fr.da2i.lup1.stage;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.ManageInternship;
import fr.da2i.lup1.util.DaoProvider;

public class ManageInternshipTest {

	@Test
	public void test() {
		Dao<ManageInternship, List<Integer>> manageDao = DaoProvider.getDao(ManageInternship.class);
		
		try {
			System.out.println("All : " + manageDao.queryForAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
