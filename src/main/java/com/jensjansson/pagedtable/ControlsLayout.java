package com.jensjansson.pagedtable;

import com.vaadin.data.Property;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

/**
 * {@link ControlsLayout} represents buttons and combo-box for controlling the paging.
 *
 * @author Ondrej Kvasnovsky
 */
public class ControlsLayout extends HorizontalLayout {
    private static final long serialVersionUID = -4908562879088496088L;

    private ComboBox itemsPerPageSelect = new ComboBox();
    private Label itemsPerPageLabel = new Label("Items per page:");
    private Label pageLabel = new Label("Page:&nbsp;", ContentMode.HTML);
    private Button btnFirst = new Button("<<");
    private Button btnPrevious = new Button("<");
    private Button btnNext = new Button(">");
    private Button btnLast = new Button(">>");
    private TextField currentPageTextField = new TextField();

    public ControlsLayout(final PagedTable table) {

        itemsPerPageSelect.addItem("5");
        itemsPerPageSelect.addItem("10");
        itemsPerPageSelect.addItem("25");
        itemsPerPageSelect.addItem("50");
        itemsPerPageSelect.setImmediate(true);
        itemsPerPageSelect.setNullSelectionAllowed(false);
        itemsPerPageSelect.setWidth("50px");
        itemsPerPageSelect.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                table.setPageLength(Integer.valueOf(String.valueOf(event
                        .getProperty().getValue())));
            }
        });
        itemsPerPageSelect.select("10");
        currentPageTextField.setValue(String.valueOf(table.getCurrentPage()));
        currentPageTextField.setConverter(Integer.class);
        final IntegerRangeValidator validator = new IntegerRangeValidator("Wrong page number", 1, table.getTotalAmountOfPages());
        currentPageTextField.addValidator(validator);
        Label separatorLabel = new Label("&nbsp;/&nbsp;", ContentMode.HTML);
        final Label totalPagesLabel = new Label(
                String.valueOf(table.getTotalAmountOfPages()), ContentMode.HTML);
        currentPageTextField.setStyleName(Reindeer.TEXTFIELD_SMALL);
        currentPageTextField.setImmediate(true);
        currentPageTextField.addValueChangeListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = -2255853716069800092L;

            public void valueChange(
                    com.vaadin.data.Property.ValueChangeEvent event) {
                if (currentPageTextField.isValid()
                        && currentPageTextField.getValue() != null) {
                    int page = Integer.valueOf(String
                            .valueOf(currentPageTextField.getValue()));
                    table.setCurrentPage(page);
                }
            }
        }
        );
        pageLabel.setWidth(null);
        currentPageTextField.setWidth("20px");
        separatorLabel.setWidth(null);
        totalPagesLabel.setWidth(null);

        HorizontalLayout pageSize = new HorizontalLayout();
        HorizontalLayout pageManagement = new HorizontalLayout();
        Button.ClickListener listener = new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            public void buttonClick(Button.ClickEvent event) {
                table.setCurrentPage(0);
            }
        };
        btnFirst.addClickListener(listener);
        Button.ClickListener listener1 = new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            public void buttonClick(Button.ClickEvent event) {
                table.previousPage();
            }
        };
        btnPrevious.addClickListener(listener1);
        Button.ClickListener listener2 = new Button.ClickListener() {
            private static final long serialVersionUID = -1927138212640638452L;

            public void buttonClick(Button.ClickEvent event) {
                table.nextPage();
            }
        };
        btnNext.addClickListener(listener2);
        Button.ClickListener listener3 = new Button.ClickListener() {
            private static final long serialVersionUID = -355520120491283992L;

            public void buttonClick(Button.ClickEvent event) {
                table.setCurrentPage(table.getTotalAmountOfPages());
            }
        };
        btnLast.addClickListener(listener3);
        btnFirst.setStyleName(Reindeer.BUTTON_LINK);
        btnPrevious.setStyleName(Reindeer.BUTTON_LINK);
        btnNext.setStyleName(Reindeer.BUTTON_LINK);
        btnLast.setStyleName(Reindeer.BUTTON_LINK);

        itemsPerPageLabel.addStyleName("pagedtable-itemsperpagecaption");
        itemsPerPageSelect.addStyleName("pagedtable-itemsperpagecombobox");
        pageLabel.addStyleName("pagedtable-pagecaption");
        currentPageTextField.addStyleName("pagedtable-pagefield");
        separatorLabel.addStyleName("pagedtable-separator");
        totalPagesLabel.addStyleName("pagedtable-total");
        btnFirst.addStyleName("pagedtable-btnFirst");
        btnPrevious.addStyleName("pagedtable-btnPrevious");
        btnNext.addStyleName("pagedtable-btnNext");
        btnLast.addStyleName("pagedtable-btnLast");

        itemsPerPageLabel.addStyleName("pagedtable-label");
        itemsPerPageSelect.addStyleName("pagedtable-combobox");
        pageLabel.addStyleName("pagedtable-label");
        currentPageTextField.addStyleName("pagedtable-label");
        separatorLabel.addStyleName("pagedtable-label");
        totalPagesLabel.addStyleName("pagedtable-label");
        btnFirst.addStyleName("pagedtable-button");
        btnPrevious.addStyleName("pagedtable-button");
        btnNext.addStyleName("pagedtable-button");
        btnLast.addStyleName("pagedtable-button");

        pageSize.addComponent(itemsPerPageLabel);
        pageSize.addComponent(itemsPerPageSelect);
        pageSize.setComponentAlignment(itemsPerPageLabel, Alignment.MIDDLE_LEFT);
        pageSize.setComponentAlignment(itemsPerPageSelect, Alignment.MIDDLE_LEFT);
        pageSize.setSpacing(true);
        pageManagement.addComponent(btnFirst);
        pageManagement.addComponent(btnPrevious);
        pageManagement.addComponent(pageLabel);
        pageManagement.addComponent(currentPageTextField);
        pageManagement.addComponent(separatorLabel);
        pageManagement.addComponent(totalPagesLabel);
        pageManagement.addComponent(btnNext);
        pageManagement.addComponent(btnLast);
        pageManagement.setComponentAlignment(btnFirst, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(btnPrevious, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(pageLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(currentPageTextField, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(separatorLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(totalPagesLabel, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(btnNext, Alignment.MIDDLE_LEFT);
        pageManagement.setComponentAlignment(btnLast, Alignment.MIDDLE_LEFT);
        pageManagement.setWidth(null);
        pageManagement.setSpacing(true);

        addComponent(pageSize);

        addComponent(pageManagement);

        setComponentAlignment(pageManagement, Alignment.MIDDLE_CENTER);

        setWidth("100%");

        setExpandRatio(pageSize, 1);

        table.addListener(new PagedTable.PageChangeListener() {
                private static final long serialVersionUID = -5737029909567376656L;

                public void pageChanged(PagedTable.PagedTableChangeEvent event) {
                PagedTableContainer containerDataSource = (PagedTableContainer) table.getContainerDataSource();
                int startIndex = containerDataSource.getStartIndex();
                btnFirst.setEnabled(startIndex > 0);
                btnPrevious.setEnabled(startIndex > 0);
                int pageLength = table.getPageLength();
                btnNext.setEnabled(startIndex < containerDataSource.getRealSize() - pageLength);
                btnLast.setEnabled(startIndex < containerDataSource.getRealSize() - pageLength);
                int currentPage = table.getCurrentPage();
                currentPageTextField.setValue(String.valueOf(currentPage));
                int totalAmountOfPages = table.getTotalAmountOfPages();
                totalPagesLabel.setValue(String.valueOf(totalAmountOfPages));
                itemsPerPageSelect.setValue(String.valueOf(pageLength));
                validator.setMaxValue(totalAmountOfPages);
            }
        });
    }

    public ComboBox getItemsPerPageSelect() {
        return itemsPerPageSelect;
    }

    public Label getItemsPerPageLabel() {
        return itemsPerPageLabel;
    }

    public Label getPageLabel() {
        return pageLabel;
    }

    public Button getBtnFirst() {
        return btnFirst;
    }

    public Button getBtnPrevious() {
        return btnPrevious;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public Button getBtnLast() {
        return btnLast;
    }

    public TextField getCurrentPageTextField() {
        return currentPageTextField;
    }
}
