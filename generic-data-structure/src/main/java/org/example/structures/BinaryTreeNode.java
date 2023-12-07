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
