package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.UnorderedListADT;

import java.util.Iterator;

public class DoubleLinkedUnorderedList<T extends Comparable> extends DoublyLinkedList<T> implements UnorderedListADT<T>
{
    public DoubleLinkedUnorderedList()
    {
        super();
    }

    @Override
    public T removeFirst()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("double linked unordered list removed first UNSUCCESSFULLY");
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
            throw new EmptyCollectionException("double linked unordered list removed last UNSUCCESSFULLY");
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
            throw new EmptyCollectionException("double linked unordered list get first UNSUCCESSFULLY");
        }

        return head.getElement();
    }

    @Override
    public T last()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("double linked unordered list get last UNSUCCESSFULLY");
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
    public int size()
    {
        return size;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BasicIterator<>();
    }

    @Override
    public void addToFront(T element)
    {
        DoublyNode<T> newNode = new DoublyNode<T>(element);
        newNode.setElement(element); //novo node = elemento adicionado

        newNode.setNext(head); //elemento seguinte do novo node = head
        newNode.setPrev(null); //elemento anterior do novo node = null

        if(head != null) //se tiver elementos na lista
        {
            head.setPrev(newNode); //elemento anterior ao head = novo node
        }

        head = newNode; //head = novo node
        tail = newNode; //tail = novo node

        size++;
    }

    @Override
    public void addToRear(T element)
    {
        DoublyNode<T> newNode = new DoublyNode<>(element);
        newNode.setElement(element);

        DoublyNode<T> last = head;

        newNode.setNext(null);

        if (head == null)
        {
            newNode.setPrev(null);

            head = newNode;
            tail = newNode;

            return;
        }

        while (last.getNext() != null)
        {
            last = last.getNext();
        }

        last.setNext(newNode);
        newNode.setPrev(last);

        size++;
    }

    @Override
    public void addAfter(T element, T target)
    {
        DoublyNode<T> newnode = new DoublyNode<T>(element);
        newnode.setElement(element);

        boolean found = false;

        // 1º caso - A lista está vazia
        if (isEmpty())
        {
            head = newnode;
            tail = newnode;

        } else
        {
            DoublyNode<T> current = head;
            while (current != null && !found)
            {
                if (target.equals(current.getElement()))
                {
                    found = true;

                } else
                {
                    current = current.getNext();
                }
            }

            if(current.getNext() == null)
            {
                current.setNext(newnode);
                newnode.setPrev(current);
                tail = newnode;

            } else
            {
                DoublyNode<T> next = current.getNext();

                current.setNext(newnode);
                newnode.setPrev(current);
                newnode.setNext(next);
                next.setPrev(newnode);
            }
        }
        size++;
    }


    @Override
    public String toString() {
        return "DoubleLinkedUnorderedList{" +
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
                throw new EmptyCollectionException("double linked unordered list nao existe elementos"); // Lança exceção se não houver próximo elemento
            }

            T element = current.getElement(); // Obtém o elemento atual
            current = current.getNext(); // Move para o próximo nó

            return element;
        }
    }
}

