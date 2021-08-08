package com.lant.www.info;

public class PageInfo {
    //总记录数
    private int rowCount;

    //总页数
    private int pageCount;

    //每页展示的记录数
    private int pageSize = 3;//默认展示5条记录

    //当前是第几页
    private int currentPage = 1;//默认当前是第一页

    //上一页
    private int prevPage = 1;

    //下一页
    private int nextPage = 2;

    //记录的起始数
    private int startRow = 0;

    //是否是第一页
    private boolean isFirst = true;

    //是否是最后一页
    private boolean isLast = false;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    //设置当前页
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;

        //上一页和下一页发生了改变
        this.prevPage = currentPage - 1;
        this.nextPage = currentPage + 1;

        //如果是第一页,上一页就是第一页
        if(currentPage == 1){
            this.prevPage = 1;
            this.isFirst = true; //如果是1就代表是第一页
        }else{
            this.isFirst = false;
        }

        //如果是最后一页,下一页就是最后一页
        if(currentPage == pageCount){
            this.nextPage = currentPage;
            this.isLast = true;
        }else{
            this.isLast = false;
        }

    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "rowCount=" + rowCount +
                ", pageCount=" + pageCount +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                ", startRow=" + startRow +
                ", isFirst=" + isFirst +
                ", isLast=" + isLast +
                '}';
    }
}
