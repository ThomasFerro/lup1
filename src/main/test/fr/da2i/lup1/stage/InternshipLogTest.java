package fr.da2i.lup1.stage;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;

import com.j256.ormlite.dao.Dao;

import fr.da2i.lup1.entity.stage.InternshipLog;
import fr.da2i.lup1.util.DaoProvider;

public class InternshipLogTest {

	@Test
	public void test() {
		Dao<InternshipLog, Integer> internshipLogDao = DaoProvider.getDao(InternshipLog.class);
		
		try {
			System.out.println("All:" + internshipLogDao.queryForAll());
			System.out.println("Test:" + internshipLogDao.queryBuilder().where().between("date_log", Timestamp.valueOf("2015-12-22 00:00:00"), Timestamp.valueOf("2015-12-22 20:00:00")).query());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
