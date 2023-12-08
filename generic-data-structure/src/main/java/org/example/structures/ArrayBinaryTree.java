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
        if(size() > 0) //se a árvore não está vazia
        {
            return (T) tree[0]; //retorna o elemento no índice 0, que representa a root da árvore
        }
        else //se a árvore estiver vazia, retorna null, pois não há root
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
        //itera sobre todos os elementos do array representando a árvore
        for(Object element : tree)
        {
            if(element != null && element.equals(targetElement)) //se o elemento não é nulo e é igual ao elemento alvo
            {
                return true; //se encontrar uma correspondência, retorna verdadeiro
            }
        }

        return false;  //se percorrer todo o array e não encontrar correspondência, retorna falso
    }

    @Override
    public T find(T targetElement) throws EmptyCollectionException
    {
        T tmp = null;
        boolean found = false;

        for(int i=0; i < count && !found; i++) //percorrer os elementos da árvore em pesquisa do elemento alvo
        {
            if(targetElement.equals(tree[i])) //se o elemento alvo for igual ao valor atual da arvore
            {
                found = true; //encontrou o elemento alvo
                tmp = tree[i]; //armazena o elemento alvo
            }
        }

        if(!found) //se nao encontrou o elemento alvo na arvore
        {
            throw new EmptyCollectionException("binary tree");
        }


        return tmp; //retorna o elemento alvo
    }

    @Override
    public Iterator<T> iteratorInOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>(); //cria uma lista temporária para armazenar os elementos durante a travessia inorder
        inorder(0, tmpList); //inicia a travessia inorder a partir do node root (índice 0)

        return tmpList.iterator(); //retorna um iterador para a lista temporária
    }


    /**
     * executa uma travessia inorder recursiva
     * @param node o node que vai ser utilizado na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void inorder(int node, ArrayUnorderedList<T> tmpList)
    {
        if(node < tree.length) //se o índice do node está dentro dos limites do array da árvore.
        {
            if(tree[node] != null) //se o node atual não é nulo
            {
                inorder(node*2+1, tmpList); //realiza a travessia inorder recursivamente na subárvore esquerda
                tmpList.addToRear(tree[node]); //adiciona o elemento do node atual à lista temporária
                inorder((node+1)*2, tmpList); //realiza a travessia inorder recursivamente na subárvore direita
            }
        }
    }


    @Override
    public Iterator<T> iteratorPreOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>(); //cria uma lista temporária para armazenar os elementos durante a travessia preorder
        preorder(0, tmpList); //inicia a travessia preorder a partir do node root (índice 0)

        return tmpList.iterator(); //retorna um iterador para a lista temporária
    }


    /**
     * executa uma travessia preorder recursiva
     * @param node o node que vai ser utilizado na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void preorder(int node, ArrayUnorderedList<T> tmpList)
    {
        if(node < tree.length) //se o índice do node está dentro dos limites do array
        {
            if(tree[node] != null) //se o node não é nulo
            {
                tmpList.addToRear(tree[node]); //adiciona o elemento do node à lista temporária
                preorder(node*2+1, tmpList); //realiza a travessia pré-ordem no filho esquerdo
                preorder((node+1)*2, tmpList); //realiza a travessia pré-ordem no filho direito
            }
        }
    }


    @Override
    public Iterator<T> iteratorPostOrder()
    {
        ArrayUnorderedList<T> tmpList = new ArrayUnorderedList<T>(); //cria uma lista temporária para armazenar os elementos durante a travessia postorder
        postorder(0, tmpList); //inicia a travessia postorder a partir do node root (índice 0)

        return tmpList.iterator(); //retorna um iterador para a lista temporária
    }


    /**
     * executa uma travessia postorder recursiva
     * @param node o node que vai ser utilizado na travessia
     * @param tmpList uma lista temporaria que vai ser usada na travessia
     */
    protected void postorder(int node, ArrayUnorderedList<T> tmpList)
    {
        if (node < tree.length) //se o índice do node está dentro dos limites do array
        {
            if (tree[node] != null) //se o node não é nulo
            {
                postorder(node*2+1, tmpList); //realiza a travessia postorder no filho esquerdo
                postorder((node+1)*2, tmpList); //realiza a travessia postorder no filho direito
                tmpList.addToRear(tree[node]); //adiciona o node atual à lista após visitar os filhos
            }
        }
    }


    @Override
    public Iterator<T> iteratorLevelOrder()
    {
        QueueADT<Integer> nodes = new LinkedQueue<>(); //cria uma fila para armazenar os índices dos nodes na ordem de nivelamento
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>(); //cria uma lista temporária para armazenar os elementos na ordem de nivelamento

        if (!isEmpty()) //se a árvore não está vazia antes de iniciar a travessia em largura
        {
            nodes.enqueue(0); //coloca o índice da root na fila
            int current;

            while (!nodes.isEmpty()) //enquanto a fila não estiver vazia, continua a travessia em largura
            {
                current = nodes.dequeue();
                results.addToRear(tree[current]); //adiciona o elemento atual à lista de resultados

                //coloca na fila o índice do filho esquerdo se existir
                if (current*2+1 < tree.length && tree[current*2+1] != null)
                {
                    nodes.enqueue(current*2+1);
                }

                //coloca na fila o índice do filho direito se existir
                if ((current+1)*2 < tree.length && tree[(current+1)*2] != null)
                {
                    nodes.enqueue((current+1)*2);
                }
            }
        }

        return results.iterator(); //retorna um iterador para a lista de resultados
    }
}
