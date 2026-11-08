package com.fraus.hotel.util;

public class Page {

    public static int startPage(int totalPages, int currentPage) {
        int startPage = 1;

        if (totalPages > 5) {
            startPage = Math.max(1, currentPage - 2);

           if (currentPage >= totalPages -2) {
                startPage = totalPages - 4;
            }
        }
        return startPage;
    }

    public static int endPage(int totalPages, int currentPage) {
        int endPage = totalPages;

        if (totalPages > 5) {
            endPage = Math.min(totalPages, currentPage + 2);

            if (currentPage <= 3) {
                endPage = 5;
            }
        }
        return endPage;
    }
}
