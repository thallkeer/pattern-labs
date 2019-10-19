package main.lab4.dao;

public interface IDAO<T>  {
    void write(T entity);
    T read();
}
