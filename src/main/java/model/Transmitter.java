package model;

public interface Transmitter<T> {
    T load();
    void save(T content);
}
