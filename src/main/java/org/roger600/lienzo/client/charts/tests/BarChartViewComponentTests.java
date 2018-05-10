package org.roger600.lienzo.client.charts.tests;

import org.roger600.lienzo.client.HasButtons;
import org.roger600.lienzo.client.MyLienzoTest;

import com.ait.lienzo.charts.client.core.axis.CategoryAxis;
import com.ait.lienzo.charts.client.core.axis.NumericAxis;
import com.ait.lienzo.charts.client.core.model.DataTable;
import com.ait.lienzo.charts.client.core.model.DataTableColumn.DataTableColumnType;
import com.ait.lienzo.charts.client.core.xy.XYChartData;
import com.ait.lienzo.charts.client.core.xy.XYChartSeries;
import com.ait.lienzo.charts.client.core.xy.bar.BarChart;
import com.ait.lienzo.charts.shared.core.types.ChartOrientation;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.shared.core.types.ColorName;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;

public class BarChartViewComponentTests implements MyLienzoTest, HasButtons
{
	private BarChart m_bc;
	private boolean m_dep = true;
	private DataTable m_table;

	@Override
	public void test(Layer layer)
	{
		m_table = buildBarChartDataTable();

		final XYChartData data = buildDataDepartment(m_table);

		m_bc = new BarChart();

		m_bc.setData(data);
		m_bc.setX(25);
		m_bc.setY(25);
	
		m_bc.setWidth(500);
		m_bc.setHeight(500);
		m_bc.setFontFamily("Verdana");
		m_bc.setFontStyle("bold");
		m_bc.setFontSize(12);
		m_bc.setCategoriesAxis(new CategoryAxis("Department"));
		m_bc.setValuesAxis(new NumericAxis("Expenses"));
		m_bc.setResizable(true);
		m_bc.setOrientation(ChartOrientation.HORIZNONAL);

		layer.add(m_bc);
		
	    m_bc.setName("DEFAULT TITLE");
		m_bc.init();

	}

	protected DataTable buildBarChartDataTable() {
		DataTable table = new DataTable();
		
		table.addColumn("department", DataTableColumnType.STRING);
		table.addColumn("automobiles", DataTableColumnType.STRING);
		table.addColumn("nyc_office_expenses", DataTableColumnType.NUMBER);
		table.addColumn("london_office_expenses", DataTableColumnType.NUMBER);

		table.addValue("department", "Engineering");
		table.addValue("department", "Services");
		table.addValue("department", "Management");
		table.addValue("department", "Sales");
		table.addValue("department", "Support");
		
		table.addValue("automobiles", "BMW");
		table.addValue("automobiles", "Mercedes");
		table.addValue("automobiles", "Jaguar");
		table.addValue("automobiles", "Audi");
		table.addValue("automobiles", "Porche");

		table.addValue("nyc_office_expenses", 16754.37);
		table.addValue("nyc_office_expenses", 26743.29);
		table.addValue("nyc_office_expenses", 32964.77);
		table.addValue("nyc_office_expenses", 48639.92);
		table.addValue("nyc_office_expenses", 58547.45);

		table.addValue("london_office_expenses", 56547.88);
		table.addValue("london_office_expenses", 41943.77);
		table.addValue("london_office_expenses", 36432.28);
		table.addValue("london_office_expenses", 26432.53);
		table.addValue("london_office_expenses", 17658.17);

		return table;
	}	
	

    protected DataTable buildAlternativeChartDataTable() {
        DataTable table = new DataTable();
        
        table.addColumn("department", DataTableColumnType.STRING);
        table.addColumn("automobiles", DataTableColumnType.STRING);
        table.addColumn("nyc_office_expenses", DataTableColumnType.NUMBER);
        table.addColumn("london_office_expenses", DataTableColumnType.NUMBER);

        table.addValue("department", "Engineering");
        table.addValue("department", "Services");
        table.addValue("department", "Management");
        table.addValue("department", "Sales");
        table.addValue("department", "Support");
        
        table.addValue("automobiles", "BMW");
        table.addValue("automobiles", "Mercedes");
        table.addValue("automobiles", "Jaguar");
        table.addValue("automobiles", "Audi");
        table.addValue("automobiles", "Porche");

        table.addValue("nyc_office_expenses", 6754.37);
        table.addValue("nyc_office_expenses", 56743.29);
        table.addValue("nyc_office_expenses", 12964.77);
        table.addValue("nyc_office_expenses", 28639.92);
        table.addValue("nyc_office_expenses", 38547.45);

        table.addValue("london_office_expenses", 56547.88);
        table.addValue("london_office_expenses", 41943.77);
        table.addValue("london_office_expenses", 36432.28);
        table.addValue("london_office_expenses", 26432.53);
        table.addValue("london_office_expenses", 17658.17);

        return table;
    }


	protected XYChartData buildDataDepartment(final DataTable table) {
		XYChartSeries seriesNYC = new XYChartSeries("NYC", ColorName.DEEPSKYBLUE, "nyc_office_expenses");

		XYChartSeries seriesLondon = new XYChartSeries("London", ColorName.DARKGREY, "london_office_expenses");

		XYChartData data = new XYChartData(table).setCategoryAxisProperty("department").addSeries(seriesNYC);

		data.addSeries(seriesLondon);

		return data;
	}

	protected XYChartData buildDataString(DataTable table) {
		XYChartSeries seriesNYC = new XYChartSeries("NYC", ColorName.DEEPSKYBLUE, "nyc_office_expenses");

		XYChartSeries seriesLondon = new XYChartSeries("London", ColorName.DARKGREY, "london_office_expenses");

		XYChartData data = new XYChartData(table).setCategoryAxisProperty("automobiles").addSeries(seriesNYC);

		data.addSeries(seriesLondon);

		return data;
	}

	@Override
	public void setButtonsPanel(Panel panel) {

		final Button add = new Button("Departments");
		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				m_dep = true;

				m_bc.setName("DEPTO");
						
				m_bc.reload(buildDataDepartment(m_table));
			}
		});
		
		panel.add(add);

		final Button mod = new Button("Automobiles");
		mod.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				m_dep = false;

				m_bc.setName("AUTOMOBILES");				
				m_bc.reload(buildDataString(m_table));

			}
		});
		
		panel.add(mod);

		final Button dir = new Button("Vertical");
		dir.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (ChartOrientation.VERTICAL == m_bc.getOrientation()) {
					dir.setText("Vertical");

					m_bc.setOrientation(ChartOrientation.HORIZNONAL);
				} else {
					dir.setText("Horizontal");

					m_bc.setOrientation(ChartOrientation.VERTICAL);
				}
				if (m_dep) {
					m_dep = false;

					m_bc.setName("Automobile Departments");

					m_bc.reload(buildDataString(m_table));
				} else {
					m_dep = true;

					m_bc.setName("Expenses Per Department");

					m_bc.reload(buildDataDepartment(m_table));
				}

			}
		});
		
		panel.add(dir);
		
		final Button increase = new Button("Change Values");
		increase.addClickHandler(new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                
                m_bc.reload(buildDataString(buildAlternativeChartDataTable()));
            }
        });
		
		panel.add(increase);

	}

}
