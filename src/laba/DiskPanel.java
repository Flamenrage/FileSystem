package laba;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DiskPanel extends JPanel {
	private int weight = 25, height = 25;
    private int k = 485 / weight;
	private Disk disk;

	public DiskPanel(Disk disk) {
		this.disk = disk;
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < disk.size(); i++) {
			if (disk.getBlock(i).isFree())
				g.setColor(new Color(200, 200, 200));
			else
				g.setColor(new Color(0, 180, 0));
			g.fillRect(i % k * weight, i / k * height, weight, height);
			g.setColor(new Color(255, 255, 255));
			g.drawRect(i % k * weight, i / k * height, weight, height);
		}
		drawfile(g);
		Graphics2D g2 = (Graphics2D) g; 
		g2.setStroke(new BasicStroke(7.0f)); 
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, 480, 340);
	}
	public void drawfile(Graphics g){
		if (MainWin.fileSystem!= null) {
			g.setColor(new Color(200, 0, 0));
			int i = MainWin.fileSystem.getFirstBlock();
			for (;;) {
				g.fillRect(i % k * weight + 2, i / k * height + 2, weight - 4, height - 4);
				if (disk.getBlock(i).getNextBlock() == null)
					break;
				else
					i = disk.getBlock(i).getNextBlock().getid();
			}
		}
	}
}
