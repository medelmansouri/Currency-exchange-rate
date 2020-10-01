package com.chalenge.exchangerate.ui.currencydetails;

import android.content.Context;
import android.util.AttributeSet;

import com.chalenge.exchangerate.utils.DateUtils;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Map;

public class CustomGraphView extends GraphView {

    public static final int ROTATION_ANGLE = 90;
    public static final int LABEL_SPACE = 17;
    public static final int VERTICAL_WIDTH = 80;

    public CustomGraphView(Context context) {
        super(context);
        prepareGraphView();
    }
    public CustomGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        prepareGraphView();
    }
    public final void prepareGraphView() {
        getGridLabelRenderer().setHorizontalLabelsAngle(ROTATION_ANGLE);
        getGridLabelRenderer().setLabelsSpace(LABEL_SPACE);
        getGridLabelRenderer().setHighlightZeroLines(true);
        getGridLabelRenderer().setLabelVerticalWidth(VERTICAL_WIDTH);


        getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return DateUtils.fromStrMillisToAAAAMMJJ(value);
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
    }

    public final void setGraphViewData(LineGraphSeries<DataPoint> lineGraphSeries) {
        removeAllSeries();
        addSeries(lineGraphSeries);
    }

    public final void setXaxisMinAndMax(Map.Entry<Long,Long> minMax) {
        getViewport().setMinX(minMax.getKey());
        getViewport().setMaxX(minMax.getValue());
    }
}
