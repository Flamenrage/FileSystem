package laba;

public class Block {
	private final int id;
	static final int size = 4;
	private boolean state;
	private Block nextBlock;

	public Block(int id) {
		this.id = id;
		this.state = false;
		this.nextBlock = null;
	}

	public int getid() {
		return this.id;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isFree() {
		if (state == true)
			return false;
		else
			return true;
	}

	public Block getNextBlock() {
		return this.nextBlock;
	}

	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}
}
