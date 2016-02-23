# Paged Table

This is a Vaadin 7 add-on component.

![Paged Table](http://gerades.savana.cz/github/PagedTable/pagedtable-example.png)

## Maven Dependency

```xml
<dependency>
   <groupId>org.vaadin.addons</groupId>
   <artifactId>pagedtable</artifactId>
   <version>0.6.8</version>
</dependency>

<repository>
   <id>qiiip-repo</id>
   <url>http://qiiip.org/mavenRepo</url>
</repository>
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

When you create a new instance of [PagedTable](https://github.com/ondrej-kvasnovsky/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/PagedTable.java) then you should create controls layout by using [createControls()](https://github.com/ondrej-kvasnovsky/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/PagedTable.java#L57) method. [createControls()](https://github.com/ondrej-kvasnovsky/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/PagedTable.java#L57) method returns instance of [ControlLayout](https://github.com/ondrej-kvasnovsky/PagedTable/blob/master/src/main/java/com/jensjansson/pagedtable/ControlsLayout.java) class. You can do the following then.

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
![Paged Table customized](http://qiiip.org/github/PagedTable/pagedtable-example-customized.png)

## Complex Example with Lazy Loading

In case you need lazy loading from database you might need this kind of paged table implementation. This is just an example and I don't think usable in real world project. If you want lazy container without much effort try to look here [Lazy Container](https://github.com/ondrej-kvasnovsky/lazy-container).

```java
public class PagedtableExample extends UI {

    @Override
    protected void init(final VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();
        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeUndefined();

        PagedTable table = createLazyTable();
        ControlsLayout controlsLayout = table.createControls();

        tableLayout.addComponent(table);
        tableLayout.addComponent(controlsLayout);
        mainLayout.addComponent(tableLayout);
        mainLayout.setComponentAlignment(tableLayout, Alignment.MIDDLE_CENTER);
        mainLayout.setMargin(true);
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(mainLayout);
        setContent(layout);
    }

    public PagedTable createLazyTable() {
        PagedTable pagedTable = new PagedTable("PagedTable example.");
        LazyLoadedContainerExamle container = new LazyLoadedContainerExamle(UserExample.class);
        pagedTable.setContainerDataSource(container);
        pagedTable.setWidth("1000px");
        pagedTable.setPageLength(10);
        pagedTable.setImmediate(true);
        pagedTable.setSelectable(true);
        pagedTable.setAlwaysRecalculateColumnWidths(true);
        pagedTable.addGeneratedColumn("Generated", new ColumnGenerator() {

            private static final long serialVersionUID = -5042109683675242407L;

            public Component generateCell(Table source, Object itemId,
                                          Object columnId) {
                Item item = source.getItem(itemId);
                return new Label("generated column");
            }
        });
        Object[] visibleColumns = {"name" , "Generated"};
        pagedTable.setVisibleColumns(visibleColumns);
        return pagedTable;
    }
}
public class UserExample {

    private String name;

    public UserExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class LazyLoadedContainerExamle extends BeanContainer {

    private List<UserExample> dbFake = new ArrayList<UserExample>();

    {
        for (int i = 0; i < 1000; i++) {
            dbFake.add(new UserExample("Sara " + i));
            dbFake.add(new UserExample("Nicolas " + i));
            dbFake.add(new UserExample("Matthew " + i));
            dbFake.add(new UserExample("Michaela " + i));
            dbFake.add(new UserExample("Martin " + i));
            dbFake.add(new UserExample("Anna " + i));
        }
    }

    public LazyLoadedContainerExamle(Class type) {
        super(type);
    }

    @Override
    public int size() {
        // TODO: here you should get COUNT from database
        int size = dbFake.size();
        return size;
    }

    @Override
    public BeanItem getItem(Object itemId) {
        return new BeanItem((UserExample) itemId);
    }

    @Override
    public List<?> getItemIds(final int startIndex, final int numberOfItems) {
        int end = startIndex + numberOfItems;
        // TODO: here you should place fetching data from database (it should be paged SQL of course)
        System.out.println("start: " + startIndex + ", end: " + end);
        List<UserExample> res = dbFake.subList(startIndex, end);
        return res;
    }
}
```

## Grails Dependency

```
mavenRepo "http://qiiip.org/mavenRepo"
compile "org.vaadin.addons:pagedtable:0.6.8"
```
