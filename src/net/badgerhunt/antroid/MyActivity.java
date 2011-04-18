package net.badgerhunt.antroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MyActivity extends Activity {
/*
    */
    /**
     * Called when the activity is first created.
     *//*

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView myListView = (ListView) findViewById(R.id.myListView);
        final EditText myEditText = (EditText) findViewById(R.id.myEditText);

        final List<String> todos = new ArrayList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todos);
        myListView.setAdapter(aa);

        myEditText.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View view, int code, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (code == KeyEvent.KEYCODE_DPAD_CENTER) {
                        todos.add(0, myEditText.getText().toString());
                        aa.notifyDataSetChanged();
                        myEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }
*/

    private int[] colors = {
            //Color.rgb(0, 39, 54),
            Color.rgb(15, 99, 110),
            Color.rgb(46, 166, 154),
            Color.rgb(108, 217, 176),
            Color.rgb(241, 107, 106)
    };

    private Cog[] cogs = new Cog[8];
    private Cog selected = null;
    private int offsetX, offsetY;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TeethView(this));
        for (int i = 0; i < this.cogs.length; i++) {
            this.cogs[i] = new Cog(100 + i * 20, 100 + i * 100, i * 5 + 35, i * 4);
        }
    }

    class TeethView extends View {
        public TeethView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            int state = e.getAction();
            int eventX = (int) e.getX();
            int eventY = (int) e.getY();
            if (state == MotionEvent.ACTION_DOWN) {
                selected = null;
                for (Cog c : cogs) {
                    int diffX = c.x - eventX;
                    int diffY = c.y - eventY;
                    float dist = diffX * diffX + diffY * diffY;
                    if (dist < c.radius * c.radius) {
                        selected = c;
                        offsetX = diffX;
                        offsetY = diffY;
                        break;
                    }
                }
            } else if (state == MotionEvent.ACTION_UP) {
                selected = null;
            } else if (state == MotionEvent.ACTION_MOVE) {
                if (selected != null) {
                    int destX = eventX + offsetX;
                    int destY = eventY + offsetY;
                    // Collision detection?
                    selected.x = destX;
                    selected.y = destY;
                }
            }
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas c) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(3);
            for (int i = 0; i < cogs.length; i++) {
                c.save(); // Save current matrix into stack
                paint.setColor(colors[i % colors.length]);
                Cog f = cogs[i];
                c.translate(f.x, f.y);
                c.rotate(f.rotation);
                if (f == selected) {
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                } else {
                    paint.setStyle(Paint.Style.STROKE);
                }
                c.drawPath(f, paint);
                c.restore();
            }
        }
    }

}


