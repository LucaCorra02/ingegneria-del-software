package it.unimi.di.sweng.esame;

public interface Observable<T> {
    void addObserver(Observer<T> observer);
    void notifyObservers();
    T getState();
}
