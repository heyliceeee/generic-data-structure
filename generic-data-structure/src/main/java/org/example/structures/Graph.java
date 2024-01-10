package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.GraphADT;
import org.example.interfaces.UnorderedListADT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Graph<T> implements GraphADT<T>
{
    protected final int DEFAULT_CAPACITY = 10;
    protected int numVertices; //numero de vertices no grafo
    protected double[][] adjMatrix; //matriz de adjacencia
    protected T[] vertices; //valores dos vertices


    /**
     * cria um grafo vazio
     */
    public Graph()
    {
        this.numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
    }


    @Override
    public boolean addVertex(T vertex)
    {
        if (vertex == null)
        {
            throw new IllegalArgumentException("vertice nulo");
        }

        if (numVertices == vertices.length)
        {
            expandCapacity();
        }

        vertices[numVertices] = vertex;

        for (int i = 0; i <= numVertices; i++)
        {
            adjMatrix[numVertices][i] = 0;
            adjMatrix[i][numVertices] = 0;
        }

        numVertices++;

        return true;
    }

    /**
     * "expanda" a matriz de adjacencia e array de capacidade de vertices para o dobro do atual tamanho
     */
    private void expandCapacity()
    {
        T[] verticesTmp = (T[]) (new Object[this.vertices.length * 2]);
        double[][] adjMatrixTmp = new double[this.vertices.length * 2][this.vertices.length * 2];

        for (int i = 0; i < this.numVertices; i++)
        {
            for (int j = 0; j < this.numVertices; j++)
            {
                adjMatrixTmp[i][j] = this.adjMatrix[i][j];
            }

            verticesTmp[i] = this.vertices[i];
        }
        this.vertices = verticesTmp;
        this.adjMatrix = adjMatrixTmp;
    }

    @Override
    public void removeVertex(T vertex)
    {
        if (vertex == null)
        {
            throw new IllegalArgumentException("vertice nulo");
        }

        for (int i = 0; i < numVertices; i++)
        {
            if (vertex.equals(vertices[i]))
            {
                removeVertex(i);
            }
        }
    }

    /**
     * remove um único vértice com o valor dado do grafo
     *
     * @param index que vai ser removido
     */
    public void removeVertex(int index)
    {
        if (indexIsValid(index))
        {
            numVertices--;

            for (int i = index; i < numVertices; i++)
            {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++)
            {
                for (int j = 0; j < numVertices; j++)
                {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++)
            {
                for (int j = 0; j < numVertices; j++)
                {
                    adjMatrix[i][j] = adjMatrix[i][j + 1];
                }
            }
        }
    }

    @Override
    public void addEdge(T vertex1, T vertex2)
    {
        this.addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * retorna o indice do vertice
     * @param vertex a ser procurado
     * @return o indice do vertice
     */
    private int getIndex(T vertex)
    {
        for (int i = 0; i < numVertices; i++)
        {
            if (vertices[i].equals(vertex))
            {
                return i;
            }
        }

        return -1;
    }

    /**
     * insere uma aresta entre 2 vertices do grafo
     * @param index1 o primeiro indice
     * @param index2 o segundo indice
     */
    public void addEdge(int index1, int index2)
    {
        if (indexIsValid(index1) && indexIsValid(index2))
        {
            adjMatrix[index1][index2] = 1;
            adjMatrix[index2][index1] = 1;
        }
        else
        {
            throw new IllegalArgumentException("Pelo menos um dos vértices não é válido");
        }
    }


    /**
     * retorna true se o indice é valido, caso contrario false
     * @param index a ser verificado
     * @return true se o indice é valido, caso contrario false
     */
    private boolean indexIsValid(int index)
    {
        return ((index < numVertices) && index >= 0);
    }

    @Override
    public void removeEdge(T vertex1, T vertex2)
    {
        this.removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public void removeEdge(int index1, int index2)
    {
        if (!indexIsValid(index1))
        {
            throw new IllegalArgumentException("O primeiro vertice nao é valido");
        }

        if (!indexIsValid(index2))
        {
            throw new IllegalArgumentException("O segundo vertice nao é valido");
        }

        if (indexIsValid(index1) && indexIsValid(index2))
        {
            adjMatrix[index1][index2] = 0;
            adjMatrix[index2][index1] = 0;
        }
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex)
    {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * retorna um iterador que executa uma travessia de pesquisa ampla comecando no indice dado
     * @param startIndex o indice para iniciar a pesquisa
     * @return um iterador que executa uma travessia de pesquisa ampla comecando no indice dado
     */
    private Iterator<T> iteratorBFS(int startIndex)
    {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex))
        {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty())
        {
            try
            {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x]);

                //encontre todos os vértices adjacentes a x que não foram visitados e coloque-os na queue
                for (int i = 0; i < numVertices; i++)
                {
                    if (adjMatrix[x][i] != 0 && !visited[i])
                    {
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            }
            catch (EmptyCollectionException ex)
            {
                System.out.println(ex.getMessage());
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex)
    {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * retorna um iterador que executa uma primeira travessia de pesquisa em profundidade começando no índice dado.
     *
     * @param startIndex o índice para iniciar o percurso de pesquisa em profundidade
     * @return iterador que executa uma primeira travessia de pesquisa em profundidade começando no índice dado.
     */
    private Iterator<T> iteratorDFS(int startIndex)
    {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex))
        {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty())
        {
            x = traversalStack.peek();
            found = false;

            //encontre um vértice adjacente a x que não foi visitado e coloque-o na queue
            for (int i = 0; (i < numVertices) && !found; i++)
            {
                if (adjMatrix[x][i] != 0 && !visited[i])
                {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty())
            {
                traversalStack.pop();
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
    {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * retorna um iterador que contém o caminho mais curto entre os dois vértices
     *
     * @param startIndex o indice do vértice inicial
     * @param targetIndex o indice do vértice final
     * @return um iterador que contém o caminho mais curto entre os dois vértices
     */
    private Iterator<T> iteratorShortestPath(int startIndex, int targetIndex)
    {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
        {
            return resultList.iterator();
        }

        Iterator<Integer> it;

        try
        {
            it = iteratorShortestPathIndices(startIndex, targetIndex);

            while (it.hasNext())
            {
                resultList.addToRear(vertices[it.next()]);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return resultList.iterator();
    }

    /**
     * retorna um iterador que contém os índices do caminho mais curto entre dois vértices
     *
     * @param startIndex o indice do vértice inicial
     * @param targetIndex o indice do vértice final
     * @return um iterador que contém os índices do caminho mais curto entre dois vértices
     * @throws EmptyCollectionException
     */
    private Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException
    {
        int index = startIndex;
        int[] pathLength = new int[numVertices];
        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex))
        {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex))
        {
            index = (traversalQueue.dequeue());

            //Atualiza o pathLength para cada vértice não visitado adjacente ao vértice no índice atual
            for (int i = 0; i < numVertices; i++)
            {
                if (adjMatrix[index][i] != 0 && !visited[i])
                {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        //nenhum caminho deve ter sido encontrado
        if (index != targetIndex)
        {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<>();
        index = targetIndex;
        stack.push(index);

        do
        {
            index = predecessor[index];
            stack.push(index);

        } while (index != startIndex);

        while (!stack.isEmpty())
        {
            resultList.addToRear(stack.pop());
        }

        return resultList.iterator();
    }

    @Override
    public boolean isEmpty()
    {
        return this.numVertices == 0;
    }

    @Override
    public boolean isConnected()
    {
        if (isEmpty())
        {
            return false;
        }

        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext())
        {
            it.next();
            count++;
        }

        return (count == numVertices);
    }

    @Override
    public int size()
    {
        return this.numVertices;
    }


    @Override
    public String toString()
    {
        if (numVertices == 0)
        {
            return "grafo vazio";
        }

        String result = "";

        result += "\n\t\tMatriz de Adjacencia\n";
        result += "\t\t-----------------------------------------\n";
        result += "\t\tindice\t";

        for (int i = 0; i < numVertices; i++)
        {
            result += " " + i;
        }

        result += "\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++)
            {
                if (adjMatrix[i][j] != 0)
                {
                    result += "1 ";

                }
                else
                {
                    result += "0 ";
                }
            }

            result += "\n";
        }

        result += "\nValores Vertice";
        result += "\n-------------\n";
        result += "indice\tvalor\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        return result;
    }
}
