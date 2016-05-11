package home.subrat.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int playerturn = 0;
    int board[] = {2,2,2,2,2,2,2,2,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        ImageView imageView = (ImageView) view;

        String tagString = (String)imageView.getTag();
        int tag = Integer.parseInt(tagString);

        if (board[tag] != 2) {
            return;
        }
        if(playerturn == 0) {
            imageView.setImageResource(R.drawable.cross);
        } else {
            imageView.setImageResource(R.drawable.circle);
        }
        board[tag] = playerturn;
        if(isGameOver(view)) {
            showPopUpAndRestart();
        }
        playerturn = 1 - playerturn;
    }

    public boolean isGameOver(View view){
        for (int i = 0;i<3;i++) {
            if (board[i] == board[i+3] && board[i+3] == board[i+6] && board[i] != 2) {
                return true;
            }
        }

        for (int i = 0;i<3;i++) {
            if (board[3*i] == board[3*i+1] && board[3*i] == board[3*i+2] && board[3*i] != 2) {
                return true;
            }
        }

        return ((board[0] == board[4] && board[0] == board[8] && board[0] != 2) || (board[2] ==
                board[4] && board[2] == board[6] &&  board[2] != 2));
    }


    public void showPopUpAndRestart(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Game won by player " + playerturn);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
