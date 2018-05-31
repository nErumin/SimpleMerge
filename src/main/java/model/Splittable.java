package model;

public interface Splittable {
    Iterable<String> lines();
    Iterable<String> newLineIncludingLines();
}
