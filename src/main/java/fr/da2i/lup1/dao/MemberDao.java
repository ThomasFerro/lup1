package fr.da2i.lup1.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import fr.da2i.lup1.entity.Member;
import fr.da2i.lup1.util.AbstractDao;

public class MemberDao extends AbstractDao<Integer, Member>{

	public MemberDao() { 
		super("member_id", "integer", "member");
	}
	
	@Override
	public Set<java.util.Map.Entry<Integer, Member>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Integer> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member put(Integer key, Member value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Member> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Member remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Member> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
