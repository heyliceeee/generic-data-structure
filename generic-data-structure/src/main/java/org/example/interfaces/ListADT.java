package org.example.interfaces;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T>
{
    /**
     * adicionar no tail da lista
     * @param element
     */
    //public void add(T element);

    /**
     * remove e retorna o primeiro elemento da lista
     * @return o primeiro elemento da lista
     */
    public T removeFirst();

    /**
     * remove e retorna o ultimo elemento da lista
     * @return o ultimo elemento da lista
     */
    public T removeLast();

    /**
     * remove e retorna o elemento especifico da lista
     * @param element o elemento que vai ser removido da lista
     * @return
     */
    public T remove(T element);

    /**
     * retorna a referencia do primeiro elemento da lista
     * @return a referencia do primeiro elemento da lista
     */
    public T first();

    /**
     * retorna a referencia do ultimo elemento da lista
     * @return a referencia do ultimo elemento da lista
     */
    public T last();

    /**
     * retorna true se na lista conter o elemento especifico na lista
     * @param target o alvo que vai ser procurado na lista
     * @return true se na lista conter o elemento
     */
    public boolean contains(T target);

    /**
     * retorna true se na lista nao conter elementos
     * @return true se na lista nao conter elementos
     */
    public boolean isEmpty();

    /**
     * retorna o numero de elementos da lista
     * @return o numero de elementos da lista
     */
    public int size();

    /**
     * retorna um iterator de elementos da lista
     * @return um iterator de elementos da lista
     */
    public Iterator<T> iterator();

    /**
     * retorna a lista
     * @return a lista
     */
    @Override
    public String toString();
}
