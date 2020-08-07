package cn.com.ecenter.xzspxt.entity;

public class Page {
    /**
     * 每页显示几条记录
     */
    private int pageSize = 10;
    /**
     * 默认 当前页 为第一页 这个数是计算出来的
     */
    private int pageNow = 1;
    /**
     * 从第几条记录开始
     */
    private int pageIndex;

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNow() {
        return pageNow;
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
    public int getPageIndex() {
        return pageIndex= (pageNow - 1) * pageSize;
    }



}
