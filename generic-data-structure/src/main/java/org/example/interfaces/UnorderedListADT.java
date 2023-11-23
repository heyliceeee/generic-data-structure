package org.example.interfaces;

public interface UnorderedListADT<T> extends ListADT<T>
{
    /**
     * adiciona um elemento específico na head da lista
     * @param element o elemento a ser adicionado na head
     */
    public void addToFront(T element);

    /**
     * adiciona um elemento específico na tail da lista
     * @param element o elemento a ser adicionado na tail
     */
    public void addToRear(T element);

    /**
     * adiciona um elemento específico depois do elemento mencionado
     * @param element o elemento a ser adicionado na lista
     * @param target o elemento mencionado, onde o novo elemento será adicionado ao lado direito
     */
    public void addAfter(T element, T target);
}
