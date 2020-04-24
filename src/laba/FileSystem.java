package laba;

import java.awt.List;
import java.util.ArrayList;

public class FileSystem {
	private int size;
	private int firstBlock;
	private File file;
	private ArrayList<File> files;

	public FileSystem(){ }
	public ArrayList<File> addFile(String name, int size, Disk disk){
		files.add(new File(name, size));
		firstBlock = disk.firstFree();
		disk.getBlock(firstBlock).setState(true);
		this.size = size;
		initialize(size, disk);
		return files;
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
	public File getFile(int index){
		return files.get(index);
	}
	public ArrayList <File> getFiles(){
		return files;
	}
	public void removeFile(FileSystem file){
		files.remove(file);
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
