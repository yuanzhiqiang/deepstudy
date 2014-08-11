1.缓冲区属性：

容量(Capacity)
	在缓冲区创建时指定，永远不能被修改

上界(Limit)
	缓冲区现存元素的计数
	
位置(Position)
	下一个被读或者写的元素的索引，会随着get(),put()方法随时更新
	
标记(Mark)
	调用mark()指定mark = position 调用reset指定position = mark,未设定的情况下是未定义的