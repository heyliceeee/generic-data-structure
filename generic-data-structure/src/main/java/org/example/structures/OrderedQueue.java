package org.example.structures;

import org.example.interfaces.QueueADT;

public class OrderedQueue<T extends Comparable<T>> implements QueueADT<T>
{
    /**
     * queue que representa a queue
     */
    private QueueADT<T> queue;


    /**
     * cria uma queue
     */
    public OrderedQueue()
    {
        this.queue = new LinkedQueue<T>();
    }


    @Override
    public void enqueue(T element)
    {
        QueueADT<T> tempQueue = new LinkedQueue<T>();

        while (!isEmpty() && element.compareTo(first()) > 0) //enquanto não estiver vazia && novo elemento > primeiro elemento
        {
            tempQueue.enqueue(dequeue()); //adiciona na tempQueue o elemento removido do front da queue
        }

        tempQueue.enqueue(element); //adiciona na tempQueue o novo elemento no rear

        while (!isEmpty()) //enquanto não estiver vazia
        {
            tempQueue.enqueue(dequeue()); //adiciona na tempQueue o elemento removido do front da queue
        }

        queue = tempQueue;
    }

    @Override
    public T dequeue()
    {
        return queue.dequeue();
    }

    @Override
    public T first()
    {
        return queue.first();
    }

    @Override
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    @Override
    public int size()
    {
        return queue.size();
    }


    @Override
    public String toString() {
        return "OrderedQueue{" +
                "queue=" + queue +
                '}';
    }


    public QueueADT<T> merge(QueueADT<T> queue1, QueueADT<T> queue2)
    {
        QueueADT<T> mergeQueue = new LinkedQueue<T>();

        while (!queue1.isEmpty() && !queue2.isEmpty()) //enquanto as queues não estiverem vazias
        {
            T element1 = queue1.first();
            T element2 = queue2.first();

            if(element1.compareTo(element2) <= 0) //elemento1 <= elemento2
            {
                mergeQueue.enqueue(queue1.dequeue()); //mergeQueue adiciona no rear o elemento do front da queue1 que foi removido

            } else
            {
                mergeQueue.enqueue(queue2.dequeue()); //mergeQueue adiciona no rear o elemento do front da queue2 que foi removido
            }
        }

        while (!queue1.isEmpty()) //enquanto o queue1 nao estiver vazio
        {
            mergeQueue.enqueue(queue1.dequeue()); //mergeQueue adiciona no rear o elemento do front da queue1 que foi removido
        }

        while (!queue2.isEmpty()) //enquanto o queue2 nao estiver vazio
        {
            mergeQueue.enqueue(queue2.dequeue()); //mergeQueue adiciona no rear o elemento do front da queue2 que foi removido
        }

        return mergeQueue;
    }
}
