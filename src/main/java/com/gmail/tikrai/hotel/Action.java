package com.gmail.tikrai.hotel;

import java.util.function.Consumer;

public record Action(
    int id,
    String name,
    Consumer<Hotel> action
) {}
