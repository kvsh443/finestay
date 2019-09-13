package lk.sliit.finestay;

public class Property {

    private String name;
    private Integer guests;
    private Integer rooms;
    private Integer baths;
    private Integer value;

    public Property() {
    }

    public Property(String name, Integer guests, Integer rooms, Integer baths, Integer value) {
        this.name = name;
        this.guests = guests;
        this.rooms = rooms;
        this.baths = baths;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
