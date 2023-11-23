package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.UnorderedListADT;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ArrayUnorderedList<T> implements UnorderedListADT<T>
{
    /**
     * constante para representar a capacidade default do array
     */
    private final int DEFAULT_CAPACITY = 100;

    /**
     * array de elementos genéricos que representam a lista
     */
    private T[] list;

    /**
     * array de elementos genéricos que representam uma nova lista
     */
    private T[] newList;

    /**
     * int que representa o elemento rear da lista
     */
    private int rear;

    /**
     * int que representa o elemento front da lista
     */
    private int front;

    /**
     * int que representa o tamanho da lista
     */
    private int size;



    public ArrayUnorderedList()
    {
        this.size = this.rear = this.front = 0;
        this.list =  (T[])(new Object[DEFAULT_CAPACITY]);
    }

    public ArrayUnorderedList(int initialCapacity)
    {
        this.size = this.rear = this.front = 0;
        this.list =  (T[])(new Object[DEFAULT_CAPACITY]);
    }


    @Override
    public T removeFirst()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array unordered list empty");
        }

        T result = list[front];
        list[front] = null; //remover primeiro elemento

        for(int i=0; i < this.rear; i++)
        {
            this.list[i] = this.list[i+1];
        }

        this.rear--;
        this.size++;

        return result;
    }

    @Override
    public T removeLast()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array unordered list empty");
        }

        this.rear--;

        T result = list[rear];
        list[rear] = null; //remover ultimo elemento

        this.size++;

        return result;
    }

    @Override
    public T remove(T element)
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array unordered list empty");
        }

        if(!contains(element)) //elemento nao existir
        {
            throw new EmptyCollectionException("elemento nao existe");
        }

        int pos = 0; //posicao do elemento a ser removido

        for(int i=0; i < this.rear; i++)
        {
            if(element.equals(list[i])) //encontrou elemento
            {
                pos = i;
            }
        }

        T result = list[pos];
        list[pos] = null; //removeu elemento

        for(int i=pos; i < this.rear; i++) //arranjar o array depois de remover elemento
        {
            this.list[i] = this.list[i+1];
        }

        this.rear--;
        this.size++;

        return result;
    }

    @Override
    public T first()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array unordered list empty");
        }

        return list[front];
    }

    @Override
    public T last()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array unordered list empty");
        }

        return list[rear - 1];
    }

    @Override
    public boolean contains(T target)
    {
        boolean found = false;

        for(int i=0; i < this.rear; i++)
        {
            if(target.equals(list[i])) //elemento mencionado = elemento atual da lista (se encontrou)
            {
                found = true;
            }
        }

        return found;
    }

    @Override
    public boolean isEmpty()
    {
        return (rear == 0);
    }

    @Override
    public int size()
    {
        return this.rear;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BasicIterator<>();
    }

    @Override
    public void addToFront(T element)
    {
        T[] unorderedList = list;

        if(rear == unorderedList.length - 1) //list chegar á capacidade max.
        {
            expandCapacity();
        }

        for(int i=rear; i > front; i--) //enquanto a rear for > q front
        {
            unorderedList[i] = unorderedList[i - 1]; //vai colocando os elementos desde a posicao do ultimo elemento ao elemento front
        }

        unorderedList[front] = element; //elemento adicionado no front

        rear++;
        size++;
    }

    @Override
    public void addToRear(T element)
    {
        T[] unorderedList = list;

        if(rear == unorderedList.length - 1) //list chegar á capacidade max.
        {
            expandCapacity();
        }

        unorderedList[rear] = element; //elemento adicionado na rear

        rear++;
        size++;
    }

    @Override
    public void addAfter(T element, T target)
    {
        T[] unorderedList = list;

        if(rear == unorderedList.length - 1) //list chegar á capacidade max.
        {
            expandCapacity();
        }

        int pos=0;
        for(int i=0; i < rear; i++) //corre a lista
        {
            if(target.equals(unorderedList[i])) //se chegou ao elemento alvo
            {
                pos = i;
            }
        }

        for(int i=rear; i > pos; i--) //correr do ultimo elemento até ao elemento target
        {
            unorderedList[i] = unorderedList[i - 1]; //vai colocando os elementos desde a posicao do ultimo elemento ao elemento target
        }

        unorderedList[pos + 1] = element; //elemento a ser adicionado, vai ser adicionado ao lado do elemento target

        rear++;
        size++;
    }

    /**
     * se a list chegou ao limite de capacidade, vai se expandir
     */
    private void expandCapacity()
    {
        T[] unorderedList = list;
        int tam = unorderedList.length + 1;
        T[] temp = (T[])(new Object[tam]);

        for (int i = 0; i < rear; i++)
        {
            temp[i] = unorderedList[i];
        }

        unorderedList = temp;
    }


    @Override
    public String toString() {
        return "ArrayUnorderedList{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", list=" + Arrays.toString(list) +
                ", newList=" + Arrays.toString(newList) +
                ", rear=" + rear +
                ", front=" + front +
                ", size=" + size +
                '}';
    }

    public class BasicIterator<T> implements Iterator<T>
    {
        private final int size;

        private final T[] items;

        private int current, expectedSize;


        public BasicIterator()
        {
            this.items = (T[]) ArrayUnorderedList.this.list;
            this.size = ArrayUnorderedList.this.rear;
            this.current = 0;
            this.expectedSize = ArrayUnorderedList.this.size;
        }


        @Override
        public boolean hasNext()
        {
            if(expectedSize != size)
            {
                throw new ConcurrentModificationException("concorrência");
            }

            return (this.items[this.current] != null);
        }

        @Override
        public T next()
        {
            T temp = items[this.current];

            if(hasNext())
            {
                this.current++;
            }

            return temp;
        }
    }
}

