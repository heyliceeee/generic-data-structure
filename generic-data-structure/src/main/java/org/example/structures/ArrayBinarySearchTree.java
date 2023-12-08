package org.example.structures;

import org.example.exceptions.EmptyCollectionException;
import org.example.interfaces.BinarySearchTreeADT;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayBinarySearchTree<T> extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T>
{
    protected int height;
    protected int maxIndex;


    /**
     * cria uma árvore de pesquisa binaria vazia
     */
    public ArrayBinarySearchTree()
    {
        super();
        height = 0;
        maxIndex = -1;
    }


    /**
     * cria uma árvore de pesquisa com o elemento especifico como root
     * @param element o elemento que ira ser o root da nova arvore
     */
    public ArrayBinarySearchTree(T element)
    {
        super(element);
        height = 1;
        maxIndex = 0;
    }



    @Override
    public void addElement(T element)
    {
        if(tree.length < maxIndex * 2 + 3)  //verifica se o array atual está prestes a ficar cheio, expandindo-o se necessário
        {
            expandCapacity();
        }

        Comparable<T> tempElement = (Comparable<T>) element;

        if(isEmpty()) //se a árvore estiver vazia, coloca o elemento como root
        {
            tree[0] = element;
            maxIndex = 0;
        }
        else
        {
            boolean added = false;
            int currentIndex = 0;

            //percorre a árvore para encontrar a posição adequada para o novo elemento
            while (!added)
            {
                if(tempElement.compareTo(tree[currentIndex]) < 0)
                {
                    //vai para a esquerda
                    if(tree[currentIndex * 2 + 1] == null)
                    {
                        tree[currentIndex * 2 + 1] = element;
                        added = true;

                        if(currentIndex * 2 + 1 > maxIndex)
                        {
                            maxIndex = currentIndex * 2 + 1;
                        }
                    }
                    else
                    {
                        currentIndex = currentIndex * 2 + 1;
                    }
                }
                else
                {
                    //vai para a direita
                    if(tree[currentIndex * 2 + 2] == null)
                    {
                        tree[currentIndex * 2 + 2] = element;
                        added = true;

                        if(currentIndex * 2 + 2 > maxIndex)
                        {
                            maxIndex = currentIndex * 2 + 2;
                        }
                    }
                    else
                    {
                        currentIndex = currentIndex * 2 + 2;
                    }
                }
            }
        }

        //atualiza a altura da árvore e incrementa o contador de elementos
        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        count++;
    }


    /**
     * expandir a capacidade do array
     */
    private void expandCapacity()
    {
        int tam = tree.length + 1; //incrementa o tamanho do array em 1 unidade.
        T[] temp = (T[]) (new Object[tam]);  //cria um novo array temporário com o novo tamanho.

        //copia os elementos do array original para o novo array.
        for(int i=0; i < count; i++)
        {
            temp[i] = tree[i];
        }

        tree = temp; //atualiza a referência do array original para o novo array expandido.
    }


    @Override
    public T removeElement(T targetElement)
    {
        T result = null;
        boolean found = false;

        if(isEmpty()) //verifica se a árvore está vazia
        {
            return result;
        }

        //procura o elemento a ser removido na árvore
        for(int i=0; (i <= maxIndex) && !found; i++)
        {
            if(tree[i] != null && targetElement.equals(tree[i]))
            {
                found = true;
                result = tree[i];

                try
                {
                    replace(i); //chama o método replace para substituir o elemento a ser removido

                } catch (EmptyCollectionException ex)
                {
                    System.out.println(ex.getMessage());
                }

                count--; //decrementa o contador de elementos na árvore
            }
        }


        //se o elemento não foi encontrado, lança uma exceção
        if(!found)
        {
            throw new EmptyCollectionException("element not found in the binary tree");
        }


        //atualiza o índice máximo e a altura da árvore
        int temp = maxIndex;
        maxIndex = -1;

        for(int i=0; i <= temp; i++)
        {
            if(tree[i] != null)
            {
                maxIndex = i;
            }
        }

        height = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;

        return result; //retorna o elemento removido
    }


    /**
     * substituir um node removido por o seu sucessor inorder, mantendo a propriedade da arvore de pesquisa binaria
     * @param targetIndex elemento a ser substituido
     */
    protected void replace(int targetIndex)
    {
        int currentIndex, oldIndex, newIndex;
        ArrayUnorderedList<Integer> oldList = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> newList = new ArrayUnorderedList<>();
        ArrayUnorderedList<Integer> tempList = new ArrayUnorderedList<>();
        Iterator<Integer> oldIt, newIt;

        if((targetIndex * 2 + 1 >= tree.length) || (targetIndex * 2 + 2 >= tree.length)) //se o node alvo nao tiver filhos
        {
            tree[targetIndex] = null;
        }

        else if((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] == null)) //se o node alvo nao tiver filhos
        {
            tree[targetIndex] = null;
        }

        else if((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) //se o node alvo tem apenas um filho esquerdo
        {
            //preenche uma nova lista com índices de nodes que irão substituir os indices correspondentes á lista antiga

            //preeencher uma nova lista
            currentIndex = targetIndex * 2 + 1;
            tempList.addToRear(currentIndex);

            while (!tempList.isEmpty())
            {
                currentIndex = tempList.removeFirst();
                newList.addToRear(currentIndex);

                if((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2))
                {
                    tempList.addToRear(currentIndex * 2 + 1);
                    tempList.addToRear(currentIndex * 2 + 2);
                }
            }

            //preencher a antiga lista
            currentIndex = targetIndex;
            tempList.addToRear(currentIndex);

            while (!tempList.isEmpty())
            {
                currentIndex = tempList.removeFirst();
                oldList.addToRear(currentIndex);

                if((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2))
                {
                    tempList.addToRear(currentIndex * 2 + 1);
                    tempList.addToRear(currentIndex * 2 + 2);
                }
            }

            //faz o replace
            oldIt = oldList.iterator();
            newIt = newList.iterator();

            while (newIt.hasNext())
            {
                oldIndex = oldIt.next();
                newIndex = newIt.next();

                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        }
        else if((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) //se o node alvo apenas conter um filho direito
        {
            //preencher a nova lista com indices dos nodes que irão ser substituidos pelo indice correspondente da lista antiga

            //preencher uma nova lista
            currentIndex = targetIndex * 2 + 2;
            tempList.addToRear(currentIndex);

            while (!tempList.isEmpty())
            {
                currentIndex = tempList.removeFirst();
                newList.addToRear(currentIndex);

                if((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2))
                {
                    tempList.addToRear(currentIndex * 2 + 1);
                    tempList.addToRear(currentIndex * 2 + 2);
                }
            }

            //preencher a antiga lista
            currentIndex = targetIndex;
            tempList.addToRear(currentIndex);

            while (!tempList.isEmpty())
            {
                currentIndex = tempList.removeFirst();
                oldList.addToRear(currentIndex);

                if((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2))
                {
                    tempList.addToRear(currentIndex * 2 + 1);
                    tempList.addToRear(currentIndex * 2 + 2);
                }
            }

            //faz o replace
            oldIt = oldList.iterator();
            newIt = newList.iterator();

            while (newIt.hasNext())
            {
                oldIndex = oldIt.next();
                newIndex = newIt.next();

                tree[oldIndex] = tree[newIndex];
                tree[newIndex] = null;
            }
        }
        else //se o node alvo tiver 2 filhos
        {
            currentIndex = targetIndex * 2 + 2;

            while (tree[currentIndex * 2 + 1] != null)
            {
                currentIndex = currentIndex * 2 + 1;
            }

            tree[targetIndex] = tree[currentIndex];

            int currentRoot = currentIndex; //o indice do root da subarvore vai ser substituido


            if (tree[currentRoot * 2 + 2] != null) //se o currentIndex tiver um filho direito
            {
                //preencher a nova lista com indices dos nodes que irão ser substituidos pelo indice correspondente da lista antiga

                //preenche a nova lista
                currentIndex = currentRoot * 2 + 2;
                tempList.addToRear(currentIndex);

                while (!tempList.isEmpty())
                {
                    currentIndex = tempList.removeFirst();
                    newList.addToRear(currentIndex);

                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2))
                    {
                        tempList.addToRear(currentIndex * 2 + 1);
                        tempList.addToRear(currentIndex * 2 + 2);
                    }
                }

                //preencher a antiga lista
                currentIndex = currentRoot;
                tempList.addToRear(currentIndex);

                while (!tempList.isEmpty())
                {
                    currentIndex = tempList.removeFirst();
                    oldList.addToRear(currentIndex);

                    if ((currentIndex * 2 + 2) <= (Math.pow(2, height) - 2))
                    {
                        tempList.addToRear(currentIndex * 2 + 1);
                        tempList.addToRear(currentIndex * 2 + 2);
                    }
                }

                //faz o replace
                oldIt = oldList.iterator();
                newIt = newList.iterator();

                while (newIt.hasNext())
                {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            }
            else
            {
                tree[currentRoot] = null;
            }
        }
    }


    @Override
    public void removeAllOcurrences(T targetElement)
    {
        while (contains(targetElement)) //verifica se a árvore ainda conter o elemento alvo
        {
            removeElement(targetElement); //se sim, remove o elemento alvo
        }
    }


    @Override
    public T removeMin()
    {
        T result = null;

        if (findMin() != null) //se existe um elemento menor
        {
            result = removeElement(findMin()); //remove o elemento menor
        }


        return result; //retorna o elemento
    }


    @Override
    public T removeMax()
    {
        T result = null;

        if (findMax() != null) //se existe um elemento maior
        {
            result = removeElement(findMax()); //remove o elemento maior
        }


        return result; //retorna o elemento
    }


    @Override
    public T findMin()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("array binary search tree empty");
        }

        int currentIndex = 0;

        //enquanto houver um filho à esquerda, continua descendo a árvore
        while (tree[currentIndex * 2 + 1] != null)
        {
            currentIndex = currentIndex * 2 + 1;
        }


        return tree[currentIndex]; //quando não houver mais filho à esquerda, chegou ao menor elemento
    }


    @Override
    public T findMax()
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("array binary search tree empty");
        }

        int currentIndex = 0;

        //enquanto houver um filho à direita, continua descendo a árvore
        while (tree[currentIndex * 2 + 2] != null)
        {
            currentIndex = currentIndex * 2 + 2;
        }


        return tree[currentIndex]; //quando não houver mais filho à direita, chegou ao maior elemento
    }


    @Override
    public String toString() {
        return "ArrayBinarySearchTree{" +
                "height=" + height +
                ", maxIndex=" + maxIndex +
                ", count=" + count +
                ", tree=" + Arrays.toString(tree) +
                '}';
    }
}
