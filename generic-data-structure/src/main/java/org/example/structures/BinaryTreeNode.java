package org.example.structures;

public class BinaryTreeNode<T>
{
    protected T element;
    protected BinaryTreeNode<T> left, right;


    /**
     * cria uma nova árvore com os dados específicos
     * @param obj o elemento que irá fazer parte da nova node da árvore
     */
    BinaryTreeNode(T obj)
    {
        element = obj;
        left = right = null;
    }


    /**
     * Retorna o elemento do node
     * @return o elemento do node
     */
    public T getElement()
    {
        return element;
    }

    /**
     * Define o elemento do node
     * @param element o elemento do node
     */
    public void setElement(T element)
    {
        this.element = element;
    }

    /**
     * Retorna o filho esquerdo do node
     * @return o filho esquerdo do node
     */
    public BinaryTreeNode<T> getLeft()
    {
        return left;
    }

    /**
     * Define o filho esquerdo do node
     * @param left o filho esquerdo do node
     */
    public void setLeft(BinaryTreeNode<T> left)
    {
        this.left = left;
    }

    /**
     * Retorna o filho direito do node
     * @return o filho direito do node
     */
    public BinaryTreeNode<T> getRight()
    {
        return right;
    }

    /**
     * Define o filho direito do node
     * @param right o filho direito do node
     */
    public void setRight(BinaryTreeNode<T> right)
    {
        this.right = right;
    }


    /**
     * retorna o número de filhos não nulos do node. este método pode ser escrito de forma mais eficiente.
     * @return o número de filhos não nulos do node
     */
    public int numChildren()
    {
        int children = 0;

        if(left != null)
        {
            children = 1 + left.numChildren();
        }


        if(right != null)
        {
            children = children + 1 + right.numChildren();
        }


        return children;
    }


    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "element=" + element +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
