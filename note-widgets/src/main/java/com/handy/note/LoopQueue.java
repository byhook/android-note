package com.handy.note;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public class LoopQueue<T> {

    private List<T> list;

    private T t;

    public LoopQueue(List<T> list) {
        this.list = list;
    }

    public synchronized int size() {
        return list.size();
    }

    private synchronized int getPrevIndex() {
        int index = list.indexOf(t);
        int safeIndex = (index - 1 + size()) % size();
        return safeIndex;
    }

    public synchronized T getPrevData() {
        return t = list.get(getPrevIndex());
    }

    private synchronized int getNextIndex() {
        int index = list.indexOf(t);
        int safeIndex = (index + 1) % size();
        return safeIndex;
    }

    public synchronized T getNextData() {
        return t = list.get(getNextIndex());
    }

    public synchronized int indexOf(T t) {
        return list.indexOf(t);
    }

    public synchronized T getData(int position) {
        return t = list.get(position);
    }

    /**
     * 禁止有重复的元素
     *
     * @param t
     */
    public synchronized void insertNextData(T t) {
        int currentIndex = list.indexOf(t);
        if (currentIndex >= 0) {
            //列表中已经存在这个元素了
            list.remove(currentIndex);
        }
        int nextIndex = getNextIndex();
        list.add(nextIndex, t);
    }

}
