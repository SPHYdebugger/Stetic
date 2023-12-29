package com.sphy.stetic.Domain;

public class Order {

    private long id;
    private String number;
    private boolean onlineOrder= false;
    private String creationDate;
    private Client client;

    public Order(long id, String number, boolean onlineOrder, String creationDate, Client client) {
        this.id = id;
        this.number = number;
        this.onlineOrder = onlineOrder;
        this.creationDate = creationDate;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isOnlineOrder() {
        return onlineOrder;
    }

    public void setOnlineOrder(boolean onlineOrder) {
        this.onlineOrder = onlineOrder;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
