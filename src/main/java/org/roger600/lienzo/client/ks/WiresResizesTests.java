package org.roger600.lienzo.client.ks;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.shape.wires.IControlHandle;
import com.ait.lienzo.client.core.shape.wires.IControlHandleList;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.client.core.shape.wires.event.*;
import com.ait.lienzo.client.core.shape.wires.layouts.impl.CardinalLayoutContainer;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.shared.core.types.ColorName;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import org.roger600.lienzo.client.HasButtons;
import org.roger600.lienzo.client.MyLienzoTest;

public class WiresResizesTests extends FlowPanel implements MyLienzoTest, HasButtons {

    private static final double SIZE = 100;

    private WiresShape wires_shape;
    private WiresManager wires_manager;
    private Text text;

    public void test( final Layer layer ) {

        wires_manager = WiresManager.get( layer );

        text = new Text( "[" + SIZE +", " + SIZE + "]" )
                .setFontFamily( "Verdana" )
                .setFontSize( 12 )
                .setStrokeWidth( 1 )
                .setStrokeColor( ColorName.WHITE );

        wires_shape = center();

    }

    private void addResizeHandlers( final WiresShape shape ) {
        shape
            .setResizable( true )
            .getGroup()
            .addNodeMouseClickHandler( new NodeMouseClickHandler() {
                @Override
                public void onNodeMouseClick( NodeMouseClickEvent event ) {
                    final IControlHandleList controlHandles = shape.loadControls( IControlHandle.ControlHandleStandardType.RESIZE );
                    if ( null != controlHandles ) {
                        if ( event.isShiftKeyDown() ) {
                            controlHandles.show();
                        } else {
                            controlHandles.hide();
                        }

                    }
                }
            } );

        shape.addWiresResizeStartHandler( new WiresResizeStartHandler() {
            @Override
            public void onShapeResizeStart( final WiresResizeStartEvent event ) {
                onShapeResize( event.getWidth(), event.getHeight() );
            }
        } );

        shape.addWiresResizeStepHandler( new WiresResizeStepHandler() {
            @Override
            public void onShapeResizeStep( final WiresResizeStepEvent event ) {
                onShapeResize( event.getWidth(), event.getHeight() );
            }
        } );

        shape.addWiresResizeEndHandler( new WiresResizeEndHandler() {
            @Override
            public void onShapeResizeEnd( final WiresResizeEndEvent event ) {
                onShapeResize( event.getWidth(), event.getHeight() );
            }
        } );
    }

    private void onShapeResize( final double width, final double height ) {
        final String t = "[" + width + ", " + height + "]";
        text.setText( t );
    }

    private WiresShape create( String color, double size, CardinalLayoutContainer.Cardinal cardinal ) {
        text.setText( "[" + SIZE +", " + SIZE + "]" );
        final MultiPath path = new MultiPath().rect(0, 0, size, size)
                .setStrokeWidth(1)
                .setStrokeColor( color )
                .setFillColor( ColorName.LIGHTGREY );
        final WiresShape wiresShape0 =
                new WiresShape( path )
                        .setDraggable(true);
        wiresShape0.setLocation(new Point2D(400 ,200));
        CardinalLayoutContainer container = (CardinalLayoutContainer)wiresShape0.getLayoutContainer();
        container.add( new Circle( size / 4 ).setFillColor( color ), cardinal );
        container.add(text, CardinalLayoutContainer.Cardinal.CENTER );

        wires_manager.register( wiresShape0 );
        wires_manager.getMagnetManager().createMagnets(wiresShape0);
        addResizeHandlers( wiresShape0 );
        return wiresShape0;
    }

    @Override
    public void setButtonsPanel( Panel panel ) {
        final Button left = new Button( "Left" );
        left.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent clickEvent ) {
                if ( null != wires_shape ) {
                    wires_manager.deregister( wires_shape );
                    wires_shape = left();
                }
            }
        } );

        panel.add( left );

        final Button right = new Button( "Right" );
        right.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent clickEvent ) {
                if ( null != wires_shape ) {
                    wires_manager.deregister( wires_shape );
                    wires_shape = right();
                }
            }
        } );

        panel.add( right );

        final Button center = new Button( "center" );
        center.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent clickEvent ) {
                if ( null != wires_shape ) {
                    wires_manager.deregister( wires_shape );
                    wires_shape = center();
                }
            }
        } );

        panel.add( center );

        final Button bottom = new Button( "Bottom" );
        bottom.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent clickEvent ) {
                if ( null != wires_shape ) {
                    wires_manager.deregister( wires_shape );
                    wires_shape = bottom();
                }
            }
        } );

        panel.add( bottom );

        final Button top = new Button( "top" );
        top.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent clickEvent ) {
                if ( null != wires_shape ) {
                    wires_manager.deregister( wires_shape );
                    wires_shape = top();
                }
            }
        } );

        panel.add( top );
    }

    private WiresShape left() {
        return create("#CC00CC", SIZE, CardinalLayoutContainer.Cardinal.WEST );
    }

    private WiresShape right() {
        return create("#0000CC", SIZE, CardinalLayoutContainer.Cardinal.EAST );
    }

    private WiresShape center() {
        return create("#CC0000", SIZE, CardinalLayoutContainer.Cardinal.CENTER );
    }

    private WiresShape top() {
        return create("#00CC00", SIZE, CardinalLayoutContainer.Cardinal.NORTH );
    }

    private WiresShape bottom() {
        return create("#CCCC00", SIZE, CardinalLayoutContainer.Cardinal.SOUTH );
    }
}
