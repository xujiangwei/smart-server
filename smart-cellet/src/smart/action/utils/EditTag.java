package smart.action.utils;

public enum EditTag {
	ADD("添加标签",1),DELETE("删除标签",2),MODIFY("修改标签",3),OPTAG("显示或隐藏标签",4);
	//成员变量
	private String name;
	private int index;
	//构造方法
	private EditTag(String name,int index){
		this.name=name;
		this.index=index;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString (){
		return this.index+"-"+this.name;
	}
}
