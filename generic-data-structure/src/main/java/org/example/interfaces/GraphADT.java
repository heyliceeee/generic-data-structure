package org.example.interfaces;

import java.util.Iterator;

public interface GraphADT<T>
{
    /**
     * adiciona um vertice ao grafo, associando o objeto com vertice
     * @param vertex o vertice a ser adicionado ao grafo
     */
    public boolean addVertex(T vertex);

    /**
     * remove uma vertice, com o valor dado, no grafo
     * @param vertex o vertice que vai ser removido no grafo
     */
    public void removeVertex(T vertex);

    /**
     * adiciona uma aresta entre 2 vertices no grafo
     * @param vertex1 o primeiro vertice
     * @param vertex2 o segundo vertice
     */
    public void addEdge(T vertex1, T vertex2);

    /**
     * remove uma aresta entre 2 vertices do grafo
     * @param vertex1 o primeiro vertice
     * @param vertex2 o segundo vertice
     */
    public void removeEdge(T vertex1, T vertex2);

    /**
     * retorna o primeiro iterador de largura começando com o vertice dado
     * @param startVertex o vertice inicial
     * @return o primeiro iterador de largura começando com o vertice dado
     */
    public Iterator<T> iteratorBFS(T startVertex);

    /**
     * retorna o primeiro iterador de profundidade começando com o vertice dado
     * @param startVertex o vertice inicial
     * @return retorna o primeiro iterador de profundidade começando com o vertice dado
     */
    public Iterator<T> iteratorDFS(T startVertex);

    /**
     * retorna o iterador que contem o caminho mais pequeno entre 2 vertices
     * @param startVertex o vertice inicial
     * @param targetVertex o vertice final
     * @return o iterador que contem o caminho mais pequeno entre 2 vertices
     */
    public Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * retorna true se o grafo for vazio, caso contrario retorna false
     * @return true se o grafo for vazio, caso contrario retorna false
     */
    public boolean isEmpty();

    /**
     * retorna true se o grafo está conectado, caso contrario retorna false
     * @return true se o grafo está conectado, caso contrario retorna false
     */
    public boolean isConnected();

    /**
     * retorna o numero de vertices do grafo
     * @return o numero de vertices do grafo
     */
    public int size();

    /**
     * retorna a matriz de adjacencia em string
     * @return a matriz de adjacencia em string
     */
    public String toString();
}
