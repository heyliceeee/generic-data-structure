package org.example.interfaces;

public interface QueueADT<T>
{
    /**
     * adiciona um elemento ao rear da queue
     * @param element o elemento que vai ser adicionado na rear da queue
     */
    public void enqueue(T element);

    /**
     * remove e retorna o elemento da front da queue
     * @return o elemento da front da queue
     */
    public T dequeue();

    /**
     * retorna, sem remover, o elemento da front da queue
     * @return o primeiro elemento da queue
     */
    public T first();

    /**
     * retorna true se a queue não contém elementos
     * @return true se a queue não contém elementos
     */
    public boolean isEmpty();

    /**
     * retorna o número de elementos da queue
     * @return o tamanho da queue
     */
    public int size();

    /**
     * retorna uma string da queue
     * @return uma string da queue
     */
    public String toString();
}
