package eak_04;

public class notebook {

    int id;
    String name;
    String сity;
    int num;

    public notebook() {
        this.id = 0;
        this.name = "";
        this.сity = "";
        this.num = 0;
    }

    public notebook(String name, String сity, int num) {
        this.id = 0;
        this.name = name;
        this.сity = сity;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return сity;
    }

    public int getNum() {
        return num;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.сity = city ;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.format("Имя=%s, город=%s, Номер=%d", name, сity, num);
    }
}
