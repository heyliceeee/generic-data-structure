package org.example.structures;

public class ArraySortingAndSearching<T> {

    /**
     * procura num array de objetos especifico utilizando a pesquisa linear
     * @param array o array a ser ordenado
     * @param min valor minimo
     * @param max valor maximo
     * @param target elemento a ser procurado
     * @return true se o elemento procurado for encontrado
     * @param <T> tipo de objeto
     */
    public static <T extends Comparable<T>> boolean linearSearch(T[] array, int min, int max, T target)
    {
        int i = min;
        boolean found = false;

        while (!found && i <= max)
        {
            if(array[i].compareTo(target) == 0) //encontrou o elemento
            {
                found = true;
            }
            i++;
        }

        return found; //se n foi encontrado
    }


    /**
     * procura num array de objetos especifico utilizando a pesquisa binaria
     * @param array o array a ser ordenada
     * @param min valor minimo
     * @param max valor maximo
     * @param target elemento a ser procurado
     * @return true se o elemento procurado for encontrado
     * @param <T> tipo de objeto
     */
    public static <T extends Comparable<T>> boolean binarySearch(T[] array, int min, int max, T target)
    {
        int midpoint = (min + max) / 2; //determina o meio
        boolean found = false;

        if(array[midpoint].compareTo(target) == 0) //encontrou o elemento
        {
            found = true;
        }
        else if(array[midpoint].compareTo(target) > 0) //elemento do array > elemento procurado
        {
            if(min <= midpoint - 1) //se o valor min for <= meio - 1
            {
                found = binarySearch(array, min, midpoint - 1, target);
            }
        }
        else if(midpoint + 1 <= max) //se o meio + 1 for <= max
        {
            found = binarySearch(array, midpoint + 1, max, target);
        }

        return found;
    }
}
