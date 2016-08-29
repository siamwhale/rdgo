package th.go.rd.manop.rdrun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * Created by user on 8/29/2016.
 */
public class MyAlert {
    public void myDialog(Context context,
                         int intIcon,
                         String strTitle,
                         String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context); //AlertDialog andriod.app
        builder.setCancelable(false);   // undo button disable
        builder.setIcon(intIcon);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // ctrl+space  add new and Enter
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
