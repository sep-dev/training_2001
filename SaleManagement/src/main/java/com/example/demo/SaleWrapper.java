package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class SaleWrapper<T> {
    public static final int MAX_PAGE_ITEM_DISPLAY = 5; //表示する最大ページ数の設定
    private Page<T> page;
    private List<PageItem> items;
    private int currentNumber; //現在表示中ページ番号


    public SaleWrapper(Page<T> page){
        this.page = page;
        items = new ArrayList<PageItem>();

        currentNumber = page.getNumber() + 1;



        int start, size; //最初に表示するページ,表示するページ数
        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY){
            start = 1;
            size = page.getTotalPages();
        }else {
            if (currentNumber <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY/2){
                start = 1;
            } else if (currentNumber >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY/2){
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1;
            } else {
                start = currentNumber - MAX_PAGE_ITEM_DISPLAY/2;
            }
            size = MAX_PAGE_ITEM_DISPLAY;
        }


        //表示するページ,表示するページと現在表示中ページが一致しているかのboolean値
        for (int i = 0; i<size; i++){
            items.add(new PageItem(start+i, (start+i)==currentNumber));
        }
    }

    public List<PageItem> getItems(){
        return items;
    }

    public int getNumber(){
        return currentNumber;
    }

    public List<T> getContent(){
        return page.getContent();
    }

    public int getSize(){
        return page.getSize();
    }

    public int getTotalPages(){
        return page.getTotalPages();
    }

    public boolean isFirstPage(){
        return page.isFirst();
    }

    public boolean isLastPage(){
        return page.isLast();
    }

    public boolean isHasPreviousPage(){
        return page.hasPrevious();
    }

    public boolean isHasNextPage(){
        return page.hasNext();
    }

    public class PageItem {
        private int number;
        private boolean current;
        public PageItem(int number, boolean current){
            this.number = number;
            this.current = current;
        }

        public int getNumber(){
            return this.number;
        }

        public boolean isCurrent(){
            return this.current;
        }
    }
}