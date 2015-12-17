package com.adminTool.bo;

import java.io.Serializable;

public class ValentineHero implements Serializable{
private Integer hero_id1;
private Integer hero_id2;
private String name1;
private String name2;


public ValentineHero(Integer hero_id1, Integer hero_id2) {

	this.hero_id1 = hero_id1;
	this.hero_id2 = hero_id2;
}
public ValentineHero() {

}
public String getName1() {
	return name1;
}
public void setName1(String name1) {
	this.name1 = name1;
}
public String getName2() {
	return name2;
}
public void setName2(String name2) {
	this.name2 = name2;
}
public Integer getHero_id1() {
	return hero_id1;
}
public void setHero_id1(Integer hero_id1) {
	this.hero_id1 = hero_id1;
}
public Integer getHero_id2() {
	return hero_id2;
}
public void setHero_id2(Integer hero_id2) {
	this.hero_id2 = hero_id2;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((hero_id1 == null) ? 0 : hero_id1.hashCode());
	result = prime * result + ((hero_id2 == null) ? 0 : hero_id2.hashCode());
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
	ValentineHero other = (ValentineHero) obj;
	if (hero_id1 == null) {
		if (other.hero_id1 != null)
			return false;
	} else if (!hero_id1.equals(other.hero_id1))
		return false;
	if (hero_id2 == null) {
		if (other.hero_id2 != null)
			return false;
	} else if (!hero_id2.equals(other.hero_id2))
		return false;
	return true;
}


}
