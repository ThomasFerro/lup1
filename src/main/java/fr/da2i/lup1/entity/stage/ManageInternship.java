package fr.da2i.lup1.entity.stage;

import java.util.Arrays;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "manage_internship")
public class ManageInternship{
	
	@DatabaseField(columnName = "member_id")
	private int memberId;
	@DatabaseField(columnName = "internship_id")
	private int internshipId;
	
	public ManageInternship(int m, int i) {
		this.memberId = m;
		this.internshipId = i;
	}

	public ManageInternship() {}

	public List<Integer> getId() {
		return Arrays.asList(this.internshipId, this.memberId);
	}

	/**
	 * Définie une nouvelle liste d'entier pour les IDs
	 * @param id	La liste d'entier représentant les identifiants de la table dans l'ordre suivant : <member_id> puis <internship_id>
	 */
	public void setId(List<Integer> id) {
		if(id.size() == 2) {
			this.memberId = id.get(0);
			this.internshipId = id.get(1);
		}
		else {
			try {
				throw new Exception("Taille de la liste incorrecte");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getInternshipId() {
		return internshipId;
	}

	public void setInternshipId(int internshipId) {
		this.internshipId = internshipId;
	}
	
	public String toString() {
		return "ManageInternship ["
				+ "member_id=" + getMemberId() + ", "
				+ "internship_id=" + getInternshipId()
				+ "]";
	}
}
