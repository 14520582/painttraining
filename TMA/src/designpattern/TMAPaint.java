package designpattern;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

public class TMAPaint extends JPanel {

 private static JFrame frame;

 private JLabel lbCircle;
 private JLabel lbRectangle;

 private JPanel panelShape;
 private JPanel panelCircle;
 private JPanel panelRectangle;
 private JPanel panelDraw;
 private JPanel panelButton;
 private JPanel panelPaint;

 private JTable tbCircle;
 private JTable tbRect;

 private JScrollPane scrollCircle;
 private JScrollPane scrollRectangle;

 private JButton btAdd;
 private JButton btRemove;
 private JButton btUndo;
 private JButton btRedo;
 private JButton btPrint;
 private JButton bt3D;
 private JButton bt2D;

 private Point last;
 private Point present;

 private int x;
 private int y;

 public static List<Shape> listShape;
 public Shape Shape;
 
 private boolean start =  true;
 
 UndoManager undoManager = new UndoManager();

 public TMAPaint() {
  
  Status.styleDraw = Status.DRAW_CIRCLE;
  Status.style2D3D = Status.D2;
  listShape = new ArrayList<>();

  setLayout(new FlowLayout(0));

  panelShape = new JPanel();
  panelShape.setBorder(BorderFactory.createLineBorder(Color.blue));
  panelShape.setLayout(new GridLayout(2, 1));
  this.add(panelShape);

  panelCircle = new JPanel();
  // panelCircle.setBorder(BorderFactory.createLineBorder(Color.black));
  panelCircle.setLayout(new FlowLayout(0));
  panelCircle.setPreferredSize(new Dimension(160, 150));
  panelShape.add(panelCircle);

  lbCircle = new JLabel("Circle");
  lbCircle.setFont(new Font("Tahoma", Font.PLAIN, 12));
  panelCircle.add(lbCircle);

  tbCircle = new JTable(new CircleTable(listShape));
  tbCircle.setFont(new Font("Tahoma", Font.PLAIN, 12));

  scrollCircle = new JScrollPane();
  scrollCircle.setPreferredSize(new Dimension(150, 120));
  scrollCircle.setViewportView(tbCircle);
  panelCircle.add(scrollCircle);

  panelRectangle = new JPanel();
  // panelRectangle.setBorder(BorderFactory.createLineBorder(Color.black));
  panelRectangle.setLayout(new FlowLayout(0));
  panelRectangle.setPreferredSize(new Dimension(180, 150));
  panelShape.add(panelRectangle);

  lbRectangle = new JLabel("Rectangle");
  lbRectangle.setFont(new Font("Tahoma", Font.PLAIN, 12));
  panelRectangle.add(lbRectangle);

  tbRect = new JTable(new RectangleTable(listShape));
  tbRect.setFont(new Font("Tahoma", Font.PLAIN, 12));

  scrollRectangle = new JScrollPane();
  scrollRectangle.setPreferredSize(new Dimension(170, 100));
  scrollRectangle.setViewportView(tbRect);

  panelRectangle.add(scrollRectangle);

  panelDraw = new JPanel();
  // panelDraw.setBorder(BorderFactory.createLineBorder(Color.green));
  panelDraw.setLayout(new FlowLayout());
  panelDraw.setPreferredSize(new Dimension(700, 400));
  this.add(panelDraw);

  panelButton = new JPanel();
  // panelButton.setBorder(BorderFactory.createLineBorder(Color.black));
  panelButton.setLayout(new GridLayout(1, 1, 15, 0));
  panelDraw.add(panelButton);

  btAdd = new JButton("Add");
  btAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btAdd.addKeyListener(new MKeyListener());
  btAdd.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    if (start) {
     JOptionPane.showMessageDialog(frame, "Please press Ctrl to change drawing circle or rectangle.");
    }
    Status.btChoice = Status.ADD;
    start = false;
   }

  });
  panelButton.add(btAdd);

  btRemove = new JButton("Remove");
  btRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btRemove.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {

    if (!listShape.isEmpty()) {
     if (tbCircle.getSelectedRow() != -1) {
      // remove all circle
      Graphics g = panelPaint.getGraphics();
      g.setColor(Color.darkGray);
      for (Shape Shape : listShape) {
       if (Shape.getType().equals("circle")) {
        g.fillOval(Shape.getX(), Shape.getY(), Shape.getRadius(), Shape.getRadius());
       }
      }

      // remove from table circle
      int row = tbCircle.getSelectedRow();
      String str = (String) tbCircle.getValueAt(row, 0);
      str = str.substring(1, str.length() - 1);
      String[] arrStr = str.split(",");
      int x = Integer.valueOf(arrStr[0].trim());
      int y = Integer.valueOf(arrStr[1].trim());
      int radius = (int) tbCircle.getValueAt(row, 1);
      for (Shape Shape : listShape) {
       if (Shape.getX() == x && Shape.getY() == y && Shape.getRadius() == radius && Shape.getType().equals("circle")) {
        listShape.remove(Shape);
        break;
       }
      }

      tbCircle = new JTable(new CircleTable(listShape));
      scrollCircle.setViewportView(tbCircle);

      // draw again after delete one
      
      
      
      for (Shape Shape : listShape) {
       if (Shape.getType().equals("circle")) {
        if (Shape.getStyle2D3D().equals("D2")) {
         g.setColor(Color.WHITE);
        } else {
         g.setColor(Color.GREEN);
        }
        g.fillOval(Shape.getX(), Shape.getY(), Shape.getRadius(), Shape.getRadius());
        
       }
      }
     } else if (tbRect.getSelectedRow() != -1) {
      // remove all rectangle
      Graphics g = panelPaint.getGraphics();
      g.setColor(Color.darkGray);
      for (Shape Shape : listShape) {
       if (Shape.getType().equals("rectangle")) {
        g.fillRect(Shape.getX(), Shape.getY(), Shape.getWidth(), Shape.getHeight());
       }
      }
      
      // remove from table
      int row = tbRect.getSelectedRow();
      String str = (String) tbRect.getValueAt(row, 0);
      str = str.substring(1, str.length() - 1);
      String[] arrStr = str.split(",");
      int x = Integer.valueOf(arrStr[0].trim());
      int y = Integer.valueOf(arrStr[1].trim());
      int width = (int) tbRect.getValueAt(row, 1);
      int height = (int) tbRect.getValueAt(row, 2);
      for (Shape Shape : listShape) {
       if (Shape.getX() == x && Shape.getY() == y && Shape.getWidth() == width && Shape.getHeight() == height && Shape.getType().equals("rectangle")) {
        listShape.remove(Shape);
        break;
       }
      }

      tbRect = new JTable(new RectangleTable(listShape));
      scrollRectangle.setViewportView(tbRect);
      
      // draw again after delete one
      
      for (Shape Shape : listShape) {
       if (Shape.getStyle2D3D().equals("D2")) {
        g.setColor(Color.WHITE);
       } else {
        g.setColor(Color.GREEN);
       }
       if (Shape.getType().equals("rectangle")) {
        g.fillRect(Shape.getX(), Shape.getY(), Shape.getWidth(), Shape.getHeight());
       }
      }
     }
    }
   }

  });
  panelButton.add(btRemove);
  
  

  btUndo = new JButton("Undo");
  btUndo.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btUndo.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    try {
     // remove all Shape
     Graphics g = panelPaint.getGraphics();
     g.setColor(Color.darkGray);
     for (Shape Shape : listShape) {
      if (Shape.getType().equals("circle")) {
       g.fillOval(Shape.getX(), Shape.getY(), Shape.getRadius(), Shape.getRadius());
      } else {
       g.fillRect(Shape.getX(), Shape.getY(), Shape.getWidth(), Shape.getHeight());
      }
     }
     
     undoManager.undo();
     
     
     // update table
     tbCircle = new JTable(new CircleTable(listShape));
     scrollCircle.setViewportView(tbCircle);
     
     tbRect = new JTable(new RectangleTable(listShape));
     scrollRectangle.setViewportView(tbRect);

     // draw again after delete one
     for (Shape Shape : listShape) {
      if (Shape.getStyle2D3D().equals("D2")) {
       g.setColor(Color.WHITE);
      } else {
       g.setColor(Color.GREEN);
      }
      if (Shape.getType().equals("circle")) {
       g.fillOval(Shape.getX(), Shape.getY(), Shape.getRadius(), Shape.getRadius());
      } else {
       g.fillRect(Shape.getX(), Shape.getY(), Shape.getWidth(), Shape.getHeight());
      }
     }
     
     btUndo.setEnabled(undoManager.canUndo());
     btRedo.setEnabled(undoManager.canRedo());
     
    } catch(Exception ex) {
     
    }
   }

  });
  panelButton.add(btUndo);

  btRedo = new JButton("Redo");
  btRedo.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btRedo.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    
    // remove all Shape
    Graphics g = panelPaint.getGraphics();
    g.setColor(Color.darkGray);
    for (Shape Shape : listShape) {
     if (Shape.getType().equals("circle")) {
      g.fillOval(Shape.getX(), Shape.getY(), Shape.getRadius(), Shape.getRadius());
     } else {
      g.fillRect(Shape.getX(), Shape.getY(), Shape.getWidth(), Shape.getHeight());
     }
    }
    
    undoManager.redo();
    
    // update table
    tbCircle = new JTable(new CircleTable(listShape));
    scrollCircle.setViewportView(tbCircle);
    
    tbRect = new JTable(new RectangleTable(listShape));
    scrollRectangle.setViewportView(tbRect);

    // draw again after delete one
    for (Shape Shape : listShape) {
     if (Shape.getStyle2D3D().equals("D2")) {
      g.setColor(Color.WHITE);
     } else {
      g.setColor(Color.GREEN);
     }
     if (Shape.getType().equals("circle")) {
      g.fillOval(Shape.getX(), Shape.getY(), Shape.getRadius(), Shape.getRadius());
     } else {
      g.fillRect(Shape.getX(), Shape.getY(), Shape.getWidth(), Shape.getHeight());
     }
    }
    
    btUndo.setEnabled(undoManager.canUndo());
    btRedo.setEnabled(undoManager.canRedo());

   }

  });
  panelButton.add(btRedo);

  btPrint = new JButton("Print");
  btPrint.setFont(new Font("Tahoma", Font.PLAIN, 14));
  btPrint.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

   }

  });
  panelButton.add(btPrint);

  bt3D = new JButton("3D");
  bt3D.setFont(new Font("Tahoma", Font.PLAIN, 14));
  bt3D.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    bt2D.setEnabled(true);
    bt3D.setEnabled(false);
    Status.style2D3D = Status.D3;
   }

  });
  panelButton.add(bt3D);

  bt2D = new JButton("2D");
  bt2D.setFont(new Font("Tahoma", Font.PLAIN, 14));
  bt2D.setEnabled(false);
  bt2D.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    bt2D.setEnabled(false);
    bt3D.setEnabled(true);
    Status.style2D3D = Status.D2;
   }

  });
  panelButton.add(bt2D);

  panelPaint = new JPanel();
  panelPaint.setBorder(BorderFactory.createLineBorder(Color.red));
  panelPaint.setLayout(new GridLayout());
  panelPaint.setBackground(Color.darkGray);
  panelPaint.setPreferredSize(new Dimension(700, 360));
  panelPaint.addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mousePressed(MouseEvent e) {
    last = e.getPoint();
   }

   @Override
   public void mouseReleased(MouseEvent e) {
    if (Status.btChoice == Status.ADD) {
     int dx = present.x - last.x;
     int dy = present.y - last.y;
     x = last.x;
     y = last.y;
     int radius = (int) Math.sqrt(dx * dx + dy * dy);

     Graphics g = panelPaint.getGraphics();
     if (Status.style2D3D == Status.D2) {
      g.setColor(Color.WHITE);
     } else {
      g.setColor(Color.GREEN);
     }

     // draw circle
     if (Status.styleDraw == Status.DRAW_CIRCLE) {
      g.fillOval(x, y, radius, radius);
      Shape = new Shape(x, y, radius, "circle", Status.style2D3D == Status.D2 ? "D2" : "D3");
      listShape.add(Shape);
      tbCircle = new JTable(new CircleTable(listShape));
      scrollCircle.setViewportView(tbCircle);
      
      undoManager.undoableEditHappened(
        new UndoableEditEvent(panelPaint, new UndoablePaint(listShape, Shape)));
     } else { // draw rectangle
      g.fillRect(x, y, dx, dy);
      Shape = new Shape(x, y, dx, dy, "rectangle", Status.style2D3D == Status.D2 ? "D2" : "D3");
      listShape.add(Shape);
      
      tbRect = new JTable(new RectangleTable(listShape));
      scrollRectangle.setViewportView(tbRect);
      
      undoManager.undoableEditHappened(
        new UndoableEditEvent(panelPaint, new UndoablePaint(listShape, Shape)));
     }
    }
    btUndo.setEnabled(undoManager.canUndo());
    btRedo.setEnabled(undoManager.canRedo());
   }

   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }

   @Override
   public void mouseDragged(MouseEvent e) {

   }

   @Override
   public void mouseMoved(MouseEvent e) {
   }
  });

  panelPaint.addMouseMotionListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mousePressed(MouseEvent e) {
   }

   @Override
   public void mouseReleased(MouseEvent e) {
   }

   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }

   @Override
   public void mouseDragged(MouseEvent e) {
    present = e.getPoint();
   }

   @Override
   public void mouseMoved(MouseEvent e) {
   }
  });
  panelDraw.add(panelPaint);
  
  undoManager.undoableEditHappened(
    new UndoableEditEvent(panelPaint, new UndoablePaint(listShape, Shape)));
  btUndo.setEnabled(undoManager.canUndo());
  btRedo.setEnabled(undoManager.canRedo());

 }

 private static void createAndShowGUI() {
  frame = new JFrame("TMAPaint");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  javax.swing.JComponent newContentPane = new TMAPaint();
  newContentPane.setOpaque(true);

  frame.setContentPane(newContentPane);
  frame.setResizable(false);
  frame.pack();
  frame.setVisible(true);
 }

 public static void main(String[] args) {
  javax.swing.SwingUtilities.invokeLater(new Runnable() {
   public void run() {
    createAndShowGUI();
   }
  });
 }

}