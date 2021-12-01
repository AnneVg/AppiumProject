package lesson16;

public enum MobileType {
    ANDROID("Android", 7),
    IOS("iPhone", 13);
    private final String name;
    private final int version;
    MobileType(String name, int version){
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return this.name;
    }
    public int getVersion(){
        return this.version;
    }
}
