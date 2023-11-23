package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.OrderedListADT;

import java.util.Iterator;

public class DoubleLinkedOrderedList<T extends Comparable> extends DoublyLinkedList<T> implements OrderedListADT<T>
{
    public DoubleLinkedOrderedList()
    {
        super();
    }


    @Override
    public void add(T element)
    {
        Comparable<T> temp = (Comparable<T>) element; //elemento q vai ser adicionado (colocou-se comparavel)
        DoublyNode<T> traverse = head; //elemento da head
        DoublyNode<T> newNode = new DoublyNode<T>(element); //elemento a ser adicionado (colocou-se no node)


        if(isEmpty()) //se a double list tiver vazia
        {
            head = newNode; //head = novo elemento
            tail = newNode; //tail = novo elemento

        } else if(temp.compareTo(tail.getElement()) >= 0) //se n tiver vazia, adiciona na tail (elemento a ser adicionado for > elemento da tail)
        {
            tail.setNext(newNode); //elemento seguinte ao tail, terá o elemento adicionado

            newNode.setPrev(tail); //elemento anterior ao elemento adicionad = tail
            newNode.setNext(null); //elemento seguinte ao elemento adicionado = vazio

            tail = newNode; //tail terá o elemento adicionado

        } else if(temp.compareTo(head.getElement()) <= 0) //adiciona no head (elemento a ser adicionado for < elemento da tail)
        {
            head.setPrev(newNode); //elemento anterior ao head = elemento adicionado

            newNode.setNext(head); //elemento seguinte ao elemento adicionado = head
            newNode.setPrev(null); //elemento anterior ao elemento adicionado = vazio

            head = newNode; //head terá o elemento adicionado

        } else
        {
            while ((temp.compareTo(traverse.getElement()) > 0)) //enquanto o elemento a ser adicionado for > head
            {
                traverse = traverse.getNext(); //traverse = elemento seguinte do traverse
            }

            newNode.setNext(traverse); //o elemento seguinte ao elemento a ser adicionado = traverse
            newNode.setPrev(traverse.getPrev()); //o elemento anterior ao elemento a ser adicionado = elemento anterior ao traverse

            traverse.getPrev().setNext(newNode); //elemento seguinte do elemento anterior ao traverse = elemento a ser adicionado
            traverse.setPrev(newNode); //elemento anterior ao traverse = elemento a ser adicionado
        }

        size++;
    }

    @Override
    public T removeFirst()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("double linked ordered list removed first UNSUCCESSFULLY");
        }

        T removed = head.getElement();
        head = head.getNext();

        if (head == null)
        {
            tail = null;
        } else
        {
            head.setPrev(null);
        }

        size--;

        return removed;
    }

    @Override
    public T removeLast()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("double linked ordered list removed last UNSUCCESSFULLY");
        }

        T removed = tail.getElement();
        tail = tail.getPrev();

        if (tail == null)
        {
            head = null;
        }
        else
        {
            tail.setNext(null);
        }

        size--;
        return removed;
    }

    @Override
    public T remove(T element)
    {
        DoublyNode<T> current = head;

        while (current != null)
        {
            if (current.getElement().equals(element))
            {
                if (current == head)
                {
                    return removeFirst();

                } else if (current == tail)
                {
                    return removeLast();

                } else
                {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());

                    size--;

                    return current.getElement();
                }
            }
            current = current.getNext();
        }

        throw new EmptyCollectionException("double linked ordered list removed UNSUCCESSFULLY");
    }

    @Override
    public T first()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("double linked ordered list get first UNSUCCESSFULLY");
        }

        return head.getElement();
    }

    @Override
    public T last()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("double linked ordered list get last UNSUCCESSFULLY");
        }

        return tail.getElement();
    }

    @Override
    public boolean contains(T target)
    {
        DoublyNode<T> current = head;

        while (current != null)
        {
            if (current.getElement().equals(target))
            {
                return true;
            }

            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BasicIterator<>();
    }

    @Override
    public String toString() {
        return "DoubleLinkedOrderedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }

    private class BasicIterator<T> implements Iterator<T> {
        private DoublyNode<T> current; // Ponteiro para o nó atual

        public BasicIterator()
        {
            current = (DoublyNode<T>) head; // Começa no nó da cabeça
        }

        @Override
        public boolean hasNext()
        {
            return current != null; // Verifica se há próximo nó
        }

        @Override
        public T next() {
            if (!hasNext())
            {
                throw new EmptyCollectionException("double linked ordered list nao existe elementos"); // Lança exceção se não houver próximo elemento
            }

            T element = current.getElement(); // Obtém o elemento atual
            current = current.getNext(); // Move para o próximo nó

            return element;
        }
    }
}

