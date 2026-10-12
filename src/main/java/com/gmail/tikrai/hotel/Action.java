package com.gmail.tikrai.hotel;

import java.util.function.Function;

public record Action(
    int id,
    String name,
    Function<Hotel, String> action
) {}
