package org.roger600.lienzo.client.charts.tests;

import org.roger600.lienzo.client.HasButtons;
import org.roger600.lienzo.client.MyLienzoTest;

import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.PieChartData;
import com.ait.lienzo.charts.client.core.model.DataTableColumn.DataTableColumnType;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.client.core.shape.Layer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;

public class PieChartComponentTests implements MyLienzoTest, HasButtons
{  
    private PieChart  m_pieChart;
    private DataTable m_dataTable;
    
    @Override
    public void setButtonsPanel(Panel panel)
    {
        final Button add = new Button("Add");
        add.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                m_dataTable.addValue("product", "kiwi");
                m_dataTable.addValue("sales", 25);
                PieChartData pieChartData = getPieChartData(); 
                m_pieChart.reload(pieChartData);
            }
        });
        
        panel.add(add);     
    }

    @Override
    public void test(Layer layer) 
    {
        m_dataTable = createDataTable();
        
        PieChartData pieChartData = getPieChartData();
        
        m_pieChart = new PieChart();
        m_pieChart.setData(pieChartData);
                
        m_pieChart.setFontFamily("Verdana");
        m_pieChart.setFontStyle("bold");
        m_pieChart.setFontSize(12);
        m_pieChart.setX(25);
        m_pieChart.setY(25);
    
        m_pieChart.setWidth(500);
        m_pieChart.setHeight(500);
        
        layer.add(m_pieChart);
        
        m_pieChart.init(600);        
    }
    
    PieChartData getPieChartData()
    {
        return new PieChartData(m_dataTable, "product", "sales");
    }
    
    DataTable createDataTable()
    {
        DataTable table = new DataTable();
        table.addColumn("product", DataTableColumnType.STRING);
        table.addColumn("sales", DataTableColumnType.NUMBER);
        
        table.addValue("product", "oranges");
        table.addValue("product", "bananas");
        table.addValue("product", "apples");
        
        // - Too verbose. Should be able to load table from objects. Something like DataTable table = DataTable.from(list);
        // - I hated the colors picked. Must be "modern" colors not Win 3.11 colors. :D
        
        table.addValue("sales", 25);
        table.addValue("sales", 25);
        table.addValue("sales", 25);
                 
        return table;
    }
}