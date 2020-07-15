package com.handy.note;

import java.util.List;

/**
 * @author: handy
 * @date: 2020-07-14
 * @description:
 */
public class LoopQueue<T> {

    private List<T> list;

    private T current;

    public LoopQueue(List<T> list) {
        this.list = list;
    }

    public synchronized int size() {
        return list.size();
    }

    private synchronized int getPrevIndex() {
        int index = list.indexOf(current);
        int safeIndex = (index - 1 + size()) % size();
        return safeIndex;
    }

    public synchronized T getPrevData() {
        return current = list.get(getPrevIndex());
    }

    private synchronized int getNextIndex() {
        int index = list.indexOf(current);
        int safeIndex = (index + 1) % size();
        return safeIndex;
    }

    public synchronized T getNextData() {
        return current = list.get(getNextIndex());
    }

    public synchronized int indexOf(T t) {
        return list.indexOf(t);
    }

    public synchronized T getData(int position) {
        return current = list.get(position);
    }

    public synchronized T getCurrentData() {
        if (current == null && list != null && !list.isEmpty()) {
            return current = list.get(0);
        }
        return current;
    }

    /**
     * 禁止有重复的元素
     *
     * @param target
     */
    public synchronized boolean insertNextData(T target) {
        int targetIndex = list.indexOf(target);
        int nextIndex = getNextIndex();
        if (targetIndex > 0 && targetIndex != nextIndex && !target.equals(current)) {
            //列表中已经存在这个元素了
            list.remove(targetIndex);
            list.add(getNextIndex(), target);
            return true;
        }
        return false;
    }

    public synchronized boolean appendData(List<T> data) {
        list.addAll(data);
        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
