package gui;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;

import shapeFactory.ShapeFactory;
import shapeFactory.ProductCircle;
import shapeFactory.ShapeConstructor;
import shapeFactory.Shape;
import display.SingletonShapeConstructor;
import display.GUI_Paint;
import display.ShapeList;
import component.Circle;
import component.Rectangle;
import component.State;

public class Paint {

	private JFrame mainFrame;
	private JPanel pnMain, pnShapeInfor, pnShapes;
	private JButton btnAdd, btnremove, btnUndo, btnRedo, btnPrint;
	private JCheckBox cb3D, cb2D;
	private JRadioButton rbtnRemove;
	private List<JRadioButton> radioButtons;

	public Paint() {

		radioButtons = new ArrayList<>();
		setView();
		setAction();

	}

	private void setView() {
		mainFrame = new JFrame("TMAPaint");
		mainFrame.setSize(800, 500);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnMain = new JPanel();
		pnMain.setBounds(0, 0, 800, 500);
		pnMain.setLayout(null);

		pnShapeInfor = new JPanel();
		pnShapeInfor.setLayout(new GridLayout(20, 1));
		pnShapeInfor.setBounds(5, 5, 280, 460);
		pnShapeInfor.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE), null));

		// rbtnRemove = new JRadioButton();
		// rbtnRemove.setBounds(10, 10, 70, 30);
		pnShapes = new JPanel();
		pnShapes.setLayout(null);
		pnShapes.setBounds(300, 40, 490, 425);
		pnShapes.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK), null));
		btnAdd = new JButton("Add");
		btnAdd.setBounds(300, 5, 70, 30);
		btnremove = new JButton("Remove");
		btnremove.setBounds(375, 5, 80, 30);
		btnUndo = new JButton("Undo");
		btnUndo.setBounds(460, 5, 70, 30);
		btnRedo = new JButton("Redo");
		btnRedo.setBounds(535, 5, 70, 30);
		btnPrint = new JButton("Print");
		btnPrint.setBounds(610, 5, 70, 30);
		cb3D = new JCheckBox("3D");
		cb3D.setBounds(685, 5, 50, 30);
		cb3D.setBorderPainted(true);
		cb2D = new JCheckBox("2D");
		cb2D.setBounds(735, 5, 50, 30);
		cb2D.setBorderPainted(true);
		cb2D.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(cb2D);
		group.add(cb3D);
		pnMain.setBackground(Color.white);
		pnMain.add(btnAdd);
		pnMain.add(btnremove);
		pnMain.add(btnUndo);
		pnMain.add(btnRedo);
		pnMain.add(btnPrint);
		pnMain.add(cb3D);
		pnMain.add(cb2D);
		pnMain.add(pnShapeInfor);
		pnMain.add(pnShapes);

		mainFrame.add(pnMain);
		mainFrame.setLocationRelativeTo(null);

	}

	private void setAction() {
		btnAdd.addActionListener(onClick);
		btnremove.addActionListener(onClick);
		btnUndo.addActionListener(onClick);
		btnRedo.addActionListener(onClick);
		btnPrint.addActionListener(onClick);
		cb2D.addActionListener(onClick);
		cb3D.addActionListener(onClick);
	}

	public void setVisible(boolean visible) {
		if (visible)
			repaint();
		mainFrame.setVisible(visible);
	}

	public void updateInfor() {
		pnShapeInfor.removeAll();
		radioButtons.clear();
		int i = 0;
		for (Shape shape : ShapeList.getInstance().getListShapes()) {
			JRadioButton rbtnRe = new JRadioButton(String.valueOf(i + 1 + "." + shape.info(shape)));
			radioButtons.add(rbtnRe);
			if (!shape.isRemove()) { // remove = false
				
				pnShapeInfor.add(rbtnRe);
				i++;
			}
		}

		System.out.println(radioButtons.size());

		pnShapeInfor.revalidate();
		pnShapeInfor.repaint();
	}

	private void repaint() {
		pnShapes.removeAll();
		Shape shape;
		for (int i = ShapeList.getInstance().getListShapes().size() - 1; i >= 0; i--) {

			shape = ShapeList.getInstance().getListShapes().get(i);
			// kiem tra trang thai remove = false
			if (State.Status.direct.equals("2D")) {
				ShapeFactory pf2d = SingletonShapeConstructor.getInstance().getBuilder().getFactory("2D");
				pf2d.createCirle();
			} else {
				ShapeFactory pf3d = SingletonShapeConstructor.getInstance().getBuilder().getFactory("3D");
				pf3d.createCirle();
			}
			if (!shape.isRemove())
				pnShapes.add(shape);
		}
		pnShapes.revalidate();
		pnShapes.repaint();
	}

	private void undo() {
		for (int i = ShapeList.getInstance().getListShapes().size() - 1; i >= 0; i--) {
			Shape shape = ShapeList.getInstance().getListShapes().get(i);
			if (shape.getStatus() != State.Status.UNDO) {
				if (shape.getStatus() == State.Status.REMOVE || shape.getStatus() == State.Status.REDO) {
					if (shape.isRemove())
						// ko can thiet
						shape.setRemove(false);
					else
						// chuyen sang trang thai de ko paint duoc
						shape.setRemove(true);
					shape.setStatus(State.Status.UNDO);
					break;
				}
//				if (shape.getStatus() == State.Status.NEW || shape.getStatus() == State.Status.REDO) {
//					if (shape.isRemove())
//						// ko can thiet
//						shape.setRemove(false);
//					else
//						// chuyen sang trang thai de ko paint duoc
//						shape.setRemove(true);
//					shape.setStatus(State.Status.UNDO);
//					break;
//				}
			}
		}
	}

	private void redo() {
		for (int i = 0; i < ShapeList.getInstance().getListShapes().size(); i++) {
			Shape shape = ShapeList.getInstance().getListShapes().get(i);
			if (shape.getStatus() == State.Status.UNDO) {
				if (shape.isRemove())
					shape.setRemove(false);
				else
					shape.setRemove(true);
				shape.setStatus(State.Status.REDO);
				break;
			}
		}
	}

	private void remove(int index) {

		ShapeList.getInstance().getListShapes().get(index).setRemove(true);
		ShapeList.getInstance().getListShapes().get(index).setStatus(State.Status.REMOVE);
		GUI_Paint.getInstance().getDisplay().updateInfor();
		GUI_Paint.getInstance().getDisplay().setVisible(true);

	}

	private ActionListener onClick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btnAdd) {
				new DetailsContainer();
			} else if (source == btnremove) {
				for (int i = 0; i < radioButtons.size(); i++) {
					try {
						if (radioButtons.get(i).isSelected()) {
							remove(i);
							//radioButtons.remove(i);
						}
					} catch (Exception x) {
						System.out.print("loi");
					}
				}

			} else if (source == btnUndo) {
				undo();
				repaint();
				updateInfor();

			} else if (source == btnRedo) {
				redo();
				repaint();
				updateInfor();
			} else if (source == cb2D) {
				State.Status.direct = "2D";
				repaint();

			} else if (source == cb3D) {
				State.Status.direct = "3D";
				repaint();

			}

		}
	};

}
