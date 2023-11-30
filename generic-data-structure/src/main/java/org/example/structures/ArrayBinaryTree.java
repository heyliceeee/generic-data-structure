package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.BinaryTreeADT;
import org.example.interfaces.QueueADT;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T>
{
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;


    /**
     * cria uma árvore binaria vazia
     */
    public ArrayBinaryTree()
    {
        count = 0;

        tree = (T[]) new Object[CAPACITY];
    }


    /**
     * cria uma árvore binária com o elemento especifico como root
     * @param element o elemento que vai ser o root
     */
    public ArrayBinaryTree(T element)
    {
        count = 1;

        tree = (T[]) new Object[CAPACITY];
        tree[0] = element;
    }


    @Override
    public T getRoot()
    {
        if(size() > 0)
        {
            return (T) tree[0];

        } else
        {
          return null;
        }
    }

    @Override
    public boolean isEmpty()
    {
        return (this.count == 0);
    }

    @Override
    public int size()
    {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement)
    {
        for(Object element : tree)
        {
            if(element != null && element.equals(targetElement))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public T find(T targetElement) throws EmptyCollectionException
    {
        T tmp = null;
        boolean found = false;

        for(int i=0; i < count && !found; i++)
        {
            if(targetElement.equals(tree[i]))
            {
                found = true;
                tmp = tree[i];
            }
        }

        if(!found)
        {
            throw new EmptyCollectionException("binary tree");
        }


        return tmp;
    }

    @Override
    public Iterator<T> iteratorInOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>();
        inorder(0, tmpList);

        return tmpList.iterator();
    }


    /**
     * executa uma travessia inorder recursiva
     * @param node o node que vai ser utilizado na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void inorder(int node, ArrayUnorderedList<T> tmpList)
    {
        if(node < tree.length)
        {
            if(tree[node] != null)
            {
                inorder(node*2+1, tmpList);
                tmpList.addToRear(tree[node]);
                inorder((node+1)*2, tmpList);
            }
        }
    }


    @Override
    public Iterator<T> iteratorPreOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>();
        preorder(0, tmpList);

        return tmpList.iterator();
    }


    /**
     * executa uma travessia preorder recursiva
     * @param node o node que vai ser utilizado na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void preorder(int node, ArrayUnorderedList<T> tmpList)
    {
        if(node < tree.length)
        {
            if(tree[node] != null)
            {
                tmpList.addToRear(tree[node]);
                preorder(node*2+1, tmpList);
                preorder((node+1)*2, tmpList);
            }
        }
    }


    @Override
    public Iterator<T> iteratorPostOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>();
        postorder(0, tmpList);

        return tmpList.iterator();
    }


    /**
     * executa uma travessia postorder recursiva
     * @param node o node que vai ser utilizado na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void postorder(int node, ArrayUnorderedList<T> tmpList)
    {
        if (node < tree.length)
        {
            if (tree[node] != null)
            {
                postorder(node * 2 + 1, tmpList);
                postorder((node + 1) * 2, tmpList);
                tmpList.addToRear(tree[node]);
            }
        }
    }


    @Override
    public Iterator<T> iteratorLevelOrder()
    {
        QueueADT<Integer> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();

        if (!isEmpty())
        {
            nodes.enqueue(0); // Enqueue the root index
            int current;

            while (!nodes.isEmpty())
            {
                current = nodes.dequeue();
                results.addToRear(tree[current]); // Add the current element to the result list

                // Enqueue the left child index if it exists
                if (current*2+1 < tree.length && tree[current*2+1] != null)
                {
                    nodes.enqueue(current*2+1);
                }

                // Enqueue the right child index if it exists
                if ((current+1)*2 < tree.length && tree[(current+1)*2] != null)
                {
                    nodes.enqueue((current+1)*2);
                }
            }
        }

        return results.iterator();
    }
}
