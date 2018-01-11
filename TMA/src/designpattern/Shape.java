package designpattern;

public class Shape {
	 
	 private int x;
	 private int y;
	 private int radius;
	 private int width;
	 private int height;
	 private String type;
	 private String style2D3D;
	 
	 public Shape() {
	  
	 }
	 
	 public Shape(int x, int y, int radius, String type, String style2D3D) {
	  this.x = x;
	  this.y = y;
	  this.radius = radius;
	  this.type = type;
	  this.style2D3D = style2D3D;
	 }
	 
	 public Shape(int x, int y, int width, int height, String type, String style2D3D) {
	  this.x = x;
	  this.y = y;
	  this.width = width;
	  this.height = height;
	  this.type = type;
	  this.style2D3D = style2D3D;
	 }

	 public int getX() {
	  return x;
	 }

	 public void setX(int x) {
	  this.x = x;
	 }

	 public int getY() {
	  return y;
	 }

	 public void setY(int y) {
	  this.y = y;
	 }

	 public int getRadius() {
	  return radius;
	 }

	 public void setRadius(int radius) {
	  this.radius = radius;
	 }

	 public int getWidth() {
	  return width;
	 }

	 public void setWidth(int width) {
	  this.width = width;
	 }

	 public int getHeight() {
	  return height;
	 }

	 public void setHeight(int height) {
	  this.height = height;
	 }

	 public String getType() {
	  return type;
	 }

	 public void setType(String type) {
	  this.type = type;
	 }

	 public String getStyle2D3D() {
	  return style2D3D;
	 }

	 public void setStyle2D3D(String style2d3d) {
	  style2D3D = style2d3d;
	 }
	 
}