package designpattern;

import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;

public class UndoablePaint extends AbstractUndoableEdit {
 
 protected List<Shape> listShape;
 protected Shape Shape;
 
 public UndoablePaint(List<Shape> listShape, Shape Shape) {
  this.listShape = listShape;
  this.Shape = Shape;
 }
 
 @Override
 public void undo() {
  
  if (!listShape.isEmpty()) {
   super.undo();
   listShape.remove(Shape);
  }
 }
 
 @Override
 public void redo() {
  super.redo();
  listShape.add(Shape);
 }

}
