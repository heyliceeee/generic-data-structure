package org.example.interfaces;

public interface StackADT<T> {
    /**
     * adiciona um elemento ao top da stack
     * @param element elemento a ser colocado na stack
     */
    public void push(T element);

    /**
     * remove e retorna o elemento do top da stack
     * @return T elemento removido do top da stack
     */
    public T pop();

    /**
     * retorna sem remover o elemento top da stack
     * @return T elemento do top da stack
     */
    public T peek();

    /**
     * retorna true se a stack não conter elementos
     * @return boolean dependendo ou não se a stack está vazia
     */
    public boolean isEmpty();

    /**
     * retorna o numero de elementos da stack
     * @return int numero de elementos da stack
     */
    public int size();

    /**
     * retorna uma string da stack
     * @return String da stack
     */
    @Override
    public String toString();
}
