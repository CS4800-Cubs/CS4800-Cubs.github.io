package com.compucompare.compucompare;

import com.compucompare.compucompare.computerType.Computer;

public class SearchResult implements Comparable<SearchResult> {
    private Computer result;
    private int relevance;

    public SearchResult(Computer result, int relevance){
        this.result = result;
        this.relevance = relevance;
    }

    @Override
    public int compareTo(SearchResult other){
        if(other == null)
            return 1;
        return this.relevance - other.relevance;
    }

    public Computer getResult()
    {
        return result;
    }
    
    public int getRelevance()
    {
        return relevance;
    }
}
