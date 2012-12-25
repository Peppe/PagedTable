This is a Vaadin 7 add-on component.

Maven Dependency

```xml
<dependency>
   <groupId>org.vaadin.addons</groupId>
   <artifactId>pagedtable</artifactId>
   <version>0.6.7</version>
</dependency>

<repository>
   <id>qiiip-repo</id>
   <url>http://qiiip.org/mavenRepo</url>
</repository>
```

Grails Dependency

```
mavenRepo "http://qiiip.org/mavenRepo"
compile "org.vaadin.addons:pagedtable:0.6.7"
```

Example (in Java)

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
        // kind of logging... should be replaced (but the example should go to separate project first)
        System.out.println("start: " + startIndex + ", end: " + end);
        List<UserExample> res = dbFake.subList(startIndex, end);
        return res;
    }
}
```

Example (in Groovy)

```groovy
class AgreementsTable extends PagedTable {

    BeanItemContainer container

    AgreementsTable() {
        super("Agreements")
        setWidth("100%")

        setSelectable(false)
        setImmediate(true)
        setAlwaysRecalculateColumnWidths(true)
        setPageLength(10)
        setBuffered(false)
        setSortEnabled(true)
        setColumnReorderingAllowed(false)
        setColumnCollapsingAllowed(false)

        container = new BeanItemContainer(Agreement.class)
        container.setBeanIdProperty("id");

        setContainerDataSource(container)

        Object[] visibleColumnIds = ["id", "label"]
        setVisibleColumns(visibleColumnIds)

        String[] columnHeaders = new String[visibleColumnIds.length]
        Integer[] columnWidths = [20, 200]

        for (int i = 0; i < visibleColumnIds.length; i++) {
            String columnId = visibleColumnIds[i]
            columnHeaders[i] = I18N.i18n(columnId)
            Integer width = columnWidths[i]
            setColumnWidth(columnId, width)
        }
        setColumnHeaders(columnHeaders)
    }
}
```
