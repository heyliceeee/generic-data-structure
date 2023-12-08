package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.BinarySearchTreeADT;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T>
{

    /**
     * cria uma árvore binária de pesquisa vazia
     */
    public LinkedBinarySearchTree()
    {
        super();
    }


    /**
     * cria uma árvore binária de pesquisa com o elemento especificado como root
     * @param element o elemento que irá ser a root da nova árvore binária de pesquisa
     */
    public LinkedBinarySearchTree(T element)
    {
        super(element);
    }


    @Override
    public void addElement(T element)
    {
        BinaryTreeNode<T> temp = new BinaryTreeNode<T>(element);
        Comparable<T> comparableElement = (Comparable<T>) element;


        if(isEmpty())
        {
            root = temp;
        }
        else
        {
            BinaryTreeNode<T> current = root;
            boolean added = false;

            while (!added)
            {
                if(comparableElement.compareTo(current.element) < 0)
                {
                    if(current.left == null)
                    {
                        current.left = temp;
                        added = true;
                    } else
                    {
                        current = current.left;
                    }
                } else
                {
                    if(current.right == null)
                    {
                        current.right = temp;
                        added = true;
                    } else
                    {
                        current = current.right;
                    }
                }
            }
        }

        count++;
    }


    @Override
    public T removeElement(T targetElement)
    {
        T result = null;

        if(!isEmpty())
        {
            if(targetElement.equals(root.element))
            {
                result = root.element;
                root = replacement (root);
                count--;
            }
            else
            {
                BinaryTreeNode<T> current, parent = root;
                boolean found = false;

                if(((Comparable) targetElement).compareTo(root.element) < 0)
                {
                    current = root.left;
                }
                else
                {
                    current = root.right;
                }


                while (current != null && !found)
                {
                    if(targetElement.equals(current.element))
                    {
                        found = true;
                        count--;
                        result = current.element;


                        if(current == parent.left)
                        {
                            parent.left = replacement (current);
                        }
                        else
                        {
                            parent.right = replacement (current);
                        }
                    }
                    else
                    {
                        parent = current;

                        if(((Comparable) targetElement).compareTo(current.element) < 0)
                        {
                            current = current.left;
                        }
                        else
                        {
                            current = current.right;
                        }
                    }
                }

                if(!found)
                {
                    throw new EmptyCollectionException("binary search tree");
                }
            }
        }

        return result;
    }


    /**
     * retorna uma referencia a um node que substituirá aquele especificado para remoção.
     * no caso em que o node removido possui 2 filhos, o sucessor inorder é utilizado como seu substituto.
     *
     * @param node o node a ser removido
     * @return uma referencia ao node substituto
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node)
    {
        BinaryTreeNode<T> result = null;

        if((node.left == null) && (node.right == null))
        {
            result = null;
        }
        else if((node.left != null) && (node.right == null))
        {
            result = node.left;
        }
        else if((node.left == null) && (node.right != null))
        {
            result = node.right;
        }
        else
        {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;

            while (current.left != null)
            {
                parent = current;
                current = current.left;
            }

            if(node.right == current)
            {
                current.left = node.left;
            }
            else
            {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }

            result = current;
        }

        return result;
    }


    @Override
    public void removeAllOcurrences(T targetElement)
    {
        try
        {
            while (contains(targetElement))
            {
                removeElement(targetElement);
            }

        } catch (Exception ElementNotFoundException){}
    }


    @Override
    public T removeMin()
    {
        T result = null;

        if(findMin() != null)
        {
            result = removeElement(findMin());
        }

        return result;
    }


    @Override
    public T removeMax()
    {
        T result = null;

        if(findMax() != null)
        {
            result = removeElement(findMax());
        }

        return result;
    }


    @Override
    public T findMin()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("linked binary tree");
        }

        BinaryTreeNode<T> current = root;

        //o ciclo só testa a parte esquerda porque os elementos menores que o root são inseridos na parte esquerda
        while (current.left != null)
        {
            current = current.left;
        }

        return current.element;
    }


    @Override
    public T findMax()
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("linked binary tree");
        }


        BinaryTreeNode<T> current = root;

        while(current.right != null)
        {
            current = current.right;
        }


        return current.element;
    }


    @Override
    public String toString() {
        return "LinkedBinarySearchTree{" +
                "count=" + count +
                ", root=" + root +
                '}';
    }
}
