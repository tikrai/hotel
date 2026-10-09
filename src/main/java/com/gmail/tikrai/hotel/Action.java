package com.gmail.tikrai.hotel;

import java.util.function.Predicate;

public record Action(
    int id,
    String name,
    Predicate<Hotel> action
) {}
