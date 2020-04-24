package laba;

public class File {

	private String name;
	private int size;
	private int firstBlock;

	public File(String name, int size, Disk disk) {
		this.name = name;
		this.size = size;

		firstBlock = disk.firstFree();
		disk.getBlock(firstBlock).setState(true);
		initialize(size, disk);
		
	}
	public void initialize(int size, Disk disk){
		int previous = -1;
		for (int i = 1; i < size; i++) {
			int k = disk.firstFree();

			disk.getBlock(k).setState(true);
			if (i == 1) {
				disk.getBlock(firstBlock).setNextBlock(disk.getBlock(k));
			} else {
				disk.getBlock(previous).setNextBlock(disk.getBlock(k));
			}
			previous = k;
		}
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

	public int getFirstBlock() {
		return firstBlock;
	}
	
	public void delete(Disk disk) {
		int id = firstBlock;
		int next;
		for (;;) {
			Block nextBlock = disk.getBlock(id).getNextBlock();
			if (nextBlock == null) {
				disk.setBlockFree(id);
				break;
			} else {
				next = disk.getBlock(id).getNextBlock().getid();
				disk.setBlockFree(id);
				id = next;
			}
		}
	}
}
