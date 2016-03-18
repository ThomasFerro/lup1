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
package fr.da2i.lup1.util;

import java.util.Iterator;
import java.util.Stack;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Edouard
 *
 */
@XmlRootElement
public class CascadingTicket extends Ticket implements Iterable<Ticket> {

	private Stack<Ticket> cascade;
	
	public CascadingTicket(String code, String message) {
		super(code, message);
	}
	
	public void push(Ticket message) {
		if (cascade == null) {
			cascade = new Stack<>();
		}
		cascade.push(message);
	}

	@Override
	public Iterator<Ticket> iterator() {
		return cascade.iterator();
	}
	

}
