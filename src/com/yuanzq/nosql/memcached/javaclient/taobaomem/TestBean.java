package com.yuanzq.nosql.memcached.javaclient.taobaomem;



import java.io.Serializable;

public class TestBean implements Serializable{   
    private static final long serialVersionUID = 5344571864700659321L;   
       
    private String name;   
    private Integer age;   
    //get、set方法略   
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
    
}  
