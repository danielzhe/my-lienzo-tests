package org.roger600.lienzo.client.charts.tests;

import org.roger600.lienzo.client.HasButtons;
import org.roger600.lienzo.client.MyLienzoTest;

import com.ait.lienzo.charts.client.ChartData;
import com.ait.lienzo.charts.client.core.Chart;
import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.PieChartData;
import com.ait.lienzo.charts.client.core.model.DataTableColumn.DataTableColumnType;
import com.ait.lienzo.charts.client.core.pie.PieChart;
import com.ait.lienzo.charts.shared.core.types.palettes.PatternFlyPalette;
import com.ait.lienzo.client.core.shape.Layer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;

public class PieChartComponentTests implements MyLienzoTest, HasButtons
{  
    private PieChart  m_pieChart;
    private DataTable m_dataTable;
    private ChartData m_chartData;
    private DataToAdd[] m_dataToAdd;
    private int m_currentDataToAddIndex;
    private Button m_addButton;
    
    public PieChartComponentTests() 
    {
        m_currentDataToAddIndex = 0;
        
        m_dataToAdd= new DataToAdd[]
        {
            new DataToAdd("Kiwi", 25),
            new DataToAdd("Strawberry", 10),
            new DataToAdd("Pineapple", 19)
        };
        
    }
    @Override
    public void setButtonsPanel(Panel panel)
    {
        m_addButton = new Button();
        
        m_addButton.setText("Add "+ m_dataToAdd[m_currentDataToAddIndex].getData()); 
        
        m_addButton.addClickHandler(new ClickHandler() 
        {
            @Override
            public void onClick(ClickEvent event) 
            {   
                DataToAdd data = m_dataToAdd[m_currentDataToAddIndex];
                
                m_pieChart.getDataHandler()
                           .add(data.getData())
                           .with(data.getValue(), "sales");
                          
                //   m_pieChart.getDataHandler().add("Pineapple").with(10.0, "sales");
                m_pieChart.applyDataChanges();
                
                configure(m_addButton);                
            }
        });
        
        panel.add(m_addButton);     
    }

    private void configure(Button addButton)
    {
        m_currentDataToAddIndex++;
        if (m_currentDataToAddIndex >= m_dataToAdd.length) 
        {
            m_currentDataToAddIndex = 0;
        }
        
        addButton.setText("Add"+ m_dataToAdd[m_currentDataToAddIndex].getData());        
    }
    
    @Override
    public void test(Layer layer) 
    {
        m_dataTable = createDataTable();
        
        ChartData data = new ChartData(m_dataTable, "product", "sales");

        m_pieChart = (PieChart)Chart.create()
                                    .pieChart()
                                    .using(data)
                                    .withColorPalette(new PatternFlyPalette());
                
        m_pieChart.setFontFamily("Verdana")
                  .setFontStyle("bold")
                  .setFontSize(12)
                  .setX(25)
                  .setY(25)
                  .setWidth(500)
                  .setHeight(500);
        
        layer.add(m_pieChart);
        
        m_pieChart.init(600);        
    }
       
    
    DataTable createDataTable()
    {
        DataTable table = new DataTable();
        table.addColumn("product", DataTableColumnType.STRING);
        table.addColumn("sales", DataTableColumnType.NUMBER);
        
        table.addValue("product", "Oranges");
        table.addValue("product", "Bananas");
//        table.addValue("product", "apples");
        
        
        table.addValue("sales", 25);
        table.addValue("sales", 25);
  //      table.addValue("sales", 25);
                 
        return table;
    }
    
    private class DataToAdd
    {
        private String m_data;
        private double m_value;
        
        public DataToAdd(String data, double value) 
        {
            setData(data);
            setValue(value);
        }

        public String getData() 
        {
            return m_data;
        }

        public void setData(String m_data) 
        {
            this.m_data = m_data;
        }

        public double getValue() 
        {
            return m_value;
        }

        public void setValue(double m_value)
        {
            this.m_value = m_value;
        } 
    }
}