/**
 * This file is part of lup1.
 *
 * lup1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * lup1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with lup1.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package fr.da2i.lup1.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import fr.da2i.lup1.entity.Task;
import fr.da2i.lup1.util.Dao;

public class TaskDao implements Dao<Integer, Task> {
	
	private static Map<Integer, Task> map = new HashMap<>();
	
	public TaskDao() {}
	
	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<Integer, Task>> entrySet() {
		return map.entrySet();
	}

	@Override
	public Task get(Object key) {
		return map.get(key);
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Set<Integer> keySet() {
		return map.keySet();
	}

	@Override
	public Task put(Integer key, Task value) {
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Task> m) {
		map.putAll(m);
	}

	@Override
	public Task remove(Object key) {
		return map.remove(key);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public Collection<Task> values() {
		return map.values();
	}

	@Override
	public void close() throws Exception {}
	
	@Override
	public String toString() {
		return map.toString();
	}
	
}
