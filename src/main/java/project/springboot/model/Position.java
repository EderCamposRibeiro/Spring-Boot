package project.springboot.model;

public enum Position {
	
	JUNIOR("Junior"),
	PLENO("Pleno"),
	SENIOR("Senior");
	
	private String name;
	
	private String value;
	
	private Position (String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value = this.name();
	}

}
