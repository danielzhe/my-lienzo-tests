package org.roger600.lienzo.client.toolboxNew.primitive;

import com.ait.lienzo.client.core.shape.IPrimitive;
import org.roger600.lienzo.client.toolboxNew.Item;

public abstract class AbstractPrimitiveItem<T extends Item> implements Item<T> {

    public abstract IPrimitive<?> asPrimitive();
}