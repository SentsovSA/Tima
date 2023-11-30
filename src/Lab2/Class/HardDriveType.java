package Lab2.Class;
//(Тип: код, наименование)
public class HardDriveType {
    private int kod;
    private String name;

    public HardDriveType(int kod, String name) {
        this.kod = kod;
        this.name = name;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HardDriveType{" +
                "kod=" + kod +
                ", name='" + name + '\'' +
                '}';
    }
}
