package com.dk.agriculture;

/**
 * Created by Anurag on 24-03-2018.
 */

public class AddModel {
     public String equipment,equipmentdesc, priceperday, maxdays, id;

    public AddModel(String equipment, String equipmentdesc, String priceperday, String maxdays, String id) {
        this.equipment = equipment;
        this.equipmentdesc = equipmentdesc;
        this.priceperday = priceperday;
        this.maxdays = maxdays;
        this.id = id;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getEquipmentdesc() {
        return equipmentdesc;
    }

    public void setEquipmentdesc(String equipmentdesc) {
        this.equipmentdesc = equipmentdesc;
    }

    public String getPriceperday() {
        return priceperday;
    }

    public void setPriceperday(String priceperday) {
        this.priceperday = priceperday;
    }

    public String getMaxdays() {
        return maxdays;
    }

    public void setMaxdays(String maxdays) {
        this.maxdays = maxdays;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
