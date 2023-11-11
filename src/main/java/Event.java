public class Event implements Entity {

    private String name;
    private String start;
    private String end;
    private String description;
    public final static String[] format = {"name", "start", "end", "description"};

    public Event(String name, String start, String end, String description){
        this.name = name;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public void setName(String name) { this.name = name; }
    public void setStart(String start) { this.start = start; }
    public void setEnd(String end) { this.end = end; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public void print(){
        System.out.print(name + "\t");
        System.out.print(start + "\t");
        System.out.print(end + "\t");
        System.out.print(description + "\t");
    }
}