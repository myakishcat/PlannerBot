import java.util.LinkedList;

public class EventList implements SpreadSheet{

    public LinkedList<Event> list = new LinkedList<>();

    public void printList(){
        for (int i = 0; i < list.size(); i++){
            System.out.print(i+1 + ". ");
            list.get(i).print();
            System.out.println();
        }
    }

    public void add(Event object){
        list.add(object);
        System.out.println("Мероприятие добавлено");
    }

    public void del(int num){
        if (num < 0 || num > list.size()){
            System.out.println("Некорректный номер");
        }
        else {
            list.remove(num - 1);
            System.out.println("Мероприятие удалено");
        }
    }

    public void change(int num){
        Event event = list.get(num - 1);
        System.out.println("Введите новые данные или команду /same, чтобы оставить те же");

        for(int i = 0; i < Event.format.length; i++){
            System.out.println(Event.format[i]);
            String enter = Utils.getString();

            if (!enter.equals("/same")){
                switch (Event.format[i]) {
                    case "name" -> event.setName(enter);
                    case "start" -> event.setStart(enter);
                    case "end" -> event.setEnd(enter);
                    case "description" -> event.setDescription(enter);
                }
            }
        }

        System.out.print("Мероприятие изменено: ");
        event.print();
        System.out.println();
    }
}