package lk.sliit.finestay;

public class Property {

    public  String name;
    private Integer guests;
    private Integer rooms;
    private Integer baths;



    public Property() {
        this.name = name;
        this.guests = guests;
        this.rooms = rooms;
        this.baths = baths;

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


}
