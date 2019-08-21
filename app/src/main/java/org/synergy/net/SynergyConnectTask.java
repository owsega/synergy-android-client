package org.synergy.net;

import android.os.AsyncTask;

import org.synergy.client.Client;

public class SynergyConnectTask extends AsyncTask<Client, Void, Void> {

    @Override
    protected Void doInBackground(Client... clients) {
        for (Client client : clients) if (client != null) client.connect();
        return null;
    }
}
/* Converted from Kotlin

package org.synergy.net

import android.os.AsyncTask
import org.synergy.client.Client

class SynergyConnectTask : AsyncTask<Client, Unit, Unit>() {
    override fun doInBackground(vararg params: Client?) {
        for (client in params) client?.connect()
    }
}
*/
