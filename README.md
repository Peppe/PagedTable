[![Published on Vaadin  Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/pagedtable)
[![Stars on Vaadin Directory](https://img.shields.io/vaadin-directory/star/pagedtable.svg)](https://vaadin.com/directory/component/pagedtable)

# Paged Table

This is the Vaadin 8 PagedTable in compatibility mode, for migrating from Vaadin 7 to Vaadin 8 projects. When you have migrated, I suggest you switch from Table to Grid.  [GridExtensionPack Add-on](https://vaadin.com/directory#!addon/gridextensionpack-add-on) provides a Paged Container and that does similar things to Grid as this addon does to Table.

![Paged Table](https://cloud.githubusercontent.com/assets/525503/24581586/dca88ac4-1726-11e7-8bc0-2885393b0aa9.png)

## Maven Dependency

```xml
<dependency>
   <groupId>org.vaadin.addons</groupId>
   <artifactId>pagedtable</artifactId>
   <version>0.7.0</version>
</dependency>
```

## Simple Example

```java
public class PagedtableExample extends UI {

    @Override
    protected void init(final VaadinRequest request) {
        PagedTable table = new PagedTable("PagedTable Example");
        ControlsLayout controls = table.createControls();

        BeanItemContainer container = new BeanItemContainer(User.class);
        table.setContainerDataSource(container);

        layout.addComponent(table);
        layout.addComponent(controls);
        setContent(layout);
    }
}
```

## How to localize or change navigation labels

When you create a new instance of [PagedTable](https://github.com/Peppe/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/PagedTable.java) then you should create controls layout by using [createControls()](https://github.com/Peppe/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/PagedTable.java#L57) method. [createControls()](https://github.com/Peppe/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/PagedTable.java#L57) method returns instance of [ControlLayout](https://github.com/Peppe/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/ControlsLayout.java) class. You can do the following then.

```java
ControlsLayout controls = table.createControls();

controls.getItemsPerPageLabel().setValue("Items");
controls.getBtnFirst().setCaption("First");
controls.getBtnLast().setCaption("Last");
controls.getBtnNext().setCaption("Next");
controls.getBtnPrevious().setCaption("Previous");
controls.getPageLabel().setValue("Current:");
```

This is how the paged table is going to look like.
![Paged Table customized](https://cloud.githubusercontent.com/assets/525503/24581624/c4109000-1727-11e7-8220-616dfaebf1fd.png)
