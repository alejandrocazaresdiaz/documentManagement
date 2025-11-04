package com.clara.ops.document_management;

public class Util {
	
    public static int calculateTotalPages(int totalItems, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero.");
        }

        int totalPages = totalItems / size;

        if (totalItems % size != 0) {
            totalPages += 1;
        }

        return totalPages;
    }


}
