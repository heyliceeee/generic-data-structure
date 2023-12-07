package org.example.interfaces;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T>
{
    /**
     * adiciona o elemento específico no local adequado na árvore.
     * @param element o elemento a ser adicionado a árvore
     */
    public void addElement(T element);


    /**
     * remove e retorna o elemento específico da árvore
     * @param targetElement o elemento a ser removido da árvore
     * @return o elemento da árvore
     */
    public T removeElement(T targetElement);


    /**
     * remove todas as ocorrências do elemento específico da árvore
     * @param targetElement o elemento que todas as ocorrencias serão removidas
     */
    public void removeAllOcurrences(T targetElement);


    /**
     * remove e retorna o elemento mais pequeno da árvore
     * @return o elemento mais pequeno da árvore
     */
    public T removeMin();


    /**
     * remove e retorna o elemento maior da árvore
     * @return o elemento maior da árvore
     */
    public T removeMax();


    /**
     * retorna uma referência do elemento mais pequeno da árvore
     * @return uma referência do elemento mais pequeno da árvore
     */
    public T findMin();


    /**
     * retorna uma referência do elemento maior da árvore
     * @return uma referência do elemento maior da árvore
     */
    public T findMax();
}
