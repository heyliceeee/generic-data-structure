package org.example.structures;

public class PriorityQueue<T> extends ArrayHeap<PriorityQueueNode<T>>
{

    /**
     * cria uma priority queue vazia
     */
    public PriorityQueue()
    {
        super();
    }


    /**
     * adiciona o elemento dado ao priority queue
     * @param obj o elemento a ser adicionado ao priority queue
     * @param priority int da prioridade do elemento a ser adicionado
     */
    public void addElement(T obj, int priority)
    {
        PriorityQueueNode<T> node = new PriorityQueueNode<T>(obj, priority);

        super.addElement(node);
    }


    /**
     * remove o segundo elemento com maior prioridade da queue e retorna a referencia
     * @return a referencia do segundo elemento com maior prioridade da queue
     */
    public T removeNext()
    {
        PriorityQueueNode<T> temp = (PriorityQueueNode<T>) super.removeMin();

        return temp.getElement();
    }
}
