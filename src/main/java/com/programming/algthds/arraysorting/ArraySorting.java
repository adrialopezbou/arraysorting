package com.programming.algthds.arraysorting;

import java.util.Arrays;
import java.util.Random;

public class ArraySorting {

    public static void main(String[] args) {
        int[] array = new int[10000];
        Random rand = new Random();
        for(int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100);
        }
        int[] selectionSortArray = array.clone();
        int[] insertionSortArray = array.clone();
        int[] bubbleSortArray = array.clone();
        int[] mergeSortArray = array.clone();
        int[] quickSortArray = array.clone();
        int[] heapSortArray = array.clone();

        System.out.println("Execution Times:");

        long startTime = System.nanoTime();
        selectionSort(selectionSortArray);
        long endTime = System.nanoTime();
        System.out.println("Selection sort: " + (endTime - startTime) / 1000 + " ms");

        startTime = System.nanoTime();
        insertionSort(insertionSortArray);
        endTime = System.nanoTime();
        System.out.println("Insertion sort: " + (endTime - startTime) / 1000 + " ms");

        startTime = System.nanoTime();
        bubbleSort(bubbleSortArray);
        endTime = System.nanoTime();
        System.out.println("Bubble sort: " + (endTime - startTime) / 1000 + " ms");

        startTime = System.nanoTime();
        mergeSort(mergeSortArray, 0, array.length - 1);
        endTime = System.nanoTime();
        System.out.println("Merge sort: " + (endTime - startTime) / 1000 + " ms");

        startTime = System.nanoTime();
        quickSort(quickSortArray, 0, array.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick sort: " + (endTime - startTime) / 1000 + " ms");

        startTime = System.nanoTime();
        heapSort(heapSortArray);
        endTime = System.nanoTime();
        System.out.println("Heap sort: " + (endTime - startTime) / 1000 + " ms");
    }

    public static void selectionSort(int[] array) {
        for(int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for(int j = i + 1; j < array.length; j++) {
                if(array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    public static void insertionSort(int[] array) {
        for(int i = 1; i < array.length; i++) {
            int j = i;
            while(j > 0 && array[j] < array[j - 1]) {
                swap(array, j, j - 1);
                j--;
            }
        }
    }

    public static void bubbleSort(int[] array) {
        for(int i = array.length - 1; i > 0; i--) {
            for(int j = 1; j <= i; j++) {
                if(array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    public static int[] mergeSort(int[] array, int low, int high) {
        if(low == high) {
            return new int[]{array[low]};
        }
        int mid = (high - low) / 2;
        int[] left = mergeSort(array, low, low + mid);
        int[] right = mergeSort(array, low + mid + 1, high);

        return mergeArrays(left, right);
    }

    private static int[] mergeArrays(int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] array = new int[left.length + right.length];
        while(i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while(i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while(j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }

        return array;
    }

    public static void quickSort(int[] array, int low, int high) {
        if(low >= high) {
            return;
        }

        int pivot = sortWithPivot(array, low, high);

        quickSort(array, low, pivot - 1);
        quickSort(array, pivot + 1, high);
    }

    private static int sortWithPivot(int[] array, int low, int high) {
        // Pivot is the most right element
        int leftWall = low;
        for(int i = low; i < high; i++) {
            if(array[i] < array[high]) {
                swap(array, leftWall, i);
                leftWall++;
            }
        }
        // Swapping pivot with the first element of greater elements than pivot
        swap(array, leftWall, high);
        return leftWall;
    }

    public static void heapSort(int[] array) {
        // Max Heap for ascending order
        createMaxHeap(array);
        sortArray(array);
    }

    private static void createMaxHeap(int[] array) {
        // Heapify only the non-leaf nodes in reverse order
        for(int i = (array.length/2) - 1; i >= 0; i--) {
            heapify(array, array.length, i);
        }
    }

    private static void sortArray(int[] array) {
        // Swap root with last node, and heapify deleting the last node in each iteration
        for(int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int end, int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largest = index;

        if (leftChildIndex < end && array[largest] < array[leftChildIndex]) {
            largest = leftChildIndex;
        }

        if (rightChildIndex < end && array[largest] < array[rightChildIndex]) {
            largest = rightChildIndex;
        }

        if(largest != index) {
            swap(array, index, largest);
            heapify(array, end, largest);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
