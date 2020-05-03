package it.polito.tdp.artsmia.model;

public class Esposizioni {
   private int id;
   private int num;
public Esposizioni(int id, int num) {
	super();
	this.id = id;
	this.num = num;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + num;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Esposizioni other = (Esposizioni) obj;
	if (id != other.id)
		return false;
	if (num != other.num)
		return false;
	return true;
}
@Override
public String toString() {
	return String.format("Esposizione id=%s, num=%s\n", id, num);
}
   
}
