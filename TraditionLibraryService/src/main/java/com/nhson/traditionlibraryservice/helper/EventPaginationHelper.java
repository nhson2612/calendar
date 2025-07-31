package com.nhson.traditionlibraryservice.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

public class EventPaginationHelper {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 100;
    private static final String DEFAULT_SORT_BY = "id";
    private static final String DEFAULT_SORT_ORDER = "asc";

    public static Pageable createPageable(Integer page, Integer size, String sortBy, String sortOrder) {
        int validPage = (page == null || page < 0) ? DEFAULT_PAGE : page;
        int validSize = (size == null || size <= 0) ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);
        String sortField = validateSortBy(sortBy);
        String order = validateSortOrder(sortOrder);
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return PageRequest.of(validPage, validSize, sort);
    }

    private static String validateSortBy(String sortBy) {
        if (sortBy == null || sortBy.trim().isEmpty()) {
            return DEFAULT_SORT_BY;
        }
        switch (sortBy.toLowerCase()) {
            case "lunarday":
                return "lunarDay";
            case "lunarmonth":
                return "lunarMonth";
            case "importancelevel":
                return "importanceLevel";
            case "type":
            case "id":
            case "name":
                return sortBy.toLowerCase();
            default:
                return DEFAULT_SORT_BY;
        }
    }
    private static String validateSortOrder(String sortOrder) {
        if (sortOrder == null || sortOrder.trim().isEmpty()) {
            return DEFAULT_SORT_ORDER;
        }
        return sortOrder.toLowerCase().equals("desc") ? "desc" : "asc";
    }
}
