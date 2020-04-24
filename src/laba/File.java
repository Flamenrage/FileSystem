package laba;

public class File {

	private String name;
	private int size;
	private FileSystem fs;
	
	public File(String name, int size, Disk disk) {
		this.name = name;
		this.size = size;
		fs = new FileSystem(size, disk);
		
	}
	public FileSystem getFs() {
		return fs;
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
