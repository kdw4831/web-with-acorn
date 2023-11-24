package test.todo.dto;

public class TodoDto {

	private int num;
	
	private String todo;
	
	private String done;
		
	public TodoDto() {}

	public TodoDto(int num, String todo, String done) {
		super();
		this.num = num;
		this.todo = todo;
		this.done = done;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}
	
	

	
}


