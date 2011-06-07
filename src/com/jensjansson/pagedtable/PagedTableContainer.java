package com.jensjansson.pagedtable;

import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractContainer;

public class PagedTableContainer extends AbstractContainer implements
        Container.Indexed {
    private static final long serialVersionUID = -2134233618583099046L;

    private final Container.Indexed container;

    public PagedTableContainer(Container.Indexed container) {
        this.container = container;
    }

    @Override
    public Item getItem(Object itemId) {
        return container.getItem(itemId);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return container.getContainerPropertyIds();
    }

    @Override
    public Collection<?> getItemIds() {
        return container.getItemIds();
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        return container.getContainerProperty(itemId, propertyId);
    }

    @Override
    public Class<?> getType(Object propertyId) {
        return container.getType(propertyId);
    }

    @Override
    public int size() {
        return container.size();
    }

    @Override
    public boolean containsId(Object itemId) {
        return container.containsId(itemId);
    }

    @Override
    public Item addItem(Object itemId) throws UnsupportedOperationException {
        return container.addItem(itemId);
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        return container.addItem();
    }

    @Override
    public boolean removeItem(Object itemId)
            throws UnsupportedOperationException {
        return container.removeItem(itemId);
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type,
            Object defaultValue) throws UnsupportedOperationException {
        return addContainerProperty(propertyId, type, defaultValue);
    }

    @Override
    public boolean removeContainerProperty(Object propertyId)
            throws UnsupportedOperationException {
        return removeContainerProperty(propertyId);
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        return removeAllItems();
    }

    public Object nextItemId(Object itemId) {
        return container.nextItemId(itemId);
    }

    public Object prevItemId(Object itemId) {
        return container.prevItemId(itemId);
    }

    public Object firstItemId() {
        return container.firstItemId();
    }

    public Object lastItemId() {
        return container.lastItemId();
    }

    public boolean isFirstId(Object itemId) {
        return container.isFirstId(itemId);
    }

    public boolean isLastId(Object itemId) {
        return container.isLastId(itemId);
    }

    public Object addItemAfter(Object previousItemId)
            throws UnsupportedOperationException {
        return container.addItemAfter(previousItemId);
    }

    public Item addItemAfter(Object previousItemId, Object newItemId)
            throws UnsupportedOperationException {
        return container.addItemAfter(previousItemId, newItemId);
    }

    public int indexOfId(Object itemId) {
        return container.indexOfId(itemId);
    }

    public Object getIdByIndex(int index) {
        return container.getIdByIndex(index);
    }

    public Object addItemAt(int index) throws UnsupportedOperationException {
        return container.addItemAt(index);
    }

    public Item addItemAt(int index, Object newItemId)
            throws UnsupportedOperationException {
        return container.addItemAt(index, newItemId);
    }

}
