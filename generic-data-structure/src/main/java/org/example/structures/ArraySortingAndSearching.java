package org.example.structures;

import java.util.Arrays;

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
    public static <T extends Comparable<? super T>> boolean linearSearch(T[] array, int min, int max, T target)
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
    public static <T extends Comparable<? super T>> boolean binarySearch(T[] array, int min, int max, T target)
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


    /**
     * ordena um array de inteiros utilizando o selection sort
     * @param array o array a ser ordenado
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] array)
    {
        int min;
        T temp;

        for(int i=0; i < array.length-1; i++)
        {
            min = i;

            for(int scan = i+1; scan < array.length; scan++)
            {
                if(array[scan].compareTo(array[min]) < 0)
                {
                    min = scan;
                }
            }

            //swap os valores
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }


    /**
     * ordena um array de inteiros utilizando o insertion sort
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] array){

        int n = array.length;

        for (int i = 1; i < n; ++i) {
            T key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }


    /**
     * ordena um array de inteiros utilizando o bubble sort
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] array){
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    // Troca os elementos
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // Se nenhum elemento foi trocado, o array está ordenado
            if (!swapped) {
                break;
            }
        }
    }


    /**
     * ordena um array de inteiros utilizando o quick sort
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void quickSort(T[] array, int low, int high){
        if (low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * ordena um array de inteiros utilizando o merge sort
     * @param array
     * @param <T>
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length > 1) {
            int mid = array.length / 2;

            T[] leftArray = Arrays.copyOfRange(array, 0, mid);
            T[] rightArray = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(array, leftArray, rightArray);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, T[] leftArray, T[] rightArray) {
        int leftIndex = 0, rightIndex = 0, mergedIndex = 0;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0) {
                array[mergedIndex++] = leftArray[leftIndex++];
            } else {
                array[mergedIndex++] = rightArray[rightIndex++];
            }
        }

        while (leftIndex < leftArray.length) {
            array[mergedIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightArray.length) {
            array[mergedIndex++] = rightArray[rightIndex++];
        }
    }
}
