package laba;

public class File {

	private String name;
	private int size;
	
	public File(String name, int size) {
		this.name = name;
		this.size = size;
		
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}

	public int getSize() {
		return size;
	}
}
