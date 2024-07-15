package com.example.demo_lockscreen;

import android.graphics.Paint;
import android.graphics.PathMeasure;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomViewData {
    private List<PathData> paths;
    private List<PaintData> paints;

    public CustomViewData() {
        this.paths = new ArrayList<>();
        this.paints = new ArrayList<>();
    }

    public void addPath(PathData pathData) {
        this.paths.add(pathData);
    }

    public void addPaint(PaintData paintData) {
        this.paints.add(paintData);
    }

    public List<PathData> getPaths() {
        return paths;
    }

    public List<PaintData> getPaints() {
        return paints;
    }
}

class PathData {
    private float[] pathPoints;

    public PathData(Path path) {
        List<Float> points = new ArrayList<>();
        PathMeasure pm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            pm = new PathMeasure((android.graphics.Path) path, false);
        }
        float[] coords = new float[2];
        while (pm.nextContour()) {
            for (float f = 0; f < pm.getLength(); f += 1) {
                pm.getPosTan(f, coords, null);
                points.add(coords[0]);
                points.add(coords[1]);
            }
        }
        pathPoints = new float[points.size()];
        for (int i = 0; i < points.size(); i++) {
            pathPoints[i] = points.get(i);
        }
    }

    public float[] getPathPoints() {
        return pathPoints;
    }
}

class PaintData {
    private int color;
    private float strokeWidth;
    private Paint.Style style;
    private Paint.Cap strokeCap;
    private Paint.Join strokeJoin;

    public PaintData(Paint paint) {
        this.color = paint.getColor();
        this.strokeWidth = paint.getStrokeWidth();
        this.style = paint.getStyle();
        this.strokeCap = paint.getStrokeCap();
        this.strokeJoin = paint.getStrokeJoin();
    }

    public int getColor() {
        return color;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public Paint.Style getStyle() {
        return style;
    }

    public Paint.Cap getStrokeCap() {
        return strokeCap;
    }

    public Paint.Join getStrokeJoin() {
        return strokeJoin;
    }
}
