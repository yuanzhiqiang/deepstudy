package com.yuanzq.jdk.collection;

public class SelfHashMap<K ,E> {

	
	public SelfHashMap(){
		entitys = new Entity[DEFAULTCAPICITY]; 
	}
	
	public SelfHashMap(int capicity){
		entitys = new Entity[capicity]; 
		currentCapicity = capicity;
	}
	
	public SelfHashMap<K ,E> put(K k, E e){
		
		int hash = k.hashCode();
		int index = hash(hash);
		for(Entity entity = entitys[index]; null != entity; entity = entity.next){
			if(hash == entity.hash){
				entity.element = e;
				return this;
			}
		}
		Entity<E> entity = new Entity<E>();
		entity.hash = hash;
		entity.element = e;
		entity.next = entitys[index];
		entitys[index] = entity;
		size++;
		if(currentCapicity * LOADFACTOR < size){
			rehash();
		}
		return this;
	}
	
	public E get(K k){
		int hash = k.hashCode();
		int index = hash(hash);
		for(Entity entity = entitys[index]; null != entity; entity = entity.next){
			if(hash == entity.hash){
				return (E) entity.element;
			}
		}
		return null;
		
	}
	
	private void rehash(){
		Entity[] newEntitys = new Entity[currentCapicity * 2];
		Entity[] oldEntitys = entitys;
		entitys = newEntitys;
		for(int i = 0; i < oldEntitys.length; i++){
			Entity entity = oldEntitys[i];
			while(entity != null){
				Entity oldEntity = entity;
				entity = entity.next;
				int index = hash(oldEntity.hash);
				oldEntity.next = entitys[index];
				entitys[index] = oldEntity;
			}
		}
		currentCapicity = currentCapicity * 2;
		System.out.println("rehashed");
	}
	
	private int hash(int hash){
		 return (hash & 0x7FFFFFFF) % entitys.length;
	}
	//默认的存储能力
	private final int DEFAULTCAPICITY = 16;
	
	//当前的存储能力
	private  int currentCapicity = 16;
	
	private int size;
	
	//负载因子
	private final float LOADFACTOR = 0.75f; 
	
	Entity[] entitys;
	
	class Entity<E> {
		private Entity<E> next;
		private E element;
		private int hash;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		SelfHashMap sh = new SelfHashMap();
		for(int i = 0;i < 15;i++){
			sh.put("yuanzhiqK" + i, "yuanzhiqW" + i);
			System.out.println(sh.get("yuanzhiqK" + i));
		}
		System.out.println(sh.size);
	}
}
