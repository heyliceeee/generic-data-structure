package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.BinaryTreeADT;
import org.example.interfaces.QueueADT;

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{
    protected int count; //tamanho da árvore
    protected BinaryTreeNode<T> root; //node que é o root da árvore


    /**
     * cria uma árvore binária vazia
     */
    public LinkedBinaryTree()
    {
        count = 0;
        root = null;
    }


    /**
     * cria uma árvore binária com o elemento específico como root
     * @param element o elemento que irá ser como root na nova árvore binária
     */
    public LinkedBinaryTree(T element)
    {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }


    @Override
    public T getRoot()
    {
        return this.root.element;
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
        try
        {
            find(targetElement);
        }
        catch (EmptyCollectionException e)
        {
            return false;
        }


        return true;
    }

    @Override
    public T find(T targetElement) throws EmptyCollectionException
    {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if(current == null)
        {
            throw new EmptyCollectionException("binary tree");
        }


        return (current.element);
    }


    /**
     * retorna uma referência para o específico elemento se este for encontrado na árvore binária
     * @param targetElement o elemento a ser procurado na árvore
     * @param next o elemento para começar a pesquisar
     * @return uma referência para o elemento procurado
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next)
    {
        if(next == null)
        {
            return null;
        }

        if(next.element.equals(targetElement))
        {
            return next;
        }

        BinaryTreeNode<T> tmp = findAgain(targetElement, next.left);

        if(tmp == null)
        {
            tmp = findAgain(targetElement, next.right);
        }


        return tmp;
    }


    @Override
    public Iterator<T> iteratorInOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>();
        inorder(root, tmpList);

        return tmpList.iterator();
    }


    /**
     * executa uma travessia inorder recursiva
     * @param node o node que vai ser usado como root para a travessia
     * @param tmpList uma lista temporaria que vai ser usada para a travessia
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tmpList)
    {
        if(node != null)
        {
            inorder(node.left, tmpList);
            tmpList.addToRear(node.element);
            inorder(node.right, tmpList);
        }
    }


    @Override
    public Iterator<T> iteratorPreOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>();
        preorder(root, tmpList);

        return tmpList.iterator();
    }


    /**
     * executa uma travessia preorder recursiva
     * @param node o node que vai ser usado como root na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tmpList)
    {
        if(node != null)
        {
            tmpList.addToRear(node.element);
            preorder(node.left, tmpList);
            preorder(node.right, tmpList);
        }
    }


    @Override
    public Iterator<T> iteratorPostOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>();
        postorder(root, tmpList);

        return tmpList.iterator();
    }


    /**
     * executa uma travessia postorder recursiva
     * @param node o node que vai ser usado como root na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tmpList)
    {
        if(node != null)
        {
            postorder(node.left, tmpList);
            postorder(node.right, tmpList);
            tmpList.addToRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder()
    {
        QueueADT<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>();

        nodes.enqueue(root);

        BinaryTreeNode<T> current;

        while (!nodes.isEmpty())
        {
            current = nodes.dequeue();
            results.addToRear(current.element);

            if(current.left != null)
            {
                nodes.enqueue(current.left);
            }

            if(current.right != null)
            {
                nodes.enqueue(current.right);
            }
        }


        return results.iterator();
    }
}
