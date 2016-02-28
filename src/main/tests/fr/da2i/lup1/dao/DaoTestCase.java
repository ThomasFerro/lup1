package fr.da2i.lup1.dao;

import org.junit.Test;

import fr.da2i.lup1.dao.MemberDao;
import fr.da2i.lup1.entity.Member;
import fr.da2i.lup1.util.Dao;

public class DaoTestCase {

	@Test
	public void test() {
		Dao<Integer, Member> memberDao = new MemberDao();
		//memberDao.clear();
		System.out.println(memberDao.containsKey("15"));
		try {
			memberDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
