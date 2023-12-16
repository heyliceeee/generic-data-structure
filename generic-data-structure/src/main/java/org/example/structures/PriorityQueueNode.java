package org.example.structures;

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode>
{
    private static int nextOrder = 0;
    private int priority;
    private int order;
    private T element;


    /**
     * cria um novo priority queue node com um dado especifico
     * @param obj o elemento da nova priority queue node
     * @param prio a prioridade do novo queue node
     */
    public PriorityQueueNode(T obj, int prio)
    {
        this.element = obj;
        this.priority = prio;
        this.order = nextOrder;

        nextOrder++;
    }


    /**
     * retorna a prioridade do node
     * @return a prioridade do node
     */
    public int getPriority()
    {
        return priority;
    }

    /**
     * retorna a ordem do node
     * @return a ordem do node
     */
    public int getOrder()
    {
        return order;
    }

    /**
     * retorna o elemento do node
     * @return o elemento do node
     */
    public T getElement()
    {
        return element;
    }


    @Override
    public String toString()
    {
        String temp = (element.toString() + priority + order);

        return temp;
    }

    /**
     * retorna 1 se o node atual tive prioridade mais alta do que o node dado, e caso contrário, -1
     * @param obj the object to be compared.
     * @return 1 se o node atual tive prioridade mais alta do que o node dado, e caso contrário, -1
     */
    @Override
    public int compareTo(PriorityQueueNode obj)
    {
        int result;
        PriorityQueueNode<T> temp = obj;

        if(priority > temp.getPriority())
        {
            result = 1;
        }
        else if(priority < temp.getPriority())
        {
            result = -1;
        }
        else if(order > temp.getOrder())
        {
            result = 1;
        }
        else
        {
            result = -1;
        }

        return result;
    }
}
