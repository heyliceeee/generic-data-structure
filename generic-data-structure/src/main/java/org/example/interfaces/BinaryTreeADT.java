package org.example.interfaces;

import java.util.Iterator;

public interface BinaryTreeADT<T>
{
    /**
     * retorna uma referencia do elemento root
     * @return uma referencia do elemento root
     */
    public T getRoot();

    /**
     * retorna true se a árvore binaria estiver vazia, caso contrário retorna falso
     * @return true se a árvore binaria estiver vazia
     */
    public boolean isEmpty();

    /**
     * retorna o número de elementos da árvore binária
     * @return o número de elementos da árvore
     */
    public int size();

    /**
     * retorna true se a árvore binária conter o elemento procurado, caso contrário retorna false
     * @param targetElement o elemento a ser procurado
     * @return true se a árvore conter o elemento procurado
     */
    public boolean contains(T targetElement);

    /**
     * retorna uma referencia de um especifico elemento se este for encontrado na árvore binaria, caso o elemento não for encontrado, retorna uma exceção
     * @param targetElement o elemento a ser procurado na árvore
     * @return uma referencia de um especifico elemento se este for encontrado na árvore
     */
    public T find(T targetElement);

    /**
     * retorna a árvore binária
     * @return a árvore
     */
    public String toString();

    /**
     * executa uma travessia inorder na árvore binária chamando um método inorder recursivo sobrecarregando que começa com a root
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorInOrder();

   /**
     * executa uma travessia preorder na árvore binária chamando um método preorder recursivo sobrecarregando que começa com a root
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * executa uma travessia postorder na árvore binária chamando um método postorder recursivo sobrecarregado que começa com a root
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorPostOrder();


    /**
     * executa uma travessia levelorder na árvore binária utilizando uma queue
     * @return um iterador sobre os elementos da árvore
     */
    public Iterator<T> iteratorLevelOrder();
}
