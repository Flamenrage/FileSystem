package laba;

public class Disk {
	private Block[] blocks;

	public Disk(int n) {
		blocks = new Block[n];
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = new Block(i);
		}
	}

	public Block getBlock(int i) {
		return blocks[i];
	}

	public int firstFree() {
		int id = -1;
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].isFree()) {
				id = blocks[i].getid();
				return id;
			}
		}
		return id;
	}
	public int freeBlocks() {
		int count = 0;
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].isFree())
				count++;
		}
		return count;
	}
	public int size() {
		return blocks.length;
	}

	public void setBlockFree(int i) {
		blocks[i].setState(false);
		blocks[i].setNextBlock(null);
	}
}
