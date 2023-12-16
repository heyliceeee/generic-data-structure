package org.example.interfaces;

public interface HeapADT<T> extends BinaryTreeADT<T>
{
    /**
     * adiciona um objeto especifico ao heap
     * @param obj o elemento a ser adicionado ao heap
     */
    public void addElement(T obj);

    /**
     * remove o elemento mais pequeno do heap
     * @return o elemento mais pequeno do heap
     */
    public T removeMin();

    /**
     * retorna a referencia do elemento mais pequeno do heap
     * @return o elemento mais pequeno do heap
     */
    public T findMin();
}
