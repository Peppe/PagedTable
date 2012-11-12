package com.jensjansson.pagedtable.example;

import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Table.ColumnGenerator;

@Theme("pagedtabletheme")
@Title("PagedTable Example")
public class PagedtableExample extends UI {

    private static final long serialVersionUID = 8672592529682639044L;

    @Override
    protected void init(final VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();
        VerticalLayout tableLayout = new VerticalLayout();
        tableLayout.setSizeUndefined();
        PagedTable table = createTable();
        tableLayout.addComponent(table);
        tableLayout.addComponent(table.createControls());
        mainLayout.addComponent(tableLayout);
        mainLayout.setComponentAlignment(tableLayout, Alignment.MIDDLE_CENTER);
        mainLayout.setMargin(true);
        addComponent(mainLayout);
    }


    public PagedTable createTable() {
        PagedTable pagedTable = new PagedTable(
                "Hello user of Vaadin! This is an example application for the PagedTable -component.");
        pagedTable.setContainerDataSource(createContainer());
        pagedTable.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
        pagedTable.setItemIconPropertyId(FLAG);
        pagedTable.setWidth("1000px");
        pagedTable.setPageLength(25);
        pagedTable.setImmediate(true);
        pagedTable.setSelectable(true);
        pagedTable.setAlwaysRecalculateColumnWidths(true);
        pagedTable.addGeneratedColumn("Generated", new ColumnGenerator() {

            private static final long serialVersionUID = -5042109683675242407L;

            public Component generateCell(Table source, Object itemId,
                    Object columnId) {
                Item item = source.getItem(itemId);
                return new Label(item.getItemProperty(NAME).getValue() + " - "
                        + item.getItemProperty(SHORT).getValue());
            }
        });
        pagedTable.setColumnHeaders(new String[] { "Country", "Country Code",
                "Path to Flag", "A generated column." });
        return pagedTable;
    }

    public static IndexedContainer createContainer() {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(NAME, String.class, null);
        container.addContainerProperty(SHORT, String.class, null);
        container.addContainerProperty(FLAG, Resource.class, null);
        for (int i = 0; i < iso3166.length; i++) {
            String name = iso3166[i++];
            String id = iso3166[i];
            Item item = container.addItem(id);
            item.getItemProperty(NAME).setValue(name);
            item.getItemProperty(SHORT).setValue(id);
            item.getItemProperty(FLAG).setValue(new ThemeResource("img/flags/" + id.toLowerCase()+ ".png"));
        }
        container.sort(new Object[] { NAME }, new boolean[] { true });
        return container;
    }

    public static final String NAME = "name";
    public static final String SHORT = "short";
    public static final String FLAG = "flag";

    private static final String[] iso3166 = new String[] { "AFGHANISTAN", "AF",
            "ÅLAND ISLANDS", "AX", "ALBANIA", "AL", "ALGERIA", "DZ",
            "AMERICAN SAMOA", "AS", "ANDORRA", "AD", "ANGOLA", "AO",
            "ANGUILLA", "AI", "ANTIGUA AND BARBUDA", "AG", "ARGENTINA", "AR",
            "ARMENIA", "AM", "ARUBA", "AW", "AUSTRALIA", "AU", "AUSTRIA", "AT",
            "AZERBAIJAN", "AZ", "BAHAMAS", "BS", "BAHRAIN", "BH", "BANGLADESH",
            "BD", "BARBADOS", "BB", "BELARUS", "BY", "BELGIUM", "BE", "BELIZE",
            "BZ", "BENIN", "BJ", "BERMUDA", "BM", "BHUTAN", "BT", "BOLIVIA",
            "BO", "BOSNIA AND HERZEGOVINA", "BA", "BOTSWANA", "BW",
            "BOUVET ISLAND", "BV", "BRAZIL", "BR",
            "BRITISH INDIAN OCEAN TERRITORY", "IO", "BRUNEI DARUSSALAM", "BN",
            "BULGARIA", "BG", "BURKINA FASO", "BF", "BURUNDI", "BI",
            "CAMBODIA", "KH", "CAMEROON", "CM", "CANADA", "CA", "CAPE VERDE",
            "CV", "CAYMAN ISLANDS", "KY", "CENTRAL AFRICAN REPUBLIC", "CF",
            "CHAD", "TD", "CHILE", "CL", "CHINA", "CN", "CHRISTMAS ISLAND",
            "CX", "COCOS (KEELING) ISLANDS", "CC", "COLOMBIA", "CO", "COMOROS",
            "KM", "CONGO", "CG", "CONGO, THE DEMOCRATIC REPUBLIC OF THE", "CD",
            "COOK ISLANDS", "CK", "COSTA RICA", "CR", "COTE D'IVOIRE", "CI",
            "CROATIA", "HR", "CUBA", "CU", "CYPRUS", "CY", "CZECH REPUBLIC",
            "CZ", "DENMARK", "DK", "DJIBOUTI", "DJ", "DOMINICA", "DM",
            "DOMINICAN REPUBLIC", "DO", "ECUADOR", "EC", "EGYPT", "EG",
            "EL SALVADOR", "SV", "EQUATORIAL GUINEA", "GQ", "ERITREA", "ER",
            "ESTONIA", "EE", "ETHIOPIA", "ET", "FALKLAND ISLANDS (MALVINAS)",
            "FK", "FAROE ISLANDS", "FO", "FIJI", "FJ", "FINLAND", "FI",
            "FRANCE", "FR", "FRENCH GUIANA", "GF", "FRENCH POLYNESIA", "PF",
            "FRENCH SOUTHERN TERRITORIES", "TF", "GABON", "GA", "GAMBIA", "GM",
            "GEORGIA", "GE", "GERMANY", "DE", "GHANA", "GH", "GIBRALTAR", "GI",
            "GREECE", "GR", "GREENLAND", "GL", "GRENADA", "GD", "GUADELOUPE",
            "GP", "GUAM", "GU", "GUATEMALA", "GT", "GUINEA", "GN",
            "GUINEA-BISSAU", "GW", "GUYANA", "GY", "HAITI", "HT",
            "HEARD ISLAND AND MCDONALD ISLANDS", "HM",
            "HOLY SEE (VATICAN CITY STATE)", "VA", "HONDURAS", "HN",
            "HONG KONG", "HK", "HUNGARY", "HU", "ICELAND", "IS", "INDIA", "IN",
            "INDONESIA", "ID", "IRAN, ISLAMIC REPUBLIC OF", "IR", "IRAQ", "IQ",
            "IRELAND", "IE", "ISRAEL", "IL", "ITALY", "IT", "JAMAICA", "JM",
            "JAPAN", "JP", "JORDAN", "JO", "KAZAKHSTAN", "KZ", "KENYA", "KE",
            "KIRIBATI", "KI", "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF", "KP",
            "KOREA, REPUBLIC OF", "KR", "KUWAIT", "KW", "KYRGYZSTAN", "KG",
            "LAO PEOPLE'S DEMOCRATIC REPUBLIC", "LA", "LATVIA", "LV",
            "LEBANON", "LB", "LESOTHO", "LS", "LIBERIA", "LR",
            "LIBYAN ARAB JAMAHIRIYA", "LY", "LIECHTENSTEIN", "LI", "LITHUANIA",
            "LT", "LUXEMBOURG", "LU", "MACAO", "MO",
            "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF", "MK", "MADAGASCAR",
            "MG", "MALAWI", "MW", "MALAYSIA", "MY", "MALDIVES", "MV", "MALI",
            "ML", "MALTA", "MT", "MARSHALL ISLANDS", "MH", "MARTINIQUE", "MQ",
            "MAURITANIA", "MR", "MAURITIUS", "MU", "MAYOTTE", "YT", "MEXICO",
            "MX", "MICRONESIA, FEDERATED STATES OF", "FM",
            "MOLDOVA, REPUBLIC OF", "MD", "MONACO", "MC", "MONGOLIA", "MN",
            "MONTENEGRO", "ME", "MONTSERRAT", "MS", "MOROCCO", "MA",
            "MOZAMBIQUE", "MZ", "MYANMAR", "MM", "NAMIBIA", "NA", "NAURU",
            "NR", "NEPAL", "NP", "NETHERLANDS", "NL", "NETHERLANDS ANTILLES",
            "AN", "NEW CALEDONIA", "NC", "NEW ZEALAND", "NZ", "NICARAGUA",
            "NI", "NIGER", "NE", "NIGERIA", "NG", "NIUE", "NU",
            "NORFOLK ISLAND", "NF", "NORTHERN MARIANA ISLANDS", "MP", "NORWAY",
            "NO", "OMAN", "OM", "PAKISTAN", "PK", "PALAU", "PW",
            "PALESTINIAN TERRITORY, OCCUPIED", "PS", "PANAMA", "PA",
            "PAPUA NEW GUINEA", "PG", "PARAGUAY", "PY", "PERU", "PE",
            "PHILIPPINES", "PH", "PITCAIRN", "PN", "POLAND", "PL", "PORTUGAL",
            "PT", "PUERTO RICO", "PR", "QATAR", "QA", "REUNION", "RE",
            "ROMANIA", "RO", "RUSSIAN FEDERATION", "RU", "RWANDA", "RW",
            "SAINT HELENA", "SH", "SAINT KITTS AND NEVIS", "KN", "SAINT LUCIA",
            "LC", "SAINT PIERRE AND MIQUELON", "PM",
            "SAINT VINCENT AND THE GRENADINES", "VC", "SAMOA", "WS",
            "SAN MARINO", "SM", "SAO TOME AND PRINCIPE", "ST", "SAUDI ARABIA",
            "SA", "SENEGAL", "SN", "SERBIA", "RS", "SEYCHELLES", "SC",
            "SIERRA LEONE", "SL", "SINGAPORE", "SG", "SLOVAKIA", "SK",
            "SLOVENIA", "SI", "SOLOMON ISLANDS", "SB", "SOMALIA", "SO",
            "SOUTH AFRICA", "ZA",
            "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS", "GS", "SPAIN",
            "ES", "SRI LANKA", "LK", "SUDAN", "SD", "SURINAME", "SR",
            "SVALBARD AND JAN MAYEN", "SJ", "SWAZILAND", "SZ", "SWEDEN", "SE",
            "SWITZERLAND", "CH", "SYRIAN ARAB REPUBLIC", "SY",
            "TAIWAN, PROVINCE OF CHINA", "TW", "TAJIKISTAN", "TJ",
            "TANZANIA, UNITED REPUBLIC OF", "TZ", "THAILAND", "TH",
            "TIMOR-LESTE", "TL", "TOGO", "TG", "TOKELAU", "TK", "TONGA", "TO",
            "TRINIDAD AND TOBAGO", "TT", "TUNISIA", "TN", "TURKEY", "TR",
            "TURKMENISTAN", "TM", "TURKS AND CAICOS ISLANDS", "TC", "TUVALU",
            "TV", "UGANDA", "UG", "UKRAINE", "UA", "UNITED ARAB EMIRATES",
            "AE", "UNITED KINGDOM", "GB", "UNITED STATES", "US",
            "UNITED STATES MINOR OUTLYING ISLANDS", "UM", "URUGUAY", "UY",
            "UZBEKISTAN", "UZ", "VANUATU", "VU", "VENEZUELA", "VE", "VIET NAM",
            "VN", "VIRGIN ISLANDS, BRITISH", "VG", "VIRGIN ISLANDS, U.S.",
            "VI", "WALLIS AND FUTUNA", "WF", "WESTERN SAHARA", "EH", "YEMEN",
            "YE", "ZAMBIA", "ZM", "ZIMBABWE", "ZW" };
}
