package com.github.lwhite1.tablesaw.filter;

import com.github.lwhite1.tablesaw.api.ColumnType;
import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.Column;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.columns.IntColumn;
import com.github.lwhite1.tablesaw.columns.LongColumn;
import com.github.lwhite1.tablesaw.columns.ShortColumn;
import org.roaringbitmap.RoaringBitmap;

/**
 */
public class IntLessThanOrEqualTo extends ColumnFilter {

  private int value;

  public IntLessThanOrEqualTo(ColumnReference reference, int value) {
    super(reference);
    this.value = value;
  }

  public RoaringBitmap apply(Table relation) {
    String name = columnReference.getColumnName();
    Column column = relation.column(name);
    ColumnType type = column.type();
    switch (type) {
      case INTEGER:
        IntColumn intColumn = relation.intColumn(name);
        return intColumn.isLessThanOrEqualTo(value);
      case LONG_INT:
        LongColumn longColumn = relation.longColumn(name);
        return longColumn.isLessThanOrEqualTo(value);
      case SHORT_INT:
        ShortColumn shortColumn = relation.shortColumn(name);
        return shortColumn.isLessThanOrEqualTo(value);
      default:
        throw new UnsupportedOperationException("Columns of type " + type.name() + " do not support the operation "
            + "lessThanOrEqualTo(anInt) ");
    }
  }
}
