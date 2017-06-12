package Subject.Models;

public class SubjectComponentTemplates {
	
	private int id;
	private String name;
	private double markPercentage;
	private int number;

	public SubjectComponentTemplates(int id, String name, double markPercentage, int number) {
		this.id = id;
		this.name = name;
		this.markPercentage =  markPercentage;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarkPercentage() {
		return markPercentage;
	}

	public void setMarkPercentage(double markPercentage) {
		this.markPercentage = markPercentage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(markPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		SubjectComponentTemplates other = (SubjectComponentTemplates) obj;
		
		return other.id == id && other.markPercentage == markPercentage &&
				other.name.equals(name) && other.number == number;
	}
}
