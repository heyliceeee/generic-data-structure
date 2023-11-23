package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.OrderedListADT;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayOrderedList<T> implements OrderedListADT<T>
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


    /**
     * cria um array vazia e nula
     */
    public ArrayOrderedList()
    {
        this.size = this.rear = this.front = 0;
        this.list =  (T[])(new Object[DEFAULT_CAPACITY]);
    }


    /**
     * cria um array
     */
    public ArrayOrderedList(int initialCapacity)
    {
        this.size = this.front = this.rear = 0;
        this.list = (T[]) (new Object[initialCapacity]);
    }


    @Override
    public T removeFirst()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array ordered list empty");
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
            throw new EmptyCollectionException("array ordered list empty");
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
            throw new EmptyCollectionException("array ordered list empty");
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
            throw new EmptyCollectionException("array ordered list empty");
        }

        return list[front];
    }

    @Override
    public T last()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("array ordered list empty");
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
    public String toString()
    {
        String s = "\n";
        for (int i = 0; i < this.rear; i++)
        {
            s += list[i] + "\n";
        }

        return s;
    }

    @Override
    public void add(T element)
    {
        if(!(element instanceof Comparable))
        {
            throw new EmptyCollectionException("O elemento ou classe não é comparável");
        }

        if(size() == list.length) //a lista está cheia
        {
            expandCapacity();
        }

        Comparable<T> temp = (Comparable<T>) element; //elemento a ser comparado (pra conseguir ser ordenado e assim adicionado na lista)
        int i=0;

        while (i < rear && temp.compareTo(list[i]) > 0) //ainda dentro da lista && elemento a ser adicionado > elemento atual
        {
            i++;
        }

        for(int j=this.rear; j > i; j--)
        {
            list[j] = list[j-1];
        }

        this.list[i] = element; //na posicao atual vai ficar o elemento adicionado

        rear++;
        size++;
    }


    /**
     * se a list chegou ao limite de capacidade, vai se expandir
     */
    private void expandCapacity()
    {
        newList = (T[]) (new Object[DEFAULT_CAPACITY]);

        T[] joinLists = Stream.concat(Arrays.stream(list), Arrays.stream(newList))
                .toArray(size -> (T[]) Array.newInstance(list.getClass().getComponentType(), size)); //junta as 2 lists (list atual e a newList)

        list = joinLists;
    }


    public class BasicIterator<T> implements Iterator<T>
    {
        private final int size;

        private final T[] items;

        private int current, expectedSize;


        public BasicIterator()
        {
            this.items = (T[]) ArrayOrderedList.this.list;
            this.size = ArrayOrderedList.this.rear;
            this.current = 0;
            this.expectedSize = ArrayOrderedList.this.size;
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
