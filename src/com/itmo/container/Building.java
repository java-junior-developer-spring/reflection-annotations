package com.itmo.container;

import com.itmo.container.annotations.Between;

public class Building {
    @Between(min = 3, max = 14, errorMessage = "Значение должно быть в диапазоне от 3 до 14, включительно")
    private final int numberOfFloors;
    @Between(min = 500, max = 100_000)
    public final int price;

    public Building(int numberOfFloors, int price) {
        this.numberOfFloors = numberOfFloors;
        this.price = price;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Building{" +
                "numberOfFloors=" + numberOfFloors +
                ", price=" + price +
                '}';
    }
}
