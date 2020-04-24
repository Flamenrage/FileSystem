package laba;

public class FileSystem {
	private String name;
	private int size;
	private int firstBlock;

	public FileSystem(int size, Disk disk) {
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
	public int getSize() {
		return size;
	}

	public int getFirstBlock() {
		return firstBlock;
	}
	
	public void delete(Disk disk) {
		int id = firstBlock;
		int next;
		Block nextBlock = disk.getBlock(id).getNextBlock();
		while (nextBlock!=null){
			next = disk.getBlock(id).getNextBlock().getid();
			disk.setBlockFree(id);
			id = next;
			nextBlock = disk.getBlock(id).getNextBlock();
		}
		if (nextBlock == null) {
			disk.setBlockFree(id);
		}
	}
}