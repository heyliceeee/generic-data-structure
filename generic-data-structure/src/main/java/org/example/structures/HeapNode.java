package org.example.structures;

public class HeapNode<T> extends BinaryTreeNode<T>
{
    protected HeapNode<T> parent;


    /**
     * cria um novo heap node com o dado especificado
     * @param obj o dado a ser contido no novo heap node
     */
    public HeapNode(T obj)
    {
        super(obj);
        this.parent = null;
    }

    /**
     * Retorna o valor de pai
     * @return o valor de pai
     */
    public HeapNode<T> getParent()
    {
        return parent;
    }

    /**
     * Define o valor de pai
     * @param parent
     */
    public void setParent(HeapNode<T> parent)
    {
        this.parent = parent;
    }
}
