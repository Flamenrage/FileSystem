package laba;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainWin {

	private JFrame frame;
	private JList list;
	private Disk disk;
	static int n = 700;
	private ArrayList<File> files = new ArrayList<File>();
	static File file;
	private JTextField textFieldFile;
	private JTextField textFieldMeasure;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin window = new MainWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWin() {
		initialize();
	}
	public void updateView(){
		if (list.isSelectionEmpty()) {
			file = null;
		} else {
			file = (File) list.getSelectedValue();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		disk = new Disk(n/Block.size);
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		DiskPanel panel = new DiskPanel(disk);
		panel.setBackground(Color.WHITE);
		panel.setBounds(197, 13, 481, 341);
		frame.getContentPane().add(panel);
		panel.repaint();
		
		JButton buttonCopy = new JButton("Дублировать");
		buttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateView();
				if (disk.freeBlocks() >= file.getSize()) {
					files.add(new File(file.getName() + "--копия", file.getSize(), disk));
				} else {
					JOptionPane.showMessageDialog(null, "Недостаточно места");
					return;
				}
				list.setListData(files.toArray());
				panel.repaint();
			}
		});
		buttonCopy.setBounds(88, 493, 118, 23);
		frame.getContentPane().add(buttonCopy);

		JButton button = new JButton("Создать");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Integer.parseInt(textFieldMeasure.getText());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
					return;
				}
				int fill = Integer.parseInt(textFieldMeasure.getText());
				if (fill % Block.size != 0) {
					fill = fill / Block.size + 1;
				} else {
					fill = fill / Block.size;
				}
				isEnoughSpace(fill);
				updateView();
				list.setListData(files.toArray());
				panel.repaint();
				textFieldMeasure.setText("");
				textFieldFile.setText("");
			}
		});
		button.setBounds(88, 427, 118, 25);
		frame.getContentPane().add(button);
		
		JButton buttonDelete = new JButton("Удалить");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateView();
				file.delete(disk);
				files.remove(file);
				file = null;
				list.setListData(files.toArray());
				panel.repaint();
			}
		});
		buttonDelete.setBounds(88, 460, 118, 23);
		frame.getContentPane().add(buttonDelete);

		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateView();
				panel.repaint();
			}
		});
		
		list.setBounds(12, 13, 173, 341);
		frame.getContentPane().add(list);
		
		JLabel label = new JLabel("Название:");
		label.setBounds(12, 370, 76, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Размер:");
		label_1.setBounds(12, 399, 56, 16);
		frame.getContentPane().add(label_1);
		
		textFieldFile = new JTextField();
		textFieldFile.setBounds(90, 367, 94, 22);
		frame.getContentPane().add(textFieldFile);
		textFieldFile.setColumns(10);
		
		textFieldMeasure = new JTextField();
		textFieldMeasure.setColumns(10);
		textFieldMeasure.setBounds(91, 396, 94, 22);
		frame.getContentPane().add(textFieldMeasure);
	}
	public void isEnoughSpace(int size){
		if (disk.freeBlocks() >= size) {
			files.add(new File(textFieldFile.getText(), size, disk));
			
		} else {
			JOptionPane.showMessageDialog(null, "Недостаточно места");
			return;
		}
	}
}
