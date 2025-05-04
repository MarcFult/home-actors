package at.fhv.sysarch.lab2.homeautomation.messages;

import java.util.List;

public class GroceryOrder {
    public final List<String> items;

    public GroceryOrder(List<String> items) {
        this.items = items;
    }
}