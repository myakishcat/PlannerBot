public class Task implements Entity {
    private String name;
    private String deadline;
    private String description;
    final static String[] format = new String[]{"name", "deadline", "description"};

    public Task(String name, String deadline, String description){
        this.name = name;
        this.deadline = deadline;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void print(){
        System.out.print(name + "\t");
        System.out.print(deadline + "\t");
        System.out.print(description + "\t");
    }
}