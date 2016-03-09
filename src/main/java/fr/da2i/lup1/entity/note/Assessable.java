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
package fr.da2i.lup1.entity.note;

import java.util.HashSet;
import java.util.Set;

import fr.da2i.lup1.util.Identifiable;

public abstract class Assessable<T> extends Identifiable<Integer> {

	private double coeff;
	private Set<T> assessables;
	
	public Assessable() {
		this.assessables = new HashSet<T>();
	}
	
	public double getCoeff() {
		return coeff;
	}
	
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	public Set<T> getAssessables() {
		return assessables;
	}

	public void setAssessables(Set<T> assessables) {
		this.assessables = assessables;
	}

	public boolean contains(T entity) {
		return assessables.contains(entity);
	}
	
	public boolean add(T entity) {
		return assessables.add(entity);
	}
	
	public boolean remove(T entity) {
		return assessables.remove(entity);
	}
	
}
