package org.example.interfaces;

public interface OrderedListADT<T> extends ListADT<T>
{
    /**
     * adiciona um elemento especifico na lista
     * @param element o elemento a ser adicionado
     */
    public void add(T element);
}
