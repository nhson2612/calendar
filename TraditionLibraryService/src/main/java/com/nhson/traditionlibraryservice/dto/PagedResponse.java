package com.nhson.traditionlibraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isFirst;
    private boolean isLast;

    public int getNextPage() {
        return hasNext ? currentPage + 1 : currentPage;
    }
    public int getPreviousPage() {
        return hasPrevious ? currentPage - 1 : 0;
    }

    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }

    public int getNumberOfElements() {
        return content != null ? content.size() : 0;
    }
}
