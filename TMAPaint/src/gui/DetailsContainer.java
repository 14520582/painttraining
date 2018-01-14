package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

public class DetailsContainer {
	private JFrame frameAdd;
	private JPanel pnAdd;
	private JTextField x, y, tfRadius, tfWidth, tfHeight;
	private ButtonGroup groupShapes;
	private JRadioButton rbtnCircle, rbtnRectangle;
	private JButton btnAdd, btnCancel;
	private JLabel lbPosition, lbX, lbY, lbRadius, lbWidth, lbHeight;

	public DetailsContainer() {
		this.setView();
		this.setEvent();
	}

	private void setView() {
	
		frameAdd = new JFrame("Add Shape");
		frameAdd.setLayout(null);
		frameAdd.setSize(200, 250);
		frameAdd.setResizable(false);

		pnAdd = new JPanel();
		pnAdd.setLayout(null);
		pnAdd.setBounds(0, 0, 200, 250);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(20, 170, 70, 30);
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(100, 170, 80, 30);

		lbPosition = new JLabel("Position: ");
		lbPosition.setBounds(15, 50, 60, 20);
		lbX = new JLabel("x: ");
		lbX.setBounds(50, 70, 30, 20);
		lbY = new JLabel("y: ");
		lbY.setBounds(100, 70, 30, 20);
		x = new JTextField();
		x.setBounds(65, 70, 30, 20);
		y = new JTextField();
		y.setBounds(120, 70, 30, 20);

		rbtnCircle = new JRadioButton("Circle");
		rbtnCircle.setBounds(10, 10, 70, 30);
		rbtnRectangle = new JRadioButton("Rectangle");
		rbtnRectangle.setBounds(90, 10, 90, 30);

		groupShapes = new ButtonGroup();
		groupShapes.add(rbtnCircle);
		groupShapes.add(rbtnRectangle);

		lbRadius = new JLabel("Radius: ");
		lbRadius.setBounds(15, 100, 50, 20);
		lbWidth = new JLabel("Width: ");
		lbWidth.setBounds(15, 100, 50, 20);
		lbHeight = new JLabel("Height: ");
		lbHeight.setBounds(15, 130, 50, 20);

		tfRadius = new JTextField();
		tfRadius.setBounds(65, 100, 50, 20);
		tfWidth = new JTextField();
		tfWidth.setBounds(65, 100, 50, 20);
		tfHeight = new JTextField();
		tfHeight.setBounds(65, 130, 50, 20);

		tfRadius.setVisible(false);
		tfWidth.setVisible(false);
		tfHeight.setVisible(false);
		lbRadius.setVisible(false);
		lbHeight.setVisible(false);
		lbWidth.setVisible(false);

		pnAdd.add(lbPosition);
		pnAdd.add(btnAdd);
		pnAdd.add(btnCancel);
		pnAdd.add(rbtnCircle);
		pnAdd.add(rbtnRectangle);
		pnAdd.add(x);
		pnAdd.add(y);
		pnAdd.add(lbX);
		pnAdd.add(lbY);
		pnAdd.add(tfRadius);
		frameAdd.add(pnAdd);
		pnAdd.add(tfWidth);
		pnAdd.add(tfHeight);
		pnAdd.add(lbWidth);
		pnAdd.add(lbRadius);
		pnAdd.add(lbHeight);
		frameAdd.setLocationRelativeTo(null);
		frameAdd.setVisible(true);
	}

	private void setEvent() {

		rbtnCircle.addActionListener(onclick);
		rbtnRectangle.addActionListener(onclick);
		btnCancel.addActionListener(onclick);
		btnAdd.addActionListener(onclick);
	}
	
	

	private ActionListener onclick = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btnCancel) {
				frameAdd.dispose();
				GUI_Paint.getInstance().getDisplay().setVisible(true);
			} else if (source == btnAdd) {
				addShape();
				frameAdd.dispose();
			} else if (source == rbtnCircle) {
				tfRadius.setVisible(true);
				tfWidth.setVisible(false);
				tfHeight.setVisible(false);
				lbRadius.setVisible(true);
				lbHeight.setVisible(false);
				lbWidth.setVisible(false);
				pnAdd.revalidate();
				pnAdd.repaint();
			} else if (source == rbtnRectangle) {
				tfRadius.setVisible(false);
				tfWidth.setVisible(true);
				tfHeight.setVisible(true);
				lbRadius.setVisible(false);
				lbHeight.setVisible(true);
				lbWidth.setVisible(true);
				pnAdd.revalidate();
				pnAdd.repaint();

			}
		}
	};
	
	private void add(Shape shape) {
		shape.setRemove(false);
		shape.setStatus(State.Status.NEW);
		ShapeList.getInstance().getListShapes().add(shape);
		GUI_Paint.getInstance().getDisplay().updateInfor();
		GUI_Paint.getInstance().getDisplay().setVisible(true);
	}
	
	private void addShape() {
		Shape shape = null;
		if (rbtnCircle.isSelected()) {
			ShapeFactory pf=SingletonShapeConstructor.getInstance().getBuilder().getFactory(State.Status.direct);
			shape=pf.createCirle();
			shape.draw(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()), Integer.parseInt(tfRadius.getText()));
		} else {
			ShapeFactory pf=SingletonShapeConstructor.getInstance().getBuilder().getFactory(State.Status.direct);
			shape=pf.createRectangle();
			shape.draw(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()),
					Integer.parseInt(tfWidth.getText()), Integer.parseInt(tfHeight.getText()));
		}
		shape.setRemove(false);
		this.add(shape);
	}

}
