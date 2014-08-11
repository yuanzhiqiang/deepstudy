package com.yuanzq.jdk.collection;

public class SelfArrayList {

	//默认的存储能力
	private final int DEFAULTCAPACITY = 10;
	
	//每次动态扩展时增长的存储能力
	private final int EXTENTCAPACITY  = 10;
	
	//当前的存储能力
	private int CURRENTCAPACITY  = 10;
	
	//当前游标
	private int offset = -1;
	
	Object[] obj = null;
	
	public SelfArrayList(){
		obj = new Object[DEFAULTCAPACITY];
	}
	
	public SelfArrayList(int capacity){
		obj = new Object[capacity];
		CURRENTCAPACITY = capacity;
	}

	public  Object get(){
		return obj[offset];
	}
	
	public  Object get(int index){
		if(index  >= CURRENTCAPACITY){
			throw new RuntimeException("请求的元素不在容器存储的范围内");
		}
		return obj[index];
	}
	
	public SelfArrayList add(Object object){
		if(CURRENTCAPACITY <= offset + 1){
			Object[] newObject = new Object[CURRENTCAPACITY + EXTENTCAPACITY];
			System.arraycopy(obj, 0, newObject, 0, CURRENTCAPACITY);
			this.obj = newObject;
			CURRENTCAPACITY = CURRENTCAPACITY + EXTENTCAPACITY;
		}
		obj[++offset] = object;
		return this;
	}
	
	public SelfArrayList remove(){
		obj[offset--] = null;
		return this;
	}
	
	public SelfArrayList remove(int index){
		if(index >= CURRENTCAPACITY){
			throw new RuntimeException("无法删除存储能力外的元素");
		}
		for(int i = index; i < offset; i++){
			obj[i] = obj[i + 1];
		}
		obj[offset--] = null;
		return this;
	}
	
	public int size(){
		return offset + 1;
	}
	public static void main(String[] args) {
		SelfArrayList list = new SelfArrayList();
		for(int i = 0; i < 100; i++){
			list.add("yuanzhiqiang" + i);
		}
		list.remove(99);
		for(int i = 0; i < list.size(); i++){
			System.out.println((String)list.get(i));
		}
	}
}
